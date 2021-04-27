/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packandgo.controller;






import com.lowagie.text.PageSize;
import com.packandgo.entity.MesTrajet;
import com.packandgo.entity.Participer;
import com.packandgo.entity.Trajet;
import com.packandgo.entity.liketrajet;
import com.packandgo.services.ServiceLikeTrajet;
import com.packandgo.services.ServiceMesTrajet;
import com.packandgo.services.ServiceParticiper;
import com.packandgo.services.TrajetService;
import com.packandgo.utils.ConnexionSingleton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * FXML Controller class
 *
 * @author asus
 */

public class AfficherTrajetFrontController implements Initializable {

    @FXML
    private VBox actualitecontainer;

    private Connection con;
    @FXML
    private ScrollPane scroll;
    int n=1;
    @FXML
    private AnchorPane pane;

    public AfficherTrajetFrontController() {
        con = ConnexionSingleton.getInstance().getCnx();
    }
    private Statement ste;
    private PreparedStatement pre;
    String s1 = "";
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
       
            actualitecontainer.setSpacing(5);
            try {
                displayActualite();
            } catch (SQLException ex) {
                Logger.getLogger(AfficherTrajetFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
             
            
        
    }
  
    private void displayActualite() throws SQLException {
        TrajetService pa = new TrajetService();
        ServiceParticiper oo=new ServiceParticiper();
        ServiceLikeTrajet l=new ServiceLikeTrajet();
        ServiceMesTrajet t=new ServiceMesTrajet();
        String req = "select * from trajet  ";
        List<VBox> list = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery(req);
        while (rs.next()) {
          

            Trajet a1 = new Trajet(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(8));
            Label k=new Label("");
           Label trajet=new Label("Trajet");
            Label nom = new Label("Point de depart : " + a1.getPt_depart()+" "+"******");
             Label nom1 = new Label("Point d'arrive : " + a1.getPt_arrive()+"    "+"******");
              Label date1 = new Label("Date de depart : " + a1.getDate_depart());
               Label date2 = new Label("Date d'arrive : " + a1.getDate_arrive());
               Label prix = new Label("Prix : " + a1.getPrix());
          HBox h = new HBox();
            h.setSpacing(10);
            h.setAlignment(Pos.CENTER);
            h.getChildren().addAll(k);
            HBox h0 = new HBox();
            h0.setSpacing(10);
            h0.setAlignment(Pos.CENTER);
            h0.getChildren().addAll(trajet);
            HBox h1 = new HBox();
            h1.setSpacing(10);
            h1.setAlignment(Pos.CENTER);
            h1.getChildren().addAll(nom,date1);
            HBox h2 = new HBox();
            h2.setSpacing(10);
            h2.setAlignment(Pos.CENTER);
            h2.getChildren().addAll(nom1,date2);
             
             HBox h5 = new HBox();
            h5.setSpacing(10);
            h5.setAlignment(Pos.CENTER);
            h5.getChildren().addAll(prix);
           
            Button bt2=new Button("participer" ) ;
            if (oo.chercher_ajout(new Participer(a1.getId_tr(),1)))
                         {
                   bt2.setDisable(true);
              }
             bt2.setOnAction(new EventHandler<ActionEvent>() {
                 @Override
                 public void handle(ActionEvent event) { 
                   
          
                            try {
                                if (!oo.chercher_ajout(new Participer(a1.getId_tr(),1))){
                                    
                                    try {
                                        oo.ajouter(new Participer(a1.getId_tr(),1));
                                        t.ajouter(new MesTrajet (a1.getDate_depart(),a1.getDate_arrive(),a1.getPt_depart(),a1.getPt_arrive(),a1.getPrix(),a1.getId_tr()));
                                        AnchorPane page;
                                        try {
                                            
                                            page = FXMLLoader.load(getClass().getResource("/com/packandgo/view/MesTrajets.fxml"));
                                            pane.getChildren().setAll(page);
                                        } catch (IOException ex) {
                                            Logger.getLogger(AfficherTrajetFrontController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        
                                        
                                    } catch (SQLException ex) {
                                        Logger.getLogger(AfficherTrajetFrontController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    bt2.setDisable(true);
                                }else System.out.println("evenement DEJA participer");
                            } catch (SQLException ex) {
                                Logger.getLogger(AfficherTrajetFrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
            
                      
                 }
             });
             java.util.Date dd = new java.util.Date();
             Button bt3=new Button("like" ) ;
            
        if (l.chercher_ajout(new liketrajet(a1.getId_tr(),1,dd)))
                         {
                   bt3.setDisable(true);
              }
             bt3.setOnAction(new EventHandler<ActionEvent>() {
                 @Override
                 public void handle(ActionEvent event) { //bitha heki chas
                     
                        try {
            if (!l.chercher_ajout(new liketrajet(a1.getId_tr(),1,dd))){
                
                try {
                    l.ajouter(new liketrajet(a1.getId_tr(),1,dd));
                     
                    String SQL1 = "UPDATE pidev.trajet SET  nb_like=nb_like+1 WHERE id_trajet ='"+a1.getId_tr()+"'";
                               int rs1 = ste.executeUpdate(SQL1);
                               
                    
                } catch (SQLException ex) {
                    Logger.getLogger(AfficherTrajetFrontController.class.getName()).log(Level.SEVERE, null, ex);
                }
                bt3.setDisable(true);
            }else System.out.println("trajet DEJA AIMER");
        } catch (SQLException ex) {
            Logger.getLogger(AfficherTrajetFrontController.class.getName()).log(Level.SEVERE, null, ex);
        } 
                      
                 }
             });
            

            VBox v = new VBox();
            v.setAlignment(Pos.CENTER);
            v.setSpacing(10);
            v.getChildren().addAll(h,h0,h1,h2,h5,bt2,bt3);

            HBox No = new HBox();
            No.setSpacing(10);
            No.setAlignment(Pos.CENTER);
            No.getChildren().addAll( v);

            VBox v1 = new VBox();
            v1.setAlignment(Pos.CENTER);
            v1.setSpacing(10);
            v1.getChildren().addAll(No);
            list.add(v1);

        }
        actualitecontainer.getChildren().addAll(list);
    }
           

}
