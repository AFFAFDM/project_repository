package Controller;

import Model.Position;

/**
 * Classe che serve per la gestione dei turni.
 */
public class TurnChanger {
	
	private Huffer huffer;
	private int eat;
	private int turn ;
	private final Player[] players = new Player[2];
	private Position huffPosition;
	
	public TurnChanger (Player player1, Player player2){
		turn = 0;
		players[0]=player1;
		players[1]=player2;
		
	}
	
	/**
	 * Cambia il turno ed eventualmente esegue il soffio. 
	 */
	public Player next(){
		turn++;
		
		huffPosition = null;
		
		if(huffer != null){
			
			if (huffer.getLargestCapture() > eat )
				huffPosition = huffer.huff();
			else 
				huffer.removeHuff();
		}
		
		eat=0; 
		huffer = new Huffer(players[turn%2]);
		
		return players[turn%2];
	}
	
	/**
	 * @return tue se la partita è finita.
	 */
	public boolean gameOver(){
		if( !players[turn%2].iterator().hasNext() || new FactoryOfPlaysForPlayer(players[turn%2]).isEmpty() )
			return true;
		return false;
	}
	
	/**
	 * @return il colore del giocatore di cui è il turno. (bianco = true).
	 */
	public boolean getTurn(){
		return turn%2==0;
	}
	
	/**
	 * Incrementa la variabile eat utilizzata per il controllo del soffio.
	 */
	public void eatIncrement(){
		eat++;
	}
	
	/**
	 * @return la posizione della pedina soffiata
	 */
	public Position getHuffPosition() {
		return huffPosition;
	}
}
