package Model;

/**
 * Classe che rappresenta la posizione.
 */
public class Position {

	private int x;
	private int y;
	
	public Position (int row, int column){
		this.x=column;
		this.y=row;
	}
	
	public int getX(){return x;}
	public int getY(){return y;}
	public String toString (){return "("+(y*8+x+1)+")";}
}
