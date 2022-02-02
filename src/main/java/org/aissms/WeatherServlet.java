package org.aissms;

import javax.servlet.ServletException;
	
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class WeatherServlet extends HttpServlet {

	static String step1 = "http://api.openweathermap.org/geo/1.0/direct?q=%s&limit=%sappid=%s";
	static String step2 = "api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=%s";

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("application/json");
		PrintWriter out = res.getWriter();

		String city = req.getPathInfo();
		city = city.substring(1, city.length());
		out.println("{\"city\":\"" + city + "\"}");
		out.flush();
	}
}