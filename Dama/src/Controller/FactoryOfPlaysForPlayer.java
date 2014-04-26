package Controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Classe che genera tutte le mosse possibili per il giocatore.
 */
public class FactoryOfPlaysForPlayer implements FactoryOfPlays {
	
	private final static Random rnd = new Random ();
	private List<AbstractPlay> everyPlay = new ArrayList<AbstractPlay>();

	/**
	 * Scorre tutte le pedine e per ciascuna aggiunge tutte le sue possibili mosse.
	 * @param player: giocatore umano.
	 */
	public FactoryOfPlaysForPlayer(Player player){
		IteratorOnPieces iteratorOnPlayer = (IteratorOnPieces) player.iterator();
		while (iteratorOnPlayer.hasNext()){
			iteratorOnPlayer.next();
			FactoryOfPlays factory = new FactoryOfPlaysForPiece(iteratorOnPlayer.getPosition(),player.getBoard());
			for (AbstractPlay play: factory)
				everyPlay.add(play);
		}
	}

	@Override
	public Iterator<AbstractPlay> iterator() {
		return everyPlay.iterator();
	}

	@Override
	public AbstractPlay mkRandom() {
		return everyPlay.get(rnd.nextInt(everyPlay.size()));
	}

	@Override
	public AbstractPlay getBest() {
		ArrayList<AbstractPlay> bestPlays = getBestPlays();
		return bestPlays.get(rnd.nextInt(bestPlays.size()));
	}
	
	/**
	 * La valutazione viene fatta in base al numero di mangiate;
	 * quindi ritorna quelle con uguale valore.
	 * @return una lista delle mosse migliori.
	 */
	private ArrayList<AbstractPlay> getBestPlays(){
			ArrayList<AbstractPlay> result = new ArrayList<AbstractPlay>();
			
			int bestEvaluation=-100;
			for(AbstractPlay play:everyPlay){
				int evaluation = play.eatNumber();
				if(evaluation>=bestEvaluation){
					if (evaluation != bestEvaluation)
						result.clear();
					result.add(play);
					bestEvaluation=evaluation;
				}
				
			}
			return result;
			
	}

	@Override
	public boolean isEmpty() {
		return everyPlay.isEmpty();
	}
	
	public AbstractPlay get(int number) {
		return everyPlay.get(number);
	}

}
