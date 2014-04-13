package com.cmu.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmu.bean.RoomBean;
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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int number = new Random().nextInt(1000);
		ArrayList<String> people = new ArrayList<String>();
		people.add("Xincheng");
		RoomBean rb = new RoomBean(number, 10, people);
		try {
			RoomService.createRoom(number, rb);
		} catch (Exception e) {
			response.getOutputStream().write(e.toString().getBytes());
			return;
		}
		String str = number+"";
		response.getOutputStream().write(str.getBytes());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
