package controller.promotions;

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
import services.PromotionService;

@WebServlet("/promociones/index.do")
public class ListPromotionsServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -8346640902238722429L;
	private PromotionService promotionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.promotionService = new PromotionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Promocion> promociones = promotionService.list();
		Usuario user = (Usuario) req.getSession().getAttribute("user");
		Collections.sort(promociones, new OrdenarProductosPorPreferencia(user.getTipoFavorito()));
		req.setAttribute("promociones", promociones);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/promociones/index.jsp");
		dispatcher.forward(req, resp);

	}

}
