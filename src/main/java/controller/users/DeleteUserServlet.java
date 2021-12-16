package controller.users;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.producto.Atraccion;
import model.producto.TipoDeAtraccion;
import persistence.iAtraccionDAO;
import services.AttractionService;
import services.UserService;

@WebServlet("/usuarios/delete.do")
public class DeleteUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1537949074766873118L;
	private UserService userService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.userService = new UserService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Integer id = Integer.parseInt(req.getParameter("id"));
		userService.delete(id);
		resp.sendRedirect("/TurismoTierraMedia/usuarios/info.do");
	}

	
	

}
