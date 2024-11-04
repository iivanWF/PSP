package iberia;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegistroUsuarioServlet")
public class RegistroUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String telefono = request.getParameter("telf");
		String correo = request.getParameter("corr");
		String password = request.getParameter("password");

		String jdbcUrl = "jdbc:mysql://localhost:3306/login?useSSL=false&serverTimezone=UTC";
		String dbUser = "root";
		String dbPassword = "nueva_contraseña";

		Connection conexion = null;
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conexion = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

			if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || correo.isEmpty()
					|| password.isEmpty()) {
				response.getWriter().println("Por favor, rellena todos los campos.");
				return;
			}

			String sql = "INSERT INTO registro (nombre, apellido, telefono, correo, password) VALUES (?, ?, ?, ?, ?)";
			ps = conexion.prepareStatement(sql);

			ps.setString(1, nombre);
			ps.setString(2, apellido);
			ps.setString(3, telefono);
			ps.setString(4, correo);
			ps.setString(5, password);

			int filasInsertadas = ps.executeUpdate();
			if (filasInsertadas > 0) {
				System.out.println("Registro exitoso.");
				response.sendRedirect("iberia1.jsp");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			response.getWriter().println("Error al cargar el controlador JDBC.");
		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().println("Error al conectar a la base de datos o al insertar los datos.");
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conexion != null)
					conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
