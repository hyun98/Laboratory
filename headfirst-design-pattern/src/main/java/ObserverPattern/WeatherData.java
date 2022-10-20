package ObserverPattern;

import java.util.ArrayList;
import java.util.List;

public class WeatherData implements Subject {

    private static List<Observer> observerList = new ArrayList<>();
    private float temperature;
    private float humidity;
    private float pressure;

    @Override
    public void registerObserver(Observer o) {
        observerList.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observerList.remove(o);
    }

    @Override
    public void notifyObservers() {
        observerList.forEach(o -> o.update(temperature, humidity, pressure));
    }

    public void getMeasurements() {
        
    }

    public void setMeasurements(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        this.pressure = pressure;
    }
}
