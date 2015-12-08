package deliverable6;

import java.util.Random;
import java.util.Scanner;

public class CitySim10000Driver {
	
	public static void main(String[] args) {
		if(args.length != 1){
			System.err.println("Incorrect number of args. Expecting 1 integer");
			System.exit(0);
		}
		Random rand = createRandom(args[0]);
		CitySim10000 cs10000 = new CitySim10000(rand);
		cs10000.simulate();
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
