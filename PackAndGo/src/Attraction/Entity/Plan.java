/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.Entity;

import java.time.LocalDate;
import java.util.Date;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author 21628
 */
public class Plan {
    int id;
    int pays_id;
    int agence_id;
    String image;
    ImageView v;
    String sujet;
    String description;
    Date date;
    Double prix;
    int rec;
    int nbr;

    public Plan() {
    }

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }

    public Plan(int id, int pays_id, int agence_id, String image, String sujet, String description, Date date, Double prix, int rec, int nbr) {
        this.id = id;
        this.pays_id = pays_id;
        this.agence_id = agence_id;
        this.image = image;
        this.sujet = sujet;
        this.description = description;
        this.date = date;
        this.prix = prix;
        this.rec = rec;
        this.nbr = nbr;
    }

    public Plan(int id, int pays_id, int agence_id, String image, ImageView v, String sujet, String description, Date date, Double prix, int rec) {
        this.id = id;
        this.pays_id = pays_id;
        this.agence_id = agence_id;
        this.image = image;
        this.v = v;
        this.sujet = sujet;
        this.description = description;
        this.date = date;
        this.prix = prix;
        this.rec = rec;
       
       
    }

    public Plan(ImageView v, String sujet, String description, Date date, Double prix) {
        this.v = v;
        this.sujet = sujet;
        this.description = description;
        this.date = date;
        this.prix = prix;
    }

    

    public ImageView getV() {
        return v;
    }

    public void setV(ImageView v) {
        this.v = v;
    }

   
    

    public Plan(int pays_id, int agence_id, String image, String sujet, String description, Date date, Double prix) {
        this.pays_id = pays_id;
        this.agence_id = agence_id;
        this.image = image;
        this.sujet = sujet;
        this.description = description;
        this.date = date;
        this.prix = prix;
    }

    public Plan(int id, int pays_id, int agence_id, String image, String sujet, String description, Date date, Double prix, int rec) {
        this.id = id;
        this.pays_id = pays_id;
        this.agence_id = agence_id;
        this.image = image;
        this.sujet = sujet;
        this.description = description;
        this.date = date;
        this.prix = prix;
        this.rec = rec;
    }

    

    
    public Plan(int id, int pays_id, int agence_id, String image, String sujet, String description, Date date, Double prix) {
        this.id = id;
        this.pays_id = pays_id;
        this.agence_id = agence_id;
        this.image = image;
        this.sujet = sujet;
        this.description = description;
        this.date = date;
        this.prix = prix;
    }

    public int getRec() {
        return rec;
    }

    public void setRec(int rec) {
        this.rec = rec;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public int getPays_id() {
        return pays_id;
    }

    public void setPays_id(int pays_id) {
        this.pays_id = pays_id;
    }

    @Override
    public String toString() {
        return "Plan{" + "id=" + id + ", agence_id=" + agence_id + ", image=" + image + ", sujet=" + sujet + ", description=" + description + ", date=" + date + ", prix=" + prix + '}';
    }
    
}
