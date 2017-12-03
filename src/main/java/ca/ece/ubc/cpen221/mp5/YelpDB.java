package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class YelpDB extends GeneralDb<Restaurant> {
	/**
	 * @AF: businesses = List of restaurants contained in the database reviews =
	 *      List of reviews contained in the database, each being associated with a
	 *      user and a business in the database users = List of users contained in
	 *      the database,
	 * 
	 *      For example, a Business named "McDonalds" with business Id "1234" might
	 *      have a review with id "4321" written by a user "John" with user id
	 *      "ABCD".
	 * 
	 * @RI: a Restaurant contained in businesses is not null the businessFile is a
	 *      JSON file that contains specific fields that describe and provide
	 *      details about the business the Business must be of subtype Restaurant
	 *      for this database.
	 * 
	 *      a Review contained in reviews is not null the reviewFile is a JSON file
	 *      that contains specific fields that describe and provide details about
	 *      the review a Review must have an associated user and an associated
	 *      business
	 * 
	 *      a User contained in users is not null the userFile is a JSON file that
	 *      contains specific fields that describe and provide details about the
	 *      user
	 *
	 */

	/**
	 * Creates the database from the provided business, user, and review files,
	 * where all businesses must be restaurants.
	 * 
	 * @param businessFile
	 *            string for the file path to the restaurant data file
	 * @param userFile
	 *            string for the file path to the user data file
	 * @param reviewFile
	 *            string for the file path to the review file
	 * @throws IOException
	 */
	public YelpDB(String businessFile, String userFile, String reviewFile) throws IOException {
		super(businessFile, userFile, reviewFile);
	}

	/**
	 * 
	 * Creates a business(restaurant) from the input JsonObject
	 * 
	 * @param data
	 *            JsonObject containing the business(restaurant) information
	 * @return the business(restaurant) parsed from the information
	 * 
	 */
	@Override
	public Business parseBusiness(JsonObject data) {
		// Address array
		String[] address = new String[5];
		address[0] = data.getString("full_address");
		address[1] = data.getString("city");
		address[2] = data.getString("state");
		address[3] = data.getJsonNumber("latitude").toString();
		address[4] = data.getJsonNumber("longitude").toString();

		// Standard characteristics
		Restaurant business = new Restaurant(data.getString("business_id"));
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
		if (business.categories().contains("Restaurants")) {
			return (Business) business;
		} else
			return null;
	}

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

		// Adds review to business
		Restaurant change = new Restaurant(data.getString("business_id"));
		Restaurant currentR = new Restaurant("nothing");
		for (Business b : this.businesses) {
			currentR = (Restaurant) b;
			if (change.equals(b)) {
				change = (Restaurant) b;
				break;
			}
		}
		if (currentR.equals(change)) {
			this.businesses.remove(change);
			change.addReview(review);
			this.businesses.add(change);
		}

		// Adds review to user
		User user = new User(data.getString("user_id"));
		User currentU = new User("nothing");
		for (User u : this.users) {
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

}
