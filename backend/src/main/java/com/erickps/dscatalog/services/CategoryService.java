package com.erickps.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erickps.dscatalog.dto.CategoryDTO;
import com.erickps.dscatalog.entities.Category;
import com.erickps.dscatalog.repositories.CategoryRepository;
import com.erickps.dscatalog.services.exceptions.ServiceNotFoundException;

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
		Category category = optionalCategory.orElseThrow(() -> new ServiceNotFoundException("Objeto não encontrado."));
		return new CategoryDTO(category);
	}

	@Transactional
	public CategoryDTO create(CategoryDTO categoryDTO) {
		Category category = new Category();
		category.setName(categoryDTO.getName());
		Category categoryCreated = categoryRepository.save(category);
		return new CategoryDTO(categoryCreated);
	}

	@Transactional
	public CategoryDTO update(CategoryDTO categoryDTO, Long id) {
		try {
			Category category = categoryRepository.getOne(id);
			category.setName(categoryDTO.getName());
			Category categoryUpdated = categoryRepository.save(category);
			return new CategoryDTO(categoryUpdated);
		} catch (EntityNotFoundException error) {
			throw new ServiceNotFoundException("Objeto não encontrado.");
		}
	}
}
