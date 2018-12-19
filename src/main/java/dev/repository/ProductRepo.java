package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
	Product findByName(String name);
}
