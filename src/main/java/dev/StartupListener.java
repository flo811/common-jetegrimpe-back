package dev;

import dev.domain.Collegue;
import dev.domain.Product;
import dev.domain.Role;
import dev.domain.RoleCollegue;
import dev.repository.CollegueRepo;
import dev.repository.ProductRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Code de démarrage de l'application.
 * Insertion de jeux de données.
 */
@Component
public class StartupListener {

    private String appVersion;
    private PasswordEncoder passwordEncoder;
    private CollegueRepo collegueRepo;
    
    @Autowired
    private ProductRepo productRepo;

    public StartupListener(@Value("${app.version}") String appVersion, PasswordEncoder passwordEncoder, CollegueRepo collegueRepo) {
        this.appVersion = appVersion;
        this.passwordEncoder = passwordEncoder;
        this.collegueRepo = collegueRepo;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void onStart() {
        // Création de deux utilisateurs

       Collegue col1 = new Collegue();
        col1.setNom("Admin");
        col1.setPrenom("DEV");
        col1.setEmail("admin@dev.fr");
        col1.setMotDePasse(passwordEncoder.encode("superpass"));
        col1.setRoles(Arrays.asList(new RoleCollegue(col1, Role.ROLE_ADMINISTRATEUR), new RoleCollegue(col1, Role.ROLE_UTILISATEUR)));
        this.collegueRepo.save(col1);

        Collegue col2 = new Collegue();
        col2.setNom("User");
        col2.setPrenom("DEV");
        col2.setEmail("user@dev.fr");
        col2.setMotDePasse(passwordEncoder.encode("superpass"));
        col2.setRoles(Arrays.asList(new RoleCollegue(col2, Role.ROLE_UTILISATEUR)));
        this.collegueRepo.save(col2);
        

        productRepo.save(new Product("name1", "name", "name",1.0, "Accessoire", true, 2));
        productRepo.save(new Product("name2", "name", "name",10.0, "Accessoire", true, 2));
        productRepo.save(new Product("name3", "name", "name",5.0, "Accessoire", true, 2));
        productRepo.save(new Product("name4", "name", "name",1.0, "Accessoire", true, 2));
        productRepo.save(new Product("name5", "name", "name",1.0, "Baudrier", true, 2));
        productRepo.save(new Product("name6", "name", "name",10.0, "Baudrier", true, 2));
        productRepo.save(new Product("name7", "name", "name",1.0, "Baudrier", true, 2));

    }
}
