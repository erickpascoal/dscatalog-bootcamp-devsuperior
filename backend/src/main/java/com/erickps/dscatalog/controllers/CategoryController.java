package com.erickps.dscatalog.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erickps.dscatalog.entities.Category;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@GetMapping
	public ResponseEntity<List<Category>> listCategories() {
		List<Category> list = new ArrayList<Category>();

		list.add(new Category(1L, "books"));
		list.add(new Category(2L, "eletronics"));

		return ResponseEntity.ok().body(list);

	}

}
