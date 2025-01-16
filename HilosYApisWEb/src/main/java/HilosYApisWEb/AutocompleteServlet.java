package HilosYApisWEb;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AutocompleteServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String GOOGLE_PLACES_API_KEY = "AIzaSyCIneSjhos49u92NpfkDy5T85BA544Tetg";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String input = request.getParameter("input");
		if (input == null || input.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write("Falta el parámetro 'input'.");
			return;
		}

		String urlString = String.format(
				"https://maps.googleapis.com/maps/api/place/autocomplete/json?input=%s&types=(cities)&components=country:es&key=%s",
				input, GOOGLE_PLACES_API_KEY);

		URL url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");

		try (Scanner scanner = new Scanner(conn.getInputStream())) {
			String jsonResponse = scanner.useDelimiter("\\A").next();

			// Procesar la respuesta JSON para extraer solo los nombres de los municipios
			String[] suggestions = jsonResponse.split("\"description\"\\s*:\\s*\""); // Buscar el campo "description"
			StringBuilder processedResponse = new StringBuilder("["); // Crear JSON simplificado

			for (int i = 1; i < suggestions.length; i++) { // Saltar la primera parte antes del primer "description"
				String suggestion = suggestions[i].split("\",")[0]; // Tomar solo el texto antes de la coma
				String[] parts = suggestion.split(","); // Dividir por comas para quitar "Spain" u otros detalles
				String municipalityName = parts[0].trim(); // Tomar solo el primer fragmento
				processedResponse.append("\"").append(municipalityName).append("\"");
				if (i < suggestions.length - 1) {
					processedResponse.append(",");
				}
			}
			processedResponse.append("]");

			// Enviar el JSON simplificado al cliente
			response.setContentType("application/json");
			response.getWriter().write(processedResponse.toString());
		}
	}

}
