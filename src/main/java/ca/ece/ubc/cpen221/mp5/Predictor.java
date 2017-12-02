package ca.ece.ubc.cpen221.mp5;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleBiFunction;
import java.util.stream.Stream;

public class Predictor<T> implements ToDoubleBiFunction<MP5Db<T>, String> {
	double a;
	double b;

	public Predictor(double a, double b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public double applyAsDouble(MP5Db<T> t, String u) {
		YelpDB yelpDb = (YelpDB) t;

		List<Business> businesses = new ArrayList<Business>(yelpDb.getBusinesses());
		Business restaurant = (Business) businesses.stream().filter(r -> r.getId().equals(u));

		double price = (double) restaurant.getPrice();

		return (this.a * price + this.b);
	}

}
