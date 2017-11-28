package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class YelpDB<Restuarant, T> extends GeneralDb<T> {

	public YelpDB() {
		super();
	}

	public void populateDatabase(String filePath) throws IOException {
		File businesses = new File(filePath);
		BufferedReader restaurantReader = new BufferedReader(new FileReader(businesses));
		String line;
		while ((line = restaurantReader.readLine()) != null) {
			StringReader sr = new StringReader(line);
			JsonReader parseRestaurant = Json.createReader(sr);
			JsonObject restaurant = parseRestaurant.readObject();
		}
		restaurantReader.close();
	}
	
	public void populateResaurants() {
		
	}
	
	public void populateUsers() {
		
	}
	
	public void populateReviews() {
		
	}
}
