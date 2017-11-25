package ca.ece.ubc.cpen221.mp5;

import java.util.Map;

public class Review {

	private String user;
	private String business;
	private int rating;
	private String date;
	private Map<String,Integer> attributes;
	
	public Review(String businessId) {
		this.business = businessId;
	}
	
	public void changeUser(String id) {
		this.user = id;
	}
	
	public void changeRating(int star) {
		this.rating = star;
	}
	
	public int rating() {
		return rating;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public Map<String, Integer> attributes(){
		return attributes;
	}
	
	private boolean addAttribute(String attribute) {
		if(attributes.containsKey(attribute)) {
			return false;
		}
		else {
			attributes.put(attribute, 0);
			return true;
		}
	}
	
	public void voteAttribute(String attribute) {
		if(!this.addAttribute(attribute)) {
			attributes.replace(attribute, attributes.get(attribute)+1);
		}
	}
	
}
