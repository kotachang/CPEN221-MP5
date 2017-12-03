package ca.ece.ubc.cpen221.mp5.tests.ca.ece.ubc.cpen221.mp5.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.function.ToDoubleBiFunction;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.Business;
import ca.ece.ubc.cpen221.mp5.Cluster;
import ca.ece.ubc.cpen221.mp5.Coordinate;
import ca.ece.ubc.cpen221.mp5.GeneralDb;
import ca.ece.ubc.cpen221.mp5.MP5Db;
import ca.ece.ubc.cpen221.mp5.Predictor;
import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.Review;
import ca.ece.ubc.cpen221.mp5.User;
import ca.ece.ubc.cpen221.mp5.YelpDB;

public class Tests {

	@Test
	public void clusterTest1() throws IOException {
		Cluster c1 = new Cluster();
		Cluster c2 = new Cluster();
		Business b = new Business("fcdjnsgO8Z5LthXUx3y-lA");
		c1.add(b);
		c2.add(b);
		String rest = "data/restaurants.json";
		String user = "data/users.json";
		String review = "data/reviews.json";
		YelpDB db = new YelpDB(rest, user, review);
		assertTrue(db.equals(c1, c2));
		assertTrue(c1.contains(b));
		c2.clear();
		assertTrue(!c2.contains(b));
	}

	@Test
	public void coordinateTest1() throws IOException {
		Coordinate c = new Coordinate(100, 100);
		Coordinate c2 = new Coordinate(120, 120);
		assertEquals((Double) c.distance(c2), (Double) 2911958.6039025364);
	}

	@Test
	public void clusterTest2() throws IOException {
		String rest = "data/restaurants.json";
		String user = "data/users.json";
		String review = "data/reviews.json";

		YelpDB db = new YelpDB(rest, user, review);

		assertTrue(db.kMeansClusters_json(3).contains("G"));
	}

	@Test
	public void businessTest1() throws IOException {
		// Testing general business methods
		String rest = "data/restaurants.json";
		String user = "data/users.json";
		String review = "data/reviews.json";

		GeneralDb<Business> db = new GeneralDb<Business>(rest, user, review);
		Business PeppermintGrill = new Business("Placeholder");
		for (Business b : db.getBusinesses()) {
			if (b.getId().equals("FWadSZw0G7HsgKXq7gHTnw")) {
				PeppermintGrill = b;
				break;
			}
		}
		assertEquals(PeppermintGrill.getId(), "FWadSZw0G7HsgKXq7gHTnw");
		assertEquals(PeppermintGrill.getPrice(), 2);
		assertEquals(PeppermintGrill.name(), "Peppermint Grill");
		assertEquals(PeppermintGrill.getSchools().toString(), "[University of California at Berkeley]");
		assertEquals(PeppermintGrill.getURL(), "http://www.yelp.com/biz/peppermint-grill-berkeley");
		assertEquals(PeppermintGrill.getPhoto(),
				"http://s3-media1.ak.yelpcdn.com/assets/2/www/img/924a6444ca6c/gfx/blank_biz_medium.gif");
		assertEquals(PeppermintGrill.getAddress(), "2505 Hearst Ave\nSte B\nUC Campus Area\nBerkeley, CA 94709");
		assertTrue(PeppermintGrill.isOpen());
		assertEquals("" + PeppermintGrill.stars(), "2.5");
		assertEquals(PeppermintGrill.reviewCount(), 16);
		assertTrue(PeppermintGrill.categories().contains("Restaurants"));
		assertEquals("" + PeppermintGrill.getCoordinates().Lat(), "37.8751965");
		assertEquals("" + PeppermintGrill.getCoordinates().Long(), "-122.2598181");
	}

