import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class CitySim9000 {
	
	private final int BOOKSTORE = 0, MALL = 1, COFFEE_SHOP = 2, UNIVERSITY = 3, OUTSIDE_CITY = 4;
	
	public CitySim9000(){
		//yo
	}
	
	//create and store city locations in a Map object
	//FUN-CITY-LOCS, FUN-OUTSIDE-CITY, FUN-AVENUES, FUN-STREETS
	public HashMap<String, ArrayList<String>> createMap(){
		//using a HashMap
		HashMap<String, ArrayList<String>> cityMap = new HashMap<>();
		
		//create destination lists
		ArrayList<String> mallDest = new ArrayList<>();
		mallDest.add("Fourth Ave," + "Bookstore"); mallDest.add("Meow Street," + "Coffee Shop");
		ArrayList<String> bookDest = new ArrayList<>();
		bookDest.add("Fourth Ave," + "Outside City"); bookDest.add("Chirp Street," + "University");
		ArrayList<String> univDest = new ArrayList<>();
		univDest.add("Fifth Ave," + "Coffee Shop"); univDest.add("Chirp Street," + "Bookstore");
		ArrayList<String> coffeeDest = new ArrayList<>();
		coffeeDest.add("Fifth Ave," + "Outside City"); coffeeDest.add("Meow Street," + "Mall");
		ArrayList<String> outsideDest = new ArrayList<>();
		outsideDest.add("Fourth Ave," + "Mall"); bookDest.add("Fifth Ave," + "University");
		
		//put origin and possible destinations into HashMap
		cityMap.put("Mall", mallDest);
		cityMap.put("Bookstore", bookDest);
		cityMap.put("University", univDest);
		cityMap.put("Coffee Shop", coffeeDest);
		cityMap.put("Outside City", outsideDest);
		
		return cityMap;
	}
	
	//pick starting location, as required by FUN-START-LOC
	public String getStartingLocation(Random rand){
		String start = "";
		
		int startInt = rand.nextInt(5);
		
		if(startInt == BOOKSTORE) start = "Bookstore";
		else if(startInt == MALL) start = "Mall";
		else if(startInt == COFFEE_SHOP) start = "Coffee Shop";
		else if(startInt == UNIVERSITY) start = "University";
		else if(startInt == OUTSIDE_CITY) start = "Outside City";
		
		return start;
	}
	
	private boolean isEnd(String location){
		if("Outside City".equals(location)) return true;
		return false;
	}
	
	
	
	public void simulateDriver(Random rand, int driverNum, HashMap<String, ArrayList<String>> cityMap, String startLoc){
		String currentLoc = startLoc;
		String nextLoc = "";
		String street = "";
		int nextLocInt = 0;
		if("Outside City".equals(currentLoc)){
			nextLocInt = rand.nextInt(cityMap.get(currentLoc).size());
			String[] streetAndLoc = cityMap.get(currentLoc).get(nextLocInt).split(",");
			street = streetAndLoc[0];
			nextLoc = streetAndLoc[1];
			System.out.printf("Driver %d heading from %s to %s via %s.", driverNum, currentLoc, nextLoc, street);
			currentLoc = nextLoc;
		}
		while(!isEnd(currentLoc)){
			nextLocInt = rand.nextInt(cityMap.get(currentLoc).size());
			String[] streetAndLoc = cityMap.get(currentLoc).get(nextLocInt).split(",");
			street = streetAndLoc[0];
			nextLoc = streetAndLoc[1];
			System.out.printf("Driver %d heading from %s to %s via %s.\n", driverNum, currentLoc, nextLoc, street);
			if("Outside City".equals(nextLoc)){
				System.out.printf("Driver %d has left the city!\n", driverNum);
			}
			currentLoc = nextLoc;
		}
		
	}
	
}
