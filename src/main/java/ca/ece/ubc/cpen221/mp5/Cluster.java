package ca.ece.ubc.cpen221.mp5;

import java.util.HashMap;
import java.util.Map;

public class Cluster {

	Map<String, Integer> clusters;

	public Cluster() {
		clusters = new HashMap<String, Integer>();
	}

	public void group(String business, Integer cluster) {
		clusters.put(business, cluster);
	}

	public void clear() {
		clusters.clear();
	}

	public Integer getCluster(String business) {
		return clusters.get(business);
	}

	public void findCenter() {

	}
}
