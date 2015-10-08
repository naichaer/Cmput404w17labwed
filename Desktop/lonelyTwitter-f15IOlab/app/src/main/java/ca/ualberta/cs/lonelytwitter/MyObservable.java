package ca.ualberta.cs.lonelytwitter;

/**
 * Created by hong8 on 10/7/15.
 */
public interface MyObservable {

    void addObserver(MyObserver observer);

    void notifyObservers();

}
