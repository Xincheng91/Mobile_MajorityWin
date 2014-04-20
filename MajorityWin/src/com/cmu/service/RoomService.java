package com.cmu.service;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import com.cmu.bean.RoomBean;
import com.cmu.bean.RoomBean.Node;

public class RoomService {
	private static ConcurrentHashMap<String, RoomBean> votingRooms = new ConcurrentHashMap<String, RoomBean>();
	
	public static void createRoom(String roomID, RoomBean room){
		votingRooms.put(roomID, room);
	}

	public static boolean isRoomExist(String roomID){
		if(!votingRooms.containsKey(roomID)){
			return false;
		}
		return true;
	}
	
	public static void addPeopleToRoom(String people, String roomID){
		votingRooms.get(roomID).addPeople(people);
	}
	
	public static ArrayList<String> getPeopleInRoom(String roomID){
		return votingRooms.get(roomID).getPeople();
	}
	
	public static int getStatusOfRoom(String roomID){
		return votingRooms.get(roomID).getStatus();
	}
	
	public static String getLeaderOfRoom(String roomID){
		return votingRooms.get(roomID).getLeader();
	}
	
	public static void closeRoom(String roomID){
		votingRooms.remove(roomID);
	}

	public static void randomPickLeader(String roomID) {
		ArrayList<String> people  = getPeopleInRoom(roomID);
		int rand = new Random().nextInt(people.size());
		votingRooms.get(roomID).setStatus(1);
		votingRooms.get(roomID).setLeader(people.get(rand));
	}
	
	public static String getQuestions(String roomID){
		return votingRooms.get(roomID).getQuestions();
	}

	public static String changeLeader(String roomID, String currentLeader) {
		ArrayList<String> people  = getPeopleInRoom(roomID);
		int num = 0;
		while(true){
			if(people.size() == 1){
				break;
			}
			int rand = new Random().nextInt(people.size());
			if(!people.get(rand).equals(currentLeader)){
				num = rand;
				break;
			}
		}
		return people.get(num);
	}

	public static void setQuestion(String roomID, String questions) {
		votingRooms.get(roomID).setQuestion(questions);
	}

	public static void setStatus(String roomID, int i) {
		votingRooms.get(roomID).setStatus(i);
	}

	public static void vote(String roomID, int option) {
		votingRooms.get(roomID).voteForOption(option);
	}

	public static int getNumOfFinishOfRoom(String roomID) {
		return votingRooms.get(roomID).getFinished();
	}

	public static int getNumOfMajorityOfRoom(String roomID) {
		return votingRooms.get(roomID).getMajority();
	}

	public static String getResult(String roomID) {
		return votingRooms.get(roomID).getFinalResult();
	}
}
