package com.knf.dev.librarymanagementsystem.controller;

import com.knf.dev.librarymanagementsystem.entity.Book;
import com.knf.dev.librarymanagementsystem.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    private final BookService bookService;

    public UserController(BookService bookService) {
        this.bookService = bookService;
    }

    // Show books with "completed" status to the user
    @GetMapping("/user/home")
    public String showUserHomePage(Model model) {
        // Fetch books with "completed" status from the service
        List<Book> completedBooks = bookService.findBooksByStatus("COMPLETED");

        // Add the list of completed books to the model
        model.addAttribute("completedBooks", completedBooks);

        return "user-home";  // View name for user home page
    }
}
