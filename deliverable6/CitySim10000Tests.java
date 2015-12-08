package deliverable6;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class CitySim10000Tests {
	
	CitySim10000 cs10000;
	Random mockRand;
	@Before
	public void setUp() throws Exception {
		mockRand = mock(Random.class);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	//2 randoms created with the same seed should produce the same 1st number on a call to nextInt()
	@Test
	public void testCreateRandSameSeed(){
		Random rand = new Random(10);
		Random randTest = CitySim10000Driver.createRandom("10");
		assertEquals(rand.nextInt(), randTest.nextInt());
	}
	
	//createRandom should return null and exit if a non integer seed is passed via the cmd line
	@Test
	public void testCreateRandBadSeed(){
		Random randTest = CitySim10000Driver.createRandom("This is not an int");
		assertNull(randTest);
	}
	
	//make sure Random passed to CitySim10000 correctly
	@Test
	public void testCitySim10000Creation(){
		Random rand = new Random(10);
		Random rand2 = new Random(10);
		cs10000 = new CitySim10000(rand);
		assertEquals(rand2.nextInt(5), cs10000.getRand().nextInt(5));
	}
	
	//2 tests to test location creation, 1 at bookstore other at outside city
	@Test
	public void testLocationCreationBookstore(){
		Location loc = new Location(LocName.BOOKSTORE);
		assertEquals(loc.getName(), LocName.BOOKSTORE);
	}
	
	@Test
	public void testLocationCreationOutsideCity(){
		Location loc = new Location(LocName.OUTSIDE_CITY);
		assertEquals(loc.getName(), LocName.OUTSIDE_CITY);
	}
	
	//now a test to see if when a Mall location is created, its connections are set correctly
	@Test
	public void testLocationConnectionsMall(){
		Location loc = new Location(LocName.MALL);
		assertEquals(loc.getOnAve(), LocName.BOOKSTORE);
		assertEquals(loc.getOnStreet(), LocName.COFFEE);
		assertEquals(loc.getAve(), RoadName.FOURTH_AVE);
		assertEquals(loc.getStreet(), RoadName.MEOW_ST);
	}
	
	//now to test outside city as a location
	@Test
	public void testLocationConnectionsOutsideCity(){
		when(mockRand.nextInt(2)).thenReturn(0);		//forces outside city to be outside mall
		cs10000 = new CitySim10000(mockRand);
		Location loc = new Location(LocName.OUTSIDE_CITY);
		assertEquals(loc.getOnAve(), LocName.MALL);
		assertNull(loc.getOnStreet());
		assertEquals(loc.getAve(), RoadName.FOURTH_AVE);
		assertNull(loc.getStreet());
	}
	
	//create a Car object that is labeled with a number
	@Test
	public void testCarNum() {
		Random rand = new Random(10);
		cs10000 = new CitySim10000(rand);
		int x = 2;
		Car testCar = new Car(x);
		assertEquals(testCar.getNum(), x);
	}
	
	//testing that creating a car also gives it a starting location
	@Test
	public void testCarStartingLocation(){
		when(mockRand.nextInt(5)).thenReturn(0);		//car should start at mall
		cs10000 = new CitySim10000(mockRand);
		
		Car c = new Car(0);
		assertEquals(c.getStartingLocation().getName(), LocName.MALL);
	}
	
	//Locations are set up correctly and a car is assigned a starting location on creation
	//Now to test moving from one location to the next
	@Test
	public void testCarTravel(){		
		when(mockRand.nextInt(5)).thenReturn(0);		//car should start at mall
		cs10000 = new CitySim10000(mockRand);
		
		Car c = new Car(0);
		assertTrue(c.travel(1));						//car travels down street
		assertEquals(c.getCurrentLocation().getName(), LocName.COFFEE);
	}
	
	@Test
	public void testCarTravel2(){
		when(mockRand.nextInt(5)).thenReturn(4);		//car should start at outside city
		when(mockRand.nextInt(2)).thenReturn(1);
		cs10000 = new CitySim10000(mockRand);
		
		Car c = new Car(0);
		assertTrue(c.travel(0));						//car should travel down ave
		assertEquals(c.getCurrentLocation().getName(), LocName.UNIVERSITY);
	}
	
	//car can now travel, now need to test leaving the city
	//hasLeft should give true if car is in outside city, will only be called after 1st travel
	@Test
	public void testCarLeaveCity(){
		when(mockRand.nextInt(5)).thenReturn(4);		//car should start at outside city
		cs10000 = new CitySim10000(mockRand);
		
		Car c = new Car(0);
		assertTrue(c.hasLeft());						
	}
	
	//hasLeft should give false if car not in outside city
	@Test
	public void testCarNotLeaveCity(){
		when(mockRand.nextInt(5)).thenReturn(0);		//car should start at mall
		cs10000 = new CitySim10000(mockRand);
		
		Car c = new Car(0);
		assertFalse(c.hasLeft());
	}
	
	//now to have a car travel until it leaves
	@Test
	public void testCarFullTravel(){
		cs10000 = new CitySim10000(mockRand);
		
		Car c = new Car(0);
		boolean traveling = true;
		while(traveling){
			traveling = c.travel(mockRand.nextInt(2));
			if(c.getCurrentLocation().getName()==LocName.OUTSIDE_CITY){
				assertFalse(traveling);
			}
			else assertTrue(traveling);
		}
		assertTrue(c.hasLeft());
	}
	
	//cars can now travel and leave the city
	@Test
	public void testSimulation(){
		Car c = mock(Car.class);
		when(c.hasLeft()).thenReturn(true);
		cs10000 = new CitySim10000(mockRand);
		assertEquals(cs10000.simulate(), 5);
	}

}
