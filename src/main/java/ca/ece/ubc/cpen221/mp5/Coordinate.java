package ca.ece.ubc.cpen221.mp5;

public class Coordinate {
	/**
	 * @AF:latitude = the latitudinal coordinate of the point, which ranges from -90
	 *              degrees(90 south) and +90 degrees (90 north).
	 * 
	 *              longitude = the longitudinal coordinate of the point, which
	 *              ranges from -180 degrees(180 west) and +180 degrees (180 east)
	 * 
	 * @RI:the latitudinal coordinate cannot be null, and must be between -90 and
	 *         +90
	 * 
	 *         the longitudinal coordinate cannot be null, and must be between -180
	 *         and +180
	 */

	private double latitude;
	private double longitude;
	private static final double R = 6371 * 1000;// in meters

	/**
	 * Constructs a Coordinate pair from a latitude coordinate and a longitude
	 * coordinate
	 * 
	 * @param latitude
	 *            a double between -90 and +90
	 * @param longitude
	 *            a double between -180 and -180
	 */
	public Coordinate(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * Computes the Cartesian distance between two coordinate points
	 * 
	 * @param otherC
	 *            the coordinate point that this point is measuring the distance
	 *            between
	 * @return a double representing the distance between the two coordinate in
	 *         meters
	 */
	public double distance(Coordinate otherC) {
		double lat = this.Lat();
		double lat2 = otherC.Lat();
		double dLat = Math.toRadians(lat2 - lat);
		double dLon = Math.toRadians(otherC.Long() - this.Long());

		double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat) * Math.cos(lat2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return R * c;
	}

	/**
	 * 
	 * @return a double representing a longitude coordinate
	 */
	public double Long() {
		return longitude;
	}

	/**
	 * 
	 * @return a double representing a latitude coordinate
	 */
	public double Lat() {
		return latitude;
	}

}
