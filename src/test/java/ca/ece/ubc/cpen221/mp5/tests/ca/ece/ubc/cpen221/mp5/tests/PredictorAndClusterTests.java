package ca.ece.ubc.cpen221.mp5.tests.ca.ece.ubc.cpen221.mp5.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.ToDoubleBiFunction;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.Business;
import ca.ece.ubc.cpen221.mp5.Cluster;
import ca.ece.ubc.cpen221.mp5.Coordinate;
import ca.ece.ubc.cpen221.mp5.GeneralDb;
import ca.ece.ubc.cpen221.mp5.MP5Db;
import ca.ece.ubc.cpen221.mp5.Predictor;
import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.YelpDB;

public class PredictorAndClusterTests {

	@Test
	public void test0() throws IOException {
		ToDoubleBiFunction<MP5Db<String>, String> predict = new Predictor<String>(1, 2);
		String rest = "data/restaurants.json";
		String user = "data/users.json";
		String review = "data/reviews.json";
		YelpDB db = new YelpDB(rest, user, review);

		System.out.println(db.kMeansClusters_json(10));
	}

	@Test
	public void predictionTest0() throws IOException {

	}
}
