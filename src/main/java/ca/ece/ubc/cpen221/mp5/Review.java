package ca.ece.ubc.cpen221.mp5;

import java.util.Map;
import java.util.Set;

public class Review {

	public static String reviewId = "1111111111111111111111";
	private String user;
	private String id;
	private String business;
	private int stars;
	private String date;
	private String userId;
	private Map<String, Integer> attributes;
	private String text;

	/**
	 * Constructor for a review
	 * 
	 * @param businessId
	 *            the id of the business that this review is for.
	 */
	public Review(String businessId) {
		this.business = businessId;
		this.id = reviewId;
		reviewId = Double.toString(Double.parseDouble(reviewId) + 1);
	}
	
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Sets the user who gave this review.
	 * 
	 * @param id
	 *            id of the user who's review this belongs to.
	 */
	public void setUser(String id) {
		this.user = id;
	}

	/**
	 * Sets the rating given for the business (integer from 1 to 5)
	 * 
	 * @param star
	 *            the rating from 1 to 5
	 */
	public void setStars(int star) {
		this.stars = star;
	}

	/**
	 * 
	 * @return the rating associated with this review.
	 */
	public int stars() {
		return stars;
	}

	/**
	 * Initializes the date that this review was created on.
	 * 
	 * @param date
	 *            the date that this review was created initially (yyyy/mm/dd).
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * 
	 * @return
	 */
	public Set<String> attributes() {
		return attributes.keySet();
	}

	public void setReviewRating(int useful, int funny, int cool) {
		this.attributes.put("useful", useful);
		this.attributes.put("funny", funny);
		this.attributes.put("cool", cool);
	}
	
	public Map<String,Integer> reviewRatings(){
		return this.attributes;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String text() {
		return text;
	}
	
	public boolean equals(Review r) {
		if(r.id == this.id) {
			return true;
		}
		return false;
	}

}