	@Test(expected = NullPointerException.class)
	public void businessTest2() {
		Business test = new Business("Placeholder");
		test.stars();
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void businessTest3() {
		// equals method test for different object types
		Business test = new Business("1234");
		Review review = new Review("1234");
		assertFalse(test.equals(review));
	}

	@Test
	public void restaurantTest1() throws IOException {
		// Testing general business methods
		String rest = "data/restaurants.json";
		String user = "data/users.json";
		String review = "data/reviews.json";

		YelpDB db = new YelpDB(rest, user, review);
		Restaurant PeppermintGrill = new Restaurant("Placeholder");
		for (Business b : db.getBusinesses()) {
			if (b.getId().equals("FWadSZw0G7HsgKXq7gHTnw")) {
				PeppermintGrill = (Restaurant) b;
				break;
			}
		}
		assertEquals(PeppermintGrill.getId(), "FWadSZw0G7HsgKXq7gHTnw");
		assertEquals(PeppermintGrill.getPrice(), 2);
		assertEquals(PeppermintGrill.name(), "Peppermint Grill");
		assertEquals(PeppermintGrill.getSchools().toString(), "[University of California at Berkeley]");
		assertEquals(PeppermintGrill.getURL(), "http://www.yelp.com/biz/peppermint-grill-berkeley");
		assertEquals(PeppermintGrill.getPhoto(),
				"http://s3-media1.ak.yelpcdn.com/assets/2/www/img/924a6444ca6c/gfx/blank_biz_medium.gif");
		assertEquals(PeppermintGrill.getAddress(), "2505 Hearst Ave\nSte B\nUC Campus Area\nBerkeley, CA 94709");
		assertTrue(PeppermintGrill.isOpen());
		assertEquals("" + PeppermintGrill.stars(), "2.5");
		assertEquals(PeppermintGrill.reviewCount(), 16);
		assertTrue(PeppermintGrill.categories().contains("Restaurants"));
		assertEquals("" + PeppermintGrill.getCoordinates().Lat(), "37.8751965");
		assertEquals("" + PeppermintGrill.getCoordinates().Long(), "-122.2598181");
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void restaurantTest2() {
		// equals method test for different object types
		Restaurant test = new Restaurant("1234");
		Review review = new Review("1234");
		assertFalse(test.equals(review));
	}

	@Test
	public void reviewTest1() throws IOException {
		// Testing general business methods
		String rest = "data/restaurants.json";
		String user = "data/users.json";
		String review = "data/reviews.json";

		YelpDB db = new YelpDB(rest, user, review);
		Review test = new Review("Placeholder");
		for (Review r : db.getReviews()) {
			if (r.getId().equals("kIeCxSZXJ3OwI3yFTZHhkA")) {
				test = r;
				break;
			}
		}
		assertEquals(test.getId(), "kIeCxSZXJ3OwI3yFTZHhkA");
		assertEquals(test.getUser(), "BIbNKvMu4Y0uigseT1Pv8A");
		assertEquals(test.getBusiness(), "65ltOonS7uaG12RRdn-W3Q");
		assertEquals(test.stars(), 5);
		assertEquals("" + test.reviewRatings().get("cool"), "0");
		assertEquals("" + test.reviewRatings().get("useful"), "1");
		assertEquals("" + test.reviewRatings().get("funny"), "2");
		assertEquals(test.getDate(), "2007-07-23");
		assertEquals(test.text(),
				"Let's begin in 1988. I'm a freshman at Cal and living in the Unit 1 dorms. Though I'm living on the meal plan, sometimes late at night I get cravings that can't be satisfied by whatever I have in my teeny dorm fridge. So off I go to the Top Dog on Durant. The mean and surly guy behind the counter often regales customers with funny stories of his awful ex-wife.\n\nCut to 2005: I find myself back in Berkeley on a regular basis, and a much younger guy is running the grill at the Center St. location. I stop going because, frankly, the guy is an asshat, and we nearly got into it once over my inability to say the entire name of the particular dog I want.\n\nCut to 2007: I'm still in downtown Berkeley, and the asshat got fired a year or so ago. The successive grillers are much more amiable. I'm back to going there more regularly.\n\nThe dogs, of course, have always been great. Bummer that they no longer have pickles, but some things aren't forever.");

	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void reviewTest2() {
		// equals method test for different object types
		Restaurant test = new Restaurant("1234");
		Review review = new Review("1234");
		assertFalse(review.equals(test));
	}

	@Test
	public void reviewTest3() {
		// equals method test for same object types
		Review review1 = new Review("1234");
		Review review2 = new Review("4321");
		Review review3 = new Review("1234");

		assertFalse(review1.equals(review2));
		assertTrue(review1.equals(review3));
	}

	@Test
	public void userTest1() throws IOException {
		// Testing general business methods
		String rest = "data/restaurants.json";
		String user = "data/users.json";
		String review = "data/reviews.json";

		YelpDB db = new YelpDB(rest, user, review);
		User test = new User("Placeholder");
		for (User u : db.getUsers()) {
			if (u.getId().equals("VQxZA1Va8MBVpI3fw3nsqg")) {
				test = u;
				break;
			}
		}

		assertEquals(test.getId(), "VQxZA1Va8MBVpI3fw3nsqg");
		assertEquals(test.getName(), "Annie M.");
		assertEquals(test.reviewCount(), 1);
		assertEquals(test.reviewCount(), test.getReview().size());
		assertEquals(test.getURL(), "http://www.yelp.com/user_details?userid=VQxZA1Va8MBVpI3fw3nsqg");
		assertEquals("" + test.votes().get("cool"), "18");
		assertEquals("" + test.votes().get("useful"), "36");
		assertEquals("" + test.votes().get("funny"), "3");
		assertEquals("" + test.averageStars(), "5.0");

		User nullReviewTest = new User("1234");
		assertEquals("" + nullReviewTest.averageStars(), "0.0");
	}

	@Test
	public void userTest2() {
		// equals method test for same object types
		User user1 = new User("1234");
		User user2 = new User("4321");
		User user3 = new User("1234");

		assertFalse(user1.equals(user2));
		assertTrue(user1.equals(user3));
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void userTest3() {
		// equals method test for different object types
		User test = new User("1234");
		Review review = new Review("1234");
		assertFalse(test.equals(review));
	}

	@Test
	public void clusteringvisualize() throws IOException {
		ToDoubleBiFunction<MP5Db<String>, String> predict = new Predictor<String>(1, 2);
		String rest = "data/restaurants.json";
		String user = "data/users.json";
		String review = "data/reviews.json";
		YelpDB db = new YelpDB(rest, user, review);
		db.kMeansClusters_json(3);
		// System.out.println(db.kMeansClusters_json(10));
		// tested through the visualizer

	}

	@Test
	public void predictionTest0() throws IOException {
		String rest = "data/restaurants.json";
		String user = "data/users.json";
		String review = "data/reviews.json";
		YelpDB db = new YelpDB(rest, user, review);
		double prediction = db.getPredictorFunction("uqSE9YipS_6ilhcVgpPXWA").applyAsDouble(db,
				"G3d-xJF_Rt-P_za2eZ1q-Q");
		assertTrue(prediction == 2.2857142857142856);
	}

	@Test
	public void predictionTest1() throws IOException {
		String rest = "data/restaurants.json";
		String user = "data/users.json";
		String review = "data/reviews.json";
		YelpDB db = new YelpDB(rest, user, review);
		double prediction = db.getPredictorFunction("QScfKdcxsa7t5qfE0Ev0Cw").applyAsDouble(db,
				"G3d-xJF_Rt-P_za2eZ1q-Q");
		assertTrue(prediction == 1.0);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void test3() throws UnsupportedOperationException, IOException {
		String rest = "data/restaurants.json";
		String user = "data/users.json";
		String review = "data/reviews.json";
		YelpDB db = new YelpDB(rest, user, review);
		double prediction = db.getPredictorFunction("zkjy_XoVgR2EFjLjtzFDNw").applyAsDouble(db,
				"G3d-xJF_Rt-P_za2eZ1q-Q");
		System.out.println(prediction);
	}

}
