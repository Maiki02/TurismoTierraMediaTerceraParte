package controller.users;

import java.io.IOException;
import java.util.List;

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
import services.UserService;

@WebServlet("/usuarios/perfil.do")
public class PerfilUserServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -8346640902238722429L;
	private UserService userService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.userService = new UserService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));

		Usuario usuario = userService.find(id);
		System.out.println(usuario);
		if (!usuario.isNull()) {
			req.setAttribute("usuario", usuario);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/usuarios/perfil.jsp");
			dispatcher.forward(req, resp);
		} else {
			req.setAttribute("flash", "Usuario no encontrado");

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(req, resp);
		}

	}


}
