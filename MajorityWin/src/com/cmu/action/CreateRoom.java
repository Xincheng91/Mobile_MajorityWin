package com.cmu.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmu.bean.PersonBean;
import com.cmu.bean.RoomBean;
import com.cmu.constants.Constants;
import com.cmu.service.RoomService;

/**
 * Servlet implementation class CreateRoom
 */
@WebServlet("/CreateRoom")
public class CreateRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateRoom() {
		super();
		// store some users
		RoomService.register("Peter", "Peter");
		RoomService.register("Alex", "Alex");
		RoomService.register("Tom", "Tom");
		RoomService.register("Katie", "Katie");
		RoomService.register("Sara", "Sara");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int roomSize = Integer.parseInt(request.getParameter("roomsize"));
		int number = new Random().nextInt(Constants.roomNumberRand);

		String roomID = Integer.toString(number);
		while (RoomService.isRoomExist(roomID)) {
			number = new Random().nextInt(Constants.roomNumberRand);
			roomID = Integer.toString(number);
		}

		// String username = request.getParameter("username");
		ArrayList<PersonBean> people = new ArrayList<PersonBean>();
		// try {
		// people.add(RoomService.getPerson(username));
		// } catch (Exception e) {
		// response.getOutputStream().write("".getBytes());
		// return;
		// }
		RoomBean rb = new RoomBean(roomID, roomSize, people);
		RoomService.createRoom(roomID, rb);
		response.getOutputStream().write(roomID.getBytes());
		return;
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
