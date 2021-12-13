package controller.session;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.producto.TipoDeAtraccion;
import model.usuario.Usuario;
import services.InvitedService;
import services.LoginService;
import services.RegisterService;

@WebServlet("/invitado")
public class InvitedServlet extends HttpServlet {
	private static final long serialVersionUID = 8308079314140233763L;
	private InvitedService invitedService;

	@Override
	public void init() throws ServletException {
		super.init();
		invitedService = new InvitedService();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Usuario usuario=invitedService.login();

		if (!usuario.isNull()) {
    		req.getSession().setAttribute("user", usuario.getNombre());
    		req.getSession().setAttribute("monedas", usuario.getMonedasDisponibles());
    		req.getSession().setAttribute("horas", usuario.getHorasDisponibles());
			resp.sendRedirect("index.jsp");
		} else {
			req.setAttribute("flash", "No se puede ingresar como invitado");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/registro.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
