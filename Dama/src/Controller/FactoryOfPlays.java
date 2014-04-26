package Controller;

/**
 *  Intefaccia per la factory di mosse.
 */
public interface FactoryOfPlays extends Iterable<AbstractPlay> {
	
	/**
	 * @return una mossa casuale tra quelle preselezionate.
	 */
	public AbstractPlay mkRandom();
	
	/**
	 * @return la mossa migliore.
	 */
	public AbstractPlay getBest();
	
	/**
	 * @return true se la factory è vuota.
	 */
	public boolean isEmpty();
	
	/**
	 * @param number: indice della mossa.
	 * @return la mossa number-esima.
	 */
	public AbstractPlay get(int number);

}
