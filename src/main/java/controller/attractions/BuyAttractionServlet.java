package controller.attractions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.producto.*;
import model.usuario.Usuario;
import persistence.iAtraccionDAO;
import persistence.commons.DAOFactory;
import services.BuyAttractionService;

@WebServlet("/attractions/buy.do")
public class BuyAttractionServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;
	private BuyAttractionService buyAttractionService;

	Map<Integer, Atraccion> mapDeAtraccionesPorID;
	Map<Integer, Promocion> mapDePromocionesPorID;

	@Override
	public void init() throws ServletException {
		super.init();
		cargarAtracciones();
		this.buyAttractionService = new BuyAttractionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer attractionId = Integer.parseInt(req.getParameter("id"));
		Usuario user = (Usuario) req.getSession().getAttribute("user");
		Map<String, String> errors = buyAttractionService.buy(user.getID(), attractionId);
		
		Usuario user2 = DAOFactory.getUsuarioDAO().find(user.getID(), this.mapDeAtraccionesPorID, mapDePromocionesPorID);
		req.getSession().setAttribute("user", user2);
		
		if (errors.isEmpty()) {
			req.setAttribute("flash", "¡Gracias por comprar!");
		} else {
			req.setAttribute("errors", errors);
			req.setAttribute("flash", "No ha podido realizarse la compra");
		}

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/attractions/index.do");
		dispatcher.forward(req, resp);
	}
	
	private void cargarAtracciones() {
		iAtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		List<Atraccion> atracciones = atraccionDAO.listar(1);
		this.mapDeAtraccionesPorID = Atraccion.crearMapDeAtracciones(atracciones);
	}
	
}
