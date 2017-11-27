package ca.ece.ubc.cpen221.mp5;

public class Coordinate {
	private double latitude;
	private double longitude;

	public Coordinate(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public double getLong() {
		return longitude;
	}

	public double getLat() {
		return latitude;
	}
}
