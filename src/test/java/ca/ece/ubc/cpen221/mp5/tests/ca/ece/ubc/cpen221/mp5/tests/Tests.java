package ca.ece.ubc.cpen221.mp5.tests.ca.ece.ubc.cpen221.mp5.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.Business;
import ca.ece.ubc.cpen221.mp5.Cluster;
import ca.ece.ubc.cpen221.mp5.Coordinate;
import ca.ece.ubc.cpen221.mp5.GeneralDb;
import ca.ece.ubc.cpen221.mp5.YelpDB;

public class Tests {

	@Test
	public void test0() throws IOException {
		Cluster c1 = new Cluster();
		Cluster c2 = new Cluster();
		Business b = new Business("McDonalds");
		c1.add(b);
		c2.add(b);
		String rest = "data/restaurants.json";
		String user = "data/users.json";
		String review = "data/reviews.json";
		YelpDB db = new YelpDB(rest, user, review);
		assertTrue(db.equals(c1, c2));
		assertTrue(c1.contains(b));
	}

	@Test
	public void test1() throws IOException {
		Coordinate c = new Coordinate(100, 100);
		Coordinate c2 = new Coordinate(120, 120);
		assertEquals((Double) c.distance(c2), (Double) 2911958.6039025364);
	}

	@Test
	public void test2() throws IOException{
		String rest = "data/restaurants.json";
		String user = "data/users.json";
		String review = "data/reviews.json";

		YelpDB db = new YelpDB(rest, user, review);

		System.out.println(db.kMeansClusters_json(3));
	}
	
	@Test
	public void businessTest1() throws IOException {
		String rest = "data/restaurants.json";
		String user = "data/users.json";
		String review = "data/reviews.json";
		
		GeneralDb<Business> db = new GeneralDb(rest,user,review);
		
		for(Business b : db.getBusinesses()) {
			if(b.getId().equals("FWadSZw0G7HsgKXq7gHTnw")) {
				Business PeppermintGrill = b;
				break;
			}
		}
		assertTrue(true);
	}
	}
