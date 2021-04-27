/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.Entity;

/**
 *
 * @author 21628
 */
public class Plan_res {
    int id;
    int utilisateur_id;
    int plan_id;

    public Plan_res(int utilisateur_id, int plan_id) {
        this.utilisateur_id = utilisateur_id;
        this.plan_id = plan_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUtilisateur_id() {
        return utilisateur_id;
    }

    public void setUtilisateur_id(int utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }

    public int getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    @Override
    public String toString() {
        return "Plan_res{" + "id=" + id + ", utilisateur_id=" + utilisateur_id + ", plan_id=" + plan_id + '}';
    }
    
}
