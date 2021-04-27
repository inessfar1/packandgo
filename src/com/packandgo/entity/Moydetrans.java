/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packandgo.entity;

/**
 *
 * @author Ines sfar-pc
 */
public class Moydetrans {
    private  int id_moy_trans,dispo;
    private String type,matricule;

    public Moydetrans() {
    }
    

    public Moydetrans(int id_moy_trans, int dispo, String type, String matricule) {
        this.id_moy_trans = id_moy_trans;
        this.dispo = dispo;
        this.type = type;
        this.matricule = matricule;
    }

    @Override
    public String toString() {
        return "Moydetrans{" + "id_moy_trans=" + id_moy_trans + ", dispo=" + dispo + ", type=" + type + ", matricule=" + matricule + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.id_moy_trans;
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
        final Moydetrans other = (Moydetrans) obj;
        if (this.id_moy_trans != other.id_moy_trans) {
            return false;
        }
        return true;
    }

    public int getId_moy_trans() {
        return id_moy_trans;
    }

    public void setId_moy_trans(int id_moy_trans) {
        this.id_moy_trans = id_moy_trans;
    }

    public int getDispo() {
        return dispo;
    }

    public void setDispo(int dispo) {
        this.dispo = dispo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
    
}
