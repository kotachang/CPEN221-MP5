package ca.ece.ubc.cpen221.mp5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {

	private String id;
	private String name;
	private List<Review> reviews = new ArrayList<Review>();
	private String date;
	private Map<String, Integer> votes = new HashMap<String, Integer>();
	private String url;

	/**
	 * 
	 * @param username
	 *            represents the users unique chosen username.
	 */
	public User(String userId) {
		this.id = userId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
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

	public float averageStars() {
		if (this.reviews.size() != 0) {
			float sum = 0;
			for (int i = 0; i < this.reviews.size(); i++) {
				sum += this.reviews.get(i).stars();
			}
			return sum / this.reviews.size();
		} else {
			return 0;
		}
	}

	/**
	 * 
	 * @param review
	 *            the review to add to this users repository of reviews
	 */
	public void addReview(Review review) {
		this.reviews.add(review);
	}

	public void setURL(String url) {
		this.url = url;
	}

	public String getURL() {
		return this.url;
	}

	public void addVote(int funny, int useful, int cool) {
		this.votes.put("funny", funny);
		this.votes.put("useful", useful);
		this.votes.put("cool", cool);
	}

	public Map<String, Integer> votes() {
		return this.votes;
	}

	public List<Review> getReview() {
		return new ArrayList<Review>(reviews);
	}

	public boolean equals(Object user) {
		if (!(user instanceof User)) {
			return false;
		}
		User thatUser = (User) user;
		return (this.getId().equals(thatUser.getId()));
	}

}
