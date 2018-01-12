package application;

/**
 * 
 * @author tramog01
 * Class for an house sale
 */

public class HouseSale {
	public String id;
	public String postcode;
	public int price;
	public String date;
	public char propType;
	public char newBuild;
	public char leaseType;
	public String paon;
	public String saon;
	public String street;
	public String locality;
	public String town;
	public String district;
	public String county;
	public char category;
	public char status;
	
	/**
	 * Getter for the sales id
	 * @return
	 */
	public String getId() {
		return id;
	}
	/**
	 * Setter for the sales id
	 * @param
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * Getter for the house's postcode
	 * @return
	 */
	public String getPostcode() {
		return postcode;
	}
	/**
	 * Setter for the house's postcode
	 * @param postcode
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	/**
	 * Getter for the price of the house sale
	 * @return
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * Setter for the price of the house sale
	 * @param price
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	/**
	 * Getter for the date of the house sale
	 * @return
	 */
	public String getDate() {
		return date;
	}
	/**
	 * Getter that returns the year that the house sale took place in
	 * @return
	 */
	public int getYear() {
		String sellDate = getDate();
		return Integer.parseInt(sellDate.substring(0, 4));
	}
	/**
	 * Setter for the date of the house sale
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date.substring(0,10);
	}
	/**
	 * Getter for the house's property type
	 * @return
	 */
	public char getPropType() {
		return propType;
	}
	/**
	 * Setter for the house's property type
	 * @param propType
	 */
	public void setPropType(char propType) {
		this.propType = propType;
	}
	/**
	 * Getter for whether the house is a new build
	 * @return
	 */
	public char getNewBuild() {
		return newBuild;
	}
	/**
	 * Setter for whether the house is a new build
	 * @param newBuild
	 */
	public void setNewBuild(char newBuild) {
		this.newBuild = newBuild;
	}
	/**
	 * Getter for the house's lease type
	 * @return
	 */
	public char getLeaseType() {
		return leaseType;
	}
	/**
	 * Setter for the house's lease type
	 * @param leaseType
	 */
	public void setLeaseType(char leaseType) {
		this.leaseType = leaseType;
	}
	/**
	 * Getter for the house's Primary Addressable Object Name
	 * @return
	 */
	public String getPaon() {
		return paon;
	}
	/**
	 * Setter for the house's Primary Addressable Object Name
	 * @param paon
	 */
	public void setPaon(String paon) {
		this.paon = paon;
	}
	/**
	 * Getter for the house's Secondary Addressable Object Name
	 * @return
	 */
	public String getSaon() {
		return saon;
	}
	/**
	 * Setter for the house's Secondary Addressable Object Name
	 * @param saon
	 */
	public void setSaon(String saon) {
		this.saon = saon;
	}
	/**
	 * Getter for the street the house is on
	 * @return
	 */
	public String getStreet() {
		return street;
	}
	/**
	 * Setter for the street the house is on
	 * @param street
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/**
	 * Getter for the house's locality
	 * @return
	 */
	public String getLocality() {
		return locality;
	}
	/**
	 * Setter for the house's locality
	 * @param locality
	 */
	public void setLocality(String locality) {
		this.locality = locality;
	}
	/**
	 * Getter for the town the house is in
	 * @return
	 */
	public String getTown() {
		return town;
	}
	/**
	 * Setter for the town the house is in
	 * @param town
	 */
	public void setTown(String town) {
		this.town = town;
	}
	/**
	 * Getter for the district the house is in
	 * @return
	 */
	public String getDistrict() {
		return district;
	}
	/**
	 * Setter for the district the house is n
	 * @param district
	 */
	public void setDistrict(String district) {
		this.district = district;
	}
	/**
	 * Getter for the county the house is in
	 * @return
	 */
	public String getCounty() {
		return county;
	}
	/**
	 * Setter for the county the house is in
	 * @param county
	 */
	public void setCounty(String county) {
		this.county = county;
	}
	/**
	 * Getter for the house's category
	 * @return
	 */
	public char getCategory() {
		return category;
	}
	/**
	 * Setter for the houses category
	 * @param category
	 */
	public void setCategory(char category) {
		this.category = category;
	}
	/**
	 * Getter for the house's status
	 * @return
	 */
	public char getStatus() {
		return status;
	}
	/**
	 * Setter for the house's status
	 * @param status
	 */
	public void setStatus(char status) {
		this.status = status;
	}
}
