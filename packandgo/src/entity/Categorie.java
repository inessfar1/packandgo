/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


import java.sql.Date;
import java.util.Objects;
import javafx.scene.image.ImageView;




/**
 *
 * @author asus
 */
public class Categorie {
    public int id ;
    public int utilisateur_id;
   public String title ;
    public String description ;
    public String categorie ;

    public Categorie(int id, int utilisateur_id, String title, String description, String categorie) {
        this.id = id;
        this.utilisateur_id = utilisateur_id;
        this.title = title;
        this.description = description;
        this.categorie = categorie;
    }

    public Categorie() {
    }

    public Categorie(int utilisateur_id, String title, String description, String categorie) {
        this.utilisateur_id = utilisateur_id;
        this.title = title;
        this.description = description;
        this.categorie = categorie;
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUtilisateur_id() {
        return utilisateur_id;
    }

    public void setUtilisateur_id(int utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", utilisateur_id=" + utilisateur_id + ", title=" + title + ", description=" + description + ", categorie=" + categorie + '}';
    }

  
    
}
