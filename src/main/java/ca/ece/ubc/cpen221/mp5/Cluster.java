package ca.ece.ubc.cpen221.mp5;

import java.util.HashMap;
import java.util.Map;

public class Cluster {

	Map<String, Integer> clusters;

	public Cluster() {
		clusters = new HashMap<String, Integer>();
	}
	
	public Integer getCluster(String business) {
		return clusters.get(business);
	}
}
