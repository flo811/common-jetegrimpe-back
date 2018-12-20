package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import dev.domain.RoleCollegue;

public interface CollegueRoleRepo extends JpaRepository<RoleCollegue, Long> {
	
}
