/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticket;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author
 */
public class SetServlet extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		// PrintWriter out = response.getWriter();

		// Check Box の情報をとりだす。
		HttpSession session = request.getSession();
		String[] strVals = request.getParameterValues("placeid");
		int numChecks = 0;
		if (strVals != null) {
			numChecks = strVals.length; // チェックの個数
		} else { // 何もチェックされていない。
			numChecks = 0;
		}

		try {

			PersistenceManagerFactory factory = PMF.get();
			PersistenceManager manager = factory.getPersistenceManager();

			long idFromCB = Long.parseLong(strVals[0]);
			PlaceData obj = (PlaceData) (manager.getObjectById(PlaceData.class, idFromCB));

			// 表示のため、結果をリクエスト変数にしまう。
			request.setAttribute("count", numChecks);
			session.setAttribute("kaijou", obj.getPlace());

			// laneの情報を呼び出す
			String que = "select from " + LaneData.class.getName()
					+ " order by idx asc";
			List<LaneData> lane = (List<LaneData>) manager.newQuery(que)
					.execute();

			manager.close();
			request.setAttribute("data", lane);

			RequestDispatcher rd = request
					.getRequestDispatcher("/laneListFromDB.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

	// <editor-fold defaultstate="collapsed"
	// desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>
}
