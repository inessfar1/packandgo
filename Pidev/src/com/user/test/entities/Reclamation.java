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
public class Reclamation {
    int id ; 
    String object,description,statut ; 

    public Reclamation() {
    }

    public Reclamation(int id) {
        this.id = id;
    }
    

    public Reclamation(int id, String object, String description, String statut) {
        this.id = id;
        this.object = object;
        this.description = description;
        this.statut = statut;
    }

    public Reclamation(String object, String description, String statut) {
        this.object = object;
        this.description = description;
        this.statut = statut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", object=" + object + ", description=" + description + ", statut=" + statut + '}';
    }
    
    
    
}
