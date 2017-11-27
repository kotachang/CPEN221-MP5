package ca.ece.ubc.cpen221.mp5;

import java.util.HashMap;
import java.util.Map;

public class Location {
	public double longitude;
	public double latitude;
	public double deltaLong;
	public double deltaLat;
	

	public Location() {
		//coordinate = new HashMap<double, double>();
		//longitude = "";
		//latitude = "";
	}

	public Location(String retaurant, double longitude, double latitude) throws NullPointerException {
		coordinate = new HashMap<String, String>();
		this.longitude = longitude;
		this.latitude = latitude;
		coordinate.put(longitude, latitude);
	}

	public Map<String, String> getLocation() {
		return coordinate;
	}

	public void findCenter() {

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
