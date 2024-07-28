package edu.librarysystem.interfaces;

import edu.librarysystem.models.Member;

public interface LibraryItem {
    String getTitle();
    String getAuthor();
    int getPages();
    boolean isAvailable();
    void setAvailable(boolean available);
    Member getLoanedTo();
    void setLoanedTo(Member loanedTo);
}
