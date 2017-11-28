package ca.ece.ubc.cpen221.mp5;

import java.math.BigInteger;
import java.util.List;

public class User {

	public static String userId = "1111111111111111111111";
	private String id;
	private String username;
	private List<Review> reviews;

	/**
	 * 
	 * @param username
	 *            represents the users unique chosen username.
	 */
	public User(String username) {
		this.username = username;
		this.id = userId;
		userId = Double.toString(Double.parseDouble(userId) + 1);
	}

	/**
	 * 
	 * @return the user's unique id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 
	 * @return the number of reviews this user has given
	 */
	public int reviewCount() {
		return this.reviews.size();
	}

	/**
	 * 
	 * @param review
	 *            the review to add to this users repository of reviews
	 */
	public void addReview(Review review) {
		this.reviews.add(review);
	}

}
