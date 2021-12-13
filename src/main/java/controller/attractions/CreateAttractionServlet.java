package controller.attractions;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.producto.*;
import model.usuario.Usuario;
import services.AttractionService;

@WebServlet("/atracciones/create.do")
public class CreateAttractionServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;
	private AttractionService attractionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionService = new AttractionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/atracciones/create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombre");
		Integer costo = Integer.parseInt(req.getParameter("costo"));
		Double duracion = Double.parseDouble(req.getParameter("duracion"));
		Integer capacidad = Integer.parseInt(req.getParameter("capacidad"));
		TipoDeAtraccion tipoAtraccion = TipoDeAtraccion.valueOf(req.getParameter("tipo-atraccion"));
		String descripcion= req.getParameter("descripcion");
		
	
		Atraccion atraccion = attractionService.create(nombre, costo, duracion, capacidad, tipoAtraccion, descripcion);
		
		if (atraccion.isValid()) {
			resp.sendRedirect("/TurismoTierraMedia/atracciones/index.do");
		} else {
			req.setAttribute("flash", atraccion.getErrors().get("flash"));

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/atracciones/create.jsp");
			dispatcher.forward(req, resp);
		}

	}

}
