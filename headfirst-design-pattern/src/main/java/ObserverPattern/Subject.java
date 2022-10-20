package ObserverPattern;

public interface Subject extends Observer {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
