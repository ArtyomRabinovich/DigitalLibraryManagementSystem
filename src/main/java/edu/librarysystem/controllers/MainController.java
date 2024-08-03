package edu.librarysystem.controllers;

import edu.librarysystem.models.Book;
import edu.librarysystem.models.Member;
import edu.librarysystem.singleton.Library;
import edu.librarysystem.observers.LibraryChangeListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class MainController implements LibraryChangeListener {

    @FXML
    private TableView<Book> bookTableView;

    @FXML
    private TableColumn<Book, Integer> bookIdColumn;

    @FXML
    private TableColumn<Book, String> bookTitleColumn;

    @FXML
    private TableColumn<Book, String> bookAuthorColumn;

    @FXML
    private TableColumn<Book, Integer> bookPagesColumn;

    @FXML
    private TableColumn<Book, Boolean> bookAvailableColumn;

    @FXML
    private TableColumn<Book, String> bookLoanedToColumn;

    @FXML
    private TableColumn<Book, String> bookIsbnColumn;

    @FXML
    private TableColumn<Book, Integer> bookYearPublishedColumn;

    @FXML
    private TableView<Member> memberTableView;

    @FXML
    private TableColumn<Member, Integer> memberIdColumn;

    @FXML
    private TableColumn<Member, String> memberNameColumn;

    @FXML
    private TableColumn<Member, Integer> memberTotalBooksLoanedColumn;

    @FXML
    private TableColumn<Member, Integer> memberCurrentlyLoanedBooksColumn;

    @FXML
    private TableColumn<Member, String> memberCreationDateColumn;

    @FXML
    private Label totalBooksLabel;

    @FXML
    private Label totalMembersLabel;

    @FXML
    private Label totalLoanedBooksLabel;

    @FXML
    private Label activeReadersLabel;

    @FXML
    private Label totalBooksLoanedLabel;

    private final Library library = Library.getInstance();

    @FXML
    public void initialize() {
        library.addChangeListener(this);

        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        bookTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        bookAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        bookPagesColumn.setCellValueFactory(new PropertyValueFactory<>("pages"));
        bookAvailableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));
        bookIsbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        bookYearPublishedColumn.setCellValueFactory(new PropertyValueFactory<>("yearPublished"));

        bookLoanedToColumn.setCellValueFactory(cellData -> {
            Book book = cellData.getValue();
            return new SimpleStringProperty(book.getLoanedTo() != null ? book.getLoanedTo().getName() : "");
        });

        memberIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        memberNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        memberTotalBooksLoanedColumn.setCellValueFactory(new PropertyValueFactory<>("totalBooksLoaned"));
        memberCurrentlyLoanedBooksColumn.setCellValueFactory(new PropertyValueFactory<>("currentlyLoanedBooks"));
        memberCreationDateColumn.setCellValueFactory(new PropertyValueFactory<>("creationDate"));

        loadBookData();
        loadMemberData();
        loadStatistics();
    }

    @FXML
    private void handleAddMember(ActionEvent event) throws IOException {
        openDialog("/edu/librarysystem/addMember.fxml", "Add Member");
        loadMemberData();
        loadStatistics();
    }

    @FXML
    private void handleAddBook(ActionEvent event) throws IOException {
        openDialog("/edu/librarysystem/addBook.fxml", "Add Book");
        loadBookData();
        loadStatistics();
    }

    @FXML
    private void handleLoanBook(ActionEvent event) throws IOException {
        openDialog("/edu/librarysystem/loanBook.fxml", "Loan Book");
        loadBookData();
        loadMemberData();
        loadStatistics();
    }

    @FXML
    private void handleReturnBook(ActionEvent event) throws IOException {
        openDialog("/edu/librarysystem/returnBook.fxml", "Return Book");
        loadBookData();
        loadMemberData();
        loadStatistics();
    }

    @FXML
    private void handleDeleteBook(ActionEvent event) throws IOException {
        openDialog("/edu/librarysystem/deleteBook.fxml", "Delete Book");
        loadBookData();
        loadMemberData();
        loadStatistics();
    }

    @FXML
    private void handleDeleteMember(ActionEvent event) throws IOException {
        openDialog("/edu/librarysystem/deleteMember.fxml", "Delete Member");
        loadMemberData();
        loadStatistics();
    }

    @FXML
    private void handleCloneBook(ActionEvent event) throws IOException {
        openDialog("/edu/librarysystem/cloneBook.fxml", "Clone Book");
        loadBookData();
        loadMemberData();
        loadStatistics();
    }

    private void openDialog(String fxmlFile, String title) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.showAndWait();
        loadBookData();
        loadMemberData();
        loadStatistics();
    }

    private void loadBookData() {
        System.out.println("Loading book data");
        List<Book> books = library.getAllBooks();
        ObservableList<Book> observableBooks = FXCollections.observableArrayList(books);
        bookTableView.setItems(observableBooks);
        bookTableView.refresh();
    }

    private void loadMemberData() {
        System.out.println("Loading member data");
        List<Member> members = library.getAllMembers();
        ObservableList<Member> observableMembers = FXCollections.observableArrayList(members);
        memberTableView.setItems(observableMembers);
        memberTableView.refresh();
    }

    private void loadStatistics() {
        int totalBooks = library.getAllBooks().size();
        int totalAvailableBooks = (int) library.getAllBooks().stream().filter(Book::isAvailable).count();
        int totalMembers = library.getAllMembers().size();
        int totalLoanedBooks = totalBooks - totalAvailableBooks;
        int activeReaders = (int) library.getAllMembers().stream().filter(member -> member.getCurrentlyLoanedBooks() > 0).count();
        int totalBooksLoaned = library.getAllMembers().stream().mapToInt(Member::getTotalBooksLoaned).sum();

        totalBooksLabel.setText("Total Available Books: " + totalAvailableBooks);
        totalMembersLabel.setText("Total Members: " + totalMembers);
        totalLoanedBooksLabel.setText("Total Loaned Books: " + totalLoanedBooks);
        activeReadersLabel.setText("Active Readers: " + activeReaders);
        totalBooksLoanedLabel.setText("Total Books Loaned: " + totalBooksLoaned);
    }

    @Override
    public void onLibraryChanged() {
        System.out.println("Library change detected");
        loadBookData();
        loadMemberData();
        loadStatistics();
    }
}
