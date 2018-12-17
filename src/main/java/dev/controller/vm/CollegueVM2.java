package dev.controller.vm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dev.domain.Collegue;
import dev.domain.Role;

public class CollegueVM2 {
	
    private String name;
    private String firstName;
    private String adress;
    private long phone;
    private String email;
    private LocalDate birthDate;
    private String password;
    private List<Role> roles = new ArrayList<>();

    public CollegueVM2() {}
    
    public CollegueVM2(Collegue col) {
        this.email = col.getEmail();
        this.name = col.getName();
        this.firstName = col.getFirstName();
        this.roles = col.getRoles().stream().map(roleCollegue -> roleCollegue.getRole()).collect(Collectors.toList());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    
    
}
