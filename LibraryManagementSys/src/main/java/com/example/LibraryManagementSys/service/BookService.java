package com.example.LibraryManagementSys.service;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private static final Logger log = LogManager.getLogger(BookService.class);

    public String addBook(String title, String isbn) {
        log.trace("Entering addBook() — title={}, isbn={}", title, isbn);
        log.debug("Validating book data before insert — isbn={}", isbn);
        if (title == null || title.isBlank()) {
            log.error("addBook() failed — title must not be blank");
            return "ERROR: title is required";
        }
        log.info("Book added successfully — title='{}', isbn={}", title, isbn);
        return "Book '" + title + "' added.";
    }

    public String borrowBook(String title, String isbn) {
        log.trace("Entering borrowBook() — title={}, isbn={}", title, isbn);
        log.debug("Checking availability before borrow — isbn={}", isbn);
        log.warn("Book stock is running low — isbn={}", isbn);
        log.info("Book borrowed successfully — title='{}'", title);
        return "Book '" + title + "' borrowed.";
    }

    public String returnBook(String title, String isbn) {
        log.trace("Entering returnBook() — title={}, isbn={}", title, isbn);
        log.info("Book returned successfully — title='{}'", title);
        return "Book '" + title + "' returned.";
    }

    public String searchBook(String isbn) {
        log.trace("Entering searchBook() method.");
        log.debug("Searching book with ISBN={}", isbn);
        log.info("Search completed for ISBN={}", isbn);
        return "Search result for " + isbn;
    }

    public String deleteBook(String isbn) {
        log.trace("Entering deleteBook() — isbn={}", isbn);
        log.error("Failed to delete the book because it does not exist — isbn={}", isbn);
        log.fatal("Database connection unavailable. Application cannot continue.");
        return "ERROR: could not delete " + isbn;
    }






}
