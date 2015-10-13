import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import java.util.Random;

public class CitySim9000Tests {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetStartingLocationBookstore() {
		CitySim9000 cs9000 = new CitySim9000();
		Random mockRand = mock(Random.class);
		when(mockRand.nextInt(5)).thenReturn(0);
		String loc = cs9000.getStartingLocation(mockRand);
		assertEquals(loc, "Bookstore");
	}
	
	@Test
	public void testGetStartingLocationOutsideCity() {
		CitySim9000 cs9000 = new CitySim9000();
		Random mockRand = mock(Random.class);
		when(mockRand.nextInt(5)).thenReturn(4);
		String loc = cs9000.getStartingLocation(mockRand);
		assertEquals(loc, "Outside City");
	}
	
	@Test
	public void testCreateRand(){
		Random rand = new Random(10);
		Random randTest = CitySim9000Driver.createRandom("10");
		assertEquals(rand.nextInt(), randTest.nextInt());
	}
	
	@Test
	public void testCreateRandBadSeed(){
		Random randTest = CitySim9000Driver.createRandom("This is not an int");
		assertNull(randTest);
	}
	
	@Test
	public void testCreateMap(){
		fail("Not created");
	}

}
