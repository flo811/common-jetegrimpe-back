package dev.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.Collegue;
import dev.domain.Role;
import dev.domain.RoleCollegue;
import dev.repository.CollegueRepo;

@CrossOrigin
@RestController
@RequestMapping("/collegue")
public class CollegueController {

	@Autowired
	private CollegueRepo collegueRepo;

	@PostMapping("/creer")
	public ResponseEntity<?> addingCollegue(@RequestBody Map<String, String> protoCollegue) {
		Collegue collegue = new Collegue(protoCollegue.get("lastName"), protoCollegue.get("firstName"),
				protoCollegue.get("adress"), Long.valueOf(protoCollegue.get("phone")), protoCollegue.get("email"),
				LocalDate.parse(protoCollegue.get("birthDate")), protoCollegue.get("password"));

		List<RoleCollegue> roles = new ArrayList<>();
		roles.add(new RoleCollegue(collegue, Role.ROLE_UTILISATEUR));

		collegue.setRoles(roles);

		this.collegueRepo.save(collegue);

		return ResponseEntity.ok("Collegue ajouté");
	}
}
