package ca.ece.ubc.cpen221.mp5;

public class Coordinate {
	private double latitude;
	private double longitude;

	public Coordinate(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public double Long() {
		return longitude;
	}

	public double Lat() {
		return latitude;
	}

	/*
	 * K-means clustering
	 * 
	 * 1. initial clusters 2. first center points 3.
	 * abs(sqrt(deltaLat^2+deltaLong^2)) = distance between a point to the center 4.
	 * put all points that are closest to that center into a 'cluster' 5. find new
	 * center 6. check if there are points that are closer to the other centers
	 * present 7. move points to the closest cluster center 8. re-configure the
	 * center.
	 * 
	 */
}
