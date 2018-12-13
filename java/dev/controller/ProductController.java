package dev.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.ProductVM;
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
		return productService.findByNameCatPriceOrd("", category, 0, Integer.MAX_VALUE, "asc", 1, Integer.MAX_VALUE).stream()
				.map(ProductVM::new)
				.collect(Collectors.toList());
	}

	@GetMapping("/criteria")
	public List<ProductVM> findByCriteria(@RequestParam String name, @RequestParam String category,
			@RequestParam double priceMin, @RequestParam double priceMax, @RequestParam String sort,
			@RequestParam int pageNbr, @RequestParam int nbrByPage) {
		return productService.findByNameCatPriceOrd(name, category, priceMin, priceMax, sort, pageNbr, nbrByPage).stream()
				.map(ProductVM::new)
				.collect(Collectors.toList());
	}

	@GetMapping("/count")
	public long getResultNumberByCriteria(@RequestParam String name, @RequestParam String category,
			@RequestParam double priceMin, @RequestParam double priceMax) {
		return productService.findByNameCatPriceOrd(name, category, priceMin, priceMax, "asc", 1, Integer.MAX_VALUE).stream().count();
	}
}
