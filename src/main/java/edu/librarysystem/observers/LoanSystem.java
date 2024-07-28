package edu.librarysystem.observers;

import edu.librarysystem.interfaces.LibraryItem;
import edu.librarysystem.models.Book;

import java.util.ArrayList;
import java.util.List;

public class LoanSystem implements Observer {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void update(LibraryItem item) {
        for (Observer observer : observers) {
            if (observer instanceof BookObserver && item instanceof Book) {
                observer.update(item);
            }
        }
    }
}
