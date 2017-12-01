package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class YelpDB<Restuarant> extends GeneralDb<Restaurant> {

	public YelpDB() {
		super();
	}
	
	@Override
	public void populateDatabase(String businessFile, String userFile, String reviewFile) throws IOException {
		this.populateRestaurants(businessFile);
		super.populateUsers(userFile);
		super.populateReviews(reviewFile);
	}

	private Restaurant parseRestaurant(JsonObject data) {
		// Address array
		String[] address = new String[6];
		address[0] = data.getString("address");
		address[1] = data.getString("city");
		address[2] = data.getString("state");
		address[3] = data.getString("postal code");
		address[4] = data.getString("latitude");
		address[5] = data.getString("longitude");

		// Standard characteristics
		Restaurant business = new Restaurant(data.getString("business_id"));
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

		if (categories.contains("Restaurants") == true) {
			return business;
		}
		else return null;
	}
	
	private void populateRestaurants(String filePath) throws IOException {
		File file = new File(filePath);
		BufferedReader fileReader = new BufferedReader(new FileReader(file));
		String line;
		while ((line = fileReader.readLine()) != null) {
			StringReader sr = new StringReader(line);
			JsonReader parseFile = Json.createReader(sr);
			JsonObject data = parseFile.readObject();
			Restaurant newRestaurant = this.parseRestaurant(data);
			this.addBusiness(newRestaurant);
		}
		fileReader.close();
	}

}
