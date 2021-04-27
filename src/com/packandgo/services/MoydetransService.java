/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packandgo.services;

import com.packandgo.entity.Moydetrans;
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
public class MoydetransService {
 
 private Connection cnx;
 
    
    public MoydetransService() 
    {
        
        ConnexionSingleton cs = ConnexionSingleton.getInstance();
        cnx = cs.getCnx();
        
    }
    
        public ArrayList<Moydetrans> afficherListCh() throws SQLException
        {
          ArrayList<Moydetrans> tabch = new ArrayList<>();
           
           
            
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery("select * from moydetran");
            
            while(rs.next())
            {
                Moydetrans c = new Moydetrans();
                c.setId_moy_trans(rs.getInt(1));
                c.setType(rs.getString(2));
                c.setMatricule(rs.getString(3));
                c.setDispo(rs.getInt(4));
              
               tabch.add(c);
               
            }
             
           return tabch;
        } 
        
        public void updatetab(Moydetrans a) throws SQLException {
            try {
        PreparedStatement PS=cnx.prepareStatement("UPDATE `moydetran` SET `type`=? ,`matricule`=?,`disponibilite`=? WHERE `id_moy_trans`=?");
         
        PS.setString(1,a.getType());
        PS.setString(2,a.getMatricule());
        PS.setInt(3,a.getDispo());  
        PS.setInt(4,a.getId_moy_trans());
        PS.executeUpdate();
     
            } catch (Exception e) {
                Logger.getLogger(ConnexionSingleton.class.getName()).log(Level.SEVERE,null,e);
            }

    }
        
         public void deleteCh(int id) throws SQLException {
       String requete = "DELETE FROM moydetran WHERE id_moy_trans="+id;
        try{
             Statement st  = cnx.createStatement();
            st.executeUpdate(requete);
            
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
         
         public void ajouterCh(Moydetrans p) throws SQLException{
       try {
            String req = "INSERT INTO moydetran ( `type`, `matricule`)"
                    + " VALUES ('"+p.getType()+"','"+p.getMatricule()+"')";


            Statement st = cnx.createStatement();
            st.executeUpdate(req);

            System.out.println("Insertion Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
         }
         
}