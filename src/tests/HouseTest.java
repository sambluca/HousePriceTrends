package tests;

import static org.junit.Assert.*;
import application.House;
import org.junit.Test;

public class HouseTest {

	@Test
	public void testIDSetter() {
		House h = new House();
		h.setId("123");
		assertEquals(h.getId(), "123");
	}

	@Test
	public void testPriceSetter() {
		House h = new House();
		h.setPrice(1000);
		assertEquals(h.getPrice(), 1000);
	}

}
