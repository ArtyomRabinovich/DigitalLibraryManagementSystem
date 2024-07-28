package edu.librarysystem.observers;

import edu.librarysystem.interfaces.LibraryItem;

public interface Observer {
    void update(LibraryItem item);
}
