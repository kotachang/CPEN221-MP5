package ca.ece.ubc.cpen221.mp5;

import java.util.Map;
import java.util.Set;

public class Review {

	private String user;
	private String business;
	private int rating;
	private String date;
	private Map<String, Integer> attributes;

	/**
	 * Constructor for a review
	 * 
	 * @param businessId
	 *            the id of the business that this review is for.
	 */
	public Review(String businessId) {
		this.business = businessId;
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
	public void setRating(int star) {
		this.rating = star;
	}

	/**
	 * 
	 * @return the rating associated with this review.
	 */
	public int rating() {
		return rating;
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

	/**
	 * 
	 * @param attribute
	 * @return true if the attribute was not already associated with this review,
	 *         false if it was already associated with this review.
	 */
	private boolean addAttribute(String attribute) {
		if (attributes.containsKey(attribute)) {
			return false;
		} else {
			attributes.put(attribute, 0);
			return true;
		}
	}

	/**
	 * Probably gonna get rid of this?
	 * @param attribute
	 */
	public void voteAttribute(String attribute) {
		if (!this.addAttribute(attribute)) {
			attributes.replace(attribute, attributes.get(attribute) + 1);
		}
	}

}
