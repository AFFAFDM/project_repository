package Model;

import java.util.Iterator;
import Controller.IteratorOnPieces;
import Controller.AbstractPlay;

/**
 * Classe rappresentante la damiera.
 */
public class Board implements Iterable<Piece> {
	
	private Piece[][] board;
	public static final boolean WHITE = true;
	public static final boolean BLACK = false;
	
	public Board (){
		board = new Piece [8][8];
		
		//Dispone le pedine bianche
		for (int row=0;row<3;row++){
			int column = (row)%2;
			while(column<8){
				board[row][column]= new Piece(WHITE);
				column += 2;
			}
		}
		
		//Dispone le pedine nere
		for (int row=5;row<8;row++){ 
			int column = (row)%2;
			while(column<8){
				board[row][column]= new Piece(BLACK);
				column += 2;
			}
		}			
	}
	
	/**
	 * costruttore di copia
	 * @param original
	 */
	public Board (Board original){
		board = new Piece [8][8];
		
		for (int row=0;row<8;row++)
			for (int column=0; column<8;column++)
				board[row][column]= original.board[row][column];
		
	}
	
	public String toString(){
		String res="     0   1   2   3   4   5   6   7\n   ---------------------------------\n";
		for(int row=0; row<8;row++){
			res += row+"  |";
			for(int column=0; column<8; column++){
				if ((column+row)%2==1)
					res+="   |";
				else{
					if(board[row][column]==null)
						res+=" _ |";
					else res+= " " + board[row][column] + " |" ; // O = bianco
				}
			} 
			res+="\n   ---------------------------------\n";
		}
		
		return res;
	}
			
	/**
	 * @param pos
	 * @return true se sono una pedina false se sono un damone
	 */
	public boolean isPiece(Position pos){ 
		return !isEmpty(pos) && (!(getPiece(pos) instanceof King)); 
	}
	public boolean isKing(Position pos){
		return !isEmpty(pos) && getPiece(pos) instanceof King;
	}

	public boolean isEmpty(Position pos){
		return (getPiece(pos)==null) ;
	}
	
	/**
	 * Sposta la pedina da start a destination.
	 * @param start
	 * @param destination
	 */
	public void move(Position start,Position destination){
		board[destination.getY()][destination.getX()] = getPiece(start);
		eliminate(start);
		if (destination.getY() == 7 || destination.getY() == 0) //condizione per damone
			board[destination.getY()][destination.getX()] = new King (getPiece(destination).getColor());
			
	}
	
	/**
	 * elimina la pedina in pos.
	 * @param pos
	 */
	public void eliminate(Position pos){
		board[pos.getY()][pos.getX()] = null;
	}
	
	public Piece getPiece(Position pos){
		return board[pos.getY()][pos.getX()];
	}
	
	/**
	 * Setta un segnalino nella destinazione di play.
	 * @param play
	 */
	public void setMarker(AbstractPlay play) {
		board[play.getDestination().getY()][play.getDestination().getX()] = new Marker (play);
	}
	
	public Marker getMarker (Position position){
		return (Marker)board[position.getY()][position.getX()];
	}

	public boolean isMarker(Position position) {
		return (board[position.getY()][position.getX()] instanceof Marker);
	}

	/**
	 * Elimina tutti i segnalini dalla damiera.
	 */
	public void removeMarkers() {
		for(int row=0; row<8;row++)
			for(int column=0; column<8; column++)
				if(isMarker(new Position(row,column)))
					board[row][column]=null;
	}

	public void setPiece(Position position, boolean color) {
		board[position.getY()][position.getX()]= new Piece(color);
	}
	
	public void setKing(Position position, boolean color){
		board[position.getY()][position.getX()]= new King(color);
	}

	/**
	 * Itera sulle pedine della damiera.
	 */
	@Override
	public Iterator<Piece> iterator() {
		return new IteratorOnPieces(){
			
			private int row,column;

			@Override
			public boolean hasNext() {
				int start = column;
				for (int r=row; r<8;r++){
					for(int c=start; c<8;c++)
						if(!isEmpty(new Position(r,c)))
							return true;
					start = 0;
				}
				return false;
			}

			@Override
			public Piece next() {
				int start = column;
				for (int r=row; r<8;r++){
					for(int c=start; c<8;c++){
						if(!isEmpty(new Position(r,c))){
							if(c==7){
								row=r+1;
								column=0;
								}
							else{
							column=c+1;
							row=r;
							}
							return getPiece(new Position(r,c));
						}
					}
					start=0;
				} 
				return null;
			}

			@Override
			public void remove() {
				// TODO Auto-generated method stub
				
			}
			
			/**
			 * Crea il clone.
			 */
			public Iterator<Piece> clone() {
				try{
				return (Iterator<Piece>) super.clone();
				}
				catch(Exception e){return null;}
			}

			@Override
			public Position getPosition() {
				if (column!=0)
					return new Position(row,column-1);
				return new Position(row-1,7);
			}
			
		};
	}
}