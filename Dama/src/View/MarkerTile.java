package View;

import javax.swing.ImageIcon;

import Controller.AbstractPlay;

/**
 * Classe grafica che rappresenta una casella evidenziata, destinazione di una mossa.
 */
public class MarkerTile extends Tile {

	private static final ImageIcon marker = new ImageIcon("images/black_tile_preselection.jpg");
	private final AbstractPlay relatedPlay;

	public MarkerTile(AbstractPlay relatedPlay){
		this.relatedPlay=relatedPlay;
		this.setImage(marker);
	}
		
	public AbstractPlay getRelatedPlay(){
		return relatedPlay;
	}
}
