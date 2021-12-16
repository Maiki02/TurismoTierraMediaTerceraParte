package controller.attractions;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.producto.*;
import model.usuario.Usuario;
import services.AttractionService;

@WebServlet("/atracciones/buy.do")
public class BuyAttractionServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;
	private AttractionService attractionService;

	Map<Integer, Atraccion> mapDeAtraccionesPorID;
	Map<Integer, Promocion> mapDePromocionesPorID;

	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionService = new AttractionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer attractionId = Integer.parseInt(req.getParameter("id"));
		Usuario user = (Usuario) req.getSession().getAttribute("user");
		Map<String, String> errors = attractionService.buy(user, attractionId);
		
		req.getSession().setAttribute("user", user);
		
		
		if (errors.isEmpty()) {
			req.setAttribute("flash", "¡Gracias por comprar!");
		} else {
			req.setAttribute("errors", errors);
			req.setAttribute("flash", "No ha podido realizarse la compra");
		}

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/atracciones/index.do");
		dispatcher.forward(req, resp);
	}
}
