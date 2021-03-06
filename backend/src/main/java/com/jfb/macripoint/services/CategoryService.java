package com.jfb.macripoint.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import com.jfb.macripoint.dto.CategoryDTO;
import com.jfb.macripoint.entities.Category;
import com.jfb.macripoint.repository.CategoryRepository;
import com.jfb.macripoint.services.exceptions.DatabaseException;
import com.jfb.macripoint.services.exceptions.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

  @Autowired
  private CategoryRepository repository;

  @Transactional(readOnly = true)
  public List<CategoryDTO> findAll() {
    List<Category> list = repository.findAll();
    List<CategoryDTO> listDto = list.stream().map(cat -> new CategoryDTO(cat)).collect(Collectors.toList());
    return listDto;
  }

  @Transactional(readOnly = true)
  public CategoryDTO findById(Long id) {
    Optional<Category> obj = repository.findById(id);
    Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada!"));
    return new CategoryDTO(entity);
  }

  @Transactional
  public CategoryDTO insert(CategoryDTO dto) {
    Category entity = new Category();
    entity.setName(dto.getName());
    entity = repository.save(entity);
    return new CategoryDTO(entity);
  }

  @Transactional
  public CategoryDTO update(Long id, CategoryDTO dto) {
    try {
      Category entity = repository.getOne(id);
        entity.setName(dto.getName());
        entity = repository.save(entity);
      return new CategoryDTO(entity);
    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException("ID não encontrado " + id);
    }
  }

  public void delete(Long id) {
    try {
      repository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new ResourceNotFoundException("ID não encontrado " + id);
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseException("Violação de integridade!");
    }
  }

}
