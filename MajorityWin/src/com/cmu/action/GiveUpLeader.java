package com.cmu.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmu.bean.PersonBean;
import com.cmu.service.RoomService;

/**
 * Servlet implementation class GiveUpLeader
 */
@WebServlet("/GiveUpLeader")
public class GiveUpLeader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GiveUpLeader() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String roomID = request.getParameter("roomID");
		String currentLeader = request.getParameter("currentLeader");
		if (RoomService.isRoomExist(roomID)) {
			// Random Pick Leader and change the status of room
			PersonBean newLeader = RoomService.changeLeader(roomID,
					RoomService.getPerson(currentLeader));
			response.getOutputStream()
					.write(newLeader.getUsername().getBytes());
		} else {
			response.getOutputStream().write(new String().getBytes());
		}
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
