package realstate.api.domain;

import static org.junit.Assert.*;

import org.junit.Before;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import realstate.api.domain.Boundarie;
import realstate.api.domain.Coordinate;

public class BoundarieTest {

	private Boundarie boundarie;

	@Before
	public void initialize() {
		Coordinate upperLeft = new Coordinate(0,10);
		Coordinate bottomRight = new Coordinate(10,0);
		boundarie = new Boundarie(upperLeft,bottomRight);
	}
	
	@Test
	public void contains() {
		assertThat( boundarie.contains(0, 0), is(true) );
	}

	@Test
	public void notContains() {
		assertThat( boundarie.contains(11, 0), is(false) );
	}
}
