package interfaces;

public interface IObserverTarget {
    void addObserver(IObserver observer);
    void removeObserver(IObserver observer);
}
