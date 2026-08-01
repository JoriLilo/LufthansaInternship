package com.example.LibraryManagementSys;

import com.example.LibraryManagementSys.service.BookService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/add")
    public String addBook(@RequestParam String title, @RequestParam String isbn) {
        return bookService.addBook(title, isbn);
    }

    @PostMapping("/borrow")
    public String borrowBook(@RequestParam String title, @RequestParam String isbn) {
        return bookService.borrowBook(title, isbn);
    }

    @PostMapping("/return")
    public String returnBook(@RequestParam String title, @RequestParam String isbn) {
        return bookService.returnBook(title, isbn);
    }


    @GetMapping("/search/{isbn}")
    public String searchBook(@PathVariable String isbn) {
        return bookService.searchBook(isbn);
    }

    @DeleteMapping
    public String deleteBook(@RequestParam String isbn) {
        return bookService.deleteBook(isbn);
    }
}
