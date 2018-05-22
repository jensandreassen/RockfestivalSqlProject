package app;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JButton;
/**
 * Shows a window for the user, used to navigate through the application
 * @author Jens Andreassen
 *
 */
public class GuiStart {
	private JFrame frame;
	private Controller controller;
	private JButton btnVisitor;
	private JButton btnAdmin;
	
	/**
	 * Create the window/application
	 * @param controller - for communication with controller
	 */
	
	public GuiStart(Controller controller) {
		this.controller = controller;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ButtonListener bl = new ButtonListener();
		frame = new JFrame();
		frame.setVisible(true);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(0, 0, 801, 694);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblVlkommenTillRockfestival = new JLabel("VäLKOMMEN TILL ROCKFESTIVAL! ");
		lblVlkommenTillRockfestival.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblVlkommenTillRockfestival.setHorizontalAlignment(SwingConstants.CENTER);
		lblVlkommenTillRockfestival.setBounds(157, 98, 489, 74);
		frame.getContentPane().add(lblVlkommenTillRockfestival);
		
		JLabel lblrDuBeskare = new JLabel("är du besökare eller administratör?");
		lblrDuBeskare.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblrDuBeskare.setHorizontalAlignment(SwingConstants.CENTER);
		lblrDuBeskare.setBounds(252, 240, 270, 16);
		frame.getContentPane().add(lblrDuBeskare);
		
		btnVisitor = new JButton("Besökare");
		btnVisitor.setBounds(228, 322, 133, 61);
		frame.getContentPane().add(btnVisitor);
		
		btnAdmin = new JButton("Administratör");
		btnAdmin.setBounds(415, 322, 133, 61);
		frame.getContentPane().add(btnAdmin);
		
		btnVisitor.addActionListener(bl);
		btnAdmin.addActionListener(bl);
	}
	/**
	 * ActionListener for the buttons in the Gui
	 */
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnAdmin) {
				controller.startNewWindow(false);
			} else if(e.getSource()==btnVisitor) {
				controller.startNewWindow(true);
			}
		}		
	}
}
