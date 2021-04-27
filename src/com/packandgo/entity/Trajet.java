/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packandgo.entity;

import java.util.Objects;

/**
 *
 * @author Ines sfar-pc
 */
public class Trajet {
    private int id_tr,id_ch,id_moy_trans;
    private String date_depart,date_arrive,pt_depart,pt_arrive;
    private float prix;

    public Trajet(String date_depart, String date_arrive, String pt_depart, String pt_arrive, float prix) {
        this.date_depart = date_depart;
        this.date_arrive = date_arrive;
        this.pt_depart = pt_depart;
        this.pt_arrive = pt_arrive;
        this.prix = prix;
    }

    public Trajet( int id_tr ,String date_depart, String date_arrive, String pt_depart, String pt_arrive, float prix) {
        this.date_depart = date_depart;
        this.date_arrive = date_arrive;
        this.pt_depart = pt_depart;
        this.pt_arrive = pt_arrive;
        this.prix = prix;
        this.id_tr = id_tr;
    }
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.id_tr;
        hash = 79 * hash + this.id_ch;
        hash = 79 * hash + this.id_moy_trans;
        hash = 79 * hash + Objects.hashCode(this.date_depart);
        hash = 79 * hash + Objects.hashCode(this.date_arrive);
        hash = 79 * hash + Objects.hashCode(this.pt_depart);
        hash = 79 * hash + Objects.hashCode(this.pt_arrive);
        hash = 79 * hash + Float.floatToIntBits(this.prix);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Trajet other = (Trajet) obj;
        if (this.id_tr != other.id_tr) {
            return false;
        }
        if (this.id_ch != other.id_ch) {
            return false;
        }
        if (this.id_moy_trans != other.id_moy_trans) {
            return false;
        }
        if (Float.floatToIntBits(this.prix) != Float.floatToIntBits(other.prix)) {
            return false;
        }
        if (!Objects.equals(this.date_depart, other.date_depart)) {
            return false;
        }
        if (!Objects.equals(this.date_arrive, other.date_arrive)) {
            return false;
        }
        if (!Objects.equals(this.pt_depart, other.pt_depart)) {
            return false;
        }
        if (!Objects.equals(this.pt_arrive, other.pt_arrive)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Trajet{" + "id_tr=" + id_tr + ", id_ch=" + id_ch + ", id_moy_trans=" + id_moy_trans + ", date_depart=" + date_depart + ", date_arrive=" + date_arrive + ", pt_depart=" + pt_depart + ", pt_arrive=" + pt_arrive + ", prix=" + prix + '}';
    }

    public int getId_tr() {
        return id_tr;
    }

    public void setId_tr(int id_tr) {
        this.id_tr = id_tr;
    }

    public int getId_ch() {
        return id_ch;
    }

    public void setId_ch(int id_ch) {
        this.id_ch = id_ch;
    }

    public int getId_moy_trans() {
        return id_moy_trans;
    }

    public void setId_moy_trans(int id_moy_trans) {
        this.id_moy_trans = id_moy_trans;
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

    public Trajet(int id_tr, int id_ch, int id_moy_trans, String date_depart, String date_arrive, String pt_depart, String pt_arrive, float prix) {
        this.id_tr = id_tr;
        this.id_ch = id_ch;
        this.id_moy_trans = id_moy_trans;
        this.date_depart = date_depart;
        this.date_arrive = date_arrive;
        this.pt_depart = pt_depart;
        this.pt_arrive = pt_arrive;
        this.prix = prix;
    }

    public Trajet() {
    }
    
    
}
