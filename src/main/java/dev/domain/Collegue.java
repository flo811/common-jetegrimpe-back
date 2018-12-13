package dev.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Collegue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String prenom;
	private String adress;
	private long phone;
	private String email;
	private LocalDate birthDate;
	private String motDePasse;
	@OneToMany(mappedBy = "collegue", cascade = CascadeType.PERSIST)
	private List<RoleCollegue> roles;

	public Collegue() {
	}

	public Collegue(String nom, String prenom, String adress, long phone, String email, LocalDate birthDate,
			String motDePasse) {
		this.nom = nom;
		this.prenom = prenom;
		this.adress = adress;
		this.phone = phone;
		this.email = email;
		this.birthDate = birthDate;
		this.motDePasse = motDePasse;
	}

	/**
	 * @return the adress
	 */
	public String getAdress() {
		return adress;
	}

	/**
	 * @param adress the adress to set
	 */
	public void setAdress(String adress) {
		this.adress = adress;
	}

	/**
	 * @return the phone
	 */
	public Number getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(long phone) {
		this.phone = phone;
	}

	/**
	 * @return the birthDate
	 */
	public LocalDate getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public List<RoleCollegue> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleCollegue> roles) {
		this.roles = roles;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
}
