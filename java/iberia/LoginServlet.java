package iberia;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String correo = request.getParameter("correo").trim();
		String password = request.getParameter("password").trim();

		String jdbcUrl = "jdbc:mysql://localhost:3306/login?useSSL=false&serverTimezone=UTC";
		String dbUser = "root";
		String dbPassword = "nueva_contraseña";

		Connection conexion = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conexion = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

			String sql = "SELECT * FROM registro WHERE correo = ? AND password = ?";
			ps = conexion.prepareStatement(sql);
			ps.setString(1, correo);
			ps.setString(2, password);

			rs = ps.executeQuery();

			if (rs.next()) {
				response.sendRedirect("iberia1.jsp");
			} else {
				response.getWriter().println("Usuario o contraseña incorrectos.");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			response.getWriter().println("Error al cargar el controlador JDBC.");
		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().println("Error al conectar a la base de datos o al consultar los datos.");
		} finally {
			try {
				if (rs != null)
					rs.close();
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
