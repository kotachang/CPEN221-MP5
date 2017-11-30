package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class GeneralDb<T> implements MP5Db<T> {

	private List<Business> businesses;
	private List<Review> reviews;
	private List<User> users;
	private static final double R = 6371 * 1000;// in meters

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
		business.setOpen(data.getInt("is_open"));

		// Recursive types
		JsonArray categories = data.getJsonArray("categories");
		for (int i = 0; i < categories.size(); i++) {
			business.addCategory(categories.getString(i));
		}
		JsonArray attributes = data.getJsonArray("attributes");
		for (int i = 0; i < attributes.size(); i++) {
			business.addAttribute((JsonObject) attributes.get(i));
		}

		// Hours
		Map<String, String> hours = new HashMap<String, String>();

		JsonArray hoursArray = data.getJsonArray("hours");
		for (int i = 0; i < hoursArray.size(); i++) {
			hours.put(hoursArray.get(i).toString(), hoursArray.getString(i));
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
		User user = new User(data.getString("name"));
		user.setId(data.getString("user_id"));
		user.accountDate(data.getString("yelping_since"));

		JsonArray friends = data.getJsonArray("friends");
		for (int i = 0; i < friends.size(); i++) {
			user.addFriend(friends.getString(i));
		}

		return user;
	}

	/**
	 * Populates the users from a given JSON file.
	 * 
	 * @throws IOException
	 */
	private void populateUsers(String filePath) throws IOException {
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
		
		Review review = new Review(data.getString("business_id"));
		review.setUser(data.getString("user_id"));
		review.setId(data.getString("review_id"));
		review.setStars(data.getInt("stars"));
		review.setDate(data.getString("date"));
		review.setReviewRating(data.getInt("useful"), data.getInt("funny"), data.getInt("cool"));
		review.setText(data.getString("text"));
		
		//Adds review to business
		Business change = new Business(data.getString("business_id"));
		for(Business b : this.businesses) {
			if(b.equals(change)) {
				change = b;
				break;
			}
		}
		this.businesses.remove(change);
		change.addReview(review);
		this.businesses.add(change);
		
		//Adds review to user
		User user = new User(data.getString("lol"));
		user.setId(data.getString("user_id"));
		for(User u : this.users) {
			if(u.equals(user)) {
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
	private void populateReviews(String filePath) throws IOException {
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
		String weight = "1";
		String result = "[{";
		List<Cluster> l = new ArrayList<Cluster>();
		l = firstCluster(k);

		for (int i = 0; i < l.size(); i++) {
			List<Business> b = new ArrayList<Business>(l.get(i).businesses);
			for (int a = 0; a < b.size(); a++) {
				/**
				 * List l should have a list of the different clusters that are made,
				 * 
				 * so the List b should be the list of businesses that are in the particular
				 * cluster so if u want a particular info of a business u should be able to get
				 * it by doing something like b.get(a).getCoordinates().Lat(); < something like
				 * this
				 * 
				 * the formatting that we need is the file called "voronoi.json" within
				 * "visualize" folder or under README.md (I moved the file around to run the
				 * python thingy but not sure if it's moved for u as well so just check both?)
				 * 
				 */
			}
		}
		return null;
	}

	public List<Cluster> firstCluster(int nk) {
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
	 */
	@Override
	public ToDoubleBiFunction getPredictorFunction(String user) {
		// TODO Auto-generated method stub
		return null;
	}

}
