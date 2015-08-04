package ticket;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import javax.jdo.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("no url...");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String username = req.getParameter("user");
		String password = req.getParameter("password");
		String password2 = req.getParameter("password2"); 
		
		RequestDispatcher rd;
		
		if(!password.equals(password2)){
			 rd = req.getRequestDispatcher("/addUser.jsp");
             rd.forward(req, resp);
		}
	
		UserData data = new UserData(username, password);
		PersistenceManagerFactory factory = PMF.get();
		PersistenceManager manager = factory.getPersistenceManager();
		try {
			manager.makePersistent(data);
		} finally {
			manager.close();
		}
		 rd = req.getRequestDispatcher("/login.jsp");
         rd.forward(req, resp);
	}
}