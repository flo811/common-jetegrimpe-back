package dev.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.ProductVM;
import dev.domain.Product;
import dev.repository.ProductRepo;
import dev.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private ProductService productService;

	@GetMapping("/few")
	public List<ProductVM> findSome(@RequestParam String category, @RequestParam int number) {
		return productService.findByTypeLimited(category, number).stream().map(ProductVM::new).collect(Collectors.toList());
	}

	@GetMapping
	public List<ProductVM> findByCriteria(@RequestParam String name, @RequestParam String category,
			@RequestParam double priceMin, @RequestParam double priceMax, @RequestParam boolean isAsc,
			@RequestParam int pageNbr, @RequestParam int nbrByPage) {
		return productService.findByNameCatPriceOrd(name, category, priceMin, priceMax, isAsc, pageNbr, nbrByPage).stream()
				.map(ProductVM::new)
				.collect(Collectors.toList());
	}
	
	@Secured(value= {"ROLE_ADMINISTRATEUR"})
	@PostMapping()
	public String createProduct(@RequestBody Product newProduct){
						
			return productRepo.findByName(newProduct.getName())
							.map(prod -> "{\"message\":\"name "+prod.getName()+" already used\"}")
							.orElseGet(() -> {productRepo.save(newProduct);
												return "{\"message\":\"succès\"}";
											 });
	}
	
}
