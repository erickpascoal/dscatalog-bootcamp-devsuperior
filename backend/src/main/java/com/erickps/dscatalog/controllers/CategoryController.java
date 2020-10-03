package com.erickps.dscatalog.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.erickps.dscatalog.dto.CategoryDTO;
import com.erickps.dscatalog.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public ResponseEntity<Page<CategoryDTO>> findAllPaged(@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "rowsPerPage", defaultValue = "10") Integer rowsPerPage,
			@RequestParam(name = "direction", defaultValue = "ASC") String direction,
			@RequestParam(name = "orderBy", defaultValue = "name") String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, rowsPerPage, Direction.valueOf(direction), orderBy);

		Page<CategoryDTO> categories = categoryService.findAllPaged(pageRequest);
		return ResponseEntity.ok().body(categories);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
		CategoryDTO categoryDTO = categoryService.findById(id);
		return ResponseEntity.ok().body(categoryDTO);
	}

	@PostMapping
	public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO categoryDTO) {
		CategoryDTO categoryCreatedDTO = categoryService.create(categoryDTO);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(categoryCreatedDTO.getId()).toUri();

		return ResponseEntity.created(uri).body(categoryCreatedDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
		CategoryDTO categoryUpdatedDTO = categoryService.update(categoryDTO, id);
		return ResponseEntity.ok().body(categoryUpdatedDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CategoryDTO> delete(@PathVariable Long id) {
		categoryService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
