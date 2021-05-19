/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hebergement.Services;

import Hebergement.Entity.Hotel;
import Base.MysqlConnect;
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
public class HotelServices {
    public ObservableList<Hotel> showHotel(){
        Connection cnx =null;
        Statement st = null;
        ResultSet rs = null;
        ObservableList<Hotel> liste = FXCollections.observableArrayList();
        String requette = "SELECT * FROM hotel";
        
        try {
            cnx = MysqlConnect.getInstance().getConnection();
            st = cnx.createStatement();
            rs = st.executeQuery(requette);
            Hotel hotels;
            while (rs.next()){
               hotels = new Hotel(rs.getInt("id"), rs.getString("nom"), rs.getString("adresse"), rs.getString("etoile"), rs.getString("num"), rs.getString("image"));
               liste.add(hotels);
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
    public boolean deleteHotel(Hotel a){
        Connection cnx =null;
        Statement st = null;
        String requette = "DELETE FROM hotel WHERE id="+a.getId()+"";
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
    
    public boolean addHotel(Hotel a){
        Connection cnx =null;
        Statement st = null;
        String requette = "INSERT INTO hotel (nom,adresse,etoile,num,image) VALUES ('"+a.getNom()+"','"+a.getAdresse()+"','"+a.getEtoile()+"','"+a.getNum()+"','"+a.getImage()+"')";
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
    
    
    
    public boolean updateHotel(Hotel a){
        Connection cnx =null;
        Statement st = null;
        String requette = "UPDATE hotel SET nom='"+a.getNom()+"',adresse='"+a.getAdresse()+"',etoile='"+a.getEtoile()+"',num='"+a.getNum()+"',image='"+a.getImage()+"' WHERE id="+a.getId()+"";
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
        
    
    
    
    
}
