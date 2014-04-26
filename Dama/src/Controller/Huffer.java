package Controller;

import Model.Position;

/**
 * Classe utilizzata per il soffio.
 */
public class Huffer {
	
	private Player player;
	private int largestCapture;
	
	public Huffer (Player player){
		this.player=player;
		largestCapture=setToBeHuffed();
	}
	
	/**
	 * Segna la pedina da soffiare.
	 * @return la massima mangiata possibile.
	 */
	private int setToBeHuffed(){
		FactoryOfPlays factory = new FactoryOfPlaysForPlayer(player);
		if (factory.isEmpty())
			return 0;
		AbstractPlay bestPlay = factory.getBest();
		int max = bestPlay.eatNumber();
		if (max > 0)
			player.getBoard().getPiece(bestPlay.getStart()).setHuff(true);
		return max;
	} 
	
	/**
	 * Itera sulle pedine per trovare quella da soffiare se esiste e in caso la cancella.
	 * @return la posizione della pedina soffiata.
	 */
	public Position huff(){	
		IteratorOnPieces iterator = (IteratorOnPieces) player.iterator();
		while (iterator.hasNext()){
			iterator.next();
			Position position = iterator.getPosition();
			if(player.getBoard().getPiece(position).getHuff()) {
				player.getBoard().eliminate(position);
				return position;
			}
		}
		return null;
		
	}
	
	/**
	 * Rimuove i segnalini di soffio dalle pedine del giocatore.
	 */
	public void removeHuff(){
		IteratorOnPieces iterator = (IteratorOnPieces) player.iterator();
		while (iterator.hasNext()){
			iterator.next();
			Position position = iterator.getPosition();
			if(player.getBoard().getPiece(position).getHuff())
				player.getBoard().getPiece(position).setHuff(false);
		}
	}
	
	/**
	 * @return il massimo numero di pedine mangiate.
	 */
	public int getLargestCapture() {
		return largestCapture;
	}
}
