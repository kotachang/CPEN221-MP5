package ca.ece.ubc.cpen221.mp5;

import java.util.ArrayList;
import java.util.List;

public class Restaurant extends Business {

	public Restaurant(String id) {
		super(id);
	}
	
	public void setName(String name) {
		super.name = name;
	}

	/**
	 * 
	 * @return
	 */
	public String getId() {
		return super.id;
	}

	/**
	 * 
	 * @return
	 */
	public String name() {
		return super.name;
	}

	/**
	 * Sets the address of the business
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
	 * 
	 * @param url
	 */
	public void setURL(String url) {
		super.url = url;
	}

	/**
	 * 
	 * @return
	 */
	public String getURL() {
		return super.url;
	}

	/**
	 * 
	 * @param url
	 */
	public void setPhoto(String url) {
		super.photoURL = url;
	}

	/**
	 * 
	 * @return
	 */
	public String getPhoto() {
		return super.photoURL;
	}

	/**
	 * 
	 * @param school
	 */
	public void addSchool(String school) {
		super.schools.add(school);
	}

	/**
	 * 
	 * @return
	 */
	public List<String> getSchools() {
		return super.schools;
	}

	/**
	 * Adds a neighborhood to the associated neighborhoods for a business
	 * 
	 * @param name
	 *            neighborhood name to associate with business
	 */
	public void addNeighbourhood(String name) {
		super.neighbourhood.add(name);
	}

	/**
	 * 
	 * @param price
	 */
	public void setPrice(int price) {
		super.price = price;
	}

	/**
	 * 
	 * @return
	 */
	public int getPrice() {
		return super.price;
	}
	

	/**
	 * @return average star rating for the business
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
	 * @return a list of reviews associated with super business
	 */
	public List<Review> reviews() {
		return super.reviews;
	}

	/**
	 * 
	 * @param review
	 */
	public void addReview(Review review) {
		reviews.add(review);
	}

	/**
	 * 
	 * @param category
	 */
	public void addCategory(String category) {
		super.categories.add(category);
	}

	/**
	 * 
	 * @return
	 */
	public List<String> categories() {
		return super.categories;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isOpen() {
		return super.isOpen;
	}

	/**
	 * 
	 * @param open
	 */
	public void setOpen(boolean open) {
		super.isOpen = open;
	}

	/**
	 * 
	 * @return
	 */
	public Coordinate getCoordinates() {
		return super.coordinate;
	}

	/**
	 * 
	 * @return
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
