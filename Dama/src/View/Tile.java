package View;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Classe grafica che rappresenta una casella astratta.
 */
public abstract class Tile extends JButton {

	public void setImage (ImageIcon icon){
		this.setIcon(icon);
	}
}
