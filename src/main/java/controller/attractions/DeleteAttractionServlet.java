package controller.attractions;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.producto.Atraccion;
import model.producto.TipoDeAtraccion;
import persistence.iAtraccionDAO;
import services.AttractionService;

@WebServlet("/atracciones/delete.do")
public class DeleteAttractionServlet extends HttpServlet {

	private static final long serialVersionUID = 1537949074766873118L;
	private AttractionService attractionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionService = new AttractionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Atraccion> atracciones = attractionService.list(1);
		
		req.setAttribute("atracciones", atracciones);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/atracciones/delete.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		attractionService.delete(id);

		resp.sendRedirect("/TurismoTierraMedia/atracciones/delete.do");
	}
	

	
	

}
