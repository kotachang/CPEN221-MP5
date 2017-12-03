package ca.ece.ubc.cpen221.mp5;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @RI : there cannot be empty clusters when finding kMeansClustering, however
 *     empty cluster method exists for testing the clustering class in general.
 *     An empty cluster has a randomly assigned center
 * 
 * @AF : = a set of businesses as a 'cluster', center = average values of the
 *     coordinates of the businesses
 * 
 */
public class Cluster {

	Set<Business> businesses;
	Coordinate center;

	double startLat = Math.random();
	double startLong = Math.random();

	/**
	 * Empty constructor for a Cluster for testing
	 */
	public Cluster() {
		businesses = new HashSet<Business>();
		center = new Coordinate(startLat, startLong);
	}

	/**
	 * Constructs a cluster from a list of businesses
	 * 
	 * @param businesses
	 *            a list containing more than 1 business
	 */
	public Cluster(List<Business> businesses) {
		this.businesses = new HashSet<Business>();
		this.businesses.addAll(businesses);
		center = new Coordinate(startLat, startLong);
		findCenter();
	}

	/**
	 * Adds a business to the cluster
	 * 
	 * @param business
	 *            the business to add to the cluster
	 */
	public void add(Business business) {
		this.businesses.add(business);
		center = new Coordinate(startLat, startLong);
	}

	/**
	 * 
	 * @return a set of businesses contained in the cluster
	 */
	public Set<Business> getBusinesses() {
		return new HashSet<Business>(businesses);
	}

	/**
	 * Removes a business from the cluster
	 * 
	 * @param business
	 *            the business to remove from the cluster
	 */
	public void remove(Business business) {
		this.businesses.remove(business);
		center = new Coordinate(startLat, startLong);
		findCenter();
	}

	/**
	 * Removes all businesses from the cluster.
	 */
	public void clear() {
		businesses.clear();
	}

	/**
	 * 
	 * Checks if a business is contained in the cluster
	 * 
	 * @param b
	 *            the business
	 * 
	 * @return true if the business is in the cluster, false otherwise
	 */
	public boolean contains(Business b) {
		return (this.businesses.contains(b));
	}

	/**
	 * Finds the location of the center of this cluster of businesses
	 * 
	 * @return Coordinate point (in Long and Lat) of the centre of the businesses
	 */
	public Coordinate findCenter() {
		double sumLong = 0;
		double sumLat = 0;

		for (Business b : businesses) {
			sumLong += b.getCoordinates().Long();
			sumLat += b.getCoordinates().Lat();
		}

		center = new Coordinate(sumLat / businesses.size(), sumLong / businesses.size());
		return center;
	}

}
