class Position{
	private int x ;
	private int y;

	public void setY(int y){
		this.y=y;
	}	
	public int getY(){
		return y;
	}
	public void setX(int x){
	this.x=x;
	}
	public int getX(){
	return x;
	}
	public boolean equals(Position o){
		return x == o.getX() && y == o.getY();
	}
	public Position (int x,int y){
		this.x=x;
		this.y=y;	
	}
}