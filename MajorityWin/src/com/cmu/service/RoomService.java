package com.cmu.service;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import com.cmu.bean.RoomBean;

public class RoomService {
	private static ConcurrentHashMap<String, RoomBean> votingRooms = new ConcurrentHashMap<String, RoomBean>();
	
	public static void createRoom(String roomID, RoomBean room){
		if(votingRooms.containsKey(roomID)){
			throw new IllegalStateException("The room has already been created");
		}else{
			votingRooms.put(roomID, room);
		}
	}

	public static boolean isRoomExist(String roomID){
		if(!votingRooms.containsKey(roomID)){
			return false;
		}
		return true;
	}
	
	public static void addPeopleToRoom(String people, String roomID){
		try {
			votingRooms.get(roomID).addPeople(people);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static ArrayList<String> getPeopleInRoom(String roomID){
		return votingRooms.get(roomID).getPeople();
	}
	
	public static void closeRoom(String roomID){
		votingRooms.remove(roomID);
	}
}
