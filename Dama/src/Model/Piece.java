package Model;

/**
 * Classe che rappresenta la pedina.
 */
public class Piece {
	
	private boolean color;   //true=bianco
	private boolean toBeHuffed;
	
	
	//costruttore per segnalino
	public Piece(){
		toBeHuffed=false;
	} 
	
	public Piece (boolean color){
		this.color=color;
		toBeHuffed=false;
	}
	
	public boolean getColor (){
		return color;
	}

	public String toString(){
		return color ? "o":"x";
	}
	
	public void setHuff(boolean toBeHuffed){
		this.toBeHuffed = toBeHuffed;
	}
	
	public boolean getHuff(){
		return toBeHuffed;
	}
	
	
}
