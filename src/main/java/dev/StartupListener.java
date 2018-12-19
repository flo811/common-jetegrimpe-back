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

/*
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

    	// Product "accessoire"
        productRepo.save(new Product("Agrès traction", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/1/2/1297_1-maxgrips-max-climbing.jpg", "name",1.0, "Accessoire", true, 5));
        productRepo.save(new Product("Lattes Pan Gullich", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/1/2/1295_1-campus-crimps-entre-prises.jpg", "name",1.0, "Accessoire", true, 15));
        productRepo.save(new Product("Lunettes d'assurage", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/b/e/be59_1-surlunettes-d-assurage-clip-up-y_y.jpg", "name",1.0, "Accessoire", true, 25));
        productRepo.save(new Product("Strap", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/b/z/bz03_1-rouleau-fx38-strap-i_bbz.jpg", "name",1.0, "Accessoire", true, 35));
        productRepo.save(new Product("Brosse en poil de mamouth", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/b/z/bz01_1-brosse-frixion-i_bbz.jpg", "name",1.0, "Accessoire", true, 2));
        productRepo.save(new Product("Sac à Magnésie", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/b/z/bz04_1-sac-a-magnesie-rasta-i_bbz.jpg", "name",1.0, "Accessoire", true, 2));
        productRepo.save(new Product("Lot de coinceurs", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/1/6/1653_1-set-pro-nuts-camp.jpg", "name",1.0, "Accessoire", true, 2));
        productRepo.save(new Product("Friend taille 2", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/1/0/10bj_1-camalot-ultralight-2-black-diamond.jpg", "name",1.0, "Accessoire", true, 2));
        productRepo.save(new Product("Friend taille 4", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/1/0/10bn_1-coinceur-camalot-ultralight-4-black-diamond.jpg", "name",1.0, "Accessoire", true, 2));
        productRepo.save(new Product("Magnésie liquide", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/0/4/0498_1-pure-grip-beal.jpg", "name",1.0, "Accessoire", true, 2));
        productRepo.save(new Product("Coinceur", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/c/g/cg31_1-carved-choks-1-climbing-technology.jpg", "name",1.0, "Accessoire", true, 2));
        productRepo.save(new Product("Lot de friends", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/1/6/1660_1_b-camalot-x4-black-diamond.jpg", "name",1.0, "Accessoire", true, 2));
        productRepo.save(new Product("Crash Pad", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/b/l/bl82_1_h-crashpad-double-air-bag-beal.jpg", "name",1.0, "Accessoire", true, 2));
        productRepo.save(new Product("Topo 100 plus belles voies de provence", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/1/0/1010_1-topo-les-100-plus-belles-grandes-voies-de-provence--.jpg", "name",1.0, "Accessoire", true, 2));
        productRepo.save(new Product("Crochet goutte d'eau", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/0/9/0980_1_d-crochets-goutte-d-eau---reglette-petzl.jpg", "name",1.0, "Accessoire", true, 2));
        
        // Product "baudrier"
        productRepo.save(new Product("Harnais shaolin", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/1/7/175e_1_a-harnais-shaolin-beal.jpg", "name",1.0, "Baudrier", true, 2));
        productRepo.save(new Product("Harnais atex kratos", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/5/2/5243_1-harnais-atex-kratos-safety.jpg", "name",1.0, "Baudrier", true, 2));
        productRepo.save(new Product("Harnais topaz camp", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/b/e/be54_1-harnais-topaz-camp.jpg", "name",1.0, "Baudrier", true, 2));
        productRepo.save(new Product("Harnais basic kratos", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/5/2/5205_1-harnais-basic-kratos-safety.jpg", "name",1.0, "Baudrier", true, 2));
        productRepo.save(new Product("Harnais volt", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/p/p/pp47_1_l-harnais-volt-lt-petzl.jpg", "name",1.0, "Baudrier", true, 2));
        productRepo.save(new Product("Harnais ibex", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/x/p/xp13_1_c-harnais-ibex-expe.jpg", "name",1.0, "Baudrier", true, 2));
        productRepo.save(new Product("Harnais wind", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/5/2/5245_1-harnais-wind-mill-kratos-safety.jpg", "name",1.0, "Baudrier", true, 2));
        productRepo.save(new Product("Harnais enfant", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/0/1/0120_1-harnais-enfant-nino-mtde.jpg", "name",1.0, "Baudrier", true, 2));
        productRepo.save(new Product("Harnais avao", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/p/p/pp65_1_l-harnais-avao-sit-ii-petzl.jpg", "name",1.0, "Baudrier", true, 2));
        productRepo.save(new Product("Harnais avalon", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/a/l/al31_1_b-harnais-avalon-alp-design.jpg", "name",1.0, "Baudrier", true, 2));
        productRepo.save(new Product("Harnais luna", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/p/e/pe50_1_v-harnais-luna-petzl.jpg", "name",1.0, "Baudrier", true, 2));
        productRepo.save(new Product("Harnais adjama", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/p/e/pe49_1_b-harnais-adjama-petzl.jpg", "name",1.0, "Baudrier", true, 2));
        productRepo.save(new Product("Harnais phantom", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/b/l/bl41_1_a-harnais-phantom-beal.jpg", "name",1.0, "Baudrier", true, 2));
        productRepo.save(new Product("Harnais sitta", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/p/v/pv51_1_e-harnais-sitta-petzl.jpg", "name",1.0, "Baudrier", true, 2));
        productRepo.save(new Product("Harnais classic", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/5/2/5216_1-harnais-classic-veste-multipoches-kratos-safety.jpg", "name",1.0, "Baudrier", true, 2));
        
        // Product "système d'assurage"
        productRepo.save(new Product("Système d'aide à l'assurage", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/c/a/ca71_1-systeme-d-aide-a-l-assurage-ohm-edelrid.jpg", "name",1.0, "Système d'assurage", true, 2));
        productRepo.save(new Product("Escaper beal", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/b/l/bl68_1-escaper-beal.jpg", "name",1.0, "Système d'assurage", true, 2));
        productRepo.save(new Product("Descendeur assureur air force", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/b/l/bl66_1_j-descendeur-assureur-air-force-one-beal.jpg", "name",1.0, "Système d'assurage", true, 2));
        productRepo.save(new Product("Descendeur assureur reverso", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/0/9/0981_1_i-descendeur-assureur-reverso-4-petzl.jpg", "name",1.0, "Système d'assurage", true, 2));
        productRepo.save(new Product("Air force", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/b/l/bl65_1-air-force-3---be-safe-screw-beal.jpg", "name",1.0, "Système d'assurage", true, 2));
        productRepo.save(new Product("Smart", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/m/a/ma10_1-smart-2-0-dark-orange-mammut.jpg", "name",1.0, "Système d'assurage", true, 2));
        productRepo.save(new Product("Unireverso", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/p/v/pv19_1-unireverso-petzl.jpg", "name",1.0, "Système d'assurage", true, 2));
        productRepo.save(new Product("Matik camp", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/c/a/ca01_1-matik-camp.jpg", "name",1.0, "Système d'assurage", true, 2));
        productRepo.save(new Product("Pack assureur", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/a/l/al42_1_a-pack-assureur-click-up--climbing-technology.jpg", "name",1.0, "Système d'assurage", true, 2));
        productRepo.save(new Product("Pack air force 2", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/b/l/bl71_1-pack-air-force-2---be-safe-vis-beal.jpg", "name",1.0, "Système d'assurage", true, 2));
        productRepo.save(new Product("Grigri", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/p/h/ph02_1_e-grigri---petzl.jpg", "name",1.0, "Système d'assurage", true, 2));
        productRepo.save(new Product("Alpine up", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/c/a/ca06_1_c-alpine-up-kit-climbing-technology.jpg", "name",1.0, "Système d'assurage", true, 2));
        productRepo.save(new Product("Plaquette gi-gi kong", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/0/9/0970_1-plaquette-gi-gi-kong.jpg", "name",1.0, "Système d'assurage", true, 2));
        productRepo.save(new Product("Descendeur assureur", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/a/l/al10_1_b-descendeur-assureur-be-up-climbing-technology.jpg", "name",1.0, "Système d'assurage", true, 2));
        productRepo.save(new Product("Assureur descendeur", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/0/6/0692_1-assureur-descendeur-neuf--.jpg", "name",1.0, "Système d'assurage", true, 2));
        
        // Product "chaussons"
        productRepo.save(new Product("Chaussons apache", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/b/n/bn01_1_b-chaussons-apache-light-andrea-boldrini.jpg", "name",1.0, "Chaussons", true, 2));
        productRepo.save(new Product("Chaussons testarossa", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/k/a/ka08_1_d-chaussons-testarossa-la-sportiva.jpg", "name",1.0, "Chaussons", true, 2));
        productRepo.save(new Product("Chaussons first garra", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/k/a/ka25_1_a-chaussons-first-garra.jpg", "name",1.0, "Chaussons", true, 2));
        productRepo.save(new Product("Chaussons cimarron", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/k/a/ka41_1_e-chaussons-cimarron-velcros-garra.jpg", "name",1.0, "Chaussons", true, 2));
        productRepo.save(new Product("Chaussons siruana", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/1/0/10au_1_0070-chaussons-siurana-femme-millet.jpg", "name",1.0, "Chaussons", true, 2));
        productRepo.save(new Product("Chaussons otaki", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/k/a/ka14_1_at-chaussons-otaki-la-sportiva.jpg", "name",1.0, "Chaussons", true, 2));
        productRepo.save(new Product("Chaussons puma", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/b/n/bn04_1_c-chaussons-puma-ii-andrea-boldrini.jpg", "name",1.0, "Chaussons", true, 2));
        productRepo.save(new Product("Chaussons instinct", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/s/c/sc20_1_bk-chaussons-instinct-vs-scarpa.jpg", "name",1.0, "Chaussons", true, 2));
        productRepo.save(new Product("Chaussons ninja", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/b/a/ba70_1_a-chaussons-ninja-junior-boreal.jpg", "name",1.0, "Chaussons", true, 2));
        productRepo.save(new Product("Chaussons diabolo", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/b/a/ba74_1_d-chaussons-diabolo-boreal.jpg", "name",1.0, "Chaussons", true, 2));
        productRepo.save(new Product("Chaussons katana", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/l/s/ls05_1_bg-chaussons-katana-femme-la-sportiva.jpg", "name",1.0, "Chaussons", true, 2));
        productRepo.save(new Product("Chaussons first garra", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/k/a/ka01_1_e-chaussons-first-garra.jpg", "name",1.0, "Chaussons", true, 2));
        productRepo.save(new Product("Chaussons solution", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/l/s/ls17_1_bi-chaussons-solution-femme-la-sportiva.jpg", "name",1.0, "Chaussons", true, 2));
        productRepo.save(new Product("Chaussons solution", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/l/s/ls02_1_bf-chaussons-solution-la-sportiva.jpg", "name",1.0, "Chaussons", true, 2));
        productRepo.save(new Product("Chaussons cliffhanger", "https://www.expe.fr/media/catalog/product/cache/1/small_image/190x/9df78eab33525d08d6e5fb8d27136e95/1/0/10ej_1_0110-chaussons-cliffhanger-millet.jpg", "name",1.0, "Chaussons", true, 2));
    */      }
}
