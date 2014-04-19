package com.cmu.bean;

import java.util.ArrayList;
import java.util.HashMap;

//we might have to provide a method to start another round, which means clear results
public class RoomBean {
	private String roomID;
	private int roomSize;
	//0:newRoom(No leader), 1:has leader(No question submit) 2:question submitted(haven't got all voting) 3: get 
	private int status;
	private String leader;
	private String questions;
	//We need a data structure to store the voteResult
	//I think two hashmap is most suitable 
	//because 1. with map from integer to integer as voteresult, it's easier to submit voteResult from client side
	//2. it's easy to result back the answer finally
	//private HashMap<Integer, Integer> voteResult;
	//private HashMap<Integer, String> optionInfo;
	private ArrayList<String> people;
	
	public RoomBean(String roomID, int roomSize, ArrayList<String> people) {
		super();
		this.roomID = roomID;
		this.status = 0;
		this.roomSize = roomSize;
		this.people = people;
		//voteResult = new HashMap<String, Integer>();
	}
	
	public void addPeople(String p){
		if(people.size() > roomSize){
			throw new IllegalStateException("The room is full");
		}
		people.add(p);
	}
	
	public ArrayList<String> getPeople(){
		return people;
	}
	
	public int getStatus(){
		return status;
	}
}
