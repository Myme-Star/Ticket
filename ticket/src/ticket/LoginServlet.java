/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticket;

import java.io.*;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.jdo.*;

/**
 *
 * @author 
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        boolean found = false;
        ArrayList<TicketData> list;

        try {
            // リクエストパラメータから入力されたパスワードを取り出し
            String password = request.getParameter("password");
            String user = request.getParameter("user");

            PersistenceManagerFactory factory = PMF.get();
            PersistenceManager manager = factory.getPersistenceManager();
            Query que = manager.newQuery(UserData.class);
            que.setFilter("username==Uparam && password==Pparam");
            que.declareParameters("String Uparam");
            que.declareParameters("String Pparam");
    		UserData data = (UserData) que.execute(user, password);
    	
    		if(data!=null){
    			 found = true;
    		}
    		
            String nextJsp;
            if (!found) {
                // パスワードが正しくない場合、セッションを無効にする。
                session.invalidate();
                // ログイン失敗ページへ移動。
                nextJsp = "/loginFailed.jsp";
                RequestDispatcher dispatcher = request.getRequestDispatcher(nextJsp);
                dispatcher.forward(request, response);
            } else {
                // パスワードが正しい場合          
                // ユーザ名をセッション変数に格納する
                session.setAttribute("user", user);
                
                // データベースに商品についての問い合わせを行う
                que = manager.newQuery(TicketData.class);
                list = (ArrayList<TicketData>) que.execute();
               
                // データベースの後始末。
                manager.close();
                // 商品名のリストを リクエスト変数　data にしまう。
                request.setAttribute("data", list);
                // 次のページに移動
                //RequestDispatcher rd = request.getRequestDispatcher("/loginSuccess.jsp"); 
                RequestDispatcher rd = request.getRequestDispatcher("/placeListFromDB.jsp");
                rd.forward(request, response);

            }  //end else

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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