package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.File;
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
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

	/**
	 * 
	 * @return list of all businesses in the database
	 */
	public List<Business> getBusinesses() {
		return new ArrayList<Business>(businesses);
	}

	/**
	 * 
	 * @return list of all reviews in the database
	 */
	public List<Review> getReviews() {
		return new ArrayList<Review>(reviews);
	}

	/**
	 * Adds a user ot the database
	 * 
	 * @param user
	 *            represents the user that will be added to the database. Should
	 *            throw error if the username is already contained in the database.
	 */
	public void addUser(User user) {
		this.users.add(user);
	}

	/**
	 * Adds a business to the database
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
	 * Adds a review to the database
	 * 
	 * @param review
	 *            represents a review to be added to the database. Will also add
	 *            associated businesses that are not already in the database to the
	 *            database
	 */
	public void addReview(Review review) {
		this.reviews.add(review);
	}

	/**
	 * 
	 * Creates a business from the input JsonObject
	 * 
	 * @param data
	 *            JsonObject containing the business information
	 * @return the business parsed from the information
	 */
	public Business parseBusiness(JsonObject data) {

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
			this.addBusiness((Business) newBusiness);
		}
		fileReader.close();
	}

	/**
	 * Creates a user from the input data
	 * 
	 * @param data
	 *            JsonObject containing the user information
	 * @return the user parsed from the data
	 */
	private User parseUser(JsonObject data) {
		// Standard Characteristics
		User user = new User(data.getString("user_id"));
		user.setURL(data.getString("url"));
		JsonObject votes = data.getJsonObject("votes");
		user.addVote(votes.getInt("funny"), votes.getInt("useful"), votes.getInt("cool"));
		user.setName(data.getString("name"));

		return user;
	}

	/**
	 * 
	 * Populates the users from a given Json file.
	 * 
	 * @param filePath
	 *            the file path of the input user data file (JSON)
	 * @throws IOException
	 *             if the file path is not a file
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

	/**
	 * Creates a reivew from the given data
	 * 
	 * @param data
	 *            JsonObject containing the review information
	 * @return the review parsed from the data
	 */
	public Review parseReview(JsonObject data) {

		// Standard characteristics
		Review review = new Review(data.getString("review_id"));
		review.setUser(data.getString("user_id"));
		review.setBusiness(data.getString("business_id"));
		review.setStars(data.getInt("stars"));
		review.setDate(data.getString("date"));
		JsonObject votes = data.getJsonObject("votes");
		review.setReviewRating(votes.getInt("useful"), votes.getInt("funny"), votes.getInt("cool"));
		review.setText(data.getString("text"));

		// Adds review to business associated with the review
		Business change = new Business(data.getString("business_id"));
		Business currentB = new Business("nothing");
		for (Business b : this.businesses) {
			// finds the business in the database, makes a copy of it, adds the review, and
			// replaces it back into the database
			currentB = b;
			if (b.equals(change)) {
				change = b;
				break;
			}
		}
		if (currentB.equals(change)) {
			this.businesses.remove(change);
			change.addReview(review);
			this.businesses.add(change);
		}

		// Adds review to user
		User user = new User(data.getString("user_id"));
		User currentU = new User("nothing");
		for (User u : this.users) {
			// finds the user in the database, makes a copy of it, adds the review, and
			// replaces it back into the database
			currentU = u;
			if (u.equals(user)) {
				user = u;
				break;
			}
		}
		if (currentU.equals(user)) {
			this.users.remove(user);
			user.addReview(review);
			this.users.add(user);
		}
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
		String result = "[";
		List<Cluster> l = new ArrayList<Cluster>();

		l = Cluster(k);

		for (int i = 0; i < l.size(); i++) {
			List<Business> b = new ArrayList<Business>(l.get(i).getBusinesses());
			for (int a = 0; a < b.size(); a++) {
				if (!(a == 0 && i == 0)) {
					result += ",";
				}
				JsonObject restaurant = Json.createObjectBuilder().add("x", b.get(a).getCoordinates().Lat())
						.add("y", b.get(a).getCoordinates().Long()).add("name", b.get(a).name()).add("cluster", i + 1)
						.add("weight", 1.0).build();

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

	/**
	 * 
	 * @param nk
	 * @return
	 */
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

	/**
	 * 
	 * @param l
	 * @param l2
	 * @return
	 */
	// PUT THIS SOMEWHERE NOT HERE LOL
	public boolean equals(List<Cluster> l, List<Cluster> l2) {
		for (int i = 0; i < l.size(); i++) {
			if (!(l.get(i).equals(l2.get(i)))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param c
	 * @param c2
	 * @return
	 */
	// PUT THIS SOMEWHERE NOT HERE LOL
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

		/*
		 * Keep track of a business's specific review written by the user from the input
		 * argument. ** rep invariant for review = only 1 review per user per business
		 */

		List<Review> tempListofReviews = new ArrayList<Review>();
		List<Business> business = new ArrayList<Business>();
		List<Review> review = new ArrayList<Review>();

		for (int i = 0; i < this.businesses.size(); i++) {
			tempListofReviews = this.businesses.get(i).reviews();
			for (int a = 0; a < tempListofReviews.size(); a++) {
				if (tempListofReviews.get(a).getUser().equals(user)) {

					business.add(businesses.get(i));
					review.add(tempListofReviews.get(a));
				}
			}
			tempListofReviews = new ArrayList<Review>();
		}

		List<Double> prices = business.stream().map(p -> (double) p.getPrice()).collect(Collectors.toList());
		List<Double> stars = review.stream().map(r -> (double) r.stars()).collect(Collectors.toList());

		double sumX = 0;
		for (int i = 0; i < prices.size(); i++) {
			sumX += prices.get(i);
		}
		double meanX = sumX / prices.size();

		double sumY = 0;
		for (int i = 0; i < stars.size(); i++) {
			sumY += stars.get(i);
		}
		double meanY = sumY / stars.size();

		double Sxx = prices.stream().reduce(0.0, (x, p) -> x + Math.pow(p - meanX, 2));
		double Syy = stars.stream().reduce(0.0, (y, s) -> y + Math.pow(s - meanY, 2));
		double Sxy = 0.0;
		for (int i = 0; i < prices.size(); i++) {
			Sxy += (prices.get(i) - meanX) * (stars.get(i) - meanY);
		}

		double b = Sxy / Sxx;
		double a = meanY - b * meanX;
		double rSquared = Math.sqrt(Math.pow(Sxy, 2) / (Sxx * Syy));

		return null;
	}

}
