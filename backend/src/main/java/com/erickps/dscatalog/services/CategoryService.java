package com.erickps.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erickps.dscatalog.dto.CategoryDTO;
import com.erickps.dscatalog.entities.Category;
import com.erickps.dscatalog.repositories.CategoryRepository;
import com.erickps.dscatalog.services.exceptions.EntityNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {
		List<Category> categories = categoryRepository.findAll();
		return categories.stream().map(category -> new CategoryDTO(category)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Optional<Category> optionalCategory = categoryRepository.findById(id);
		Category category = optionalCategory.orElseThrow(() -> new EntityNotFoundException("Objeto não encontrado."));
		return new CategoryDTO(category);
	}
}
