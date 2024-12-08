/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Functional_Dependencies;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/**
 *
 * @author anupbochare
 */
public class WeatherService {
private static final String API_KEY = "your_openweathermap_api_key";
    private static final String CITY = "Boston,US";
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather";
    
    public JSONObject getWeatherData() throws Exception {
        String urlString = String.format("%s?q=%s&appid=%s&units=imperial", API_URL, CITY, API_KEY);
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        
        return new JSONObject(response.toString());
    }
}
