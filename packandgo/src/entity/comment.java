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
public class comment {
    public int id ;
    public int categorie_id;
    public int utilisateur_id;
   public String text ;
   public Date date ; 
   public String image ;

    public comment(int id, int categorie_id, int utilisateur_id, String text, Date date) {
        this.id = id;
        this.categorie_id = categorie_id;
        this.utilisateur_id = utilisateur_id;
        this.text = text;
        this.date = date;
    }

    public comment(int categorie_id, int utilisateur_id, String text, Date date) {
        this.categorie_id = categorie_id;
        this.utilisateur_id = utilisateur_id;
        this.text = text;
        this.date = date;
    }
  public comment(int categorie_id, int utilisateur_id, String text, Date date , String image) {
        this.categorie_id = categorie_id;
        this.utilisateur_id = utilisateur_id;
        this.text = text;
        this.date = date;
        this.image = image ;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
  
  
  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    public int getUtilisateur_id() {
        return utilisateur_id;
    }

    public void setUtilisateur_id(int utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "comment{" + "id=" + id + ", categorie_id=" + categorie_id + ", utilisateur_id=" + utilisateur_id + ", text=" + text + ", date=" + date + '}';
    }

   
}
