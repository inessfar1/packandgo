/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hebergement.Services;

import Base.MysqlConnect;
import Hebergement.Entity.Chambre;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Chaima
 */
public class ChambreServices {
    public ObservableList<Chambre> showChambre(){
        Connection cnx =null;
        Statement st = null;
        ResultSet rs = null;
        ObservableList<Chambre> liste = FXCollections.observableArrayList();
        String requette = "SELECT * FROM chambre";
        
        try {
            cnx = MysqlConnect.getInstance().getConnection();
            st = cnx.createStatement();
            rs = st.executeQuery(requette);
            Chambre chambres;
            while (rs.next()){
               chambres = new Chambre(rs.getInt("id"), rs.getInt("hotel_id"), rs.getString("type"), rs.getString("vue"), rs.getString("duree"), rs.getDouble("prix"),rs.getString("image"));
               liste.add(chambres);
            }
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException e) { /* Ignored */}
    }
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { /* Ignored */}
    }
    }
        return liste;
        
}
    
    public boolean deleteChambre(Chambre a){
        Connection cnx =null;
        Statement st = null;
        String requette = "DELETE FROM chambre WHERE id="+a.getId()+"";
        try {
            cnx = MysqlConnect.getInstance().getConnection();
            st = cnx.createStatement();
            st.executeUpdate(requette);
            return true;
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }finally {
    
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { /* Ignored */}
    }
    }
        
    }
    
    public boolean addChambre(Chambre a){
        Connection cnx =null;
        Statement st = null;
        String requette = "INSERT INTO chambre (hotel_id,type,vue,duree,prix,image) VALUES ('"+a.getHotel_id()+"','"+a.getType()+"','"+a.getVue()+"','"+a.getDuree()+"',"+a.getPrix()+",image='"+a.getImage()+"')";
        try {
            cnx = MysqlConnect.getInstance().getConnection();
            st = cnx.createStatement();
            st.executeUpdate(requette);
            return true;
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }finally {
    
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { /* Ignored */}
    }
    }}
    
    
    public boolean updateChambre(Chambre a){
        Connection cnx =null;
        Statement st = null;
        String requette = "UPDATE chambre SET hotel_id="+a.getHotel_id()+"duree='"+a.getDuree()+"',prix="+a.getPrix()+",image='"+a.getImage()+"' WHERE id="+a.getId()+"";
        try {
            cnx = MysqlConnect.getInstance().getConnection();
            st = cnx.createStatement();
            st.executeUpdate(requette);
            return true;
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }finally {
    
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { /* Ignored */}
    }
    }}
    
    
    public int rowChambre(){
        Connection cnx =null;
        Statement st = null;
        ResultSet rs = null;
        ObservableList<Chambre> liste = FXCollections.observableArrayList();
        String requette = "SELECT * FROM chambre";
        int i=0;
        
        try {
            cnx = MysqlConnect.getInstance().getConnection();
            st = cnx.createStatement();
            rs = st.executeQuery(requette);
            Chambre chambres;
            while (rs.next()){
               i=i+1;
            }
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException e) { /* Ignored */}
    }
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { /* Ignored */}
    }
    }
        return i;
        
}
    
    
    
}
