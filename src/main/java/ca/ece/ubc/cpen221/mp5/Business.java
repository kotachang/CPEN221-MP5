package ca.ece.ubc.cpen221.mp5;

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
	private String postalCode;
	private Coordinate coordinate;
	private String neighbourhood;
	private List<JsonObject> attributes;
	private Map<String, String> hours;
	private List<Review> reviews;
	private int isOpen;
	private List<String> categories;

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
		this.postalCode = address[3];
		this.coordinate = new Coordinate(Double.parseDouble(address[4]), Double.parseDouble(address[5]));
	}

	/**
	 * Adds a neighborhood to the associated neighborhoods for a business
	 * 
	 * @param name
	 *            neighborhood name to associate with business
	 */
	public void addNeighbourhood(String name) {
		this.neighbourhood = name;
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
				average += r.rating();
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

	/**
	 * Associates the attribute with this business, or increments the count of the
	 * attribute if it is already associated with the business
	 * 
	 * @param attribute
	 *            the attribute to associate this business with
	 */
	public void addAttribute(JsonObject attribute) {
			this.attributes.add(attribute);
	}

	public void addCategory(String category) {
		this.categories.add(category);
	}

	public List<String> categories() {
		return this.categories;
	}

	/**
	 * 
	 * @return a set of attributes associated with this business
	 */
	public List<JsonObject> attributes() {
		return this.attributes;
	}

	/**
	 * 
	 * @return a map where the keys are the days of the week, and the values are the
	 *         hours they are open on that day.
	 */
	public Map<String, String> hours() {
		return this.hours;
	}

	/**
	 * Updates the hours of operations for this business
	 * 
	 * @param hours
	 *            map where the keys are the days of the week, and the values are
	 *            the hours they are open on that day.
	 */
	public void changeHours(Map<String, String> hours) {
		this.hours = hours;
	}

	public int isOpen() {
		return this.isOpen;
	}

	public void setOpen(int open) {
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
