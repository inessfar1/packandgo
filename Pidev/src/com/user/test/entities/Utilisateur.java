/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user.test.entities;

/**
 *
 * @author ASUS
 */
public class Utilisateur {
    private int id ; 
    private String nom ;
    private String prenom ;
    private String image ;
    private String email ;
    private String username ;
    private String password ;
    private String roles ;
    private String activationToken ;
    private String resetToken ;

    public Utilisateur() {
    }

    public Utilisateur(int id, String nom, String prenom, String image, String email, String username, String password, String roles, String activationToken, String resetToken) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.image = image;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.activationToken = activationToken;
        this.resetToken = resetToken;
    }

    public Utilisateur(String nom, String prenom, String image, String email, String username, String password, String roles, String activationToken, String resetToken) {
        this.nom = nom;
        this.prenom = prenom;
        this.image = image;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.activationToken = activationToken;
        this.resetToken = resetToken;
    }

    public Utilisateur(String nom, String prenom, String image, String email, String username, String password, String roles) {
        this.nom = nom;
        this.prenom = prenom;
        this.image = image;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Utilisateur(int id, String nom, String prenom, String image, String email, String username, String password, String roles) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.image = image;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }
    

    public void setId(int id) {
        this.id = id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getActivationToken() {
        return activationToken;
    }

    public void setActivationToken(String activationToken) {
        this.activationToken = activationToken;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", image=" + image + ", email=" + email + ", username=" + username + ", password=" + password + ", roles=" + roles + ", activationToken=" + activationToken + ", resetToken=" + resetToken + '}';
    }
    
    
}
