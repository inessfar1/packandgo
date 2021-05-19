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
public class Chambre {
    int id;
    int hotel_id;
    String type;
    String vue;
    String duree;
    Double prix;
    String image; 

    public Chambre() {
    }

    public Chambre(int hotel_id, String type, String vue, String duree, Double prix, String image) {
        this.hotel_id = hotel_id;
        this.type = type;
        this.vue = vue;
        this.duree = duree;
        this.prix = prix;
        this.image = image;
    }

    public Chambre(int id, int hotel_id, String type, String vue, String duree, Double prix, String image) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.type = type;
        this.vue = vue;
        this.duree = duree;
        this.prix = prix;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVue() {
        return vue;
    }

    public void setVue(String vue) {
        this.vue = vue;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Chambre{" + "id=" + id + ", hotel_id=" + hotel_id + ", type=" + type + ", vue=" + vue + ", duree=" + duree + ", prix=" + prix + ", image=" + image + '}';
    }
    
}
