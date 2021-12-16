package controller.users;

import java.util.List;
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
import services.UserService;

@WebServlet("/usuarios/edit.do")
public class EditUserServlet extends HttpServlet {

	private static final long serialVersionUID = 7598291131560345626L;
	private UserService userService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.userService = new UserService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Usuario usuario=userService.find(Integer.parseInt(req.getParameter("id")));
		req.setAttribute("usuario", usuario);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/usuarios/edit.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		String nombre = req.getParameter("nombre");
		boolean esAdmin = req.getParameter("es-admin").equals("SI");
		
		try {
			Double monedasDisponibles = Double.parseDouble(req.getParameter("monedas"));
			Double tiempo = Double.parseDouble(req.getParameter("tiempo"));
			Double totalAPagar = Double.parseDouble(req.getParameter("total-a-pagar"));
			Double totalHorasGastadas = Double.parseDouble(req.getParameter("total-horas-gastadas"));
			TipoDeAtraccion tipoAtraccion = TipoDeAtraccion.valueOf(req.getParameter("tipo-favorito"));
			Usuario usuario = userService.update(id, nombre, monedasDisponibles, tiempo, totalAPagar, totalHorasGastadas, tipoAtraccion, esAdmin);
			resp.sendRedirect("/TurismoTierraMedia/usuarios/info.do");
		} catch(Exception e) {
			req.setAttribute("flash", "Ocurrio un error con los campos llenados");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/TurismoTierraMedia/usuarios/edit.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
