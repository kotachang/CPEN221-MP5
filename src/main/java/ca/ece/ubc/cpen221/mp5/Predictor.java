package ca.ece.ubc.cpen221.mp5;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleBiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * RI: predicted rating value is for the specific user, and the specific retaurant, result of the prediction is within the range of 1 and 5 including 1 and 5. 
 * AF: applyAsDouble function returns predicted rating of a restaurant based
 * on the prices
 *
 * @param <T>
 *            argument general type T as MP5Db
 */
public class Predictor<T> implements ToDoubleBiFunction<MP5Db<T>, String> {
	double a;
	double b;
	
	public Predictor(double a, double b) {
		this.a = a;
		this.b = b;
	}

	/**
	 * @param MP5Db<T>
	 *            database interface
	 * @param u
	 *            = String = businessID
	 * Required: String u is a valid businessID. arguments are not null. 
	 * 
	 * @return predicted double rating that the user might give to the restaurant if
	 *         the predicted rating is less than 1, return 1. if the predicted
	 *         rating is greater than 5, return 5.
	 */
	@Override
	public double applyAsDouble(MP5Db<T> t, String u) {
		YelpDB yelpDb = (YelpDB) t;

		List<Business> businesses = new ArrayList<Business>(yelpDb.getBusinesses());
		List<Business> b = businesses.stream().filter(r -> r.getId().equals(u)).collect(Collectors.toList());
		Business restaurant = b.get(0);
		double price = (double) restaurant.getPrice();
		double returnPrice = (this.a * price + this.b);

		if (returnPrice < 1) {
			return 1.0;
		} else if (returnPrice > 5) {
			return 5.0;
		}

		return returnPrice;
	}

}
