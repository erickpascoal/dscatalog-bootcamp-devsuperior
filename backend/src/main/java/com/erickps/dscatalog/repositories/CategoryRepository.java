package com.erickps.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erickps.dscatalog.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
