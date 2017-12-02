package ca.ece.ubc.cpen221.mp5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {

	private String id;
	private String name;
	private List<Review> reviews = new ArrayList<Review>();
	private Map<String, Integer> votes = new HashMap<String, Integer>();
	private String url;

	/**
	 * 
	 * @param userId
	 */
	public User(String userId) {
		this.id = userId;
	}

	/**
	 * Sets the name (ie. First name and/or last name and/or initials) of this user
	 * 
	 * @param name
	 *            string of the user's name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the name of this user
	 * 
	 * @return string of the name of this user
	 */
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

	/**
	 * 
	 * @return the average number of stars assigned to businesses by this user
	 */
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
	 * Associates this review with this user (must be written by this user)
	 * 
	 * @param review
	 *            the review to add to this users repository of reviews
	 */
	public void addReview(Review review) {
		this.reviews.add(review);
	}

	/**
	 * Sets the url of the website/yelp site of this user's profile
	 * 
	 * @param url
	 *            string of the url
	 */
	public void setURL(String url) {
		this.url = url;
	}

	/**
	 * Gets the url of the website/yelp site of this user's profile
	 * 
	 * @return
	 */
	public String getURL() {
		return this.url;
	}

	/**
	 * Sets the category votes assigned to this user, through other reviews
	 * 
	 * @param useful
	 *            the rating of "useful" category
	 * @param funny
	 *            the rating of "funny" category
	 * @param cool
	 *            the rating of "cool" category
	 */
	public void addVote(int funny, int useful, int cool) {
		this.votes.put("funny", funny);
		this.votes.put("useful", useful);
		this.votes.put("cool", cool);
	}

	/**
	 * 
	 * @return a map of the vote categories and their associated number of votes
	 */
	public Map<String, Integer> votes() {
		return this.votes;
	}

	/**
	 * 
	 * 
	 * @return list of reviews written by this user
	 */
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
