package com.cmu.bean;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

//we might have to provide a method to start another round, which means clear results
public class RoomBean {
	private String roomID;
	private int roomSize;
	//0:newRoom(No leader), 1:has leader(No question submit) 2:question submitted(haven't got all voting) 3: get MajorityVote or getAllVote
	private int status;
	private String leader;
	private String questions;
	private ArrayList<String> people;
	private ArrayList<Node> options;
	
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
	
	public String getLeader(){
		return leader;
	}

	public void setStatus(int i) {
		this.status = i;
	}
	
	public void setLeader(String leader){
		this.leader  = leader;
	}
	
	public String getQuestions(){
		return questions;
	}

	public void setQuestion(String q) {
		this.questions = q;
		JSONObject jsObject = (JSONObject) JSONValue.parse(q);
		String option1 = (String) jsObject.get("option1");
		String option2 = (String) jsObject.get("option2");
		String option3 = (String) jsObject.get("option3");
		options.add(new Node(option1));
		options.add(new Node(option2));
		options.add(new Node(option3));
	}
	
	public class Node{
		String option;
		int votedNum;
		public Node(String option){
			this.option = option;
		}
		
		public int getVotedNum(){
			return votedNum;
		}
	}

	public void voteForOption(int option) {
		options.get(option).votedNum++;
		int total = 0;
		for(int i = 0; i < options.size(); i++){
			total += options.get(i).votedNum;
		}
		if(total >= (people.size()/2 + 1)){
			status = 3;
			
		}
	}
	
	public ArrayList<Node> getOptions(){
		return options;
	}

	public String getFinalResult() {
		String finalResult = new String();
		int max = 0;
		for(int i = 0; i < options.size(); i++){
			if(options.get(i).getVotedNum()>max){
				max = options.get(i).getVotedNum();
				finalResult = options.get(i).option;
			}
		}
		return finalResult;
	}
	
	public int getMajority() {
		int max = 0;
		for(int i = 0; i < options.size(); i++){
			max=Math.max(max, options.get(i).getVotedNum());
		}
		return max;
	}
	
	public int getFinished() {
		int total = 0;
		for(int i = 0; i < options.size(); i++){
			total+=options.get(i).getVotedNum();
		}
		return total;
	}
}
