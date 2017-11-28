package ca.ece.ubc.cpen221.mp5;

import java.math.BigInteger;
import java.util.List;

public class User {
	
	public static String userId = "1111111111111111111111";
	private String id;
	private String username;
	private List<Review> reviews;
	
	public User(String username) {
		this.username = username;
		this.id = userId;
		userId = Double.toString(Double.parseDouble(userId) + 1);
	}
	
	public String getId() {
		return this.id;
	}
	
	public int reviewCount() {
		return this.reviews.size();
	}
	
	public void addReview(Review review) {
		this.reviews.add(review);
	}
	
}
