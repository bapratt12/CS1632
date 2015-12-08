package deliverable6;

import java.util.Random;

public class CitySim10000 {
	
	protected static Random rand;
	
	public CitySim10000(Random r){
		rand = r;
		if(rand == null) System.exit(0);		
	}
	
	public int simulate(){
		int numCars = 5;
		int numDrives = 0;
		for(int k = 0; k < numCars; k++){
			Car driver = new Car(k);
			while(driver.travel(rand.nextInt(2))){
				numDrives++;
			}
			System.out.println();
		}
		System.out.println("\nAll drivers have left the city!");
		return numDrives;
	}

	public Random getRand() {
		return rand;
	}
	
	
}
