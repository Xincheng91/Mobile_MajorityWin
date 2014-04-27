package com.cmu.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmu.service.RoomService;

/**
 * Servlet implementation class SubmitVote
 */
@WebServlet("/SubmitVote")
public class SubmitVote extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SubmitVote() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String roomID = request.getParameter("roomID");
		int option = Integer.parseInt(request.getParameter("option"));
		System.out.println("SubmitVote: " + option);
		if (RoomService.isRoomExist(roomID)) {
			RoomService.vote(roomID, option);
			response.getOutputStream().write(new String("OK").getBytes());
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
