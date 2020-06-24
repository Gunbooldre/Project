import java.util.Scanner;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.io.File;
import javafx.scene.layout.Pane;

public class Map extends Pane{
	//Data
	private	int unit = 50;
	private	int size;
	private	int[][] map;
	private Position start;
	
	//Methods
	public Map(String path) throws Exception{
		Scanner in = new Scanner(new File(path));
			size = in.nextInt();
			map = new int [size][size];
			for(int i = 0; i < size; i++)
				for(int j = 0; j<size;j++){
				map[i][j] = in.nextInt();
				Rectangle square = new Rectangle(j*unit,i*unit,unit,unit);
				square.setFill(Color.WHITE);	
				square.setStroke(Color.BLACK);
				getChildren().add(square);
				if(map[i][j] == 0){
					square.setFill(Color.WHITE);	
					square.setStroke(Color.BLACK);
				}else if(map[i][j] == 1){
					square.setFill(Color.BLACK);	
					square.setStroke(Color.WHITE);
				} else if (map[i][j] == 2){
					start = new Position(j, i);
					square.setFill(Color.WHITE);	
					square.setStroke(Color.BLACK);
				}
			}
	}
	//GetUnit methods 
	public int getUnit(){
		return unit;
	}
	//GetSize methods
	public int getSize(){	
		return size;
	}	
	//GetMap methods 
	public int[][] getMap(){
		return map;
	}
	//GetMethodStartPosition
	public Position getStartPosition(){
		return start;
	}
}