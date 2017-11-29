package ca.ece.ubc.cpen221.mp5;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Cluster {

	Map<Coordinate, Integer> clusters;
	Set<Coordinate> locations;

	public Cluster() {
		clusters = new HashMap<Coordinate, Integer>();
	}

	public void group(Coordinate business, Integer cluster) {
		clusters.put(business, cluster);
	}

	public void clear() {
		clusters.clear();
	}

	public Integer getCluster(Coordinate business) {
		return clusters.get(business);
	}

	public void findCenter() {
		locations = new HashSet<Coordinate>();
		locations = clusters.keySet();
		for (Coordinate c : locations) {

		}
	}
}
