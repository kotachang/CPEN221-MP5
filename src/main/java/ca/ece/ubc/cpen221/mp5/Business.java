package ca.ece.ubc.cpen221.mp5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

public class Business {

	private String id;
	private String name;
	private String address;
	private String city;
	private String state;
	private Coordinate coordinate;
	private List<String> neighbourhood = new ArrayList<String>();
	private List<Review> reviews = new ArrayList<Review>();;
	private boolean isOpen;
	private List<String> categories = new ArrayList<String>();
	private String url;
	private String photoURL;
	private List<String> schools = new ArrayList<String>();
	private int price;

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

	public String name() {
		return this.name;
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
		this.address = address[0];
		this.city = address[1];
		this.state = address[2];
		this.coordinate = new Coordinate(Double.parseDouble(address[3]), Double.parseDouble(address[4]));
	}

	public void setURL(String url) {
		this.url = url;
	}

	public String getURL() {
		return this.url;
	}

	public void setPhoto(String url) {
		this.photoURL = url;
	}

	public String getPhoto() {
		return this.photoURL;
	}

	public void addSchool(String school) {
		this.schools.add(school);
	}

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

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPrice() {
		return this.price;
	}

	/**
	 * @return average star rating for the business
	 * 
	 */
	public float stars() throws NullPointerException {
		float average = 0;
		float count = 0;
		if (this.reviews().isEmpty()) {
			throw new NullPointerException();
		} else {
			for (Review r : this.reviews) {
				average += r.stars();
				count++;
			}
			return average / count;
		}

	}

	/**
	 * 
	 * @return a list of reviews associated with this business
	 */
	public List<Review> reviews() {
		return this.reviews;
	}

	public void addReview(Review review) {
		reviews.add(review);
	}

	
	public void addCategory(String category) {
		this.categories.add(category);
	}

	public List<String> categories() {
		return this.categories;
	}

	public boolean isOpen() {
		return this.isOpen;
	}

	public void setOpen(boolean open) {
		this.isOpen = open;
	}

	public Coordinate getCoordinates() {
		return this.coordinate;
	}

	public int reviewCount() {
		return this.reviews().size();
	}

	public boolean equals(Business b) {
		if (this.id == b.id) {
			return true;
		} else {
			return false;
		}
	}

}
