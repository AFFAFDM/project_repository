package Controller;

import Model.Position;

/**
 * Classe che rappresenta una factory
 * di mangiate ideali data una pedina della C.P.U.
 */
public class FactoryOfCapturingsForCpuForPiece extends FactoryOfPlaysForCpuForPiece {
	
	public FactoryOfCapturingsForCpuForPiece(Position position, Player cpu, int largestCapture){
		super(position,cpu,largestCapture);
	}
	
	/**
	 * Carica le mosse migliori nella factory.
	 */
	@Override
	protected void loadPlays(Position position, Player cpu, int largestCapture){
		FactoryOfPlays factory = new FactoryOfCapturingsForPiece(position,cpu.getBoard());
		int bestEvaluation=-100;
		for(AbstractPlay play:factory){
			int evaluation = evaluate(play,cpu,largestCapture);
			if(evaluation>=bestEvaluation){
				if (evaluation != bestEvaluation)
					bestPlays.clear();
				bestPlays.add(play);
				bestEvaluation=evaluation;
			}
			
		}
		maxEvaluation=bestEvaluation;
	}

	/**
	 * @param number: indice della mossa.
	 * @return la mossa number-esima.
	 */
	public AbstractPlay get(int number) {
		return super.get(number);
	}
	
}
