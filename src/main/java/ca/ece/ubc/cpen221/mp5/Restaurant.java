package ca.ece.ubc.cpen221.mp5;

import java.util.ArrayList;
import java.util.List;

public class Restaurant extends Business {
	/**
	 * @AF: A restaurant is created by the parameters: businessId, name, address,
	 *      url Other characteristics such as neighborhoods, reviews can be added
	 *      later, and are not necessary to represent a business.
	 * 
	 *      For example, a restaurant named "McDonalds" with businessId "1357"
	 *      located at "123 ABC St. Vancouver, BC V1A 2B3" with site url "yelp.blah"
	 *      is fully characterized, but as users add reviews and associate
	 *      neighborhoods and price ratings, the characteristics grow.
	 * 
	 * @RI: this type of business needs to be of subtype restaurant id is not null,
	 *      and is unique to this restaurant in its database name is not null, but
	 *      not necessarily unique (there could be 2 different locations of
	 *      McDonalds, with different reviews etc.) address is not null price is 0
	 *      if not assigned yet. url is not null, but photourl can be null if there
	 *      is no photo. reviews can be an empty list, but should not be null and
	 *      should not contain null reviews.
	 *
	 */

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
