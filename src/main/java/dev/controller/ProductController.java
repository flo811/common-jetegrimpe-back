
package dev.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/{name}")
	public ProductVM findByName(@PathVariable String name) {
//		return new ProductVM(productRepo.findByName(name));
		return productRepo.findByName(name)
				.map(prod -> new ProductVM(prod))
				.orElseGet(() -> null); //A modifier en travaillant à partir de response entity pour traiter ce cas.
	}

	@GetMapping("/few")
	public List<ProductVM> findSome(@RequestParam String category, @RequestParam int number) {
		return productService.findByNameCatPriceOrd("", category, 0, Integer.MAX_VALUE, "asc", 1, Integer.MAX_VALUE).stream()
				.map(ProductVM::new)
				.collect(Collectors.toList());
	}

	@GetMapping
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
	
	// Modify a product
	@Secured("ROLE_ADMINISTRATEUR")
	@PatchMapping("/{name}")
	public ProductVM patch(@PathVariable String name, @RequestBody Product productNew){
		
		//Méthode avec repo => ProductVM
//		Product productOld = productRepo.findByName(name);
//		System.out.println("djehdiede");
//		productNew.setId(productOld.getId());
//
//		productRepo.save(productNew); 
//		return new ProductVM(productNew);
		

		//Méthode avec repo => Optional
		return productRepo.findByName(name)
				   .map(prod -> {productNew.setId(prod.getId());
				   				 prod = productNew;
				   				 productRepo.save(prod);
				   				 return new ProductVM(prod);})
								.orElseGet(() -> null); //A modifier en travaillant à partir de response entity pour traiter ce cas.
		
		
	}


		
//	@Secured("ROLE_ADMINISTRATEUR")
//	@PostMapping()
//	public String createProduct(@RequestBody Product newProduct){
//						
//			return productRepo.findByName(newProduct.getName())
//							.map(prod -> "{\"message\":\"name "+prod.getName()+" already used\"}")
//							.orElseGet(() -> {productRepo.save(newProduct);
//												return "{\"message\":\"succès\"}";
//											 });
//
//	}
	
	@Secured("ROLE_ADMINISTRATEUR")
	@PostMapping()
	public ResponseEntity<String> createProduct(@RequestBody Product newProduct){		
					
								
			return productRepo.findByName(newProduct.getName())
							.map(prod -> ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
													   .body("{\"message\":\"name "+prod.getName()+" already used\"}"))
 							.orElseGet(() -> {productRepo.save(newProduct);
 												return ResponseEntity.status(HttpStatus.OK)
 													   .body("{\"message\":\" "+newProduct.getName()+" ajouté avec succès\"}");
 											 });
			
	}	

	
}
