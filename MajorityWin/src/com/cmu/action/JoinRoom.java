package com.cmu.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmu.service.RoomService;

/**
 * Servlet implementation class JoinRoom
 */
@WebServlet("/JoinRoom")
public class JoinRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinRoom() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String roomID = request.getParameter("roomID");
		int id = Integer.parseInt(roomID);
		if(RoomService.isRoomExist(id)){
			RoomService.addPeopleToRoom("YiLi", id);
			response.getOutputStream().write(new String("Success").getBytes());
		}else{
			response.getOutputStream().write(new String("NotSuccess").getBytes());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
