package controller.promotions;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.producto.*;
import model.usuario.Usuario;
import persistence.commons.DAOFactory;
import services.AttractionService;
import services.PromotionService;

@WebServlet("/promociones/create.do")
public class CreatePromotionServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;
	private PromotionService promotionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.promotionService = new PromotionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Atraccion> atracciones = DAOFactory.getAtraccionDAO().listar(1); // listamos las validas
		req.setAttribute("atracciones", atracciones);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/promociones/create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Integer id=promotionService.establecerID(); //Insertamos con id negativa, despues se va a cambiar
			Promocion promocion= promotionService.crearPromocion(req, id);
			promotionService.create(promocion);
			resp.sendRedirect("/TurismoTierraMedia/promociones/index.do");
		} catch (Exception e) {
			req.setAttribute("flash", "Cargaste mal un dato");

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/promociones/create.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
