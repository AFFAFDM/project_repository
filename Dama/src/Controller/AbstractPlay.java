package Controller;
import Model.Board;
import Model.Position;

//Classe per la mossa astratta.

public abstract class AbstractPlay {
	
	public abstract void execute();
	
	public abstract boolean isValid();
	
	public abstract String toString();
	
	public abstract Position getDestination();
	
	public abstract int eatNumber();
	
	public abstract Position getStart();
	
	public abstract Board getBoard();
	
	public abstract void setBoard (Board board);
	

	/**
	 * La funzione controlla se position è valida.
	 * @param position
	 * @return Se position è interno alla scacchiera.
	 */
	public boolean inRange(Position position){
		return position.getX() >= 0 && position.getY() >= 0 && position.getX() < 8 && position.getY() < 8;
	}
}
