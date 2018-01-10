package application;

public class House {
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
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date.substring(0,10);
	}
	public char getPropType() {
		return propType;
	}
	public void setPropType(char propType) {
		this.propType = propType;
	}
	public char getNewBuild() {
		return newBuild;
	}
	public void setNewBuild(char newBuild) {
		this.newBuild = newBuild;
	}
	public char getLeaseType() {
		return leaseType;
	}
	public void setLeaseType(char leaseType) {
		this.leaseType = leaseType;
	}
	public String getPaon() {
		return paon;
	}
	public void setPaon(String paon) {
		this.paon = paon;
	}
	public String getSaon() {
		return saon;
	}
	public void setSaon(String saon) {
		this.saon = saon;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public char getCategory() {
		return category;
	}
	public void setCategory(char category) {
		this.category = category;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
}
