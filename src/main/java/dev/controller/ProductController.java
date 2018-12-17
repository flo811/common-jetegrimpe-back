package dev.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.ProductVM;
import dev.domain.Product;
import dev.domain.Role;
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
	public List<ProductVM> findSome(@RequestParam String type, @RequestParam int number) {
		return productRepo.findAll().subList(0, number).stream().map(ProductVM::new).collect(Collectors.toList());
	}

	@GetMapping
	public List<ProductVM> findByCriteria(@RequestParam String name, @RequestParam String category,
			@RequestParam double priceMin, @RequestParam double priceMax, @RequestParam boolean isAsc,
			@RequestParam int pageNbr, @RequestParam int nbrByPage) {
		return productService.findByNameCatPriceOrd(name, category, priceMin, priceMax, isAsc, pageNbr, nbrByPage).stream()
				.map(ProductVM::new)
				.collect(Collectors.toList());
	}
	
	
	
	// Modify a product
	@Secured("ROLE_ADMINISTRATEUR")
	@PatchMapping("/{name}")
	public ProductVM patch(@PathVariable String name, @RequestBody Product productNew){
		
		Product productOld = productRepo.findByName(name);
		System.out.println("djehdiede");
		productNew.setId(productOld.getId());

		productRepo.save(productNew); 
		return new ProductVM(productNew);
	}
	
}
