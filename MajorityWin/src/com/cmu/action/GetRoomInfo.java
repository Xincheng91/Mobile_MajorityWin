package com.cmu.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.cmu.bean.PersonBean;
import com.cmu.constants.Constants.RoomStatus;
import com.cmu.service.RoomService;

/**
 * Servlet implementation class GetRoomInfo
 */
@WebServlet("/GetRoomInfo")
public class GetRoomInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetRoomInfo() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String roomID = request.getParameter("roomID");
		if (!RoomService.isRoomExist(roomID)) {
			response.getOutputStream().write(new String().getBytes());
			return;
		}
		ArrayList<PersonBean> people = RoomService.getPeopleInRoom(roomID);
		String result = new String();
		if (people.size() == 1) {
			result = people.get(0).getUsername();
		} else if (people.size() > 1) {
			for (PersonBean p : people) {
				result = result + p.getUsername() + ",";
			}
		}
		RoomStatus status = RoomService.getStatusOfRoom(roomID);
		System.out.println("GetRoomInfo: " + status + " " + status.ordinal());
		PersonBean leader = RoomService.getLeaderOfRoom(roomID);
		JSONObject jsObject = new JSONObject();
		jsObject.put("participants", result);
		jsObject.put("status", status.ordinal());
		jsObject.put("leader", leader == null ? null : leader.getUsername());
		response.getOutputStream().write(jsObject.toJSONString().getBytes());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
