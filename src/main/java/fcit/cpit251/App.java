package fcit.cpit251;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.core5.net.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

public class App {
    private final static String API_URL = "https://api.openweathermap.org/data/2.5/weather";

    public static void main(String[] args) {
        URIBuilder uriBuilder = null;
        try {
            uriBuilder = new URIBuilder(API_URL);
            uriBuilder.addParameter("lat", "21.543333");
            uriBuilder.addParameter("lon", "39.172778");
            uriBuilder.addParameter("appid", System.getenv("API_KEY"));
            uriBuilder.addParameter("units", "metric");
            URI uri = uriBuilder.build();
            HttpResponse<String> response = HTTPHelper.sendGet(uri);
            if (response != null) {
                System.out.println(response.body());
                WeatherInfo wInfo = parseWeatherResponse(response.body(), WeatherInfo.class);
                System.out.println(wInfo);
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static WeatherInfo parseWeatherResponse(String responseString, Class<?> elementClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode weatherInfoNode = objectMapper.readTree(responseString);
            WeatherInfo wInfo = new WeatherInfo();
            double tempMin = weatherInfoNode.get("main").get("temp_min").doubleValue();
            double tempMax = weatherInfoNode.get("main").get("temp_max").doubleValue();
            String city = weatherInfoNode.get("name").toString();

            wInfo.setTempMin(tempMin);
            wInfo.setTempMax(tempMax);
            wInfo.setCity(city);


            return wInfo;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}