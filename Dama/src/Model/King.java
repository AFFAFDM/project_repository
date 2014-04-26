package Model;

/**
 * Classe che rappresenta il damone.
 */
public class King extends Piece {
	
	public King (boolean color){
		super(color);
	}
	
	public String toString(){
		return super.getColor() ? "*":"$";
	}
}
