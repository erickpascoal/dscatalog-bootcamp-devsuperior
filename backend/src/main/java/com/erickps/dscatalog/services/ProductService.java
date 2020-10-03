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

import com.erickps.dscatalog.dto.ProductDTO;
import com.erickps.dscatalog.models.Product;
import com.erickps.dscatalog.repositories.ProductRepository;
import com.erickps.dscatalog.services.exceptions.DatabaseException;
import com.erickps.dscatalog.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
		Page<Product> categories = productRepository.findAll(pageRequest);
		return categories.map(product -> new ProductDTO(product));
	}

	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		Product product = optionalProduct.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado."));
		return new ProductDTO(product, product.getCategories());
	}

	@Transactional
	public ProductDTO create(ProductDTO productDTO) {
		Product product = new Product();
		product.setName(productDTO.getName());
		Product productCreated = productRepository.save(product);
		return new ProductDTO(productCreated);
	}

	@Transactional
	public ProductDTO update(ProductDTO productDTO, Long id) {
		try {
			Product product = productRepository.getOne(id);
			product.setName(productDTO.getName());
			Product productUpdated = productRepository.save(product);
			return new ProductDTO(productUpdated);
		} catch (EntityNotFoundException e) {
			throw new ObjectNotFoundException("Objeto não encontrado. id: " + id);
		}
	}

	public void delete(Long id) {
		try {
			productRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException("Objeto não encontrado. id: " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Objeto ligado a outro e não pode ser deletado");
		}

	}
}
