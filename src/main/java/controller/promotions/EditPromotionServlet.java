package controller.promotions;

import java.util.List;
import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.producto.*;
import persistence.iAtraccionDAO;
import persistence.commons.DAOFactory;
import services.AttractionService;
import services.PromotionService;

@WebServlet("/promociones/edit.do")
public class EditPromotionServlet extends HttpServlet {

	private static final long serialVersionUID = 7598291131560345626L;
	private PromotionService promotionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.promotionService = new PromotionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Promocion> promociones = promotionService.list();
		List<Atraccion> atracciones = DAOFactory.getAtraccionDAO().listar(1); // listamos las validas

		req.setAttribute("promociones", promociones);
		req.setAttribute("atracciones", atracciones);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/promociones/edit.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Integer id = Integer.parseInt(req.getParameter("id")); // La id se mantiene igual
			Promocion promocion = promotionService.crearPromocion(req, id);
			promotionService.update(promocion);
			
			
			resp.sendRedirect("/TurismoTierraMedia/promociones/index.do");
		} catch (Exception e) {
			req.setAttribute("flash", "Se ha presentado un error, cargaste mal un dato");
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/promociones/edit.jsp");
			dispatcher.forward(req, resp);
		}
	}

}
