package Controller;

import Model.Board;
import Model.Position;


/**
 * Classe che rappresenta
 * l'intelligenza artificiale
 * della C.P.U.
 */
public class Cpu extends Player {
	private int largestCapture;  //mangiata massima
	
	public Cpu(boolean color, Board board) {
		super(color, board);
		largestCapture=0;
	}
	
	/**
	 * @return La prossima mossa scelta dalla C.P.U.
	 */
	public AbstractPlay nextPlay(){
		Huffer huffer = new Huffer(this);
		largestCapture = huffer.getLargestCapture();
		FactoryOfPlaysForCpu factory = new FactoryOfPlaysForCpu(this,largestCapture);
		
		AbstractPlay play = factory.getBest();
		
		return play;
	}
	
	/**
	 * Usato per mangiare consecutivamente.
	 * @param position: posizione di partenza.
	 * @return La prossima mangiata.
	 */
	public Capture nextCapture (Position position){
		FactoryOfCapturingsForCpuForPiece factory = new FactoryOfCapturingsForCpuForPiece(position,this,largestCapture);
		return (Capture) factory.getBest();
	}
	

}
