package com.library.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.library.model.Books;


public interface BooksRepository extends JpaRepository<Books, Long>{
    
}
