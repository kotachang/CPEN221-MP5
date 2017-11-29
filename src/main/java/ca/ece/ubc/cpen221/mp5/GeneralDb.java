package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.ToDoubleBiFunction;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class GeneralDb<T> implements MP5Db<T> {

	private List<Business> businesses;
	private List<Review> reviews;
	private List<User> users;

	/**
	 * Constructor for an empty database
	 */
	public GeneralDb() {
		this.businesses = new ArrayList<Business>();
		this.reviews = new ArrayList<Review>();
		this.users = new ArrayList<User>();
	}

	/**
	 * 
	 * @param user
	 *            represents the user that will be added to the database. Should
	 *            throw error if the username is already contained in the database.
	 */
	public void addUser(User user) {
		this.users.add(user);
	}

	/**
	 * 
	 * @param business
	 *            represents a business to be added to the database
	 */
	public void addBusiness(Business business) {
		if (!this.businesses.contains(business)) {
			this.businesses.add(business);
		}
	}

	/**
	 * 
	 * @param review
	 *            represents a review to be added to the database. Will also add
	 *            associated businesses that are not already in the database to the
	 *            database
	 */
	public void addReview(Review review) {
		this.reviews.add(review);
	}

	private Business parseBusiness(JsonObject data) {

		// Address array
		String[] address = new String[6];
		address[0] = data.getString("address");
		address[1] = data.getString("city");
		address[2] = data.getString("state");
		address[3] = data.getString("postal code");
		address[4] = data.getString("latitude");
		address[5] = data.getString("longitude");

		// Standard characteristics
		Business business = new Business(data.getString("business_id"));
		business.setName(data.getString("name"));
		business.addNeighbourhood(data.getString("neighborhood"));

		// Hours
		Map<String, String> hours = new HashMap<String, String>();

		return null;

	}

	/**
	 * Populates the businesses from a given JSON file.
	 * 
	 * @throws IOException 
	 */
	private void populateBusinesses(String filePath) throws IOException {
		File file = new File(filePath);
		BufferedReader fileReader = new BufferedReader(new FileReader(file));
		String line;
		while ((line = fileReader.readLine()) != null) {
			StringReader sr = new StringReader(line);
			JsonReader parseFile = Json.createReader(sr);
			JsonObject data = parseFile.readObject();
			Business newBusiness = this.parseBusiness(data);
		}
		fileReader.close();
	}

	/**
	 * Populates the users from a given JSON file.
	 */
	private void populateUsers() {
		User user = new User("placeholder");
		this.addUser(user);
	}

	/**
	 * Populates the reviews from a given JSON file.
	 */
	private void populateReviews() {
		Review review = new Review("placeholder");
		this.addReview(review);
	}

	/**
	 * 
	 */
	@Override
	public Set getMatches(String queryString) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return 
	 * 
	 */
	@Override
	public String kMeansClusters_json(int k) {
		// TODO Auto-generated method stub
		
		return null;
	}
			
	/**
	 * 
	 */
	@Override
	public ToDoubleBiFunction getPredictorFunction(String user) {
		// TODO Auto-generated method stub
		return null;
	}

}
