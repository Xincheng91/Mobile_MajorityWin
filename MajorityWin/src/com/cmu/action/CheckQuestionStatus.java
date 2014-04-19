package com.cmu.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.cmu.service.RoomService;

/**
 * Servlet implementation class CheckQuestionStatus
 */
@WebServlet("/CheckQuestionStatus")
public class CheckQuestionStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckQuestionStatus() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String roomID = request.getParameter("roomID");
		if (RoomService.isRoomExist(roomID)) {
			int status = RoomService.getStatusOfRoom(roomID);
			JSONObject jsObject = new JSONObject();
			if(status == 2){
				jsObject.put("OK", true);
				jsObject.put("status", status);
				jsObject.put("questions", RoomService.getQuestions(roomID));
			}else if(status < 2){
				jsObject.put("OK", false);
				jsObject.put("status", status);
				jsObject.put("questions", "");
			}
			response.getOutputStream().write(jsObject.toJSONString().getBytes());
		} else {
			response.getOutputStream().write(
					new String().getBytes());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
