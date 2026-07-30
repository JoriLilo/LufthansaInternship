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


}
