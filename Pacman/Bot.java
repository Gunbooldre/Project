import java.util.*;

public class Bot{
	public static void main(String[] args){
		int[][] map = new int[8][8];
		for (int i = 0;i<8;i++){
			for (int j = 0;j<8;j++){
				map[j][i] = 0;
			}
		}

		ArrayList<Integer> move = new ArrayList<>();
		Point start = new Point(0,0,0,move);
		Point target = new Point(5,6,0,move);
		int [] dx = {1,0,-1,0};
		int [] dy = {0,-1,0,1};
		Vector <Point> points = new Vector<>();
		points.add(start);
		Point temp;
		int x,y;
		boolean [][]visit = new boolean[8][8];
		for (int i = 0;i<8;i++){
			for (int j = 0;j<8;j++){
				visit[j][i] = false;
			}
		}
		visit[3][4] = true;
		visit[1][6] = true;
		visit[2][6] = true;
		visit[3][6] = true;
		visit[4][6] = true;
		visit[5][1] = true;
		visit[5][2] = true;
		visit[5][3] = true;
		visit[5][4] = true;
		visit[5][5] = true;
		ArrayList <Integer> m;
		while(!points.isEmpty()){
			temp = points.firstElement();
			points.remove(0);
			if ((temp.x==target.x)&&(temp.y==target.y)){
				System.out.println(temp.dis);
				System.out.println(temp.move.size());
				for(int i = 0;i<temp.move.size();i++){
					System.out.println(move.get(i));
				}
				break;
			}
			for(int i = 0;i<4;i++){
				x=temp.x+dx[i];
				y=temp.y+dy[i];
				if((x >= 0 && x <= 7 && y >= 0 && y <= 7)&&!visit[x][y]){
					visit[x][y]=true;
					m=temp.move;
					m.add(i);
					points.add(new Point(x,y,temp.dis+1,m));
				}
			}
		}
	}	
}
class Point{
	int x;
	int y;
	int dis;
	ArrayList<Integer> move;
	Point(int x, int y, int dis, ArrayList<Integer> move){
		this.x = x;
		this.y=y;
		this.dis = dis;
		this.move=move;
	}
}