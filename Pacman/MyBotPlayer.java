import javafx.scene.shape.*;
import javafx.scene.paint.*;
import java.util.*;
//Класс игрока(мяча)
public class MyBotPlayer implements BotPlayer{
	private Circle ball;
	public Map map;
	private Position position;
	private int[][] arr;
	private int unit;
	private Position target;

	//Создаем мяч
	MyBotPlayer(Map map){
		this.map=map;
		arr=map.getMap();
		unit=map.getUnit();
		position = map.getStartPosition();
		ball = new Circle(position.getX()*unit+unit/2.0,position.getY()*unit+unit/2.0, unit/2.0);
		ball.setFill(Color.RED);
		map.getChildren().add(ball);
	}
	public void eat(Food food){
		new Thread(()->{
			try{
				while(!position.equals(food.getPosition())){
					while(position.getX()!=food.getPosition().getX()){
						if (position.getX()<food.getPosition().getX()){
							moveRight();
						}
						else if(position.getX()>food.getPosition().getX()){
							moveLeft();
						}
						Thread.sleep(150);
					}
					while(position.getY()!=food.getPosition().getY()){
						if (position.getY()<food.getPosition().getY()){
							moveDown();
						}
						else if(position.getY()>food.getPosition().getY()){
							moveUp();
						}
						Thread.sleep(150);
					}
					Thread.sleep(150);
				}
			}
			catch(Exception exc){}
		}).start();
		
	}
	public void find(Food food){
			new Thread(()->{
				try{
					int [] dx = {1,0,-1,0};
					int [] dy = {0,-1,0,1};
					Vector <Position> points = new Vector<>();
					Position temp;
					int x,y;
					boolean[][] visit;
					ArrayList<Integer> actions ;
					while(!position.equals(food.getPosition())){
						points = new Vector<>();
						visit = new boolean[map.getSize()][map.getSize()];
						for (int i = 0;i<map.getSize();i++){
							for (int j = 0;j<map.getSize();j++){
								visit[j][i] = false;
							}
						}
						for(int i = 0;i<map.getSize();i++){
							for(int j = 0;j<map.getSize();j++){
								if (map.getMap()[j][i]==1)
								visit[j][i] = true;
							}
						}
						position.setDis(0);
						position.setMove(new ArrayList<>());
						points.add(position);
						visit[position.getX()][position.getY()]=true;
						while(!points.isEmpty()){
							temp=points.firstElement();
							points.remove(0);
							if(temp.equals(food.getPosition())){
								System.out.println("Size: "+temp.move.size());
								System.out.println("Dis: "+temp.dis);
								for (int i =0;i<temp.move.size();i++){
									switch(temp.move.get(i)){
										case 0:moveRight();System.out.println("right");break;
										case 1:moveUp();System.out.println("up");break;
										case 2:moveLeft();System.out.println("left");break;
										case 3:moveDown();System.out.println("down");break;
									}
									Thread.sleep(150);
								}
								Thread.sleep(150);
								break;
							}
							for(int i =0;i<4;i++){
								x=temp.getX()+dx[i];
								y=temp.getY()+dy[i];
								if((x >= 0 && x <= arr.length-1 && y >= 0 && y <= arr.length-1)&&!visit[x][y]){
									visit[x][y]=true;
									actions = new ArrayList<>();
									for(int j = 0;j<temp.move.size();j++){
										actions.add(temp.move.get(j));
									}
									actions.add(i);
									points.add(new Position(x,y,temp.dis+1,actions));
								}
							}
						}
					}
				}
				catch(Exception exc){}
			}).start();
	}
	
	//Движение мяча направо
	public void moveRight(){
		if ((position.getX()<map.getSize()-1)&&arr[position.getX()+1][position.getY()]!=1){
			position.setX(position.getX()+1);
			ball.setCenterX(position.getX()*unit+unit/2.0);
		}
		else{
			System.out.println("Invalid position!");
		}
	}
	//Движение мяча налево
	public void moveLeft(){
		if ((position.getX()>0)&&arr[position.getX()-1][position.getY()]!=1){
			position.setX(position.getX()-1);
			ball.setCenterX(position.getX()*unit+unit/2.0);
		}
	}
	//Движение мяча вверх
	public void moveUp(){
		if ((position.getY()>0)&&arr[position.getX()][position.getY()-1]!=1){
			position.setY(position.getY()-1);
			ball.setCenterY(position.getY()*unit+unit/2.0);
		}
	}
	//Движение мяча вниз
	public void moveDown(){
		if ((position.getY()<map.getSize()-1)&&arr[position.getX()][position.getY()+1]!=1){
			position.setY(position.getY()+1);
			ball.setCenterY(position.getY()*unit+unit/2.0);
		}
	}
	public Position getPosition(){
		return position;
	}
	
}