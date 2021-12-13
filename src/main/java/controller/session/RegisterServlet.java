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
import services.LoginService;
import services.RegisterService;

@WebServlet("/registrarse")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 8308079314140233763L;
	private RegisterService registerService;

	@Override
	public void init() throws ServletException {
		super.init();
		registerService = new RegisterService();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String password2 = req.getParameter("password2");
		TipoDeAtraccion atraccionFavorita=null;
		Double monedasDisponibles = Double.parseDouble(req.getParameter("monedas"));
		Double horasDisponibles = Double.parseDouble(req.getParameter("horas"));
		String mensajeDeError="";
		try {
			atraccionFavorita=TipoDeAtraccion.valueOf(req.getParameter("tipo-atraccion-fav"));
		} catch(Exception e) {
			mensajeDeError="No se ingresó un tipo de atracción favorito";
		}
		if (!password.equals(password2)) {
			mensajeDeError="Las contraseñas no son iguales.";
		}else if(password.length() <4) {
			mensajeDeError="La contraseña debe tener al menos 4 caracteres";
		} else if (monedasDisponibles < 0 || horasDisponibles < 0) {
			mensajeDeError="Monedas u horas ingresadas son negativas.";
		}
		
		
		if(!mensajeDeError.equals("")) {
			req.getSession().setAttribute("flash", mensajeDeError);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/registro.jsp");
			dispatcher.forward(req, resp);
		}
		Usuario usuario = registerService.register(username, password, atraccionFavorita, monedasDisponibles,
				horasDisponibles);

		if (!usuario.isNull()) {
    		req.getSession().setAttribute("user", usuario.getNombre());
    		req.getSession().setAttribute("monedas", usuario.getMonedasDisponibles());
    		req.getSession().setAttribute("horas", usuario.getHorasDisponibles());
			resp.sendRedirect("index.jsp");
		} else {
			req.setAttribute("flash", "Nombre de usuario ya registrado.");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/registro.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
