package fcit.cpit251;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class WeatherInfo {

    private double tempMin;
    private double tempMax;
    private String city;

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    @SuppressWarnings("unchecked")
    @JsonProperty("main")
    private void unpackNested(Map<String, Object> main) {
        this.tempMin = (double) main.get("temp_min");
        this.tempMax = (double) main.get("temp_max");
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("name")
    private void unpackkNested(Map<String, Object> cord) {
        this.city = (String) cord.get("name");

    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                " tempMin=" + tempMin +
                "°C, tempMax=" + tempMax +
                "°C, city=" + city.replace("\"","") +
                '}';
    }
}