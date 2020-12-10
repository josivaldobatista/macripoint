package com.jfb.macripoint.services;

import java.util.List;

import com.jfb.macripoint.entities.Category;
import com.jfb.macripoint.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;
    
    public List<Category> findAll() {
        return repository.findAll();
    }
}
