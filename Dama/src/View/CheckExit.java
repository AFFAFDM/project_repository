package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Classe grafica che rappresenta la finestra di uscita.
 */
public class CheckExit extends JFrame {

public CheckExit() {
	super("Are you sure?");
	this.setResizable(false);
	this.setLayout(new BorderLayout());
	JLabel label=new JLabel("Do you really want to exit?");
	label.setHorizontalAlignment(SwingConstants.CENTER);
	this.add(label, BorderLayout.CENTER);
	JPanel yesNo = new JPanel();
	yesNo.setLayout(new FlowLayout());

	JButton yes = new JButton("Yes");
	yes.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}

	});

	yesNo.add(yes);
	JButton no= new JButton("No");
	no.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	});
	
	yesNo.add(no);
	this.add(yesNo, BorderLayout.SOUTH);
	
	this.setSize(300, 100);
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	this.setLocationRelativeTo(null);
	}
}