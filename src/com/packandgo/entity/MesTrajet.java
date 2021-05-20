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
public class MesTrajet {
    private int id;
    private String date_depart,date_arrive,pt_depart,pt_arrive;
    private float prix;
    private int id_trajet;

    public MesTrajet(int id, String date_depart, String date_arrive, String pt_depart, String pt_arrive, float prix, int id_trajet) {
        this.id = id;
        this.date_depart = date_depart;
        this.date_arrive = date_arrive;
        this.pt_depart = pt_depart;
        this.pt_arrive = pt_arrive;
        this.prix = prix;
        this.id_trajet = id_trajet;
    }

    public MesTrajet(String date_depart, String date_arrive, String pt_depart, String pt_arrive, float prix, int id_trajet) {
        this.date_depart = date_depart;
        this.date_arrive = date_arrive;
        this.pt_depart = pt_depart;
        this.pt_arrive = pt_arrive;
        this.prix = prix;
        this.id_trajet = id_trajet;
    }

    public int getId_trajet() {
        return id_trajet;
    }

    public void setId_trajet(int id_trajet) {
        this.id_trajet = id_trajet;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_depart() {
        return date_depart;
    }

    public void setDate_depart(String date_depart) {
        this.date_depart = date_depart;
    }

    public String getDate_arrive() {
        return date_arrive;
    }

    public void setDate_arrive(String date_arrive) {
        this.date_arrive = date_arrive;
    }

    public String getPt_depart() {
        return pt_depart;
    }

    public void setPt_depart(String pt_depart) {
        this.pt_depart = pt_depart;
    }

    public String getPt_arrive() {
        return pt_arrive;
    }

    public void setPt_arrive(String pt_arrive) {
        this.pt_arrive = pt_arrive;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "MesTrajet{" + "id=" + id + ", date_depart=" + date_depart + ", date_arrive=" + date_arrive + ", pt_depart=" + pt_depart + ", pt_arrive=" + pt_arrive + ", prix=" + prix + ", id_trajet=" + id_trajet + '}';
    }

   


   
}
