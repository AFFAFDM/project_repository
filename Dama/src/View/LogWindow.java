package View;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

/**
 * Classe grafica che rappresenta la finestra che contiene lo storico delle mosse.
 */
public class LogWindow extends JFrame {

	private static final int HEIGHT = 600;
	private static final int WIDTH = 250;
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	private JList<String> list;
	

		public LogWindow() {
			super("Game History");
			setLayout(new FlowLayout());
			setSize(WIDTH, HEIGHT);
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			setResizable(false);
			list = new JList<String>(listModel);
			list.setLayoutOrientation(JList.VERTICAL);
			JScrollPane listScroller = new JScrollPane(list);
			listScroller.setPreferredSize(new Dimension(230, 550));
			add(listScroller);
			
		}	

		public void setMessage(String text) {
			listModel.addElement(text);
			this.invalidate();
			this.validate();
		}
		
}