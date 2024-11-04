package iberia;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BuscarVuelos")
public class BuscarVuelos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/login?useSSL=false&serverTimezone=UTC";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "nueva_contraseña";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		String origen = request.getParameter("origen").trim();
		String destino = request.getParameter("destino").trim();
		String fechaIda = request.getParameter("fecha-ida");
		int personas = Integer.parseInt(request.getParameter("personas"));

		PrintWriter out = response.getWriter();
		Random random = new Random();

		out.println("<html lang='es'>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("<title>Resultados de Vuelos</title>");
		out.println("<style>");
		out.println(
				"body { font-family: Arial, sans-serif; background-image: url('image/nuub.jpg'); background-size: cover; background-position: center; margin: 0; padding: 20px; color: black; }"); // Cambiado
		out.println(
				"#resultado { max-width: 800px; margin: auto; background: rgba(255, 255, 255, 0.9); padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }");
		out.println("h2 { color: #333; }");
		out.println(
				".vuelo { border: 1px solid #ddd; border-radius: 5px; padding: 15px; margin: 15px 0; background: #fafafa; }");
		out.println(".vuelo p { margin: 5px 0; }");
		out.println(".precio { font-weight: bold; color: green; }");
		out.println(".no-vuelos { color: red; font-weight: bold; }");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conexion = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
				String sql = "SELECT * FROM vuelos WHERE origen = ? AND destino = ? AND salida = ? AND personas >= ?";
				try (PreparedStatement ps = conexion.prepareStatement(sql)) {
					ps.setString(1, origen);
					ps.setString(2, destino);
					ps.setString(3, fechaIda);
					ps.setInt(4, personas);

					try (ResultSet rs = ps.executeQuery()) {
						out.println("<div id='resultado'>");
						if (rs.next()) {
							out.println("<h2>Vuelos disponibles:</h2>");
							do {
								String idVuelo = rs.getString("idvuelo");
								String salida = rs.getString("salida");
								String llegada = rs.getString("llegada");
								int asientosDisponibles = rs.getInt("personas");

								int precio = random.nextInt(541) + 60;

								out.println("<div class='vuelo'>");
								out.println("<p><strong>Vuelo ID:</strong> " + idVuelo + "</p>");
								out.println("<p><strong>Origen:</strong> " + origen + ", <strong>Destino:</strong> "
										+ destino + "</p>");
								out.println("<p><strong>Fecha de salida:</strong> " + salida + "</p>");
								out.println("<p><strong>Fecha de llegada:</strong> " + llegada + "</p>");
								out.println(
										"<p><strong>Asientos disponibles:</strong> " + asientosDisponibles + "</p>");
								out.println("<p class='precio'>Precio: " + precio + " €</p>");
								out.println("</div>");
							} while (rs.next());
						} else {
							out.println(
									"<h3 class='no-vuelos'>No hay vuelos disponibles con los criterios seleccionados.</h3>");
						}
						out.println("</div>");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.println("Error en la base de datos: " + e.getMessage());
		}

		out.println("</body>");
		out.println("</html>");
	}
}
