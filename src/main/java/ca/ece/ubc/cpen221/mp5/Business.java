package ca.ece.ubc.cpen221.mp5;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Business {

	private String id;
	private String name;
	private String address;
	private String city;
	private String state;
	private String postalCode;
	private double longitude;
	private double latitude;
	private String neighbourhood;
	private Map<String, Integer> attributes;
	private Map<String, String> hours;
	private List<Review> reviews;

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
	 *            postal code; fifth element = longitudinal coordinate; sixth
	 *            element = latitudinal coordinate
	 */
	public void setAddress(String[] address) {
		this.address = address[0];
		this.city = address[1];
		this.state = address[2];
		this.postalCode = address[3];
		this.longitude = Double.parseDouble(address[4]);
		this.latitude = Double.parseDouble(address[5]);
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
	public void addAttribute(String attribute) {
		if (attributes.containsKey(attribute)) {
			attributes.put(attribute, attributes.get(attribute) + 1);
		} else {
			attributes.put(attribute, 1);
		}
	}

	/**
	 * 
	 * @return a set of attributes associated with this business
	 */
	public Set<String> attributes() {
		return attributes.keySet();
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

}
