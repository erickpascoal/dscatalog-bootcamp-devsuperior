package com.erickps.dscatalog.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erickps.dscatalog.dto.CategoryDTO;
import com.erickps.dscatalog.models.Category;
import com.erickps.dscatalog.repositories.CategoryRepository;
import com.erickps.dscatalog.services.exceptions.DatabaseException;
import com.erickps.dscatalog.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional(readOnly = true)
	public Page<CategoryDTO> findAllPaged(PageRequest pageRequest) {
		Page<Category> categories = categoryRepository.findAll(pageRequest);
		return categories.map(category -> new CategoryDTO(category));
	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Optional<Category> optionalCategory = categoryRepository.findById(id);
		Category category = optionalCategory.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado."));
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
		} catch (EntityNotFoundException e) {
			throw new ObjectNotFoundException("Objeto não encontrado. id: " + id);
		}
	}

	public void delete(Long id) {
		try {
			categoryRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException("Objeto não encontrado. id: " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Objeto ligado a outro e não pode ser deletado");
		}

	}
}
