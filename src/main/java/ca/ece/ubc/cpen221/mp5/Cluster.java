package ca.ece.ubc.cpen221.mp5;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Cluster {

	Map<Business, Integer> clusters;
	Set<Business> locations;

	double startLat = Math.random();
	double startLong = Math.random();

	public Cluster() {
		clusters = new HashMap<Business, Integer>();

	}

	public void group(Business business, Integer cluster) {
		clusters.put(business, cluster);
	}

	public void shortestDistance() {

	}

	public void clear() {
		clusters.clear();
	}

	public Integer getCluster(Coordinate business) {
		return clusters.get(business);
	}

	public void findCenter() {
		locations = new HashSet<Business>();
		locations = clusters.keySet();

		for (Business b : locations) {

		}
	}
}
