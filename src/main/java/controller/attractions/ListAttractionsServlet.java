package controller.attractions;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import comparador.OrdenarProductosPorPreferencia;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.producto.*;
import model.usuario.Usuario;
import services.AttractionService;

@WebServlet("/atracciones/index.do")
public class ListAttractionsServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -8346640902238722429L;
	private AttractionService attractionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionService = new AttractionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Atraccion> atracciones = attractionService.list(1);
		Usuario user = (Usuario) req.getSession().getAttribute("user");
		Collections.sort(atracciones, new OrdenarProductosPorPreferencia(user.getTipoFavorito()));
		
		req.setAttribute("atracciones", atracciones);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/atracciones/index.jsp");
		dispatcher.forward(req, resp);

	}

}
