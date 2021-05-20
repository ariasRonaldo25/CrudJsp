package co.empresa.Crud.controlador;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.lang.*;



import co.empresa.Crud.dao.UsuarioDao;
import co.empresa.Crud.dao.UsuarioDaoFactory;
import co.empresa.Crud.dao.UsuarioDaoMysql;
import co.empresa.Crud.dao.UsuarioDaoPostgreSQL;
import co.empresa.Crud.modelo.Usuario;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDao usuarioDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioServlet() {
        
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		
		String type = getServletContext().getInitParameter("type");
		this.usuarioDao = UsuarioDaoFactory.getUsuarioDao(type);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
		try {
		switch(action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertarUsuario(request, response);
				break;
			case "/delete":
				eliminarUsuario(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				actualizarUsuario(request, response);
				break;
			default:
				listUsuarios(request, response);
				break;
		
		}
		}catch(SQLException e) {
			throw new ServletException(e);
		}
		
		
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("usuario.jsp");
		dispatcher.forward(request, response);
	}
	
	private void insertarUsuario(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, SQLException, IOException {
		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");
		String pais = request.getParameter("pais");
		
		Usuario usuario = new Usuario (nombre, email, pais);
		
		usuarioDao.insert(usuario);
		
		response.sendRedirect("list");
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException,IOException, NumberFormatException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		Usuario usuarioActual = usuarioDao.select(id);
		
		request.setAttribute("usuario", usuarioActual);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("usuario.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");
		String pais = request.getParameter("pais");
		
		Usuario usuario = new Usuario (id, nombre, email, pais);
		
		usuarioDao.update(usuario);
		
		response.sendRedirect("list");
	}
	
	private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		
		usuarioDao.delete(id);
		
		response.sendRedirect("list");
	}
	
	private void listUsuarios(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, SQLException, IOException {
		
		List <Usuario> listUsuarios = usuarioDao.selectAll();
		request.setAttribute("listUsuarios", listUsuarios);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("usuariolist.jsp");
		dispatcher.forward(request, response);
		
		
	}

}

