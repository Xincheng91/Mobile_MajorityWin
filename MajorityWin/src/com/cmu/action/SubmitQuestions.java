package com.cmu.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.cmu.service.RoomService;

/**
 * Servlet implementation class SubmitQuestions
 */
@WebServlet("/SubmitQuestions")
public class SubmitQuestions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitQuestions() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String roomID = request.getParameter("roomID");
		String questions = request.getParameter("questions");
		if (RoomService.isRoomExist(roomID)) {
			RoomService.setQuestion(roomID, questions);
			RoomService.setStatus(roomID, 2);
			response.getOutputStream().write(new String("Success").getBytes());
		} else {
			response.getOutputStream().write(
					new String().getBytes());
		}
	}

}
