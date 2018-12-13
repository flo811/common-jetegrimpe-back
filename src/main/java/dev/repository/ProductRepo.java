package dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{
	
	public Optional<Product> findByName(String name);

}
