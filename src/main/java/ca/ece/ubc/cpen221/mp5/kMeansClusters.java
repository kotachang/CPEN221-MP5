package ca.ece.ubc.cpen221.mp5;

public class kMeansClusters {

	public double deltaLong;
	public double deltaLat;

	public kMeansClusters() {

	}

	public kMeansClusters(double latitude, double latitude2, double longitude, double longitude2) {
		this.deltaLat = Math.abs(latitude - latitude2);
		this.deltaLong = Math.abs(longitude - longitude2);
	}

	public void firstPoints() {

	}
	// return List<Set<Restaurant>>

}
