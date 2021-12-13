/*package controller.attractions;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.*;

@WebServlet("/registro.do")
public class ListTypeAttractionsServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -8346640902238722429L;
	private TypeAttractionService typeAttractionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.typeAttractionService = new TypeAttractionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<String> nombreTipoAtracciones=  typeAttractionService.list();
		req.setAttribute("tipoDeAtracciones", nombreTipoAtracciones);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("registro.jsp");
		dispatcher.forward(req, resp);

	}

}*/
