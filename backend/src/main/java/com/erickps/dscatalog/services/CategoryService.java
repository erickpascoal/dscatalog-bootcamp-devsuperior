package com.erickps.dscatalog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erickps.dscatalog.dto.CategoryDTO;
import com.erickps.dscatalog.entities.Category;
import com.erickps.dscatalog.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {

		List<Category> categories = categoryRepository.findAll();

		return categories.stream().map(category -> new CategoryDTO(category)).collect(Collectors.toList());
	}
}
