package com.jfb.macripoint.services;

import java.util.List;
import java.util.stream.Collectors;

import com.jfb.macripoint.dto.CategoryDTO;
import com.jfb.macripoint.entities.Category;
import com.jfb.macripoint.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;
    
    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> list = repository.findAll(); 
        List<CategoryDTO> listDto = list.stream().map(cat -> new CategoryDTO(cat))
            .collect(Collectors.toList());
        return listDto;
    }
}
