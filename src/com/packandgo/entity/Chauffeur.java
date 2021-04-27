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
public class Chauffeur {
    
  
      private int id_ch,disp;
    private String nom,prenom;
    public Chauffeur()
    {
        
    }

    public Chauffeur(int id_ch, int disp, String nom, String prenom) {
        this.id_ch = id_ch;
        this.disp = disp;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId_ch() {
        return id_ch;
    }

    public void setId_ch(int id_ch) {
        this.id_ch = id_ch;
    }

    public int getDisp() {
        return disp;
    }

    public void setDisp(int disp) {
        this.disp = disp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "Chauffeur{" + "id_ch=" + id_ch + ", disp=" + disp + ", nom=" + nom + ", prenom=" + prenom + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + this.id_ch;
        hash = 13 * hash + this.disp;
        hash = 13 * hash + Objects.hashCode(this.nom);
        hash = 13 * hash + Objects.hashCode(this.prenom);
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
        final Chauffeur other = (Chauffeur) obj;
        if (this.id_ch != other.id_ch) {
            return false;
        }
        if (this.disp != other.disp) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        return true;
    }
    
    
    
}
