package application;
import org.sqlite.*;
import java.sql.*;
import java.util.ArrayList;

public class HousesSearch{
	private static ArrayList<House> houses = new ArrayList<House>();
	
	public static void buildHouseArray(ResultSet housesData) throws SQLException {
			House h = new House();
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

			houses.add(h);
	}
	
	
	public static ResultSet getResultsFromDB(String searchTerm) {
		houses.clear();
		Connection c = null;
		ResultSet housesData = null;
		try {
			//connect to db
			c = DriverManager.getConnection("jdbc:sqlite:" + Index.path.getPath());

			//run query
			PreparedStatement s = c.prepareStatement("SELECT * FROM houses WHERE postcode LIKE ?");
			s.setString(1, searchTerm);
			housesData = s.executeQuery();
		}
		catch (SQLException se) {
			se.printStackTrace();
		}
		
		return housesData;
	}
	
	public static ArrayList<House> noFilterSearch(String searchTerm) throws SQLException {
		ResultSet housesData= getResultsFromDB(searchTerm);
		
		while(housesData.next()) {
			buildHouseArray(housesData);
		}
		return houses;		
	}
	
	public static ArrayList<House> maxPriceSearch(String searchTerm, int maxPrice) throws SQLException {
		ResultSet housesData= getResultsFromDB(searchTerm);
		
		while(housesData.next()) {
			if(housesData.getInt("price") <= maxPrice) {
				buildHouseArray(housesData);				
			}
		}

		return houses;		
	}
	
	
	public static ArrayList<House> minPriceSearch(String searchTerm, int minPrice) throws SQLException {
		ResultSet housesData= getResultsFromDB(searchTerm);
		
		while(housesData.next()) {
			if(housesData.getInt("price") >= minPrice) {
				buildHouseArray(housesData);				
			}
		}

		return houses;		
	}
	
	public static ArrayList<House> houseNoSearch(String searchTerm, String houseNumber) throws SQLException {
		ResultSet housesData= getResultsFromDB(searchTerm);
		
		while(housesData.next()) {
			if(housesData.getString("paon").equals(houseNumber)) {
				buildHouseArray(housesData);				
			}
		}

		return houses;		
	}


}
