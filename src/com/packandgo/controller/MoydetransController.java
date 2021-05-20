/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packandgo.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.packandgo.entity.Moydetrans;
import java.io.IOException;
import com.packandgo.services.MoydetransService;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author is
 */
public class MoydetransController implements Initializable {
    
    
     @FXML
    private TableView<Moydetrans> ListeCh;
    
    @FXML
    private TableColumn<Moydetrans, String> Type;
    @FXML
    private TableColumn<Moydetrans, String> Matricule;
    @FXML
    private TableColumn<Moydetrans, Integer> Dispo;
    
    private final ObservableList<Moydetrans> data = FXCollections.observableArrayList();
    ObservableList<String> list = FXCollections.observableArrayList("ok","bb");
    
     @FXML
     AnchorPane add_chpane,list_pane;
   
    
     @FXML
     JFXTextField txt_nom,txt_pre;
    @FXML
    private JFXButton tr;
    @FXML
    private TableColumn<?, ?> Type1;
    @FXML
    private TableColumn<?, ?> Type11;
    
     
    
    
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
         affall();   
        
        
    }  
    
    void affall()
    {
        MoydetransService cs = new MoydetransService();
             ArrayList<Moydetrans> tabch = new ArrayList<>();
         try {
             tabch = cs.afficherListCh();
            
         }
         catch (SQLException ex) {
             Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
         }
        try {
              for (int i=0;i<cs.afficherListCh().size(); i++){   
                  
            data.add(cs.afficherListCh().get(i));
            ListeCh.setItems(data);
            ListeCh.setEditable(true);
                  
                    }     
              
          //  Id.setCellValueFactory(new PropertyValueFactory<>("Id"));
            Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
            Matricule.setCellValueFactory(new PropertyValueFactory<>("Matricule"));
            Dispo.setCellValueFactory(new PropertyValueFactory<>("Dispo"));

            
            
            
//            Id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            Type.setCellFactory(TextFieldTableCell.forTableColumn());
            Matricule.setCellFactory(TextFieldTableCell.forTableColumn()); 
            Dispo.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
          
            
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    @FXML
    private void ChangerNom(TableColumn.CellEditEvent bb) throws SQLException {
        MoydetransService cs = new MoydetransService();
        Moydetrans tab_Demandeselected = ListeCh.getSelectionModel().getSelectedItem();
     tab_Demandeselected.setType(bb.getNewValue().toString());
     cs.updatetab(tab_Demandeselected);
    }

    @FXML
    private void ChangerPre(TableColumn.CellEditEvent bb) throws SQLException {
        MoydetransService cs = new MoydetransService();
       Moydetrans tab_Demandeselected = ListeCh.getSelectionModel().getSelectedItem();
     tab_Demandeselected.setMatricule(bb.getNewValue().toString());
     cs.updatetab(tab_Demandeselected);
    }
    @FXML
    private void ChangerDis(TableColumn.CellEditEvent bb) throws SQLException {
         MoydetransService cs = new MoydetransService();
           Moydetrans tab_Demandeselected = ListeCh.getSelectionModel().getSelectedItem();
      tab_Demandeselected.setDispo((int) bb.getNewValue());
     cs.updatetab(tab_Demandeselected);
     
    }
     @FXML
     private void gerer_ch_btn(ActionEvent event)
     {
           Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               
                double widhStrage = stage.getWidth();
                double heightStrage = stage.getHeight();
           
           try {
               
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/packandgo/view/chauffeur.fxml"));
         Parent root = loader.load();
        
                 Scene scene1 = new Scene(root);
                 stage.setScene(scene1);
                stage.setHeight(heightStrage);
                stage.setWidth(widhStrage);
                  stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
     }
     
     @FXML
      private void gerer_ve_btn(ActionEvent event)
     {
           Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               
                double widhStrage = stage.getWidth();
                double heightStrage = stage.getHeight();
           
           try {
               
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/packandgo/view/MoyendeTransport.fxml"));
         Parent root = loader.load();
        
                 Scene scene1 = new Scene(root);
                 stage.setScene(scene1);
                stage.setHeight(heightStrage);
                stage.setWidth(widhStrage);
                  stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
     }
    @FXML
    private void gerer_trajet_btn(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               
                double widhStrage = stage.getWidth();
                double heightStrage = stage.getHeight();
           
           try {
               
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/packandgo/view/Trajet.fxml"));
         Parent root = loader.load();
        
                 Scene scene1 = new Scene(root);
                 stage.setScene(scene1);
                stage.setHeight(heightStrage);
                stage.setWidth(widhStrage);
                  stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
    
     @FXML
    private void supprimer(ActionEvent event) throws SQLException {

          ListeCh.setItems(data);

             ObservableList<Moydetrans> allDemandes,SingleDemandes ;
             allDemandes=ListeCh.getItems();
             SingleDemandes=ListeCh.getSelectionModel().getSelectedItems();
             Moydetrans A = SingleDemandes.get(0);
             MoydetransService STD = new MoydetransService(); // STD = Service TAB DEMANDE
             STD.deleteCh(A.getId_moy_trans());
             SingleDemandes.forEach(allDemandes::remove);
              TrayNotification tray =new TrayNotification();
            tray.setTitle("Succès");
        tray.setMessage("Suppression avec succès !");
        tray.setAnimationType(AnimationType.POPUP);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndWait();
            
        
    }
    
    @FXML
    private void ajouterch(ActionEvent event) throws SQLException {
        
         Moydetrans c = new Moydetrans();
         
       
        
                     c.setType(txt_nom.getText()); 
             c.setMatricule(txt_pre.getText());
           
                MoydetransService ps = new MoydetransService();
                 ps.ajouterCh(c);  
                  add_chpane.setVisible(false);
                  data.clear();
                  affall();
        list_pane.setVisible(true);
           
          TrayNotification tray =new TrayNotification();
            tray.setTitle("Succès");
        tray.setMessage("Ajout avec succès !");
        tray.setAnimationType(AnimationType.POPUP);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndWait();
       
    }
    
    @FXML
    private void ann(ActionEvent event) 
    {
       
        add_chpane.setVisible(false);
        list_pane.setVisible(true);
        
    }
    
     @FXML
    private void ajouter_btn(ActionEvent event)
    {
        add_chpane.setVisible(true);
        list_pane.setVisible(false);
    }

    

    
  
    
}
