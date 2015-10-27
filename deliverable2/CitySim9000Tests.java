import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class CitySim9000Tests {
	
	@Mock
	CitySim9000 cs9000Mock = Mockito.mock(CitySim9000.class);
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(cs9000Mock);
	}

	@After
	public void tearDown() throws Exception {
	}

	//Starting city location chooses 1 of 5 locations randomly
	//If 0 is the randomly selected number, Bookstore should be selected
	@Test
	public void testGetStartingLocationBookstore() {
		CitySim9000 cs9000 = new CitySim9000();
		Random mockRand = mock(Random.class);
		when(mockRand.nextInt(5)).thenReturn(0);
		String loc = cs9000.getStartingLocation(mockRand);
		assertEquals(loc, "Bookstore");
	}
	
	//Starting city location chooses 1 of 5 locations randomly
	//If 4 is the randomly selected number, Outside City should be selected
	@Test
	public void testGetStartingLocationOutsideCity() {
		CitySim9000 cs9000 = new CitySim9000();
		Random mockRand = mock(Random.class);
		when(mockRand.nextInt(5)).thenReturn(4);
		String loc = cs9000.getStartingLocation(mockRand);
		assertEquals(loc, "Outside City");
	}
	
	//Starting city location chooses 1 of 5 locations randomly
	//If the random number isn't 0-4, "" should be returned and the program should exit
	@Test
	public void testGetStartingLocationBad() {
		CitySim9000 cs9000 = new CitySim9000();
		Random mockRand = mock(Random.class);
		when(mockRand.nextInt(5)).thenReturn(5);
		String loc = cs9000.getStartingLocation(mockRand);
		assertEquals(loc, "");
	}
	
	//2 randoms created with the same seed should produce the same 1st number on a call to nextInt()
	@Test
	public void testCreateRandSameSeed(){
		Random rand = new Random(10);
		Random randTest = CitySim9000Driver.createRandom("10");
		assertEquals(rand.nextInt(), randTest.nextInt());
	}
	
	//createRandom should return null and exit if a non integer seed is passed via the cmd line
	@Test
	public void testCreateRandBadSeed(){
		Random randTest = CitySim9000Driver.createRandom("This is not an int");
		assertNull(randTest);
	}
	
	//city map should contain 5 locations as keys, check some are there 
	//check that some keys that should not be there are not
	@Test
	public void testCreateMapContainsKeys(){
		CitySim9000 cs9000 = new CitySim9000();
		HashMap<String, ArrayList<String>> cityMap = cs9000.createMap();
		assertTrue(cityMap.containsKey("Mall"));
		assertTrue(cityMap.containsKey("University"));
		assertFalse(cityMap.containsKey("OutsideCity"));
		assertFalse(cityMap.containsKey("Yo momma's house"));
	}
	
	//Each location has 2 other locations that can be reached from it by a certain street/avenue
	//Check that the streets and destinations from mall are correct
	@Test
	public void testCreateMapValues(){
		CitySim9000 cs9000 = new CitySim9000();
		HashMap<String, ArrayList<String>> cityMap = cs9000.createMap();
		ArrayList<String> mallExpected = new ArrayList<>();
		mallExpected.add("Fourth Ave," + "Bookstore"); mallExpected.add("Meow Street," + "Coffee Shop");
		ArrayList<String> mallActual = cityMap.get("Mall");
		assertArrayEquals(mallExpected.toArray(), mallActual.toArray());
	}
	
	//The drive should end when outside city is reached
	//This test forces outside city to be the first place to be found after initial location
	//Count should be 1, as simulation should end once outside city is reached
	@Test
	public void testSimulateDriverEnd(){
		CitySim9000 cs9000 = new CitySim9000();
		Random mockRand = mock(Random.class);
		when(mockRand.nextInt(2)).thenReturn(0);
		HashMap<String, ArrayList<String>> mockMap = mock(HashMap.class);
		ArrayList<String> listToReturn = new ArrayList<String>();
		listToReturn.add("Fifth Ave,Outside City");
		when(mockMap.get("Coffee Shop")).thenReturn(listToReturn);
		int count = -1;
		count = cs9000.simulateDriver(mockRand, 0, mockMap, "Coffee Shop");
		assertEquals(count, 1);
	}
	
	//The drive should not end if outside city is the starting location
	//Force outside city to be starting location and ensure count > 1
	//Run simulation 50 times to be sure
	@Test
	public void testSimulateDriverOutsideStart(){
		CitySim9000 cs9000 = new CitySim9000();
		Random rand = new Random();
		HashMap<String, ArrayList<String>> cityMap = cs9000.createMap();
		for(int k = 0; k < 50; k++){
			int count = cs9000.simulateDriver(rand, k, cityMap, "Outside City");
			assertTrue(count > 1);
		}
	}
	
	
	//test that fails
	//if no test fails, test status in jUnit window in Eclipse doesn't expand, this ensures it does
	@Test
	public void thisTestFails(){
		fail("I'm a failure :(");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
