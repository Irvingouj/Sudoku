package Interfaces;

import java.util.ArrayList;
import java.util.List;

public interface ObservedObject {
    List<Observer> observers = new ArrayList<>();


    void addObserver(Observer observer);



    
    public void somethingHasChanged();
}
