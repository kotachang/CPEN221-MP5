package ca.ece.ubc.cpen221.mp5;

import java.util.List;
import java.util.Map;

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
	private List<String> attributes;
	private Map<String, String> hours;
	private List<Review> reviews; 

	/**
	 * 
	 * @param id
	 */
	public Business(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @param address
	 */
	public void setAddress(String[] address) {
		this.address = address[0];
		this.city = address[1];
		this.state = address[2];
		this.postalCode = address[3];
		this.longitude = address[4];
		this.latitude = address[5];
	}

	/**
	 * 
	 * @param name
	 */
	public void addNeighbourhood(String name) {
		this.neighbourhood = name;
	}

	/**
	 * @return 
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
	 * @return
	 */
	public List<Review> reviews() {
		return this.reviews;
	}

	public void addReview(Review review) {
		reviews.add(review);
	}

	/**
	 * 
	 * @param attribute
	 */
	public void addAttribute(String attribute) {
		attributes.add(attribute);
	}

	/**
	 * 
	 * @return
	 */
	public List<String> attributes() {
		return attributes;
	}

	/**
	 * 
	 * @return
	 */
	public Map<String, String> hours() {
		return this.hours;
	}

	/**
	 * 
	 * @param hours
	 */
	public void changeHours(Map<String, String> hours) {
		this.hours = hours;
	}

}
