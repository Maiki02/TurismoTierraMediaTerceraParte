package controller.promotions;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.producto.Atraccion;
import model.producto.Promocion;
import model.producto.TipoDeAtraccion;
import persistence.iAtraccionDAO;
import services.AttractionService;
import services.PromotionService;

@WebServlet("/promociones/delete.do")
public class DeletePromotionServlet extends HttpServlet {

	private static final long serialVersionUID = 1537949074766873118L;
	private PromotionService promotionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.promotionService = new PromotionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Promocion> promociones = promotionService.list();
		
		req.setAttribute("promociones", promociones);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/promociones/delete.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		promotionService.delete(id);

		resp.sendRedirect("/TurismoTierraMedia/promociones/delete.do");
	}
	

	
	

}
