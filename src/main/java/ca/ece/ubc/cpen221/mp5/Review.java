package ca.ece.ubc.cpen221.mp5;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 
 *  @RI : Only one review per user for a business
 *
 */

public class Review {
	
	private String user;
	private String id;
	private String business;
	private int stars;
	private String date;
	private String userId;
	private Map<String, Integer> attributes = new HashMap<String, Integer>();
	private String text;

	/**
	 * Constructor for a review
	 * 
	 * @param businessId
	 *            the id of the business that this review is for.
	 */
	public Review(String reviewId) {
		this.id = reviewId;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * Sets the business id associated with this review
	 * 
	 * @param businessId the string of the business id
	 */
	public void setBusiness(String businessId) {
		this.business = businessId;
	}
	
	/**
	 * Gets the business id associated with this review
	 * 
	 * @return a string of the business id
	 */
	public String getBusiness() {
		return this.business;
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
	 * Gets the user who wrote this review
	 * 
	 * @return a string of the user's id
	 */
	public String getUser() {
		return this.user;
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
	 * Initializes or updates this review's assigned ratings
	 * 
	 * @param useful the rating of "useful" category  
	 * @param funny the rating of "funny" category 
	 * @param cool the rating of "cool" category
	 */
	public void setReviewRating(int useful, int funny, int cool) {
		this.attributes.put("useful", useful);
		this.attributes.put("funny", funny);
		this.attributes.put("cool", cool);
	}
	
	/**
	 * 
	 * @return a map of the rating categories and the review's user-voted ratings
	 */
	public Map<String,Integer> reviewRatings(){
		return this.attributes;
	}
	
	/**
	 * Sets the text of the review (the written review)
	 * 
	 * @param text string of text
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * 
	 * @return a string of the user's written review
	 */
	public String text() {
		return text;
	}
	
	public boolean equals(Object review) {
		if (!(review instanceof Review)) {
			return false;
		}
		Review thatReview = (Review) review;
		return (this.getId().equals(thatReview.getId()));
	}

}
