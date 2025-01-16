package HilosYApisWEb;

import java.util.concurrent.CountDownLatch;

public class SynchronizedAPIHandler {
	private String weatherData;
	private String curiousData;
	private final CountDownLatch latch = new CountDownLatch(2);

	public void fetchWeatherData(String municipio) {
		new Thread(() -> {
			try {
				// Llamar al servlet de información meteorológica
				weatherData = callAPI("/HilosYApisWEB/weather?municipio=" + municipio);
			} finally {
				latch.countDown();
			}
		}).start();
	}

	public void fetchCuriousData(String municipio) {
		new Thread(() -> {
			try {
				// Llamar al servlet de datos curiosos
				curiousData = callAPI("/HilosYApisWEB/curious?municipio=" + municipio);
			} finally {
				latch.countDown();
			}
		}).start();
	}

	public String waitForResponses() throws InterruptedException {
		latch.await();
		return "{ \"weather\": " + weatherData + ", \"curious\": " + curiousData + " }";
	}

	private String callAPI(String endpoint) {
		// Implementar llamada al servlet correspondiente
		return ""; // Simulación
	}
}
