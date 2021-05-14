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
public class Plan {
    int id;
    int agence_id;
    String image;
    String sujet;
    String description;
    String date;
    Double prix;
    int rec;
    int nbr;
    
    
    public Plan() {
    }

    public Plan(int id, int agence_id, String image, String sujet, String description, String date, Double prix, int rec, int nbr) {
        this.id = id;
        this.agence_id = agence_id;
        this.image = image;
        this.sujet = sujet;
        this.description = description;
        this.date = date;
        this.prix = prix;
        this.rec = rec;
        this.nbr = nbr;
    }

    public Plan(int agence_id, String image, String sujet, String description, String date, Double prix, int rec, int nbr) {
        this.agence_id = agence_id;
        this.image = image;
        this.sujet = sujet;
        this.description = description;
        this.date = date;
        this.prix = prix;
        this.rec = rec;
        this.nbr = nbr;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAgence_id() {
        return agence_id;
    }

    public void setAgence_id(int agence_id) {
        this.agence_id = agence_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public int getRec() {
        return rec;
    }

    public void setRec(int rec) {
        this.rec = rec;
    }

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }

    @Override
    public String toString() {
        return sujet;
    }
    
    
    
    
}
