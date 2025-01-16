package HilosYApisWEb;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CuriousDataServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String municipio = request.getParameter("municipio");
		if (municipio == null || municipio.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write("Falta el parámetro 'municipio'.");
			return;
		}

		String encodedMunicipio = URLEncoder.encode(municipio, "UTF-8");
		String urlString = String.format(
				"https://es.wikipedia.org/w/api.php?action=query&format=json&prop=extracts&exintro=true&titles=%s",
				encodedMunicipio);

		URL url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");

		try (Scanner scanner = new Scanner(conn.getInputStream())) {
			String jsonResponse = scanner.useDelimiter("\\A").next();
			response.setContentType("application/json");
			response.getWriter().write(jsonResponse);
		}
	}
}