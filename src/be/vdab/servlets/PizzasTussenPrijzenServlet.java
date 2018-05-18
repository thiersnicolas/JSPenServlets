package be.vdab.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.repositories.PizzaRepository;
import be.vdab.util.StringUtils;

/**
 * Servlet implementation class PizzasTussenPrijzenServlet
 */
@WebServlet("/pizzas/tussenprijzen.htm")
public class PizzasTussenPrijzenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/pizzatussenprijzen.jsp";
	private final PizzaRepository pizzaRepository = new PizzaRepository();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		if (request.getQueryString() != null) {
			Map<String, String> fouten = new HashMap<>();
			String van = request.getParameter("van");
			if( ! StringUtils.isBigDecimal(van)) {
				fouten.put("van",  "tik een getal");
			}
			String tot = request.getParameter("tot");
			if ( ! StringUtils.isBigDecimal(tot)) {
				fouten.put("tot", "tik een getal");
			}
			if (!fouten.isEmpty()) {
				request.setAttribute("fouten", fouten);
			} else {
				request.setAttribute("pizzas", pizzaRepository.findByPrijsBetween(new BigDecimal(van), new BigDecimal(tot)));
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
