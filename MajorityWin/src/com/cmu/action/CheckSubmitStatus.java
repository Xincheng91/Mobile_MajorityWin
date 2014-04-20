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
 * Servlet implementation class CheckSubmitStatus
 */
@WebServlet("/CheckSubmitStatus")
public class CheckSubmitStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckSubmitStatus() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String roomID = request.getParameter("roomID");
		if (RoomService.isRoomExist(roomID)) {
			int status = RoomService.getStatusOfRoom(roomID);
			int numOfFinished = RoomService.getNumOfFinishOfRoom(roomID);
			int numOfMajority = RoomService.getNumOfMajorityOfRoom(roomID);
			JSONObject jsObject = new JSONObject();
			if(status == 3){
				jsObject.put("numOfFinished", numOfFinished);
				jsObject.put("numOfMajority", numOfMajority);
				jsObject.put("status", status);
				jsObject.put("result", RoomService.getResult(roomID));
			}else if(status < 3){
				jsObject.put("numOfFinished", numOfFinished);
				jsObject.put("numOfMajority", numOfMajority);
				jsObject.put("status", status);
				jsObject.put("result", "");
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