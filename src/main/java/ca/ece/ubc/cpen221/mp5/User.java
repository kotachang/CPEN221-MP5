package ca.ece.ubc.cpen221.mp5;

import java.math.BigInteger;
import java.util.List;

public class User {

	public static String userId = "1111111111111111111111";
	private String id;
	private String username;
	private List<Review> reviews;
	private String date;
	private List<String> friends;

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
	
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return the number of reviews this user has given
	 */
	public int reviewCount() {
		return this.reviews.size();
	}
	
	public void addFriend(String id) {
		this.friends.add(id);
	}

	/**
	 * 
	 * @param review
	 *            the review to add to this users repository of reviews
	 */
	public void addReview(Review review) {
		this.reviews.add(review);
	}
	
	public void accountDate(String date) {
		this.date = date;
	}
	
	public boolean equals(User u) {
		if(u.getId() == this.getId()) {
			return true;
		}
		return false;
	}

}
