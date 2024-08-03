package edu.librarysystem.observers;

/**
 * The {@code LibraryChangeListener} interface should be implemented by any class
 * that wishes to be notified when the library changes.
 */
public interface LibraryChangeListener {

    /**
     * Invoked when the library changes.
     */
    void onLibraryChanged();
}
