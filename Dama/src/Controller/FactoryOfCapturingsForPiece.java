package Controller;

import Model.Board;
import Model.King;
import Model.Position;

/**
 * Classe che rappresenta tutte le mangiate 
 * possibili per un pezzo.
 */
public class FactoryOfCapturingsForPiece extends FactoryOfPlaysForPiece {

	public FactoryOfCapturingsForPiece(Position position, Board field) {
		super(position, field);
	}
	
	/**
	 * Carica tutte le mangiate possibili per
	 * la pedina nella factory.
	 * @param position: posizione della pedina.
	 * @param field: damiera su cui effettuare il controllo.
	 */
	@Override
	protected void loadPossiblePlays (Position position, Board field) {
		if ( field.getPiece(position)!=null ){
			
			int forward = field.getPiece(position).getColor() ? +1 : -1;
			AbstractPlay test;
			
			test = new Capture(field, position, 1, forward);
			if (test.isValid())
				plays.add(test);
			
			test = new Capture(field, position, -1, forward);
			if (test.isValid())
				plays.add(test);
			
			if (field.getPiece(position) instanceof King)
			{
				int backward = forward * -1;
				
				test = new Capture(field, position, 1, backward);
				if (test.isValid())
					plays.add(test);	
				
				test = new Capture(field, position, -1, backward);
				if (test.isValid())
					plays.add(test);
			}
		}
		
	}
	
	/**
	 * @param number: indice della mossa.
	 * @return la mossa number-esima.
	 */
	public AbstractPlay get(int number) {
		return super.get(number);
	}

}
