package Controller;

import java.util.Iterator;

import Model.Piece;
import Model.Position;

/**
 * Interaccia per iterare sulle pedine
 */
public interface IteratorOnPieces extends Iterator<Piece>,Cloneable {
	
	/**
	 * @return una copia dell'iteratore
	 */
	public Iterator<Piece> clone() ; 
	
	/**
	 * @return la posizione dell'ultima pedina iterata.
	 */
	public Position getPosition();
}
