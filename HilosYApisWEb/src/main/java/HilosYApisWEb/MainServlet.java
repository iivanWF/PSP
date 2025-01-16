package HilosYApisWEb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {
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

		SynchronizedAPIHandler handler = new SynchronizedAPIHandler();
		handler.fetchWeatherData(municipio);
		handler.fetchCuriousData(municipio);

		try {
			String result = handler.waitForResponses();
			response.setContentType("application/json");
			response.getWriter().write(result);
		} catch (InterruptedException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("Error al sincronizar las respuestas.");
		}
	}
}
