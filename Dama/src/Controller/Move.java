package Controller;
import Model.Board;
import Model.Position;

/**
 * Classe che rappresenta il movimento.
 */
public class Move extends AbstractPlay {
	
	private Board field;
	private int horizontal,vertical;
	private Position start;
	
	public Move(Board board, Position start, int horizontal , int vertical ){
		field = board;
		this.start=start;
		this.horizontal=horizontal;
		this.vertical=vertical;
		
	}
	
	@Override
	public Position getDestination(){
		return new Position(start.getY() + vertical ,start.getX() + horizontal);
	}
	
	
	@Override
	public void execute() {
		if (isValid())
			field.move(start, getDestination());
	}

	@Override
	public boolean isValid() {
	
		Position destination = getDestination();
		if(!inRange(start) || !inRange(destination))
			return false;
		if (field.getPiece(start)==null)
			return false; //casella di partenza vuota
		if (field.getPiece(destination)!=null)
			return false; //casella di arrivo non vuota
		return true;
			
		
	}

	@Override
	public String toString() {
		return "Move from " + getStart() + " to " + getDestination();
		}

	@Override
	public int eatNumber() {
		return 0;
	}

	@Override
	public Position getStart() {
		return  start;
	}
	
	@Override
	public Board getBoard() {
		return field;
	}
	
	@Override
	public void setBoard (Board board){
		field=board;
	}
	
	
	
}
