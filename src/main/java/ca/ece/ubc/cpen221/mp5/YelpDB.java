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

	public YelpDB(String businessFile, String userFile, String reviewFile) throws IOException {
		super(businessFile, userFile, reviewFile);
	}

	private Restaurant parseRestaurant(JsonObject data) {
		// Address array
		// Address array
		String[] address = new String[5];
		address[0] = data.getString("full_address");
		address[1] = data.getString("city");
		address[2] = data.getString("state");
		address[3] = data.getJsonNumber("latitude").toString();
		address[4] = data.getJsonNumber("longitude").toString();

		// Standard characteristics
		Restaurant business = new Restaurant(data.getString("business_id"));
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

		JsonArray categories = data.getJsonArray("categories");
		for (int i = 0; i < categories.size(); i++) {
			business.addCategory(categories.getString(i));
		}

		if (categories.contains("Restaurants") == true) {
			return business;
		} else
			return null;
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
