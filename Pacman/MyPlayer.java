import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

public class MyPlayer implements Player{
	//Circle 
	private Circle ball;
	//Map
	private Map map;
	//Position
	private Position position ;

	public MyPlayer(Map map){
		this.map = map;
		position = map.getStartPosition();
		//Ball 
		ball = new Circle(map.getUnit()/2);
		ball.setFill(Color.RED);
		ball.setStroke(Color.WHITE);
		map.getChildren().add(ball);
		move(KeyCode.T);
	}
	//KeyCode method , to use keybord
		public void move(KeyCode code) {
			switch (code){
				case RIGHT:
				case D:
				moveRight();
				break;
				case LEFT:
				case A:
				moveLeft();
				break;
				case UP:
				case W:
				moveUp();
				break;
				case DOWN:
				case S:
				moveDown();
				break;
			}

			ball.setCenterX(position.getX() * map.getUnit() + map.getUnit()/2 );
			ball.setCenterY(position.getY() * map.getUnit() + map.getUnit()/2 );
		}

		@Override
		public void moveRight(){
			int x = position.getX();
			if(x + 1 < map.getSize() && map.getMap()[position.getY()][x + 1] != 1){
				position.setX(x + 1);
			 }
		}

		@Override
		public void moveLeft(){
			int x =position.getX();
			if(x - 1 >=0 && map.getMap() [position.getY()][x - 1] !=1 ){
				position.setX(x - 1);
			}
		}

		@Override
		public void moveDown(){
			int y = position.getY();
			if(y + 1 < map.getSize() && map.getMap()[y + 1][position.getX()] != 1){
				position.setY(y + 1);
			}
		}

		@Override
		public void moveUp(){
			int y =position.getY();
			if(y - 1 >=0 && map.getMap() [y - 1] [position.getX()] !=1 ){
				position.setY(y - 1);
			}	
		}	

		@Override
		public Position getPosition(){
			return position;
		}
} 