/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticket;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.*;
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
public class CartServlet extends HttpServlet {

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
		HttpSession session = request.getSession();
		String[] strVals = request.getParameterValues("kosuu");
		/*
		 * for(int i=0;i<strVals.length;i++){ int a=0; if(strVals[i]==null)
		 * strVals[i]=Integer.toString(a); }
		 */

		PersistenceManagerFactory factory = PMF.get();
		PersistenceManager manager = factory.getPersistenceManager();

		try {

			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			int total = 0;

			// Checkbox の (name: shohinid)と結び付けられた value の並びをとりだす。
			for (int i = 0; i < strVals.length; i++) {
				// i番目のshohinid 項目の value をとりだす。
				int idFromCB = i + 1;
				if (strVals[i] == "" || Integer.parseInt(strVals[i]) == 0)
					continue;

				// その商品の情報（名前、価格）をDBから取り出す。合計金額も計算する。
				Query que = manager.newQuery(LaneData.class);
				que.setFilter("idx==Iparam");
				que.declareParameters("String Iparam");
				List<LaneData> obj = (List<LaneData>) que.execute(Integer.toString(idFromCB));

				Map<String, Object> record = new HashMap<String, Object>();
				record.put("id", obj.get(0).getIdx());
				record.put("name", obj.get(0).getName());
				int k = Integer.parseInt(strVals[i]);
				int p = Integer.parseInt(obj.get(0).getPrice());
				int price = k * p;
				record.put("tanka", p);
				record.put("kosuu", k);
				record.put("price", price);
				total = total + price;
				list.add(record);

			}// end for (int i チェックされた商品についての繰り返し。
			manager.close();

			// 表示のため、結果をリクエスト変数にしまう。
			// request.setAttribute("count", numChecks);
			request.setAttribute("data", list);
			request.setAttribute("total", total);

			RequestDispatcher rd = request
					.getRequestDispatcher("/cartCheckBox.jsp");
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
