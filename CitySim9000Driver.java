import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class CitySim9000Driver {
	
	//accept 1 arg, integer seed
	public static void main(String[] args) {
		if(args.length != 1){
			System.err.println("Incorrect number of args. Expecting 1 integer");
			System.exit(0);
		}
		Random rand = createRandom(args[0]);
		if(rand == null) System.exit(0);
		CitySim9000 cs9000 = new CitySim9000();
		HashMap<String, ArrayList<String>> cityMap = cs9000.createMap();
		String startLoc = cs9000.getStartingLocation(rand);
		if("".equals(startLoc)) System.exit(0);
		for(int k = 0; k < 5; k++){
			cs9000.simulateDriver(rand, k, cityMap, startLoc);
			System.out.println("-----");
		}
	}
	
	public static Random createRandom(String seedString){
		int seed = 0;
		try{
			seed = Integer.parseInt(seedString);
		}
		catch(NumberFormatException e){
			e.printStackTrace();
			return null;
		}
		Random rand = new Random(seed);
		return rand;
	}

}
