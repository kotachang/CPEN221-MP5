package ca.ece.ubc.cpen221.mp5;

import java.util.function.ToDoubleBiFunction;

public class Predictor<T> implements ToDoubleBiFunction<MP5Db<T>, String> {
	
	public Predictor() {

	}

	@Override
	public double applyAsDouble(MP5Db<T> t, String u) {

		return 0;
	}

}
