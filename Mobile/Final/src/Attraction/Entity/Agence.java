/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.Entity;

/**
 *
 * @author 21628
 */
public class Agence {
      
    private int id;
    
    
    int pays_id;
    
    
    private String logo;

    
    private String nom;

    
    private String adresse;

    
    private String email;

    
    private int tel;

    
    public Agence() {
    }

    public Agence(int id, int pays_id, String logo, String nom, String adresse, String email, int tel) {
        this.id = id;
        this.pays_id = pays_id;
        this.logo = logo;
        this.nom = nom;
        this.adresse = adresse;
        this.email = email;
        this.tel = tel;
    }

    public Agence(String logo, String nom, String adresse, String email, int tel) {
        this.logo = logo;
        this.nom = nom;
        this.adresse = adresse;
        this.email = email;
        this.tel = tel;
    }
    
    
    public Agence(int pays_id, String logo, String nom, String adresse, String email, int tel) {
        this.pays_id = pays_id;
        this.logo = logo;
        this.nom = nom;
        this.adresse = adresse;
        this.email = email;
        this.tel = tel;
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getPays_id() {
        return pays_id;
    }

    public void setPays_id(int pays_id) {
        this.pays_id = pays_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Agence{" + "id=" + id + ",\n pays_id=" + pays_id + ",\n logo=" + logo + ",\n nom=" + nom + ",\n adresse=" + adresse + ",\n email=" + email + ",\n tel=" + tel + '}';
    }

  

 
}
