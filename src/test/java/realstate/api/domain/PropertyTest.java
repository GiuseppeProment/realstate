package realstate.api.domain;

import org.junit.Test;

import realstate.api.domain.AttributeRangeException;
import realstate.api.domain.Property;

public class PropertyTest {

	@Test(expected=AttributeRangeException.class)
	public void CoordinateXMinIsZero() throws Exception {
		new Property().setX(-1);
	}

	@Test(expected=AttributeRangeException.class)
	public void CoordinateXMaxIsOneThousandFourHundred() throws Exception {
		new Property().setX(1401);
	}

	@Test(expected=AttributeRangeException.class)
	public void CoordinateYMinIsZero() throws Exception {
		new Property().setY(-1);
	}

	@Test(expected=AttributeRangeException.class)
	public void CoordinateYMaxIsOneThousand() throws Exception {
		new Property().setY(1001);
	}
	
	@Test(expected=AttributeRangeException.class)
	public void bedsMaxIsFive() throws Exception {
		new Property().setBeds(10);
	}

	@Test(expected=AttributeRangeException.class)
	public void bedsMinIsOne() throws Exception {
		new Property().setBeds(0);
	}
	
	@Test(expected=AttributeRangeException.class)
	public void bathsMaxIsFour() throws Exception {
		new Property().setBaths(10);
	}

	@Test(expected=AttributeRangeException.class)
	public void bathsMinIsOne() throws Exception {
		new Property().setBaths(0);
	}

	@Test(expected=AttributeRangeException.class)
	public void squareMetersMinIsTwenty() throws Exception {
		new Property().setSquareMeters(15);
	}

	@Test(expected=AttributeRangeException.class)
	public void squareMetersMaxIsTwoHundredForty() throws Exception {
		new Property().setSquareMeters(250);
	}

}
