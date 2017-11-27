package ca.ece.ubc.cpen221.mp5;

import java.util.HashMap;
import java.util.Map;

public class Location {
	public String longitude;
	public String latitude;
	public int deltaLong;
	public int deltaLat;
	public Map<String, String> coordinate;

	public Location() {
		coordinate = new HashMap<String, String>();
		longitude = "";
		latitude = "";
	}

	public Location(String retaurant, String longitude, String latitude) throws NullPointerException {
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
