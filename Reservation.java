/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hebergement.Entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Chaima
 */
public class Reservation {
    private int id;
    private int user_id;
    private Chambre chambre;
    private Date date_deb;
    private Date date_fin;

    public Reservation( int user_id, Chambre chambre, Date date_deb, Date date_fin) {
        this.user_id = user_id;
        this.chambre = chambre;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
    }

    public Reservation(int id, int user_id, Chambre chambre, Date date_deb, Date date_fin) {
        this.id = id;
        this.user_id = user_id;
        this.chambre = chambre;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
    }

   

    public Reservation() {
    } 
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Chambre getChambre() {
        return chambre;
    }

    public void setChambre(Chambre chambre) {
        this.chambre = chambre;
    }

    public Date getDate_deb() {
        return date_deb;
    }

    public void setDate_deb(Date date_deb) {
        this.date_deb = date_deb;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.id;
        hash = 59 * hash + this.user_id;
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
        final Reservation other = (Reservation) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.user_id != other.user_id) {
            return false;
        }
        if (!Objects.equals(this.chambre, other.chambre)) {
            return false;
        }
        if (!Objects.equals(this.date_deb, other.date_deb)) {
            return false;
        }
        if (!Objects.equals(this.date_fin, other.date_fin)) {
            return false;
        }
        return true;
    }

   
    
}
