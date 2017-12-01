package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.ToDoubleBiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class GeneralDb<T> implements MP5Db<T> {

	protected List<Business> businesses;
	protected List<Review> reviews;
	protected List<User> users;
	protected static final double R = 6371 * 1000;// in meters

	/**
	 * Constructor for an empty database
	 * 
	 * @throws IOException
	 */
	public GeneralDb(String businessFile, String userFile, String reviewFile) throws IOException {
		this.businesses = new ArrayList<Business>();
		this.reviews = new ArrayList<Review>();
		this.users = new ArrayList<User>();
		this.populateBusinesses(businessFile);
		this.populateUsers(userFile);
		this.populateReviews(reviewFile);
	}

	public List<Business> getBusinesses() {
		return new ArrayList<Business>(businesses);
	}

	public List<Review> getReviews() {
		return new ArrayList<Review>(reviews);
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
		String[] address = new String[5];
		address[0] = data.getString("full_address");
		address[1] = data.getString("city");
		address[2] = data.getString("state");
		address[3] = data.getJsonNumber("latitude").toString();
		address[4] = data.getJsonNumber("longitude").toString();

		// Standard characteristics
		Business business = new Business(data.getString("business_id"));
		business.setAddress(address);
		business.setName(data.getString("name"));
		business.setOpen(data.getBoolean("open"));
		business.setURL(data.getString("url"));
		business.setPhoto(data.getString("photo_url"));
		business.setPrice(data.getInt("price"));

		// Recursive types
		JsonArray schools = data.getJsonArray("schools");
		for (int i = 0; i < schools.size(); i++) {
			business.addSchool(schools.getString(i));
		}

		JsonArray neighborhoods = data.getJsonArray("neighborhoods");
		for (int i = 0; i < neighborhoods.size(); i++) {
			business.addNeighbourhood(neighborhoods.getString(i));
		}

		JsonArray categories = data.getJsonArray("categories");
		for (int i = 0; i < categories.size(); i++) {
			business.addCategory(categories.getString(i));
		}
		return business;

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
			this.addBusiness(newBusiness);
		}
		fileReader.close();
	}

	private User parseUser(JsonObject data) {
		User user = new User(data.getString("user_id"));
		user.setURL(data.getString("url"));
		JsonObject votes = data.getJsonObject("votes");
		user.addVote(votes.getInt("funny"), votes.getInt("useful"), votes.getInt("cool"));
		user.setName(data.getString("name"));

		return user;
	}

	/**
	 * Populates the users from a given JSON file.
	 * 
	 * @return
	 * 
	 * @throws IOException
	 */
	protected void populateUsers(String filePath) throws IOException {
		File file = new File(filePath);
		BufferedReader fileReader = new BufferedReader(new FileReader(file));
		String line;
		while ((line = fileReader.readLine()) != null) {
			StringReader sr = new StringReader(line);
			JsonReader parseFile = Json.createReader(sr);
			JsonObject data = parseFile.readObject();
			User newUser = this.parseUser(data);
			this.addUser(newUser);
		}
		fileReader.close();
	}

	private Review parseReview(JsonObject data) {

		Review review = new Review(data.getString("review_id"));
		review.setUser(data.getString("user_id"));
		review.setBusiness(data.getString("business_id"));
		review.setStars(data.getInt("stars"));
		review.setDate(data.getString("date"));
		JsonObject votes = data.getJsonObject("votes");
		review.setReviewRating(votes.getInt("useful"), votes.getInt("funny"), votes.getInt("cool"));
		review.setText(data.getString("text"));

		// Adds review to business
		Business change = new Business(data.getString("business_id"));
		for (Business b : this.businesses) {
			if (b.equals(change)) {
				change = b;
				break;
			}
		}

		this.businesses.remove(change);
		change.addReview(review);
		this.businesses.add(change);

		// Adds review to user
		User user = new User(data.getString("user_id"));
		for (User u : this.users) {
			if (u.equals(user)) {
				user = u;
				break;
			}
		}

		this.users.remove(user);
		user.addReview(review);
		this.users.add(user);

		return review;
	}

	/**
	 * Populates the reviews from a given JSON file.
	 * 
	 * @throws IOException
	 */
	protected void populateReviews(String filePath) throws IOException {
		File file = new File(filePath);
		BufferedReader fileReader = new BufferedReader(new FileReader(file));
		String line;
		while ((line = fileReader.readLine()) != null) {
			StringReader sr = new StringReader(line);
			JsonReader parseFile = Json.createReader(sr);
			JsonObject data = parseFile.readObject();
			Review newReview = this.parseReview(data);
			this.addReview(newReview);
		}
		fileReader.close();
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
		String weight = "1.0";
		String result = "[";
		List<Cluster> l = new ArrayList<Cluster>();
		JsonArrayBuilder arr = Json.createArrayBuilder();

		l = Cluster(k);

		for (int i = 0; i < l.size(); i++) {
			List<Business> b = new ArrayList<Business>(l.get(i).getBusinesses());
			for (int a = 0; a < b.size(); a++) {
				if(a != 0) {
					result += ",";
				}
				JsonObject restaurant = Json.createObjectBuilder().add("x", b.get(a).getCoordinates().Lat())
						.add("y", b.get(a).getCoordinates().Long()).add("name", b.get(a).name()).add("cluster", i + 1)
						.add("weight", weight).build();
				result += restaurant.toString();
			}
		}

		result += "]";

		try {
			FileWriter writer = new FileWriter("visualize/voronoi.json");
			writer.write(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Cluster> Cluster(int nk) {
		List<Cluster> sublist = new ArrayList<Cluster>();
		List<Cluster> previous = new ArrayList<Cluster>();
		int k = 0;
		int closest = 0;
		int removeI = 0;

		for (int i = 0; i < nk - 1; i++) {
			sublist.add(new Cluster(businesses.subList(k, k + 1)));
			k++;
		}
		sublist.add(new Cluster(businesses.subList(k, businesses.size() - 1)));
		previous.addAll(sublist);

		while (true) {
			for (int a = 0; a < businesses.size(); a++) {
				double distance = Integer.MAX_VALUE;
				for (int b = 0; b < sublist.size(); b++) {
					if (sublist.get(b).contains(businesses.get(a))) {
						removeI = b;
					}
					/*
					 * comment for Kota : try to optimize by storing the center val then switching
					 * after recalculating , assign the value;
					 */
					if (distance > (businesses.get(a).getCoordinates().distance(sublist.get(b).findCenter()))) {
						distance = (businesses.get(a).getCoordinates().distance(sublist.get(b).findCenter()));
						closest = b;
					}
				}
				sublist.get(removeI).remove(businesses.get(a));
				sublist.get(closest).add(businesses.get(a));

			}
			if (previous.equals(sublist)) {
				break;
			} else {
				previous.clear();
				previous.addAll(sublist);
			}
		}

		return sublist;
	}

	public boolean equals(List<Cluster> l, List<Cluster> l2) {
		for (int i = 0; i < l.size(); i++) {
			if (!(l.get(i).equals(l2.get(i)))) {
				return false;
			}
		}
		return true;
	}

	public boolean equals(Cluster c, Cluster c2) {
		if (c.businesses.equals(c2.businesses)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 */
	@Override
	public ToDoubleBiFunction getPredictorFunction(String user) {

		Map<String, Business> idBus = new HashMap<String, Business>();
		Map<Business, Review> busR = new HashMap<Business, Review>();
		List<Review> tempList = new ArrayList<Review>();
		List<String> busIds = new ArrayList<String>();

		/*
		 * Keep track of a business's specific review written by the user from the input
		 * argument. ** rep invariant for review = only 1 review per user per business
		 * **
		 * 
		 */
		for (int i = 0; i < this.businesses.size(); i++) {
			tempList = this.businesses.get(i).reviews();
			for (int a = 0; a < tempList.size(); a++) {
				if (tempList.get(a).getUser().equals(user)) {

					idBus.put(this.businesses.get(i).getId(), this.businesses.get(i));
					busIds.add(this.businesses.get(i).getId());
					busR.put(this.businesses.get(i), tempList.get(a));
				}
			}
			tempList = new ArrayList<Review>();
		}

		List<Double> prices = new ArrayList<Double>();
		List<Integer> stars = new ArrayList<Integer>();

		/*
		 * Get the prices of the business and the review of that business entered by the
		 * input argument user. put them in separate lists for map, filter, reduce
		 * processes but same index for the same business.
		 * 
		 */
		for (Map.Entry<String, Business> entry : idBus.entrySet()) {
			for (int i = 0; i < busIds.size(); i++) {
				prices.add((double) idBus.get(busIds.get(i)).getPrice());
				stars.add(busR.get(idBus.get(busIds.get(i))).stars());
			}
		}

		/*
		 * Declare the object type Predictor (Sxx, Syy, Sxy)
		 */

		return null;
	}

}
