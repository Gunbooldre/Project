import javafx.scene.input.KeyCode;

public interface Player{
	public void move (KeyCode code);

	public void moveRight();

	public void moveLeft();

	public void moveDown();

	public void moveUp();

	public Position getPosition();
} 