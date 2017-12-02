package ca.ece.ubc.cpen221.mp5;

import java.io.IOException;

public class TestRip {

	public static void main(String[] args) throws IOException {
		String rest = "data/restaurants.json";
		String user = "data/users.json";
		String review = "data/reviews.json";
		YelpDB db = new YelpDB(rest, user, review);

		System.out.println(db.kMeansClusters_json(10));
	}

}
