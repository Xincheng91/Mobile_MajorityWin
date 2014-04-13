package com.cmu.service;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import com.cmu.bean.RoomBean;

public class RoomService {
	private static ConcurrentHashMap<Integer, RoomBean> votingRooms = new ConcurrentHashMap<Integer, RoomBean>();
	
	public static void createRoom(int roomID, RoomBean room){
		if(votingRooms.containsKey(roomID)){
			throw new IllegalStateException("The room has already been created");
		}else{
			votingRooms.put(roomID, room);
		}
	}
	
	public static void addPeopleToRoom(String people, int roomID){
		if(!votingRooms.containsKey(roomID)){
			throw new IllegalStateException("The room doesn't exist");
		}
		try {
			votingRooms.get(roomID).addPeople(people);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static ArrayList<String> getPeopleInRoom(int roomID){
		if(!votingRooms.containsKey(roomID)){
			throw new IllegalStateException("The room doesn't exist");
		}
		return votingRooms.get(roomID).getPeople();
	}
	
	public static void closeRoom(int roomID){
		if(!votingRooms.containsKey(roomID)){
			throw new IllegalStateException("The room doesn't exist");
		}
		votingRooms.remove(roomID);
	}
}
