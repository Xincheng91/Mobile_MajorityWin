package com.cmu.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			response.getOutputStream().write(new String("NotExist").getBytes());
			return;
		}
		ArrayList<String> people = RoomService.getPeopleInRoom(roomID);
		String result = new String();
		if (people.size() == 1) {
			result = people.get(0);
		} else if (people.size() > 1) {
			for (String p : people) {
				result = result + p + ",";
			}
		}
		response.getOutputStream().write(result.getBytes());
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
