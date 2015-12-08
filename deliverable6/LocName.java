package deliverable6;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum LocName {
	MALL, BOOKSTORE, COFFEE, UNIVERSITY, OUTSIDE_CITY;
	
	private static final List<LocName> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();

	public static LocName randomLocation()  {
		LocName test = VALUES.get(CitySim10000.rand.nextInt(SIZE));
		return test;
	}
}
