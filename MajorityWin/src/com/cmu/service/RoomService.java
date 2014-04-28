package com.cmu.service;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import com.cmu.bean.PersonBean;
import com.cmu.bean.RoomBean;
import com.cmu.constants.Constants;
import com.cmu.constants.Constants.RoomStatus;

public class RoomService {
	private static ConcurrentHashMap<String, PersonBean> users = new ConcurrentHashMap<String, PersonBean>();
	private static ConcurrentHashMap<String, RoomBean> votingRooms = new ConcurrentHashMap<String, RoomBean>();

	public static boolean login(String username, String password) {
		if (users.containsKey(username)
				&& users.get(username).getPassword().equals(password)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean register(String username, String password) {
		if (users.containsKey(username)) {
			return false;
		}
		PersonBean pb = new PersonBean(username, password);
		users.put(username, pb);
		System.out.println("Register: " + users);
		return true;
	}

	public static PersonBean getPerson(String username)
			throws IllegalStateException {
		if (users.containsKey(username)) {
			return users.get(username);
		} else {
			throw new IllegalStateException("No such user");
		}
	}

	public static String createRoom(int roomSize) {
		int roomID = new Random().nextInt(Constants.roomNumberRand);
		while (votingRooms.containsKey(String.valueOf(roomID))) {
			roomID = new Random().nextInt(Constants.roomNumberRand);
		}
		String roomIDStr = String.valueOf(roomID);
		votingRooms.put(roomIDStr, new RoomBean(roomIDStr, roomSize));
		System.out.println("createRoom: " + roomID);
		return roomIDStr;
	}

	public static boolean isRoomExist(String roomID) {
		if (!votingRooms.containsKey(roomID)) {
			return false;
		}
		return true;
	}

	public static RoomBean getRoom(String roomID) {
		if (votingRooms.containsKey(roomID)) {
			return votingRooms.get(roomID);
		} else {
			return null;
		}
	}

	public static void addPeopleToRoom(PersonBean person, String roomID) {
		votingRooms.get(roomID).addPeople(person);
	}

	public static ArrayList<PersonBean> getPeopleInRoom(String roomID) {
		return votingRooms.get(roomID).getPeople();
	}

	public static RoomStatus getStatusOfRoom(String roomID) {
		return votingRooms.get(roomID).getStatus();
	}

	public static int getRoomSize(String roomID) {
		return votingRooms.get(roomID).getRoomSize();
	}

	public static void setRoomSize(String roomID) {
		votingRooms.get(roomID).setRoomSize();
	}

	public static PersonBean getLeaderOfRoom(String roomID) {
		return votingRooms.get(roomID).getLeader();
	}

	public static void closeRoom(String roomID) {
		votingRooms.remove(roomID);
	}

	public static void randomPickLeader(String roomID) {
		ArrayList<PersonBean> people = getPeopleInRoom(roomID);
		int rand = new Random().nextInt(people.size());
		votingRooms.get(roomID).setStatus(RoomStatus.LEADER_CHOSEN);
		votingRooms.get(roomID).setLeader(people.get(rand));
		System.out.println("randomPickLeader: "
				+ people.get(rand).getUsername());
	}

	public static PersonBean changeLeader(String roomID,
			PersonBean currentLeader) {
		ArrayList<PersonBean> people = getPeopleInRoom(roomID);
		int num = 0;
		while (true) {
			if (people.size() == 1) {
				break;
			}
			int rand = new Random().nextInt(people.size());
			if (!people.get(rand).equals(currentLeader)) {
				num = rand;
				break;
			}
		}
		votingRooms.get(roomID).setLeader(people.get(num));
		System.out.println("changeLeader: " + people.get(num).getUsername());
		return people.get(num);
	}

	public static String getQuestions(String roomID) {
		return votingRooms.get(roomID).getQuestion();
	}

	public static void setQuestion(String roomID, String questions) {
		votingRooms.get(roomID).setQuestion(questions);
	}

	public static void setStatus(String roomID, RoomStatus i) {
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

	public static ArrayList<String> getResult(String roomID) {
		return votingRooms.get(roomID).getFinalResult();
	}

	public static boolean startNewRound(PersonBean person, String roomID) {
		if (votingRooms.containsKey(roomID)) {
			return votingRooms.get(roomID).startNewRound(person);
		} else {
			return false;
		}
	}

	public static boolean exitRoom(PersonBean person, String roomID) {
		if (votingRooms.containsKey(roomID)) {
			return votingRooms.get(roomID).exitRoom(person);
		} else {
			return false;
		}
	}
}
