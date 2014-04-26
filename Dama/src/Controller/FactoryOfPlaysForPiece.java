package Controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import Model.Board;
import Model.King;
import Model.Position;

/**
 * Classe che rappresenta la factory 
 * che genera tutte le mosse possibili
 * data una pedina.
 * 
 */
public class FactoryOfPlaysForPiece implements FactoryOfPlays {
	
	protected List<AbstractPlay> plays = new ArrayList<AbstractPlay> () ;
	private final static Random rnd = new Random();
	
	public FactoryOfPlaysForPiece (Position position, Board field){
		loadPossiblePlays(position, field);
	}
	
	/**
	 * Carica tutte le mosse valide.
	 * @param position: posizione della pedina.
	 * @param field: damiera associata.
	 */
	protected void loadPossiblePlays(Position position, Board field) {
		if ( field.getPiece(position)!=null ){
			
		int forward = field.getPiece(position).getColor() ? +1 : -1;
		AbstractPlay test;
		
		test = new Move(field, position, 1, forward);
		if (test.isValid())
			plays.add(test);
		
		test = new Move(field, position, -1, forward);
		if (test.isValid())
			plays.add(test);
		
		test = new Capture(field, position, 1, forward);
		if (test.isValid())
			plays.add(test);
		
		test = new Capture(field, position, -1, forward);
		if (test.isValid())
			plays.add(test);
		
		if (field.getPiece(position) instanceof King)
		{
			int backward = forward * -1;
			
			test = new Move(field, position, 1, backward);
			if (test.isValid())
				plays.add(test);
			
			test = new Move(field, position, -1, backward);
			if (test.isValid())
				plays.add(test);
			
			test = new Capture(field, position, 1, backward);
			if (test.isValid())
				plays.add(test);	
			
			test = new Capture(field, position, -1, backward);
			if (test.isValid())
				plays.add(test);
		}
		}
	}

	@Override
	public Iterator<AbstractPlay> iterator() {
		return plays.iterator();
	}

	@Override
	public AbstractPlay mkRandom() {
		return plays.get(rnd.nextInt(plays.size()));
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
		
		int bestEvaluation=-1;
		for(AbstractPlay play:plays){
			int evaluation = play.eatNumber();
			if(evaluation>=bestEvaluation){
				if (evaluation > bestEvaluation)
					result.clear();
				result.add(play);
				bestEvaluation=evaluation;
			}
			
		}
		return result;
		
	}
	
	
	
	@Override
	public boolean isEmpty() {
		return plays.isEmpty();
	}
	
	public AbstractPlay get(int number) {
		return  plays.get(number);
	}

}
