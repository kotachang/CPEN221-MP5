package ca.ece.ubc.cpen221.mp5;

import java.util.List;

public class User {

	private String username;
	private List<Review> reviews;
	
	public User(String username) {
		this.username = username;
	}
	
	public String getId() {
		return this.hashId();
	}
	
	
	public String hashId() {
		String hash = "";
		for(Character c : this.username.toCharArray()) {
			hash += c.hashCode()*43/11;
			Integer x = c.hashCode();
			Double y = Math.sqrt((double) x);
			hash += y.intValue() * 51 / 7;
			hash += Character.forDigit(y.intValue(), 10);
		}
		if(hash.length() < 22) {
			for(int i = hash.length(); i < 22; i++) {
				Double z = (Double) Math.random()*10;
				hash += z.intValue();
			}
		}
		return hash.substring(0,22);
		
	}
	
	public int reviewCount() {
		return this.reviews.size();
	}
	
	public void addReview(Review review) {
		this.reviews.add(review);
	}
	
}
