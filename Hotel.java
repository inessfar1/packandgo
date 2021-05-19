/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hebergement.Entity;

/**
 *
 * @author Chaima
 */
public class Hotel {
    int id;
    String nom;
    String adresse;
    String etoile;
    String num;
    String image;

    public Hotel() {
    }

    public Hotel(String nom, String adresse, String etoile, String num, String image) {
        this.nom = nom;
        this.adresse = adresse;
        this.etoile = etoile;
        this.num = num;
        this.image = image;
    }

    public Hotel(int id, String nom, String adresse, String etoile, String num, String image) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.etoile = etoile;
        this.num = num;
        this.image = image;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEtoile() {
        return etoile;
    }

    public void setEtoile(String etoile) {
        this.etoile = etoile;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return nom;
    }

    
    
}
