/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packandgo.entity;

import java.util.Objects;

/**
 *
 * @author Anisos
 */
public class Trajet extends Object{
    
    private int id;
  //public float idf;
    private float prix;
    private String d_depart,d_arriver,p_depart,p_arriver;

    
    public Trajet()
    {
        
    }
    
    public Trajet(int id, float prix, String d_depart, String d_arriver, String p_depart, String p_arriver) {
        this.id = id;
        this.prix = prix;
        this.d_depart = d_depart;
        this.d_arriver = d_arriver;
        this.p_depart = p_depart;
        this.p_arriver = p_arriver;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getD_depart() {
        return d_depart;
    }

    public void setD_depart(String d_depart) {
        this.d_depart = d_depart;
    }

    public String getD_arriver() {
        return d_arriver;
    }

    public void setD_arriver(String d_arriver) {
        this.d_arriver = d_arriver;
    }

    public String getP_depart() {
        return p_depart;
    }

    public void setP_depart(String p_depart) {
        this.p_depart = p_depart;
    }

    public String getP_arriver() {
        return p_arriver;
    }

    public void setP_arriver(String p_arriver) {
        this.p_arriver = p_arriver;
    }

    @Override
    public String toString() {
        return "Trajet{" + "id=" + id + ", prix=" + prix + ", d_depart=" + d_depart + ", d_arriver=" + d_arriver + ", p_depart=" + p_depart + ", p_arriver=" + p_arriver + '}';
    }
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Float.floatToIntBits(this.prix);
        hash = 53 * hash + Objects.hashCode(this.d_depart);
        hash = 53 * hash + Objects.hashCode(this.d_arriver);
        hash = 53 * hash + Objects.hashCode(this.p_depart);
        hash = 53 * hash + Objects.hashCode(this.p_arriver);
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
        if (this.id != other.id) {
            return false;
        }
        if (Float.floatToIntBits(this.prix) != Float.floatToIntBits(other.prix)) {
            return false;
        }
        if (!Objects.equals(this.d_depart, other.d_depart)) {
            return false;
        }
        if (!Objects.equals(this.d_arriver, other.d_arriver)) {
            return false;
        }
        if (!Objects.equals(this.p_depart, other.p_depart)) {
            return false;
        }
        if (!Objects.equals(this.p_arriver, other.p_arriver)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
