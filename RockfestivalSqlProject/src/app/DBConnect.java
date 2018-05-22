package app;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
/**
 * Handles the connection with the database, uses JDBC Api
 * @author Jens Andreassen
 *
 */
public class DBConnect {
	private Connection connection;
	private Statement statement;
	static final String JDBC_DRIVER = "org.postgres.Driver";  
	static final String DB_URL = "jdbc:postgresql://localhost/postgres";
	static final String USER = "postgres";
	static final String PASS = "psql";
	/**
	 * Opens a connection woth the databse
	 */
	private void connect(){
		try{
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
		}catch(Exception e){
			System.err.println(e.getClass().getName()+": "+e.getMessage());
		}
	}
	/**
	 * closes the connection with the database
	 */
	private void close() {
		try {
			if(!statement.isClosed()) {
				statement.close();
			}
			if(!connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * adds new band to database
	 * @param band
	 * @param land
	 * @param info
	 * @return
	 */
	public boolean addBand(String band, String land, String info){
		connect();
		try{
			statement = connection.createStatement();
			statement.execute("insert into band(band_namn, ursprungsland, information)  "
					+ "VALUES ('"+ band + "', '"+ land + "', '" + info + "');");
		}catch(Exception e){
			e.printStackTrace();
			close();
			return false;
		}
		close();
		return true;
	}
	/**
	 * adds bandcontact to databse
	 * @param band
	 * @param contact
	 * @return
	 */
	public boolean addContact(String band, String contact){
		connect();
		try{
			statement = connection.createStatement();
			statement.execute("update band set kontaktperson=(select personnummer from funktionar where namn='"+ contact +"') where band_namn='" + band +"';");
		}catch(Exception e){
			e.printStackTrace();
			close();
			return false;
		}
		close();
		return true;
	}
	/**
	 * adds show to dabase
	 * @param band
	 * @param scen
	 * @param start
	 * @param end
	 * @return
	 */
	public boolean addShow(String band, String scen, Date start, Date end){
		Timestamp startStamp = new Timestamp(start.getTime()),
					endStamp = new Timestamp(end.getTime());
		connect();
		try{
			statement = connection.createStatement();
			statement.execute("insert into spelning(scen_id, band_id, start_tid, slut_tid) " + 
					"select scen.scen_id, band.band_id, '" + startStamp + "', '" + endStamp + "' " + 
					"from scen, band " + 
					"where scen.scen_namn='" + scen + "' " + 
					"and band.band_namn='" + band + "';");
		}catch(Exception e){
			System.out.println("Fel");
			e.printStackTrace();
			close();
			return false;
		}
		close();
		return true;
	}
	/**
	 * Gets play scheme from database
	 * @return
	 */
	public ArrayList<Object[]> getSchema() {
		ArrayList<Object[]> arr = new ArrayList<Object[]>();
		connect();
		try{
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select band.band_namn, scen.scen_namn, spelning.start_tid, spelning.slut_tid \r\n" + 
					"from band \r\n" + 
					"INNER JOIN spelning on band.band_id=spelning.band_id \r\n" + 
					"INNER JOIN scen on scen.scen_id=spelning.scen_id \r\n" + 
					"ORDER BY DATE(spelning.start_tid), spelning.start_tid, scen.scen_namn;");
			while(rs.next()){
				Object[] objarr = new Object[4];
				objarr[0] = rs.getString(1);//Band
				objarr[1] = rs.getString(2);//Scen
				objarr[2] = rs.getTimestamp(3);//Start
				objarr[3] = rs.getTimestamp(4);//Slut
				arr.add(objarr);
			}
		}catch(Exception e){
			e.printStackTrace();
			close();
		}
		close();
		return arr;
	}
	/**
	 * gets the bandinfo from the database
	 * @param band
	 * @return
	 */
	public String getBandInfo(String band) {
		connect();
		String info = "";
		try{
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT information FROM band where band_namn ='" + band + "';");
			while(rs.next()){
				info = rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		close();
		return info;
	}
	/**
	 * gets the list of possible stages or bands depending on input
	 * @param stage or band
	 * @return String[] containing band or stage-names
	 */
	public String[] getLists(String value) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("V�lj h�r");
		connect();
		try{
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT " + value + "_namn FROM " + value +";");
			while(rs.next()){
				list.add(rs.getString(1));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		close();
		return list.toArray(new String[list.size()]);
	}
}
