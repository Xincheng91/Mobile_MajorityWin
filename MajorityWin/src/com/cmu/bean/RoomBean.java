package com.cmu.bean;

import java.util.ArrayList;

public class RoomBean {
	private int RoomID;
	private int RoomSize;
	private ArrayList<String> people;
	
	public RoomBean(int roomID, int roomSize, ArrayList<String> people) {
		super();
		RoomID = roomID;
		RoomSize = roomSize;
		this.people = people;
	}
	
	public void addPeople(String p){
		if(people.size() > RoomSize){
			throw new IllegalStateException("The room is full");
		}
		people.add(p);
	}
	
	public ArrayList<String> getPeople(){
		return people;
	}
}
