/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packandgo.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.packandgo.entity.Moydetrans;
import com.packandgo.entity.Trajet;
import com.packandgo.services.MoydetransService;
import java.io.IOException;
import com.packandgo.services.TrajetService;
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
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author is
 */
public class TrajetController implements Initializable {
    
    
     @FXML
    private TableView<Trajet> ListeCh;
    
    @FXML
    private TableColumn<Trajet, String> Date_depart;
    @FXML
    private TableColumn<Trajet, String> Date_arrive;
    @FXML
    private TableColumn<Trajet, String> Pt_depart;
    @FXML
    private TableColumn<Trajet, String> Pt_arrive;
    @FXML
    private TableColumn<Trajet, Float> Prix;
    
    private final ObservableList<Trajet> data = FXCollections.observableArrayList();
    ObservableList<String> list = FXCollections.observableArrayList("ok","bb");
    
     @FXML
     AnchorPane add_chpane,list_pane;
   
    
    @FXML
     JFXTextField pd,pa,p;
    @FXML
    JFXDatePicker dd,da;
    
     
    
    
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
         affall();   
        
        
    }  
    
    void affall()
    {
        TrajetService cs = new TrajetService();
             ArrayList<Trajet> tabch = new ArrayList<>();
         try {
             tabch = cs.afficherListCh();
            
            
         }
         catch (SQLException ex) {
             Logger.getLogger(TrajetController.class.getName()).log(Level.SEVERE, null, ex);
         }
        try {
              for (int i=0;i<cs.afficherListCh().size(); i++){   
                  
            data.add(cs.afficherListCh().get(i));
            ListeCh.setItems(data);
            ListeCh.setEditable(true);
                  System.out.println(data);
                    }     
              
          //  Id.setCellValueFactory(new PropertyValueFactory<>("Id"));
            Date_depart.setCellValueFactory(new PropertyValueFactory<>("Date_depart"));
            Date_arrive.setCellValueFactory(new PropertyValueFactory<>("Date_arrive"));
            Pt_depart.setCellValueFactory(new PropertyValueFactory<>("Pt_depart"));
            Pt_arrive.setCellValueFactory(new PropertyValueFactory<>("Pt_arrive"));
            Prix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
            

            
            
            
//            Id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            Date_depart.setCellFactory(TextFieldTableCell.forTableColumn());
            Date_arrive.setCellFactory(TextFieldTableCell.forTableColumn()); 
            Pt_depart.setCellFactory(TextFieldTableCell.forTableColumn());
            Pt_arrive.setCellFactory(TextFieldTableCell.forTableColumn());
            Prix.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
          
            
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
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

             ObservableList<Trajet> allDemandes,SingleDemandes ;
             allDemandes=ListeCh.getItems();
             SingleDemandes=ListeCh.getSelectionModel().getSelectedItems();
             Trajet A = SingleDemandes.get(0);
             TrajetService STD = new TrajetService(); // STD = Service TAB DEMANDE
             STD.deleteCh(A.getId_tr());
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
        
         Trajet c = new Trajet();
         
       
            
            c.setDate_depart(String.valueOf(dd.getValue())); 
             c.setDate_arrive(String.valueOf(da.getValue()));
             c.setPt_depart(pd.getText());
             c.setPt_arrive(pa.getText());
             c.setPrix(Float.parseFloat(p.getText()));
           
               TrajetService ps = new TrajetService();
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
    private void Changerdd(TableColumn.CellEditEvent bb) throws SQLException {
        TrajetService cs = new TrajetService();
       Trajet tab_Demandeselected = ListeCh.getSelectionModel().getSelectedItem();
     tab_Demandeselected.setDate_depart(bb.getNewValue().toString());
     cs.updatetab(tab_Demandeselected);
    }

    @FXML
    private void Changerda(TableColumn.CellEditEvent bb) throws SQLException {
        TrajetService cs = new TrajetService();
       Trajet tab_Demandeselected = ListeCh.getSelectionModel().getSelectedItem();
     tab_Demandeselected.setDate_arrive(bb.getNewValue().toString());
     cs.updatetab(tab_Demandeselected);
    }
     @FXML
    private void Changerpd(TableColumn.CellEditEvent bb) throws SQLException {
        TrajetService cs = new TrajetService();
       Trajet tab_Demandeselected = ListeCh.getSelectionModel().getSelectedItem();
     tab_Demandeselected.setPt_depart(bb.getNewValue().toString());
     cs.updatetab(tab_Demandeselected);
    }
    @FXML
    private void Changerpa(TableColumn.CellEditEvent bb) throws SQLException {
        TrajetService cs = new TrajetService();
       Trajet tab_Demandeselected = ListeCh.getSelectionModel().getSelectedItem();
     tab_Demandeselected.setPt_arrive(bb.getNewValue().toString());
     cs.updatetab(tab_Demandeselected);
    }
    @FXML
    private void Changerp(TableColumn.CellEditEvent bb) throws SQLException {
        TrajetService cs = new TrajetService();
       Trajet tab_Demandeselected = ListeCh.getSelectionModel().getSelectedItem();
     tab_Demandeselected.setPrix(Float.parseFloat(bb.getNewValue().toString()));
     cs.updatetab(tab_Demandeselected);
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
