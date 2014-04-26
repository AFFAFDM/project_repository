package Model;

import Controller.AbstractPlay;

/**
 * Classe che rappresenta il segnalino, ovvero una posizione raggiungibile con una sola mossa dalla pedina.
 * Utilizzato per la grafica.
 */
public class Marker extends Piece {
	
	private final AbstractPlay relatedPlay;
	
	public Marker (AbstractPlay play){
		this.relatedPlay=play;
	}

	/**
	 * @return la mossa associata.
	 */
	public AbstractPlay getRelatedPlay(){
		return this.relatedPlay;
	}
}
