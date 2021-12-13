package controller.attractions;

import java.util.List;
import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.producto.*;
import services.AttractionService;

@WebServlet("/atracciones/edit.do")
public class EditAttractionServlet extends HttpServlet {

	private static final long serialVersionUID = 7598291131560345626L;
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
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/atracciones/edit.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		
		String nombre = req.getParameter("nombre");
		Double costo = Double.parseDouble(req.getParameter("costo"));
		Double duracion = Double.parseDouble(req.getParameter("duracion"));
		Integer capacidad = Integer.parseInt(req.getParameter("capacidad"));
		TipoDeAtraccion tipoAtraccion = TipoDeAtraccion.valueOf(req.getParameter("tipo-atraccion"));
		String descripcion= req.getParameter("descripcion");

		Atraccion atraccion = attractionService.update(id, nombre, costo, duracion, capacidad, tipoAtraccion, descripcion);

		if (atraccion.isValid()) {
			resp.sendRedirect("/TurismoTierraMedia/atracciones/index.do");
		} else {
			req.setAttribute("atraccion", atraccion);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/TurismoTierraMedia/atracciones/edit.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
