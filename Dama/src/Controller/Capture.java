package Controller;

import Model.Board;
import Model.King;
import Model.Piece;
import Model.Position;


/**
 * Classe che rappresenta la mangiata.
 */
public class Capture extends AbstractPlay {

	private Board field;
	private int horizontal;
	private int vertical;
	private Position start;
	
	public Capture(Board field, Position start, int horizontal , int vertical ){
		this.field = field;
		this.start=start;
		this.horizontal=horizontal*2;
		this.vertical=vertical*2;
	}
	
	/**
	 * @return La posizione di arrivo della mangiata. 
	 */
	public Position getDestination(){
		return new Position(start.getY() + vertical, start.getX() + horizontal);
	}
	
	/**
	 * Esegue la mangiata sulla damiera field.
	 */
	@Override
	public void execute() {
		if (isValid()){
			field.move(start, getDestination());
			field.eliminate(new Position (start.getY()+(vertical/2), start.getX()+(horizontal/2)));
		}
	}
	
	
	/**
	 * @return True se la mangiata è valida.
	 */
	public boolean isValid() {
	
		Position destination=getDestination();
		Position middle = new Position(start.getY()+(vertical/2), start.getX()+(horizontal/2));
		if(!inRange(start) || !inRange(destination))
			return false;
		if (field.getPiece(start)==null)
			return false; //casella di partenza vuota
		if (field.getPiece(middle)==null)
			return false; // manca la pedina in  mezzo
		if (field.getPiece(start).getColor()==field.getPiece(middle).getColor())
			return false; //nella casella media c'è una pedina mia
		if (field.getPiece(destination)!=null)
			return false; //casella di arrivo non vuota
		if (field.isPiece(start)&&field.isKing(middle))
			return false; //una pedina non può mangiare un damone
		return true;
			
		
	}

	@Override
	public String toString() {
		return "Capture from " + getStart() + " to " + getDestination();
		
	}
	
	/**
	 * @return Il numero massimo di mangiate consecutive per quella mossa.
	 */
	public int eatNumber (){
		Piece piece = field.getPiece(start);
		Player player = new Player(piece.getColor(),field);
		int result=0;
		Position destination = getDestination();
		if (piece instanceof King)
			field.setKing(destination, player.getColor());							// metto un segnalino
		else
			field.setPiece(destination, player.getColor());							// metto un segnalino
		
		FactoryOfPlays factory = new FactoryOfPlaysForPiece(destination, player.getBoard());
		
		if(factory.isEmpty()){														// non ci sono mosse
			field.eliminate(destination);
			return 1;
		}
		
		for (AbstractPlay play : factory){
			result = Math.max(result,  play.eatNumber());							// scelgo la mossa migliore
			field.eliminate(play.getDestination());									// elimino il segnalino
		}
		field.eliminate(destination);
		return 1 + result;
		
	}

	@Override
	public Position getStart() {
		return start;
	}

	@Override
	public Board getBoard() {
		return field;
	}
	
	/**
	 * Imposta la damiera sulla quale verrà eseguita la mossa.
	 * Usato per la C.P.U.
	 */
	public void setBoard (Board board){
		field=board;
	}
	
	
}

