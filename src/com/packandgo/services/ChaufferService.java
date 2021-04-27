/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packandgo.services;

import com.packandgo.entity.Chauffeur;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class ChaufferService {
 
 private Connection cnx;
 
    
    public ChaufferService() 
    {
        
        ConnexionSingleton cs = ConnexionSingleton.getInstance();
        cnx = cs.getCnx();
        
    }
    
        public ArrayList<Chauffeur> afficherListCh() throws SQLException
        {
          ArrayList<Chauffeur> tabch = new ArrayList<>();
           
           
            
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery("select * from chauffeur");
            
            while(rs.next())
            {
                Chauffeur c = new Chauffeur();
                c.setId_ch(rs.getInt(1));
                c.setNom(rs.getString(2));
                c.setPrenom(rs.getString(3));
                c.setDisp(rs.getInt(4));
              
               tabch.add(c);
               
            }
             
           return tabch;
        } 
        
        public void updatetab(Chauffeur a) throws SQLException {
            try {
        PreparedStatement PS=cnx.prepareStatement("UPDATE `chauffeur` SET `nom`=? ,`prenom`=?,`disponibilite`=? WHERE `id_chauffeur`=?");
         
        PS.setString(1,a.getNom());
        PS.setString(2,a.getPrenom());
        PS.setInt(3,a.getDisp());  
        PS.setInt(4,a.getId_ch());
        PS.executeUpdate();
     
            } catch (Exception e) {
                Logger.getLogger(ConnexionSingleton.class.getName()).log(Level.SEVERE,null,e);
            }

    }
        
         public void deleteCh(int id) throws SQLException {
       String requete = "DELETE FROM chauffeur WHERE id_chauffeur="+id;
        try{
             Statement st  = cnx.createStatement();
            st.executeUpdate(requete);
            
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
         
         public void ajouterCh(Chauffeur p) throws SQLException{
       try {
            String req = "INSERT INTO chauffeur ( `nom`, `prenom`)"
                    + " VALUES ('"+p.getNom()+"','"+p.getPrenom()+"')";


            Statement st = cnx.createStatement();
            st.executeUpdate(req);

            System.out.println("Insertion Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
         }
         
         
         public int rowChauffeur(){
        
        Statement st = null;
        ResultSet rs = null;
        ObservableList<Chauffeur> liste = FXCollections.observableArrayList();
        String requette = "SELECT * FROM chauffeur";
        int i=0;
        
        try {
            
            st = cnx.createStatement();
            rs = st.executeQuery(requette);
            Chauffeur chauffeurs;
            while (rs.next()){
               i=i+1;
            }
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return i;
        
}
         
}
                   
           

         


   
        
        
    
    

