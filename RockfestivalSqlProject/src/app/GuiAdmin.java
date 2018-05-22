package app;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import java.awt.Color;
/**
 * Shows a window for the user from a Admin-perspective, used to navigate through the 
 * application and make requests and provide input to the database.
 * @author Jens Andreassen
 *
 */
public class GuiAdmin {

	private JFrame frame;
	private JTextField txtBokaNamn;
	private JTextField txtBokaLand;
	private JTextField txtBokaInfo;
	private JComboBox<?> txtKontaktBand;
	private JTextField txtKontaktPerson;
	private JComboBox<?> txtSpelNamn;
	private JComboBox<?> txtSpelScen;
	private JButton btnBokaBand;
	private JButton btnKontakt;
	private JButton btnSpelning;
	private JSpinner spinnerStart;
	private JSpinner spinnerSlut;
	private Controller controller;
	private JButton btnBack;
	private String[] bands;
	private String[] scener;
	


	/**
	 * Create the window/application
	 * @param controller - for communication with controller
	 * @param scener - array of strings representing the stages to fill roll-down lists
	 * @param bands - array of strings representing the bands to fill roll-down lists
	 */
	public GuiAdmin(Controller controller, String[] bands, String[] scener) {
		this.controller = controller;
		this.bands = bands;
		this.scener = scener;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ButtonListener bl = new ButtonListener();
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setVisible(true);
		frame.setBounds(0, 0, 801, 694);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnBokaBand = new JButton("Lägg till");
		btnBokaBand.setBounds(633, 107, 97, 25);
		frame.getContentPane().add(btnBokaBand);
		
		btnKontakt = new JButton("Lägg till");
		btnKontakt.setBounds(633, 335, 97, 25);
		frame.getContentPane().add(btnKontakt);
		
		btnSpelning = new JButton("Lägg till");
		btnSpelning.setBounds(633, 497, 97, 25);
		frame.getContentPane().add(btnSpelning);
		
		JLabel lblBokaBand = new JLabel("Boka band");
		lblBokaBand.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBokaBand.setBounds(73, 59, 199, 25);
		frame.getContentPane().add(lblBokaBand);
		
		JLabel lblLggTillKontakt = new JLabel("Lägg till kontakt");
		lblLggTillKontakt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLggTillKontakt.setBounds(87, 282, 199, 25);
		frame.getContentPane().add(lblLggTillKontakt);
		
		JLabel lblLggTillSpelning = new JLabel("Lägg till spelning");
		lblLggTillSpelning.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLggTillSpelning.setBounds(87, 433, 199, 25);
		frame.getContentPane().add(lblLggTillSpelning);
		
		txtBokaNamn = new JTextField();
		txtBokaNamn.setText("Namn");
		txtBokaNamn.setBounds(73, 108, 116, 22);
		frame.getContentPane().add(txtBokaNamn);
		txtBokaNamn.setColumns(10);
		
		txtBokaLand = new JTextField();
		txtBokaLand.setText("Ursprungsland");
		txtBokaLand.setBounds(201, 108, 116, 22);
		frame.getContentPane().add(txtBokaLand);
		txtBokaLand.setColumns(10);
		
		txtBokaInfo = new JTextField();
		txtBokaInfo.setHorizontalAlignment(SwingConstants.LEFT);
		txtBokaInfo.setText("Information");
		txtBokaInfo.setBounds(324, 107, 279, 82);
		frame.getContentPane().add(txtBokaInfo);
		txtBokaInfo.setColumns(10);
		
		txtKontaktBand = new JComboBox<Object>(bands);
		txtKontaktBand.setSelectedIndex(0);
		txtKontaktBand.setBounds(73, 336, 116, 22);
		frame.getContentPane().add(txtKontaktBand);
		
		
		txtKontaktPerson = new JTextField();
		txtKontaktPerson.setText("Kontaktperson");
		txtKontaktPerson.setBounds(201, 336, 116, 22);
		frame.getContentPane().add(txtKontaktPerson);
		txtKontaktPerson.setColumns(10);
		
		txtSpelNamn = new JComboBox<Object>(bands);
		txtSpelNamn.setSelectedIndex(0);
		txtSpelNamn.setBounds(73, 498, 116, 22);
		frame.getContentPane().add(txtSpelNamn);
		
		
		txtSpelScen = new JComboBox<Object>(scener);
		txtSpelScen.setSelectedIndex(0);
		txtSpelScen.setBounds(201, 498, 116, 22);
		frame.getContentPane().add(txtSpelScen);
		
		JLabel lblStart = new JLabel("Start:");
		lblStart.setBounds(332, 480, 56, 16);
		frame.getContentPane().add(lblStart);
		
		spinnerStart = new JSpinner();
		spinnerStart.setModel(new SpinnerDateModel(new Date(1508432400000L), new Date(1508432400000L), new Date(1508623200000L),Calendar.HOUR ));
		spinnerStart.setBounds(332, 498, 134, 22);
		frame.getContentPane().add(spinnerStart);
		
		spinnerSlut = new JSpinner();
		spinnerSlut.setModel(new SpinnerDateModel(new Date(1508432400000L), new Date(1508432400000L), new Date(1508623200000L), Calendar.HOUR));
		spinnerSlut.setBounds(487, 498, 116, 22);
		frame.getContentPane().add(spinnerSlut);
		
		btnBack = new JButton("Bakåt");
		btnBack.setBounds(12, 609, 97, 25);
		frame.getContentPane().add(btnBack);
		
		btnBokaBand.addActionListener(bl);
		btnKontakt.addActionListener(bl);
		btnSpelning.addActionListener(bl);
		btnBack.addActionListener(bl);
	}
	/**
	 * Shows output to the user
	 */
	public void confirm(boolean b){
		if(b){
			JOptionPane.showMessageDialog(null, "Tillagt!");
		} else {
			JOptionPane.showMessageDialog(null, "Något gick fel, prova igen");
		}
	}
	/**
	 * ActionListener for the buttons in the Gui
	 */
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnBokaBand) {
				confirm(controller.addBand(txtBokaNamn.getText(),txtBokaLand.getText(), txtBokaInfo.getText() ));
			} else if(e.getSource()==btnKontakt) {
				if(txtKontaktBand.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null, "Du måste välja ett band");
				} else {
					confirm(controller.addContact((String)txtKontaktBand.getSelectedItem(), txtKontaktPerson.getText()));
				}
			} else if(e.getSource()==btnSpelning) {
				if(txtSpelNamn.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null, "Du måste välja ett band");
				} else if(txtSpelScen.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null, "Du måste välja en scen");
				} else {
					confirm(controller.addShow((String)txtSpelNamn.getSelectedItem(), (String)txtSpelScen.getSelectedItem(), (Date)spinnerStart.getValue(),(Date)spinnerSlut.getValue()));
				}
			}  else if(e.getSource()==btnBack) {
				frame.dispose();
			}
		}		
	}
}
