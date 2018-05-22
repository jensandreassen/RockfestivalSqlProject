package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
/**
 * Shows a window for the user from a Visitor-perspective, used to navigate through the 
 * application and make requests to the database
 * @author Jens Andreassen
 *
 */
public class GuiVisitor {
	
	private JFrame frame;
	private JTextPane textFieldSchema;
	private JComboBox<?> txtBand;
	private JTextPane textFieldBandInfo;
	private JButton btnInfo;
	private Controller controller;
	private JButton btnBack;
	private String[] bands;

	/**
	 * Create the window/application
	 * @param controller - for communication with controller
	 * @param bands - array of strings representing the bands to fill roll-down lists
	 */
	public GuiVisitor(Controller controller, String[] bands) {
		this.controller = controller;
		this.bands = bands;
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
		frame.getContentPane().setLayout(null);
		
		textFieldSchema = new JTextPane();
		textFieldSchema.setBounds(71, 36, 642, 261);
		frame.getContentPane().add(textFieldSchema);
		textFieldSchema.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		btnInfo = new JButton("Visa Bandinfo");
		btnInfo.setBounds(71, 335, 146, 25);
		frame.getContentPane().add(btnInfo);
		
		txtBand = new JComboBox<Object>(bands);
		txtBand.setSelectedIndex(0);
		txtBand.setBounds(71, 374, 146, 22);
		frame.getContentPane().add(txtBand);
		
		textFieldBandInfo = new JTextPane();
		textFieldBandInfo.setBounds(265, 336, 442, 252);
		frame.getContentPane().add(textFieldBandInfo);
		textFieldBandInfo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		btnBack = new JButton("Bak\u00E5t");
		btnBack.setBounds(12, 609, 97, 25);
		frame.getContentPane().add(btnBack);
		
		frame.setBounds(0, 0, 801, 694);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		

		btnInfo.addActionListener(bl);
		btnBack.addActionListener(bl);
		
		textFieldSchema.setText(controller.getSchema());
	}
	/**
	 * ActionListener for the buttons in the Gui
	 */
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnInfo) {
				if(txtBand.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null, "Du m�ste v�lja ett band");
				} else {
					textFieldBandInfo.setText(controller.getBandInfo((String)txtBand.getSelectedItem()));
				}
			} else if(e.getSource()==btnBack) {
				frame.dispose();
			}
		}		
	}
}
