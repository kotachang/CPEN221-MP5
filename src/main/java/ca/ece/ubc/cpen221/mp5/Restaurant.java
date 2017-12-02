package ca.ece.ubc.cpen221.mp5;

import java.util.ArrayList;
import java.util.List;

public class Restaurant extends Business {

	/**
	 * Constructs a new restaurant
	 * 
	 * @param id
	 *            a unique id associated with the restaurant
	 */
	public Restaurant(String id) {
		super(id);
	}

	/**
	 * Sets the name of the restaurant to the specified name
	 * 
	 * @param name
	 *            the desired new name of the restaurant
	 */
	public void setName(String name) {
		super.name = name;
	}

	/**
	 * Gets the restaurant id
	 * 
	 * @return a string of the restaurant id
	 */
	public String getId() {
		return super.id;
	}

	/**
	 * Sets the address of the restaurant
	 * 
	 * @param address
	 *            an array of strings where the: first element = address; second
	 *            element = city; third element = state; fourth element =
	 *            coordinates.
	 */
	public String name() {
		return super.name;
	}

	/**
	 * Sets the address of the restaurant
	 * 
	 * @param address
	 *            an array of strings where the: first element = street address;
	 *            second element = city; third element = state; fourth element =
	 *            postal code; fifth element = coordinates.
	 */
	public void setAddress(String[] address) {
		super.address = address[0];
		super.city = address[1];
		super.state = address[2];
		super.coordinate = new Coordinate(Double.parseDouble(address[3]), Double.parseDouble(address[4]));
	}

	/**
	 * Sets the url for this restaurant's yelp site
	 * 
	 * @param url
	 *            the string of the url
	 */
	public void setURL(String url) {
		super.url = url;
	}

	/**
	 * Gets the url for this restaurant's yelp site
	 * 
	 * @return the string of the url, null if it has no website/has not been set
	 */
	public String getURL() {
		return super.url;
	}

	/**
	 * Sets the url for this restaurant's photo for their yelp site
	 * 
	 * @param url
	 *            the string of the url for the photo
	 */
	public void setPhoto(String url) {
		super.photoURL = url;
	}

	/**
	 * Gets the url for this restaurant's photo for their yelp site
	 * 
	 * @return the string of the url for the photo
	 */
	public String getPhoto() {
		return super.photoURL;
	}

	/**
	 * Adds the school to list of schools close to restaurant
	 * 
	 * @param school
	 *            string of the name of the school
	 */
	public void addSchool(String school) {
		super.schools.add(school);
	}

	/**
	 * 
	 * @return a list of the names of schools nearby this restaurant
	 */
	public List<String> getSchools() {
		return super.schools;
	}

	/**
	 * Adds a neighborhood to the associated neighborhoods for a restaurant
	 * 
	 * @param name
	 *            neighborhood name to associate with restaurant
	 */
	public void addNeighbourhood(String name) {
		super.neighbourhood.add(name);
	}

	/**
	 * Sets the price rating of this restaurant
	 * 
	 * @param price
	 *            the rating of the price of this restaurant, an integer ranging
	 *            from 1 to 5
	 */
	public void setPrice(int price) {
		super.price = price;
	}

	/**
	 * Gets the price rating of this restaurant
	 * 
	 * @return the rating of the price of this restaurant, and inteer ranging from 1
	 *         to 5
	 */
	public int getPrice() {
		return super.price;
	}

	/**
	 * @return average star rating for the restaurant
	 * 
	 */
	public float stars() throws NullPointerException {
		float average = 0;
		float count = 0;
		if (super.reviews().isEmpty()) {
			throw new NullPointerException();
		} else {
			for (Review r : super.reviews) {
				average += r.stars();
				count++;
			}
			return average / count;
		}

	}

	/**
	 * 
	 * @return a list of reviews associated with this restaurant
	 */
	public List<Review> reviews() {
		return super.reviews;
	}

	/**
	 * Associates a review with this restaurant
	 * 
	 * @param review
	 *            the review to be added to this restaurant
	 */
	public void addReview(Review review) {
		reviews.add(review);
	}

	/**
	 * Adds a category that helps describe this restaurant
	 * 
	 * @param category
	 *            a string that represents the category
	 */
	public void addCategory(String category) {
		super.categories.add(category);
	}

	/**
	 * 
	 * @return list of the categories associated with this restaurant
	 */
	public List<String> categories() {
		return super.categories;
	}

	/**
	 * Tells if the restaurant currently operating
	 * 
	 * @return
	 */
	public boolean isOpen() {
		return super.isOpen;
	}

	/**
	 * Sets if this restaurant is operational
	 * 
	 * @param open
	 *            true if it is open, false if it is closed down.
	 */
	public void setOpen(boolean open) {
		super.isOpen = open;
	}

	/**
	 * Gets the coordinate (Lat,Long) of the location of this restaurant
	 * 
	 * @return a Coordinate pair of the location of this restaurant
	 */
	public Coordinate getCoordinates() {
		return super.coordinate;
	}

	/**
	 * 
	 * @return the number of reviews associated with this restaurant
	 */
	public int reviewCount() {
		return super.reviews().size();
	}

	@Override
	public boolean equals(Object business) {
		if (!(business instanceof Restaurant)) {
			return false;
		}
		Business thatBusiness = (Business) business;
		return (super.getId().equals(thatBusiness.getId()));
	}

}
