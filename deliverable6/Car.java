package deliverable6;

public class Car {
	private int num;
	private final Location STARTING_LOCATION;
	private Location currentLocation;
	
	public Car(int n){
		num = n;
		LocName start = LocName.randomLocation();
		STARTING_LOCATION = new Location(start);
		currentLocation = STARTING_LOCATION;
	}
	
	//travels randomly down either ave or street
	//returns true if still traveling, false if done
	public boolean travel(int next){		
		//make sure outside city starters don't try to go down a street
		if(currentLocation.getName()==LocName.OUTSIDE_CITY) next = 0;
		
		//driving down an ave!
		if(next == 0){
			System.out.println("Driver " + num + " heading from " + currentLocation.getName() +
					" to " + currentLocation.getOnAve() + " via " + currentLocation.getAve());
			currentLocation = new Location(currentLocation.getOnAve());
		}
		
		//driving down a street!
		else if(next == 1){
			System.out.println("Driver " + num + " heading from " + currentLocation.getName() +
					" to " + currentLocation.getOnStreet() + " via " + currentLocation.getStreet());
			currentLocation = new Location(currentLocation.getOnStreet());
		}
		
		//driving off the grid!
		else{
			System.out.println("It seems you've taken a wrong turn somewhere...");
		}
		
		return !this.hasLeft();
	}
	
	public boolean hasLeft(){
		if(currentLocation.getName() == LocName.OUTSIDE_CITY){
			System.out.println("Driver " + num + " has left the city!");
			return true;
		}
		else return false;
	}
	
	public int getNum() {
		return num;
	}
	
	public Location getStartingLocation() {
		return STARTING_LOCATION;
	}
	
	public Location getCurrentLocation() {
		return currentLocation;
	}
	
	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}
	
	
}
