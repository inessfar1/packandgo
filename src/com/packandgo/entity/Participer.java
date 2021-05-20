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
public class Participer {
    private int id;
    private int id_trajet;
    private int id_user;
    private Trajet trajet;

    public Participer(int id, int id_trajet, int id_user, Trajet trajet) {
        this.id = id;
        this.id_trajet = id_trajet;
        this.id_user = id_user;
        this.trajet = trajet;
    }

    public Trajet getTrajet() {
        return trajet;
    }

    public void setTrajet(Trajet trajet) {
        this.trajet = trajet;
    }

    public Participer(Trajet trajet) {
        this.trajet = trajet;
    }

    public Participer(int id, int id_trajet, int id_user) {
        this.id = id;
        this.id_trajet = id_trajet;
        this.id_user = id_user;
    }

    public Participer(int id_trajet, int id_user) {
        this.id_trajet = id_trajet;
        this.id_user = id_user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_trajet() {
        return id_trajet;
    }

    public void setId_trajet(int id_trajet) {
        this.id_trajet = id_trajet;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Participer{" + "id=" + id + ", id_trajet=" + id_trajet + ", id_user=" + id_user + ", trajet=" + trajet + '}';
    }

   


   
}
