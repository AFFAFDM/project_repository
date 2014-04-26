package Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import Model.Board;
import Model.King;
import Model.Piece;
import Model.Position;

/**
 * La classe rappresenta la
 * factory di mosse ideali per 
 * la C.P.U. data una pedina.
 */
public class FactoryOfPlaysForCpuForPiece implements FactoryOfPlays {
	
	private static final Random rnd = new Random ();
	protected int maxEvaluation;
	protected List<AbstractPlay> bestPlays = new ArrayList<AbstractPlay>();
	
	public FactoryOfPlaysForCpuForPiece (Position position, Player cpu,int largestCapture){
		loadPlays(position,cpu,largestCapture);
	}
	
	/**
	 * Carica le mosse della pedina per la cpu.
	 * @param position: Posizione della pedina considerata.
	 * @param cpu: C.P.U. (giocatore a cui appartiene la pedina)
	 * @param largestCapture: Intero rappresentante la mangiata massima (utilizzato per considerare il soffio).
	 */
	protected void loadPlays(Position position, Player cpu,int largestCapture){
		FactoryOfPlays factory = new FactoryOfPlaysForPiece(position,cpu.getBoard());
		int bestEvalution=-100; // valore inizializzato a -inf.
		for(AbstractPlay play:factory){
			int evaluation = evaluate(play,cpu,largestCapture);
			if(evaluation>=bestEvalution){
				if (evaluation != bestEvalution)
					bestPlays.clear();
				bestPlays.add(play);
				bestEvalution=evaluation;
			}
			
		}
		maxEvaluation=bestEvalution;
	}
	
	/**
	 * Funzione che controlla se il risultato calcolato da evaluateRecursively causerà il soffio;
	 * nel caso si verifichi ne modificherà il risultato.
	 * @param play: mossa da valutare
	 * @param cpu: giocatore associato alla mossa (C.P.U.)
	 * @param largestCapture: Intero rappresentante la mangiata massima (utilizzato per considerare il soffio).
	 * @return un intero rappresentate la valutazione della C.P.U. sulla mossa.
	 */
	public int evaluate (AbstractPlay play, Player cpu, int largestCapture) {
		int evaluation = evaluateRecursively(play, cpu);
		if (play.eatNumber() < largestCapture)
			evaluation--;
		return evaluation;
		
	}
	
	/**
	 * Valuta ricorsivamente la differenza tra il numero di pedine mangiate dalla mia mossa
	 * e dalla miglior contromossa dell'avverisario.
	 * Caso base: mossa che mangia una pedina o nessuna.
	 * @param play: mossa da valutare.
	 * @param cpu: giocatore associato (C.P.U.).
	 * @return intero rappresentate la valutazione della mossa.
	 */
	private int evaluateRecursively (AbstractPlay play, Player cpu){
		Board copy = new Board (play.getBoard());
		play.setBoard(copy);
		int evaluation = play.eatNumber();
		Piece piece = copy.getPiece(play.getStart()); //salvo il pezzo per poi controllare se è un damone
		
		play.execute(); //sulla copia della damiera
		
		if (play instanceof Capture) {
			FactoryOfPlays consecutiveCaptures = new FactoryOfCapturingsForPiece(play.getDestination(), copy);
			int max = 0;
			if (!consecutiveCaptures.isEmpty()) {
				for (AbstractPlay p : consecutiveCaptures)
					max = Math.max(max, evaluateRecursively(p,cpu));
				play.setBoard(cpu.getBoard());
				return 1+max;
			}
		}
		Player otherPlayer = new Player(!cpu.getColor(), copy);
		FactoryOfPlays factory = new FactoryOfPlaysForPlayer(otherPlayer);
		
		int counterPlayEvaluation = 0;
		if (!factory.isEmpty()){
			AbstractPlay bestCounterPlay = factory.getBest();
			counterPlayEvaluation = bestCounterPlay.eatNumber();
		}
		
		if (!(piece instanceof King) && play.getDestination().getY() == 0)
			evaluation++;
			
		play.setBoard(cpu.getBoard());
		
		return evaluation - counterPlayEvaluation; 
	}

	@Override
	public Iterator<AbstractPlay> iterator() {
		return bestPlays.iterator();
	}

	@Override
	public AbstractPlay mkRandom() {
			return bestPlays.get(rnd.nextInt(bestPlays.size()));
	}

	@Override
	public AbstractPlay getBest() {
		return mkRandom();
	}

	@Override
	public boolean isEmpty() {
		return bestPlays.isEmpty();
	}
	
	/**
	 * @return la valutazione massima
	 */
	public int getMaxEvaluation(){
		return maxEvaluation;
	}
	
	public AbstractPlay get(int number) {
		return bestPlays.get(number);
	}

}
