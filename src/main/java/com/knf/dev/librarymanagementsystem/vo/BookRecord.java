package com.knf.dev.librarymanagementsystem.vo;

import com.knf.dev.librarymanagementsystem.entity.Book;

public record BookRecord(
        Long id,
        String isbn,
        String name,
        String serialName,
        String description,
        Book.BookStatus status
) { }