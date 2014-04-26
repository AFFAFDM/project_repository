package Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Classe che rappresenta la factory delle mosse ideali per la C.P.U.
 */
public class FactoryOfPlaysForCpu implements FactoryOfPlays {
	
	public final static Random rnd = new Random();
	List<AbstractPlay> bestPlays = new ArrayList<AbstractPlay>();
	
	/**
	 * Scorre tutte le pedine e per ciascuna aggiunge le 
	 * mosse della factory di quella pedina a questa factory.
	 * @param cpu
	 * @param largestCapture
	 */
	public FactoryOfPlaysForCpu (Player cpu, int largestCapture){
		IteratorOnPieces iterator = (IteratorOnPieces) cpu.iterator();
		int bestEvaluation = -100; //inizializzato a -inf.
		while(iterator.hasNext()){
			iterator.next();
			FactoryOfPlaysForCpuForPiece factory = new FactoryOfPlaysForCpuForPiece(iterator.getPosition(), cpu, largestCapture);
			int evaluation = factory.getMaxEvaluation();
			if (evaluation >= bestEvaluation) {
				if (evaluation != bestEvaluation)
					bestPlays.clear();
				addAll(factory);
				bestEvaluation = evaluation;
			}
		}
			
	}
	
	/**
	 * Aggiunge tutte le mosse di factory(parametro) a questa factory.
	 * @param factory: la seconda factory.
	 */
	private void addAll(FactoryOfPlays factory) {
		for (AbstractPlay play : factory)
			bestPlays.add(play);
	}
	
	/**
	 * Iteratore sulle mosse.
	 */
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

	@Override
	public AbstractPlay get(int number) {
		return bestPlays.get(number);
	}

}
