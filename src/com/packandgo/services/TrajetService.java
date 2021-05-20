/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packandgo.services;

import com.packandgo.entity.Trajet;
import com.packandgo.utils.ConnexionSingleton;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

/**
 *
 * @author Ines sfar-pc
 */
public class TrajetService {
 
 private Connection cnx;
 
    
    public TrajetService() 
    {
        
        ConnexionSingleton cs = ConnexionSingleton.getInstance();
        cnx = cs.getCnx();
        
    }
    
    
        public ArrayList<Trajet> afficherListCh() throws SQLException
        {
          ArrayList<Trajet> tabch = new ArrayList<>();
           
           
            
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery("select * from trajet");
            
            while(rs.next())
            {
                Trajet c = new Trajet();
                c.setId_tr(1);
                c.setDate_depart(rs.getString(2));
                c.setDate_arrive(rs.getString(3));
                c.setPt_depart(rs.getString(4));
                c.setPt_arrive(rs.getString(5));
                c.setPrix(rs.getFloat(8));
              
               tabch.add(c);
               
            }
             
           return tabch;
        } 
        
         public void deleteCh(int id) throws SQLException {
             
       String requete = "DELETE FROM trajet WHERE id_trajet="+id;
        try{
             Statement st  = cnx.createStatement();
            st.executeUpdate(requete);
            
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
         
         public void updatetab(Trajet a) throws SQLException {
            try {
        PreparedStatement PS=cnx.prepareStatement("UPDATE `trajet` SET `date_depart`=? ,`date_arrive`=?,`pt_depart`=?,`pt_arrive`=?,`prix`=? WHERE `id_trajet`=?");
         
        PS.setString(1,a.getDate_depart());
        PS.setString(2,a.getDate_arrive());
        PS.setString(3,a.getPt_depart()); 
        PS.setString(4,a.getPt_arrive());
        PS.setFloat(5,a.getPrix());
        PS.setInt(6,a.getId_tr());
        
        PS.executeUpdate();
     
            } catch (Exception e) {
                Logger.getLogger(ConnexionSingleton.class.getName()).log(Level.SEVERE,null,e);
            }}
         
         
            public void ajouterCh(Trajet p) throws SQLException{
       try {
            String req = "INSERT INTO trajet ( `date_depart`, `date_arrive`, `pt_depart`, `pt_arrive`, `prix`, `nb_like`)"
                    + " VALUES ('"+p.getDate_depart()+"','"+p.getDate_arrive()+"','"+p.getPt_depart()+"','"+p.getPt_arrive()+"','"+p.getPrix()+"','"+0+"')";


            Statement st = cnx.createStatement();
            st.executeUpdate(req);

            System.out.println("Insertion Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
         }

    }
