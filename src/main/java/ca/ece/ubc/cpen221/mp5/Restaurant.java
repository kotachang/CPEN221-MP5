package ca.ece.ubc.cpen221.mp5;

import java.util.ArrayList;
import java.util.List;

public class Restaurant extends Business {

	/**
	 * Constructs a new restaurant
	 * 
	 * @param id
	 *            a unique id associated with the restaurant
	 */
	public Restaurant(String id) {
		super(id);
	}

	@Override
	public boolean equals(Object business) {
		if (!(business instanceof Restaurant)) {
			return false;
		}
		Business thatBusiness = (Business) business;
		return (super.getId().equals(thatBusiness.getId()));
	}

}
