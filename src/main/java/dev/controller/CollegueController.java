package dev.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.CollegueVM2;
import dev.domain.Collegue;
import dev.domain.Role;
import dev.domain.RoleCollegue;
import dev.repository.CollegueRepo;

@CrossOrigin
@RestController
@RequestMapping("/utilisateur")
public class CollegueController {

	@Autowired
	private CollegueRepo collegueRepo;
	
	
	
	// add new person
	@PostMapping("/creer-compte")
	public ResponseEntity<String> addPerson(@RequestBody CollegueVM2 collegue) {
		List<RoleCollegue> roles = new ArrayList<>();
		Collegue collegueCreate = new Collegue(collegue.getName(), collegue.getFirstName(), collegue.getAdress(), collegue.getPhone(), collegue.getEmail(), collegue.getBirthDate(), collegue.getPassword());
		roles.add(new RoleCollegue(collegueCreate, Role.ROLE_UTILISATEUR));
		collegueCreate.setRoles(roles);
		
		if(!collegueRepo.findByEmail(collegue.getEmail()).isPresent()) {
			collegueRepo.save(collegueCreate);
			return new ResponseEntity<String>("{\"message\" : \"ajout du collègue avec succès\"}", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("\"{\"message\" : \"Vous disposez déjà d'un compte\"}", HttpStatus.BAD_REQUEST);
		}
	}
	
	
	// modify a person
	@Secured("ROLE_UTILISATEUR")
	@PatchMapping("/profil")
	public CollegueVM2 patch(@RequestBody Collegue collegue) {
		return collegueRepo.findByEmail(collegue.getEmail()).map(col -> {
			collegue.setId(col.getId());
			col = collegue;
			collegueRepo.save(col);
			return new CollegueVM2(col);
		}).orElseGet(() -> null);
	}
	
	
}
