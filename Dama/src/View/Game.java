package View;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


import javax.swing.JFrame;

import Controller.Cpu;
import Controller.FactoryOfCapturingsForPiece;
import Controller.FactoryOfPlays;
import Controller.FactoryOfPlaysForPiece;
import Controller.Player;
import Controller.Capture;
import Controller.AbstractPlay;
import Controller.Move;
import Controller.TurnChanger;

import Model.Board;
import Model.Position;

/**
 * Classe grafica che rappresenta la finestra di gioco.
 * Contiene il metodo main().
 */
public class Game extends JFrame {
	
	private static final int HEIGHT = 600;
	private static final int WIDTH = 600;
	private Board board;
	private TurnChanger turnChanger;
	private Cpu black;        //non ancora usati
	private LogWindow log = new LogWindow();
	private final CheckExit checkExit=new CheckExit();
	
	public Game() {
		super("Checkers");
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null); //lo centra nello schermo
		this.board = new Board();
		this.black=new Cpu(Board.BLACK, board);
		
		setLayout(new GridLayout(8,8));
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent e) {
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				checkExit.setVisible(true);
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
			}
			
			@Override
			public void windowOpened(WindowEvent e) {
			}
		
		});
		
		turnChanger = new TurnChanger(new Player(Board.WHITE,board),black);
		showBoard(true);
		initializeSound();
	}
	
	private void initializeSound (){
		new Sound ("music/soundTrack.wav").start();
	}

	private void showBoard(boolean activate) {
		for (int row = 0; row < 8; row++)
            for (int column = 0; column < 8; column++){
            	Position position = new Position(row,column);
            
                    if (board.isEmpty(position))
                    	add(new EmptyTile((row+column)%2==0 ? Board.BLACK : Board.WHITE));
                    
                    else if(board.isMarker(position) && activate)
                    	addMarker(new MarkerTile(board.getMarker(position).getRelatedPlay()));
                    
                    else 
                    	addPiece(new BusyTile( board.getPiece(position)),position,activate);            
           }
	}
	
	/**
	 * Aggiunge una casella segnalino.
	 * @param markerTile
	 */
	private void addMarker(final MarkerTile markerTile) {
		add(markerTile);
		markerTile.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Position huffPosition;
				
				board.removeMarkers();
				AbstractPlay play= markerTile.getRelatedPlay();
				play.execute();
				log.setMessage(play.toString());//aggiorna log
				if (play instanceof Capture)
					turnChanger.eatIncrement();
				
				FactoryOfPlays factory = new FactoryOfCapturingsForPiece(play.getDestination(), board);
				boolean flag = true;
				if (play instanceof Move || factory.isEmpty()){
					turnChanger.next();
				
					huffPosition = turnChanger.getHuffPosition();
					if (huffPosition != null)
						log.setMessage("Huff in " + huffPosition);
						
					getContentPane().removeAll();
					invalidate();
					showBoard(true);
					validate();	
					
					if(turnChanger.gameOver()){
						flag=false;
						new GameEnding(!turnChanger.getTurn(),Game.this).setVisible(true);
						log.setMessage("GAME OVER");
					} 
					else {
						cpuTurn();
						turnChanger.next();
						
						huffPosition = turnChanger.getHuffPosition();
						if (huffPosition != null)
							log.setMessage("Huff in " + huffPosition);
						
						if(turnChanger.gameOver()){
							new GameEnding(!turnChanger.getTurn(),Game.this).setVisible(true);
							log.setMessage("GAME OVER");
						}
					}
				}
				
				getContentPane().removeAll();
				invalidate();
				showBoard(flag);
				validate();
			}			
		});
	}

	private void addPiece(final BusyTile busyTile, final Position position, final boolean activate) {
		add(busyTile);
		if(busyTile.getColor())//ovvero se è bianco
			if (activate) {
				busyTile.addActionListener(new ActionListener() {
			
					@Override
					public void actionPerformed(ActionEvent e) { 
						board.removeMarkers();
					FactoryOfPlays factory= new FactoryOfPlaysForPiece(position,board);
					
					for (AbstractPlay play : factory)
						board.setMarker(play);
					
					getContentPane().removeAll();
					invalidate();
					showBoard(activate);
					validate();
					
				}
	
				});
			}
			
	}
	
	/**
	 * Gestisce il turno della C.P.U.
	 */
	public void cpuTurn() {

		AbstractPlay play=black.nextPlay();
		play.execute();
		log.setMessage(play.toString());
		if(play instanceof Capture){
			turnChanger.eatIncrement();
			
			FactoryOfPlays factory = new FactoryOfCapturingsForPiece(play.getDestination(), board);
			
			while (!factory.isEmpty()){
				play = black.nextCapture(play.getDestination());
				play.execute();
				factory = new FactoryOfCapturingsForPiece(play.getDestination(), board);
				log.setMessage(play.toString());
				turnChanger.eatIncrement();
			} 
		}
	}
	
	public void setVisible(boolean isVisible) {
		super.setVisible(isVisible);
		log.setVisible(isVisible);
		
	}
	
	public void restart (){
		this.log.setVisible(false);
		this.setVisible(false);
		getContentPane().removeAll();
		this.board=new Board();
		black=new Cpu(Board.BLACK,board);
		turnChanger = new TurnChanger(new Player(Board.WHITE,board),black);
		this.log=new LogWindow();
		showBoard(true);
		this.log.setVisible(true);
		this.setVisible(true);	
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.setVisible(true);
		game.setResizable(false);
		
	}

}
