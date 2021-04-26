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
public class Blog {
    public int id ;
   public String title ;
    public String description ;
 public String image ;
 private ImageView photo;

    public Blog(int id, String title, String description, String image, ImageView photo) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.photo = photo;
    }

    public Blog(String title, String description, String image, ImageView photo) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.photo = photo;
    }

    public Blog(int id, String title, String description, String image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public Blog(String title, String description, String image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Blog{" + "id=" + id + ", title=" + title + ", description=" + description + ", image=" + image + ", photo=" + photo + '}';
    }
 
 
   
 
    
}
