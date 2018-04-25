package be.vdab.servlets;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IndexServlet
 */

@WebServlet("/index.htm")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String VIEW = "/WEB-INF/JSP/index.jsp";

	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) 
	throws ServletException, IOException {
		int uur = LocalDateTime.now().getHour();
		request.setAttribute("begroeting",
				uur >= 6 && uur < 12 ? "Goede morgen" : uur >= 12 && uur < 18 ? "Goede middag" : "Goede avond");
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 *      protected void doGet(HttpServletRequest request, HttpServletResponse
	 *      response) throws ServletException, IOException { // TODO Auto-generated
	 *      method stub PrintWriter out = response.getWriter();
	 *      out.println("<!doctype html>"); out.println("<html lang='nl'>");
	 *      out.println("<head>"); out.println("<title>Pizza Luigi</title>");
	 *      out.println("</head>"); out.println("<body><h1>"); int uur =
	 *      LocalDateTime.now().getHour(); out.print(uur >= 6 && uur < 12 ?
	 *      "Goede morgen" : uur >= 12 && uur < 18 ? "Goede middag": "Goede avond");
	 *      out.println("</h1></body></html>"); }
	 **/

}