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
	private static final long servialUID = 3L;

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
		int id = Integer.parseInt(roomID.trim());
		if (!RoomService.isRoomExist(id)) {
			response.getOutputStream().write(new String("NotExist").getBytes());
			return;
		}
		ArrayList<String> people = RoomService.getPeopleInRoom(id);
		String result = "";
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
