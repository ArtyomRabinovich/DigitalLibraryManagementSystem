    package edu.librarysystem.facade;

    import edu.librarysystem.commands.*;
    import edu.librarysystem.interfaces.Command;
    import edu.librarysystem.invoker.LibrarySystem;
    import edu.librarysystem.services.LibraryItemService;
    import edu.librarysystem.services.UserService;

    /**
     * The {@code LibraryFacade} class provides a simplified interface for managing
     * library items and members by encapsulating the complexity of the underlying
     * command pattern implementation.
     */
    public class LibraryFacade {
        private final LibrarySystem librarySystem;
        private final LibraryItemService libraryItemService;
        private final UserService userService;

        /**
         * Constructs a new {@code LibraryFacade} with the specified services.
         *
         * @param librarySystem      the library system to manage commands
         * @param libraryItemService the service to manage library items
         * @param userService        the service to manage library members
         */
        public LibraryFacade(LibrarySystem librarySystem, LibraryItemService libraryItemService, UserService userService) {
            this.librarySystem = librarySystem;
            this.libraryItemService = libraryItemService;
            this.userService = userService;
        }

        /**
         * Adds a new book to the library.
         *
         * @param title         the title of the book
         * @param author        the author of the book
         * @param pages         the number of pages in the book
         * @param isbn          the ISBN of the book
         * @param yearPublished the year the book was published
         */
        public void addBook(String title, String author, int pages, String isbn, int yearPublished) {
            Command addBookCommand = new AddLibraryItemCommand(libraryItemService, title, author, pages, isbn, yearPublished);
            librarySystem.addCommand(addBookCommand);
        }

        /**
         * Deletes a book from the library.
         *
         * @param id the ID of the book to delete
         */
        public boolean deleteBook(int id) {
            Command deleteBookCommand = new DeleteLibraryItemCommand(libraryItemService, id);
            librarySystem.addCommand(deleteBookCommand);
            return false;
        }

        /**
         * Loans a book to a member.
         *
         * @param bookId   the ID of the book to loan
         * @param memberId the ID of the member to loan the book to
         */
        public void loanBook(int bookId, int memberId) {
            Command loanBookCommand = new LoanLibraryItemCommand(libraryItemService, bookId, memberId);
            librarySystem.addCommand(loanBookCommand);
        }

        /**
         * Returns a loaned book to the library.
         *
         * @param id the ID of the book to return
         */
        public void returnBook(int id) {
            Command returnBookCommand = new ReturnLibraryItemCommand(libraryItemService, id);
            librarySystem.addCommand(returnBookCommand);
        }

        /**
         * Adds a new member to the library.
         *
         * @param name the name of the member to add
         */
        public void addMember(String name) {
            Command addMemberCommand = new AddMemberCommand(userService, name);
            librarySystem.addCommand(addMemberCommand);
        }

        /**
         * Deletes a member from the library.
         *
         * @param id the ID of the member to delete
         */
        public boolean deleteMember(int id) {
            Command deleteMemberCommand = new DeleteMemberCommand(userService, id);
            librarySystem.addCommand(deleteMemberCommand);
            return false;
        }

        /**
         * Duplicates a book in the library.
         *
         * @param id the ID of the book to duplicate
         */
        public void duplicateBook(int id) {
            Command duplicateBookCommand = new DuplicateBookCommand(libraryItemService, id);
            librarySystem.addCommand(duplicateBookCommand);
        }
    }
