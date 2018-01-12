package application;
import org.sqlite.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * 
 * @author tramog01
 *Class that searches the database with whatever filters the user requires.
 */

public class HousesSearch{
	private static ArrayList<HouseSale> houseSales = new ArrayList<HouseSale>();
	/**
	 * Adds the next result in the ResultSet housesData to the housesSales array list
	 * @param housesData an arraylist of houseSales
	 * @throws SQLException
	 */
	public static void buildHouseArray(ResultSet housesData) throws SQLException {
			HouseSale h = new HouseSale();
			h.setId(housesData.getString("id"));
			h.setPrice(housesData.getInt("price"));
			h.setDate(housesData.getString("sale_date"));
			h.setPostcode(housesData.getString("postcode"));
			h.setPropType(housesData.getString("prop_type").charAt(0));
			h.setNewBuild(housesData.getString("newbuild").charAt(0));
			h.setLeaseType(housesData.getString("leasetype").charAt(0));
			h.setPaon(housesData.getString("paon"));
			h.setSaon(housesData.getString("saon"));
			h.setStreet(housesData.getString("street"));
			h.setLocality(housesData.getString("locality"));
			h.setTown(housesData.getString("town"));
			h.setDistrict(housesData.getString("district"));
			h.setCounty(housesData.getString("county"));
			h.setCategory(housesData.getString("category").charAt(0));
			h.setStatus(housesData.getString("status").charAt(0));

			houseSales.add(h);
	}
	
	/**
	 * Runs a SQL query against the database that returns all columns where the postcode for each row is like the searchTerm passed to it
	 * @param searchTerm the postcode (or partial postcode) you want to search for 
	 * @return ResultSet housesData, a result set for the database search results
	 */
	public static ResultSet getResultsFromDB(String searchTerm) {
		houseSales.clear();
		Connection c = null;
		ResultSet housesData = null;
		try {
			//Connect to db, uses whatever path the user entered when they opened the program
			c = DriverManager.getConnection("jdbc:sqlite:" + Index.path.getPath());

			//Run query, selecting all the columns from houseSales, using a prepared statement to match the searchTerm to each rows postcode
			PreparedStatement s = c.prepareStatement("SELECT * FROM sales WHERE postcode LIKE ?");
			s.setString(1, searchTerm + "%");
			housesData = s.executeQuery();
		}
		catch (SQLException se) {
			se.printStackTrace();
		}
		
		return housesData;
	}
	/**
	 * Searches the database without doing any further filtering than on a postcode
	 * @param searchTerm the postcode (or partial postcode) you want to search for
	 * @return ArrayList of house sales
	 * @throws SQLException
	 */
	public static ArrayList<HouseSale> noFilterSearch(String searchTerm) throws SQLException {
		ResultSet housesData= getResultsFromDB(searchTerm);
		
		while(housesData.next()) {
			buildHouseArray(housesData);
		}
		return houseSales;		
	}
	
	/**
	 * Searches the database, gets all the results for the searchTerm, but only adds it to the arrayList if the price is less than or equal to the maxPrice passed through
	 * @param searchTerm the postcode (or partial postcode) you want to search for
	 * @param maxPrice the max price of sales that will be filtered by
	 * @return ArrayList of house sales
	 * @throws SQLException
	 */
	public static ArrayList<HouseSale> maxPriceSearch(String searchTerm, int maxPrice) throws SQLException {
		ResultSet housesData= getResultsFromDB(searchTerm);
		
		while(housesData.next()) {
			if(housesData.getInt("price") <= maxPrice) {
				buildHouseArray(housesData);				
			}
		}

		return houseSales;		
	}
	
	/**
	 * Searches the database, gets all the results for the searchTerm, but only adds it to the arrayList if the price is more than or equal to the minPrice passed through
	 * @param searchTerm the postcode (or partial postcode) you want to search for
	 * @param minPrice the minimum price of sales that will be filtered by
	 * @return ArrayList of house sales
	 * @throws SQLException
	 */
	public static ArrayList<HouseSale> minPriceSearch(String searchTerm, int minPrice) throws SQLException {
		ResultSet housesData= getResultsFromDB(searchTerm);
		
		while(housesData.next()) {
			if(housesData.getInt("price") >= minPrice) {
				buildHouseArray(housesData);				
			}
		}

		return houseSales;		
	}
	/**
	 * Searches the database, gets all the results for the searchTerm, but only adds it to the arrayList if the paon is the same as the one passed through
	 * @param searchTerm the postcode (or partial postcode) you want to search for
	 * @param filterPaon the paon you want to filter by
	 * @return ArrayList of house sales
	 * @throws SQLException
	 */
	public static ArrayList<HouseSale> houseNoSearch(String searchTerm, String filterPaon) throws SQLException {
		ResultSet housesData= getResultsFromDB(searchTerm);
		
		while(housesData.next()) {
			if(housesData.getString("paon").equals(filterPaon)) {
				buildHouseArray(housesData);				
			}
		}

		return houseSales;		
	}


}
