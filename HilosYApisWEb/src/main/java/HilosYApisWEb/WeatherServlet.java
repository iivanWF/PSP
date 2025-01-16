package HilosYApisWEb;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WeatherServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String OPENWEATHER_API_KEY = "3b8f0915e8f882d5c1662eca86d1fbea";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String municipio = request.getParameter("municipio");
		if (municipio == null || municipio.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write("Falta el parámetro 'municipio'.");
			return;
		}

		String urlString = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&units=metric&appid=%s",
				municipio, OPENWEATHER_API_KEY);

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
