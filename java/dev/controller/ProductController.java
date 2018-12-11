package dev.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.ProductVM;
import dev.repository.ProductRepo;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductRepo productRepo;

	@GetMapping
	public List<ProductVM> findSome(@RequestParam String type, @RequestParam int number) {
		return productRepo.findAll().subList(0, number).stream().map(ProductVM::new).collect(Collectors.toList());
	}
}
