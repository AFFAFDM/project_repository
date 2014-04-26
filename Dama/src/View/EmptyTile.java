
package View;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

/**
 * Classe grafica che rappresenta una casella vuota.
 */
public class EmptyTile extends Tile {
	private static final ImageIcon black = new ImageIcon("images/black_tile.jpg");
	private static final ImageIcon white = new ImageIcon("images/white_tile.jpg");
	
	public EmptyTile(boolean color){
			setImage(color ? white : black);
			this.setBorder(BorderFactory.createEmptyBorder());
	}
	

}
