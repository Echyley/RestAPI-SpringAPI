package com.library.library.services;

import com.library.library.exceptions.ResourceNotFoundException;
import com.library.library.model.Books;
import com.library.library.repositories.BooksRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class BooksService {
    
    private final Logger logger = Logger.getLogger(BooksService.class.getName());

    @Autowired
    BooksRepository repository;

    //Find All
    public List<Books> findAll() {
        logger.info("Finding all books!");

        return repository.findAll();
    }

    //Create
    public Books create(Books book) {
        logger.info("Creating a book!");

        return repository.save(book);
    }

    //Read
    public Books findById(Long id) {
        logger.info("Finding a book!");
        return repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
     }

    //Update
    public Books update(Long id, Books updatedBook) {
        Books existingBook = repository.findById(id).orElse(null);

        if (existingBook != null) {
            if (updatedBook.getTitle() != null) {
                existingBook.setTitle(updatedBook.getTitle());
            }
            if (updatedBook.getGenre() != null) {
                existingBook.setGenre(updatedBook.getGenre());
            }
            if (updatedBook.getSize() != null) {
                existingBook.setSize(updatedBook.getSize());
            }
           
            return repository.save(existingBook);
        }

        return null;
    }

    //Delete
    public void delete(Long id) {
        logger.info("Deleting a book!");

        var entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No records found for thid id"));

        repository.delete(entity);
    }
}
