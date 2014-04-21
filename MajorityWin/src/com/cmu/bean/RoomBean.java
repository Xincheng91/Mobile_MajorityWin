package com.cmu.bean;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.cmu.constants.Constants.RoomStatus;

//we might have to provide a method to start another round, which means clear results
public class RoomBean {
	private String roomID;
	private int roomSize;
	private RoomStatus status;
	private PersonBean leader;
	private String question;
	private ArrayList<PersonBean> people;
	private ArrayList<Option> options;

	public RoomBean(String roomID, int roomSize, ArrayList<PersonBean> people) {
		super();
		this.setRoomID(roomID);
		this.status = RoomStatus.NEW_ROOM;
		this.roomSize = roomSize;
		this.people = people;
		this.options = new ArrayList<Option>();
	}

	public void addPeople(PersonBean p) throws IllegalStateException {
		if (people.size() > roomSize) {
			throw new IllegalStateException("The room is full");
		}
		people.add(p);
	}

	public ArrayList<PersonBean> getPeople() {
		return people;
	}

	public RoomStatus getStatus() {
		return status;
	}

	public PersonBean getLeader() {
		return leader;
	}

	public void setStatus(RoomStatus i) {
		this.status = i;
	}

	public void setLeader(PersonBean leader) {
		this.leader = leader;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String q) {
		this.question = q;
		JSONObject jsObject = (JSONObject) JSONValue.parse(q);
		String option1 = (String) jsObject.get("option1");
		String option2 = (String) jsObject.get("option2");
		String option3 = (String) jsObject.get("option3");
		options.add(new Option(option1));
		options.add(new Option(option2));
		options.add(new Option(option3));
	}

	public void voteForOption(int option) {
		options.get(option).votedNum++;
		if (getFinished() >= people.size()
				|| getMajority() > (people.size() / 2)) {
			status = RoomStatus.VOTED;
		}
	}

	public ArrayList<Option> getOptions() {
		return options;
	}

	public String getFinalResult() {
		String finalResult = new String();
		int max = 0;
		for (int i = 0; i < options.size(); i++) {
			if (options.get(i).getVotedNum() > max) {
				max = options.get(i).getVotedNum();
				finalResult = options.get(i).option;
			}
		}
		return finalResult;
	}

	public int getMajority() {
		int max = 0;
		for (int i = 0; i < options.size(); i++) {
			max = Math.max(max, options.get(i).getVotedNum());
		}
		return max;
	}

	public int getFinished() {
		int total = 0;
		for (int i = 0; i < options.size(); i++) {
			total += options.get(i).getVotedNum();
		}
		return total;
	}

	public String getRoomID() {
		return roomID;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}

	public class Option {
		String option;
		int votedNum;

		public Option(String option) {
			this.option = option;
			this.votedNum = 0;
		}

		public int getVotedNum() {
			return votedNum;
		}
	}
}
