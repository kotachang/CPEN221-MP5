package ca.ece.ubc.cpen221.mp5;

import java.util.ArrayList;
import java.util.List;

public class Business {

	/**
	 * @AF:A business is created by the parameters: businessId, name, address, url
	 *       Other characteristics such as neighborhoods, reviews can be added
	 *       later, and are not necessary to represent a business.
	 * 
	 *       For example, a business named "CIBC" with businessId "1357" located at
	 *       "123 ABC St. Vancouver, BC V1A 2B3" with site url "yelp.blah" is fully
	 *       characterized, but as users add reviews and associate neighborhoods and
	 *       price ratings, the characteristics grow.
	 * 
	 * @RI:id is not null, and is unique to this business in its database name is
	 *        not null, but not necessarily unique (there could be 2 different
	 *        locations of McDonalds, with different reviews etc.) address is
	 *        notnull price is 0 if not assigned yet. url is not null, but photourl
	 *        can be null if there is no photo. reviews can be an empty list, but
	 *        should not be null and should not contain null reviews.
	 */

	protected String id;
	protected String name;
	protected String address;
	protected String city;
	protected String state;
	protected Coordinate coordinate;
	protected List<String> neighbourhood = new ArrayList<String>();
	protected List<Review> reviews = new ArrayList<Review>();;
	protected boolean isOpen;
	protected List<String> categories = new ArrayList<String>();
	protected String url;
	protected String photoURL;
	protected List<String> schools = new ArrayList<String>();
	protected int price = 0;

	/**
	 * Constructs a new business
	 * 
	 * @param id
	 *            a unique id associated with the business
	 */
	public Business(String id) {
		this.id = id;
	}

	/**
	 * Sets the name of the business to the specified name
	 * 
	 * @param name
	 *            the desired new name of the business
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the business id
	 * 
	 * @return a string of the business id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * gets the name of the business
	 * 
	 * @return a string of the name of the business (null if it hasn't been
	 *         initialized)
	 */
	public String name() {
		return this.name;
	}

	/**
	 * Sets the address of the business
	 * 
	 * @param address
	 *            an array of strings where the: first element = address; second
	 *            element = city; third element = state; fourth element =
	 *            coordinates.
	 */
	public void setAddress(String[] address) {
		this.address = address[0];
		this.city = address[1];
		this.state = address[2];
		this.coordinate = new Coordinate(Double.parseDouble(address[3]), Double.parseDouble(address[4]));
	}

	public String getAddress() {
		return this.address;
	}

	/**
	 * Sets the url for this business's yelp site
	 * 
	 * @param url
	 *            the string of the url
	 */
	public void setURL(String url) {
		this.url = url;
	}

	/**
	 * Gets the url for this business's yelp site
	 * 
	 * @return the string of the url, null if it has no website/has not been set
	 */
	public String getURL() {
		return this.url;
	}

	/**
	 * Sets the url for this business's photo for their yelp site
	 * 
	 * @param url
	 *            the string of the url for the photo
	 */
	public void setPhoto(String url) {
		this.photoURL = url;
	}

	/**
	 * Gets the url for this business's photo for their yelp site
	 * 
	 * @return the string of the url for the photo
	 */
	public String getPhoto() {
		return this.photoURL;
	}

	/**
	 * Adds the school to list of schools close to business
	 * 
	 * @param school
	 *            string of the name of the school
	 */
	public void addSchool(String school) {
		this.schools.add(school);
	}

	/**
	 * 
	 * @return a list of the names of schools nearby this business
	 */
	public List<String> getSchools() {
		return this.schools;
	}

	/**
	 * Adds a neighborhood to the associated neighborhoods for a business
	 * 
	 * @param name
	 *            neighborhood name to associate with business
	 */
	public void addNeighbourhood(String name) {
		this.neighbourhood.add(name);
	}

	/**
	 * Sets the price rating of this business
	 * 
	 * @param price
	 *            the rating of the price of this business, an integer ranging from
	 *            1 to 5
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * Gets the price rating of this business
	 * 
	 * @return the rating of the price of this business, and inteer ranging from 1
	 *         to 5
	 */
	public int getPrice() {
		return this.price;
	}

	/**
	 * @return average star rating for the business
	 * 
	 */
	public float stars() throws NullPointerException {
		float average = 0;
		if (this.reviews().isEmpty()) {
			throw new NullPointerException();
		} else {
			for (Review r : this.reviews) {
				average += r.stars();
			}
			return (Math.round(average / this.reviews.size() * 2) / 2.0f);
		}

	}

	/**
	 * 
	 * @return a list of reviews associated with this business
	 */
	public List<Review> reviews() {
		return this.reviews;
	}

	/**
	 * Associates a review with this business
	 * 
	 * @param review
	 *            the review to be added to this business
	 */
	public void addReview(Review review) {
		reviews.add(review);
	}

	/**
	 * Adds a category that helps describe this business
	 * 
	 * @param category
	 *            a string that represents the category
	 */
	public void addCategory(String category) {
		this.categories.add(category);
	}

	/**
	 * 
	 * @return list of the categories associated with this business
	 */
	public List<String> categories() {
		return this.categories;
	}

	/**
	 * Tells if the business currently operating
	 * 
	 * @return
	 */
	public boolean isOpen() {
		return this.isOpen;
	}

	/**
	 * Sets if this business is operational
	 * 
	 * @param open
	 *            true if it is open, false if it is closed down.
	 */
	public void setOpen(boolean open) {
		this.isOpen = open;
	}

	/**
	 * Gets the coordinate (Lat,Long) of the location of this business
	 * 
	 * @return a Coordinate pair of the location of this business
	 */
	public Coordinate getCoordinates() {
		return this.coordinate;
	}

	/**
	 * 
	 * @return the number of reviews associated with this business
	 */
	public int reviewCount() {
		return this.reviews().size();
	}

	/**
	 * 
	 */
	public boolean equals(Object business) {
		if (!(business instanceof Business)) {
			return false;
		}
		Business thatBusiness = (Business) business;
		return (this.getId().equals(thatBusiness.getId()));
	}

}
