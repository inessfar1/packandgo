/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packandgo.entity;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author walid
 */
public class liketrajet {
    private int id;
    private int categorie_id;
    private int utilisateur_id;
    private Date date;

    public liketrajet(int id, int categorie_id, int utilisateur_id, Date date) {
        this.id = id;
        this.categorie_id = categorie_id;
        this.utilisateur_id = utilisateur_id;
        this.date = date;
    }

    public liketrajet(int categorie_id, int utilisateur_id, Date date) {
        this.categorie_id = categorie_id;
        this.utilisateur_id = utilisateur_id;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "likecategorie{" + "id=" + id + ", categorie_id=" + categorie_id + ", utilisateur_id=" + utilisateur_id + ", date=" + date + '}';
    }

    
}
