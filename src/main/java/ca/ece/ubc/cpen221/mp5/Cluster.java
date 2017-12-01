package ca.ece.ubc.cpen221.mp5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Cluster {

	Set<Business> businesses;
	Coordinate center;

	double startLat = Math.random();
	double startLong = Math.random();

	public Cluster() {
		businesses = new HashSet<Business>();
	}

	public Cluster(List<Business> businesses) {
		this.businesses = new HashSet<Business>();
		this.businesses.addAll(businesses);
		center = new Coordinate(startLat, startLong);
	}

	public Cluster(Business business) {
		businesses.add(business);
	}

	public void add(Business business) {
		this.businesses.add(business);
	}

	public Set<Business> getBusinesses() {
		return new HashSet<Business>(businesses);
	}

	public void remove(Business business) {
		this.businesses.remove(business);
	}

	public void clear() {
		businesses.clear();
	}

	public boolean contains(Business b) {
		return (this.businesses.contains(b));
	}

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
