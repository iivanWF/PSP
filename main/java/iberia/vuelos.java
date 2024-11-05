package iberia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class vuelos {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/login?useSSL=false&serverTimezone=UTC";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "nueva_contraseña";

	private static final String[] DESTINOS = { "Nueva York", "Londres", "Tokio", "París", "Hong Kong", "Singapur",
			"Dubai", "Los Ángeles", "Chicago", "Sídney", "Toronto", "San Francisco", "Shanghai", "Beijing", "São Paulo",
			"Mumbai", "Seúl", "Moscú", "Estambul", "Bangkok", "Milán", "Ámsterdam", "Frankfurt", "Ciudad de México",
			"Kuala Lumpur", "Buenos Aires", "Zurich", "Barcelona", "Bruselas", "Johannesburgo" };

	private static final int DIAS = 400;

	public static void main(String[] args) {
		generarVuelos();
	}

	public static void generarVuelos() {
		Connection conexion = null;
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

			String sqlInsert = "INSERT INTO vuelos (idvuelo, origen, destino, salida, llegada, personas) VALUES (?, ?, ?, ?, ?, ?)";
			ps = conexion.prepareStatement(sqlInsert);

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			int idCounter = 1;

			LocalDate fechaInicio = LocalDate.now();

			for (int dia = 0; dia < DIAS; dia++) {
				LocalDate fechaActual = fechaInicio.plusDays(dia);

				for (String destino : DESTINOS) {
					String salida = fechaActual.atTime(8, 0).format(formatter);
					String llegada = fechaActual.atTime(10, 0).format(formatter);

					ps.setInt(1, idCounter);
					ps.setString(2, "Madrid");
					ps.setString(3, destino);
					ps.setString(4, salida);
					ps.setString(5, llegada);
					ps.setInt(6, 150);

					ps.executeUpdate();
					idCounter++;
				}
			}

			System.out.println("Vuelos generados e insertados exitosamente.");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error en la base de datos: " + e.getMessage());
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
