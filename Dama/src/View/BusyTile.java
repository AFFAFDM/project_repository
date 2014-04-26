package View;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import Model.King;
import Model.Piece;

/**
 * Classe grafica che rappresenta una casella occupata.
 */
public class BusyTile extends Tile {
	
	private final boolean color;
	private final static ImageIcon whitePiece = new ImageIcon("images/black_tile_white_piece.jpg");
	private final static ImageIcon blackPiece = new ImageIcon("images/black_tile_red_piece.jpg");
	private final static ImageIcon whiteKing = new ImageIcon("images/black_tile_white_king.jpg");
	private final static ImageIcon blackKing = new ImageIcon("images/black_tile_red_king.jpg");
	
	public BusyTile(Piece piece){	
		this.setImage(selectImage(piece));
		this.setBorder(BorderFactory.createEmptyBorder());
		this.color=piece.getColor();
	}
	
	public boolean getColor(){
		return color;
	}
	
	private ImageIcon selectImage(Piece piece){
		if (piece instanceof King)
			return piece.getColor() ? whiteKing : blackKing;
		else 
			return piece.getColor() ? whitePiece : blackPiece;
	}

}
