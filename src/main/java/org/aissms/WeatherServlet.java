package org.aissms;

import javax.servlet.ServletException;
	
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;


public class WeatherServlet extends HttpServlet {

	static String step1 = "https://api.openweathermap.org/geo/1.0/direct?q=%s&limit=%s&appid=%s";
	static String step2 = "https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=%s";
	static String apikey = "add api key here";

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		String city = req.getPathInfo();
		city = city.substring(1, city.length());
		String response = getWeatherData(city);
		out.println(response);
		out.flush();
	}

	private String getWeatherData(String city) throws IOException {
		return getCoordinates(city);

	}

	private String getCoordinates (String city) throws IOException {
		try {
			String url = String.format(step1, city, 1, apikey);
			System.out.println(url);
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

			HttpClient client = HttpClient.newHttpClient();
			HttpResponse<String> res = client.send(request, BodyHandlers.ofString());
			return res.body();
		} catch (InterruptedException e) {
			System.out.println(e);
		}
		return null;
	}
}