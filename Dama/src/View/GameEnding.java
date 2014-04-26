package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Classe grafica che rappresenta la finestra di fine partita.
 */
public class GameEnding extends JFrame{
	private static final ImageIcon victory=new ImageIcon ("images/victory.png");
	private static final ImageIcon defeat=new ImageIcon ("images/defeat.png");
	private final int WIDTH=600;
	private final int HEIGHT=300;
	
	public GameEnding(boolean youWin,final Game game){
		setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		JLabel label= new JLabel(youWin? victory :defeat);
		add(label,BorderLayout.CENTER);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		JButton quit = new JButton(" Quit ");
		this.setResizable(false);
		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

		});

		JButton restart = new JButton("Restart");
		restart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				game.restart();
				dispose();
			}

		});

		panel.add(quit);
		panel.add(restart);
		add(panel,BorderLayout.SOUTH);

	}
	
}
