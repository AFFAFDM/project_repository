package Controller;
import java.util.Iterator;

import Model.Board;
import Model.Piece;
import Model.Position;

/**
 * Classe che raprresenta il giocatore.
 */
public class Player implements Iterable<Piece>{

	private boolean myColor;
	private Board field;
	
	public Player (boolean color, Board field ){

		myColor=color;
		this.field=field;
	}
	public Board getBoard(){
		return field;
	}
	
	public boolean getColor(){
		return myColor;
	}
	
	/**
	 * Restituisce un iteratore sulle pedine del giocatore
	 */
	@Override
	public Iterator<Piece> iterator() {
		return new IteratorOnPieces(){
			
			private IteratorOnPieces iteratorOnBoard = (IteratorOnPieces) field.iterator();

			@Override
			public boolean hasNext() {
				Iterator<Piece> clone = iteratorOnBoard.clone();
				while(clone.hasNext()){
					Piece piece = clone.next();
					if(piece.getColor()==myColor)
						return true;
				}
				return false;
			}

			@Override
			public Piece next() {
				while (iteratorOnBoard.hasNext()){
					Piece piece = iteratorOnBoard.next();
					if (piece.getColor()==myColor)
						return piece;
				}
				return null;
			}

			@Override
			public void remove() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public Position getPosition() {
				return iteratorOnBoard.getPosition();
			}
			
			public Iterator<Piece> clone() {
				try{
				return (Iterator<Piece>) super.clone();
				}
				catch(Exception e){return null;}
			}

		};
			
	
		
	}
	
	
}
