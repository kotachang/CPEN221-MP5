package ca.ece.ubc.cpen221.mp5;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleBiFunction;
import java.util.stream.Stream;

public class Predictor<T> implements ToDoubleBiFunction<MP5Db<T>, String> {
	double b;
	double a;
	
	public Predictor(double b, double a) {
		this.b = b;
		this.a = a;
	}

	@Override
	public double applyAsDouble(MP5Db<T> t, String u) {
		
		
		return 0;
	}

}
