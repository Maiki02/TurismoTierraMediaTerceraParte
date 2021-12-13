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
		System.out.println("Redireccionando");
		List<Promocion> promociones = promotionService.list();
		List<Atraccion> atracciones = DAOFactory.getAtraccionDAO().listar(1); //listamos las validas
		
		req.setAttribute("promociones", promociones);
		req.setAttribute("atracciones", atracciones);
		System.out.println("Redireccionando");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/promociones/edit.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		String nombre = req.getParameter("nombre");
		TipoDeAtraccion tipoAtraccion = TipoDeAtraccion.valueOf(req.getParameter("tipo-atraccion"));
		TipoDePromocion tipoPromocion= TipoDePromocion.valueOf(req.getParameter("tipo-promocion"));
		String descripcion= req.getParameter("descripcion");
		if(tipoPromocion == TipoDePromocion.AXB) {
			Atraccion atraccion=req.getParameter("atraccion-premio");
		} else if (tipoPromocion == TipoDePromocion.ABSOLUTA) {
			Double costo=Double.parseDouble(req.getParameter("costo-absoluto"));
		} else if (tipoPromocion == TipoDePromocion.PORCENTUAL) {
			Double descuento=Double.parseDouble(req.getParameter("porcentaje-descuento"));
		}
		
		List<Atraccion> atraccionesInvolucradas= req.getParameter("atracciones-involucradas");

		Promocion promocion = promotionService.update(id, nombre, costo, tipoAtraccion, descuento, id, tipoAtraccion, descripcion);

		if (promocion.isValid()) {
			resp.sendRedirect("/TurismoTierraMedia/atracciones/index.do");
		} else {
			req.setAttribute("promocion", promocion);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/TurismoTierraMedia/atracciones/edit.jsp");
			dispatcher.forward(req, resp);
		}*/
	}
}
