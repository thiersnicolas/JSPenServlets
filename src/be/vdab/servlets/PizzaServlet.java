package be.vdab.servlets;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Pizza;
import be.vdab.repositories.PizzaRepository;

/**
 * Servlet implementation class PizzaServlet
 */
@WebServlet("/pizzas.htm")
public class PizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String VIEW = "/WEB-INF/JSP/pizzas.jsp";
	private static final String PIZZAS_REQUESTS = "pizzasRequests";
	private final PizzaRepository pizzaRepository = new PizzaRepository();
	private String pizzaFotosPad;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// request.setAttribute("pizzas", Arrays.asList(
		// new Pizza(12, "prosciutto", BigDecimal.valueOf(4), true),
		// new Pizza(14, "Margherita", BigDecimal.valueOf(5), false),
		// new Pizza(17, "Calzone", BigDecimal.valueOf(4), false)));
		//Map<Long, Pizza> pizzas = new LinkedHashMap<>(); // keys zijn pizza ids
		//pizzas.put(23L, new Pizza(23, "Fungi & Olive", BigDecimal.valueOf(5), false));
		//pizzas.put(12L, new Pizza(12, "Prosciutto", BigDecimal.valueOf(4), true));	
		//pizzas.put(14L, new Pizza(14, "Margehrita", BigDecimal.valueOf(5), false));
		//pizzas.put(17L, new Pizza(17, "Calzone", BigDecimal.valueOf(4), false));
		//request.setAttribute("pizzas", pizzas);
		((AtomicInteger) this.getServletContext().getAttribute(PIZZAS_REQUESTS)).incrementAndGet();
		List<Pizza> pizzas = pizzaRepository.findAll();
		request.setAttribute("pizzas", pizzas);
		request.setAttribute("pizzaIdsMetFoto",
		pizzas.stream()
				.filter(pizza -> Files.exists(Paths.get(pizzaFotosPad, pizza.getId() + ".jpg")))
				.map(pizza -> pizza.getId())
				.collect(Collectors.toList()));
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	
	@Override
	public void init() throws ServletException {
		this.getServletContext().setAttribute(PIZZAS_REQUESTS, new AtomicInteger());
		pizzaFotosPad = this.getServletContext().getRealPath("/pizzafotos");
	}

	

}
