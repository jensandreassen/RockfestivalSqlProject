package app;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
/**
 * Acts as a Controller for the application. Handles small logic and the communication
 * between the gui-classes and the database-connector.
 * @author Jens Andreassen
 *
 */
public class Controller {
	private DBConnect connect;
	/**
	 * Constructor
	 */
	public Controller(){
		connect = new DBConnect();
		new GuiStart(this);
	}
	/**
	 * Gets new data regarding the bands and the stages then
	 * opens a gui-window depending on the parameter
	 * @param b - represents one of the two gui-classes
	 */
	public void startNewWindow(boolean b) {
		String[] bands = connect.getLists("band"),
				scener = connect.getLists("scen");
		bands[0] = "Välj Band";
		scener[0] = "Välj Scen"; 
		if(b){
			new GuiVisitor(this, bands);
		}else{
			new GuiAdmin(this, bands, scener);
		}
	}
	/**
	 * Fetches the scheme and works on it in regards to design
	 * @return scheme
	 */
	@SuppressWarnings("deprecation")
	public String getSchema() {
		int date = 0;
		String scheme = "Spelschema:\n";
		ArrayList<Object[]> arr = connect.getSchema();
		for(int i=0; i<arr.size();i++) {
			Object[] obj = arr.get(i);
			Timestamp start = (Timestamp) obj[2], stop = (Timestamp)obj[3];
			String name = (String)obj[0];
			if(start.getDate()>date) {
				date = start.getDate();
				scheme += start.getDate() + "/" + start.getMonth() + ":\n";
			}
			if(name.length()<10) {
				obj[0] = name + "          ";
			}
			scheme += "\t" + obj[1] + ":    \t" +
					obj[0] + "\t" + "kl: " + 
					start.getHours() + " - " + 
					stop.getHours() + "\n";
		}
		return scheme;
	}
	/**
	 * Fetches and returns the information regarding the band
	 * @param band - the band to be used for the query
	 * @return information
	 */
	public String getBandInfo(String band) {
		return band + ":\n\n" + connect.getBandInfo(band);
	}
	/**
	 * Adds band to the database via the DBConnect-class
	 * @param band bandname
	 * @param land landname
	 * @param info information
	 * @return true/false depending on the outcome of the request
	 */
	public boolean addBand(String band, String land, String info) {
		return connect.addBand(band, land, info);
	}
	/**
	 * Adds contact to the database via the DBConnect-class
	 * @param band bandname
	 * @param contact contact
	 * @return true/false depending on the outcome of the request
	 */
	public boolean addContact(String band, String contact) {
		return connect.addContact(band, contact);
	}
	/**
	 * Adds show to the database via the DBConnect-class
	 * @param band bandname
	 * @param scen stagename
	 * @param start start of show
	 * @param slut end of show
	 * @return true/false depending on the outcome of the request
	 */
	public boolean addShow(String band, String scen, Date start, Date slut) {
		System.out.println(band + "   " + scen + "   " + start.toString() + "   " + slut.toString());
		return connect.addShow(band, scen, start, slut);
	}
	/**
	 * Starts the application
	 * @param args
	 */
	public static void main(String[] args) {
		new Controller();
	}
}
