package controller.session;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.usuario.Usuario;
import services.LoginService;

@WebServlet("/invitado")
public class InvitedServlet extends HttpServlet {
	private static final long serialVersionUID = 8308079314140233763L;
	private LoginService invitedService;

	@Override
	public void init() throws ServletException {
		super.init();
		invitedService = new LoginService();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Usuario usuario=invitedService.login("invitado", "1234");
		if (!usuario.isNull()) {
    		req.getSession().setAttribute("user", usuario);
			resp.sendRedirect("index.jsp");
		} else {
			req.setAttribute("flash", "No se puede ingresar como invitado");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/registro.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
