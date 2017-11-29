package ca.ece.ubc.cpen221.mp5;

import java.util.Set;

public class kMeansClusters {

	// private double deltaLong;
	// private double deltaLat;
	private Cluster cluster = new Cluster();
	private Coordinate startCoordinate;

	public kMeansClusters(int number, Set<Restaurant> restaurants) {
		double startLong = Math.random();
		double startLat = Math.random();
		startCoordinate = new Coordinate(startLong, startLat);
	}

	private void putGroups() {
		
	}

	// return List<Set<Restaurant>>

}
