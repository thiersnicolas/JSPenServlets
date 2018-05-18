package be.vdab.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.repositories.PizzaRepository;

/**
 * Servlet implementation class VoorkeurPizzaServlet
 */
@WebServlet("/pizzas/voorkeuren.htm")
public class VoorkeurPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW="/WEB-INF/JSP/voorkeurpizzas.jsp";
	private final PizzaRepository pizzaRepository = new PizzaRepository();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setAttribute("pizzas", pizzaRepository.findAll());
		if (request.getParameterValues("id") != null) {
			request.setAttribute("voorkeurPizzas", Arrays.stream(request.getParameterValues("id"))
					.map(id -> pizzaRepository.read(Long.parseLong(id)))
					.filter(optionalPizza -> optionalPizza.isPresent())
					.map(optionalPizza -> optionalPizza.get())
					.collect(Collectors.toList()));
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
