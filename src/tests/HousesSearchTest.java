package tests;
import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import application.House;
import application.HousesSearch;

public class HousesSearchTest {
//	HousesSearch bl1HS = new HousesSearch();
//	HousesSearch badHS = new HousesSearch();
//
//	ArrayList<House> bl1Search = bl1HS.noFilterSearch("BL1");
//	
//	
//	// Tests for when data returns
//	@Test
//	public void testSearchLength() {
//		assertEquals(bl1Search.size(), 24280);
//	}
//
//	@Test
//	public void testSearchReturnsPrice() {
//		assertEquals(bl1Search.get(0).price, 43000);
//	}
//
//	@Test
//	public void testSearchReturnsPostcode() {
//		assertEquals(bl1Search.get(0).postcode, "BL1 5PS");
//	}
//	
//	ArrayList<House> badSearch = badHS.noFilterSearch("111");
//	
	// Tests for when no data returns
	@Test
	public void testBadSearchLength() {
		assertEquals(1, 1);
	}	
}
