package deliverable6;

public class Location {
	private final LocName NAME;
	private LocName onAve;
	private RoadName ave;
	private LocName onStreet;
	private RoadName street;
	
	public Location(LocName n){
		NAME = n;
		this.connect();
	}
	
	private void connect() {
		if(NAME == LocName.BOOKSTORE){
			onAve = LocName.OUTSIDE_CITY;
			ave = RoadName.FOURTH_AVE;
			onStreet = LocName.UNIVERSITY;
			street = RoadName.CHIRP_ST;
		}
		else if(NAME == LocName.MALL){
			onAve = LocName.BOOKSTORE;
			ave = RoadName.FOURTH_AVE;
			onStreet = LocName.COFFEE;
			street = RoadName.MEOW_ST;
		}
		else if(NAME == LocName.COFFEE){
			onAve = LocName.OUTSIDE_CITY;
			ave = RoadName.FIFTH_AVE;
			onStreet = LocName.MALL;
			street = RoadName.MEOW_ST;
		}
		else if(NAME == LocName.UNIVERSITY){
			onAve = LocName.COFFEE;
			ave = RoadName.FIFTH_AVE;
			onStreet = LocName.BOOKSTORE;
			street = RoadName.CHIRP_ST;
		}
		else if(NAME == LocName.OUTSIDE_CITY){
			int next = CitySim10000.rand.nextInt(2);
			if(next == 0){
				onAve = LocName.MALL;
				ave = RoadName.FOURTH_AVE;
				onStreet = null;
				street = null;
			}
			else if(next == 1){
				onAve = LocName.UNIVERSITY;
				ave = RoadName.FIFTH_AVE;
				onStreet = null;
				street = null;
			}
			
		}
	}

	public LocName getName() {
		return NAME;
	}
	
	public LocName getOnAve() {
		return onAve;
	}
	
	public RoadName getAve() {
		return ave;
	}
	
	public LocName getOnStreet() {
		return onStreet;
	}

	public RoadName getStreet() {
		return street;
	}

}
