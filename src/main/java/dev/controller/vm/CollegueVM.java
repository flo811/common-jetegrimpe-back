package dev.controller.vm;

import dev.domain.Collegue;
import dev.domain.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Structure modèlisant un collègue servant à communiquer avec l'extérieur (WEB API).
 */
public class CollegueVM {

    private String email;
    private String name;
    private String firstName;
    private List<Role> roles = new ArrayList<>();

    public CollegueVM(Collegue col) {
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
}
