package com.cmu.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.cmu.constants.Constants.RoomStatus;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String roomID = request.getParameter("roomID");
		if (RoomService.isRoomExist(roomID)) {
			RoomStatus status = RoomService.getStatusOfRoom(roomID);
			int numOfFinished = RoomService.getNumOfFinishOfRoom(roomID);
			int numOfMajority = RoomService.getNumOfMajorityOfRoom(roomID);
			JSONObject jsObject = new JSONObject();
			if (status == RoomStatus.VOTED) {
				jsObject.put("numOfFinished", numOfFinished);
				jsObject.put("numOfMajority", numOfMajority);
				jsObject.put("status", status.ordinal());
				jsObject.put("roomSize", RoomService.getRoomSize(roomID));
				ArrayList<String> resultArr = RoomService.getResult(roomID);
				String resultStr = resultArr.get(0);
				for (int i = 1; i < resultArr.size(); i++) {
					resultStr += "or " + resultArr.get(i);
				}
				jsObject.put("result", resultStr);
			} else {
				jsObject.put("numOfFinished", numOfFinished);
				jsObject.put("numOfMajority", numOfMajority);
				jsObject.put("status", status.ordinal());
				jsObject.put("roomSize", RoomService.getRoomSize(roomID));
				jsObject.put("result", "");
			}
			response.getOutputStream()
					.write(jsObject.toJSONString().getBytes());
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
