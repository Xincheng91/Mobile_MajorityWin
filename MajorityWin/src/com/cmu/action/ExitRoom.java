package com.cmu.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmu.bean.PersonBean;
import com.cmu.service.RoomService;

@WebServlet("/ExitRoom")
public class ExitRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExitRoom() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String roomID = request.getParameter("roomID");
		try {
			PersonBean pb = RoomService.getPerson(username);
			if (RoomService.exitRoom(pb, roomID)) {
				response.getOutputStream().write("OK".getBytes());
			} else {
				response.getOutputStream().write("".getBytes());
			}
		} catch (Exception e) {
			response.getOutputStream().write("OK".getBytes());
		}
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
}
