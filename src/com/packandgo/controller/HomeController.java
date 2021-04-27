/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packandgo.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.packandgo.entity.Chauffeur;
import java.io.IOException;
import com.packandgo.services.ChaufferService;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javax.swing.JFileChooser;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author is
 */
public class HomeController implements Initializable {
    
    
     @FXML
    private TableView<Chauffeur> ListeCh;
    
    @FXML
    private TableColumn<Chauffeur, String> Nom;
    @FXML
    private TableColumn<Chauffeur, String> Prenom;
    @FXML
    private TableColumn<Chauffeur, Integer> Disp;
    
    private final ObservableList<Chauffeur> data = FXCollections.observableArrayList();
    ObservableList<String> list = FXCollections.observableArrayList("ok","bb");
    
     @FXML
     AnchorPane add_chpane,list_pane;
   
    
     @FXML
     JFXTextField txt_nom,txt_pre;
    @FXML
    private JFXButton tr;
    @FXML
    private Button Pdfchauffeur;
    
    @FXML
    private TextField rechercher;
    
     
    
    
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       
         affall();   
        RechercheAV();
        
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
    
    void affall()
    {
        ChaufferService cs = new ChaufferService();
             ArrayList<Chauffeur> tabch = new ArrayList<>();
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
                  System.out.println(data);
                    }     
              
            //ines_dfsdfsg.setCellValueFactory(new PropertyValueFactory<>("ines_dfsdfsg"));
            Nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
            Prenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
            Disp.setCellValueFactory(new PropertyValueFactory<>("Disp"));

            
            
            
//            ines_dfsdfsg.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            Nom.setCellFactory(TextFieldTableCell.forTableColumn());
            Prenom.setCellFactory(TextFieldTableCell.forTableColumn()); 
            Disp.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
          
            
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
       RechercheAV();
    }
    
    public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Chauffeur> filteredData = new FilteredList<>(data, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(event -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (event.getNom().toLowerCase().contains(lowerCaseFilter) ) {
					return true; // Filter matches first name.
				} 

                             
                        
                                 
                             
				else if (String.valueOf(event.getId_ch()).contains(lowerCaseFilter))
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Chauffeur> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(ListeCh.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		ListeCh.setItems(sortedData);
    }
    
    @FXML
    private void ChangerNom(TableColumn.CellEditEvent bb) throws SQLException {
        ChaufferService cs = new ChaufferService();
        Chauffeur tab_Demandeselected = ListeCh.getSelectionModel().getSelectedItem();
     tab_Demandeselected.setNom(bb.getNewValue().toString());
     cs.updatetab(tab_Demandeselected);
    }

    @FXML
    private void ChangerPre(TableColumn.CellEditEvent bb) throws SQLException {
        ChaufferService cs = new ChaufferService();
       Chauffeur tab_Demandeselected = ListeCh.getSelectionModel().getSelectedItem();
     tab_Demandeselected.setPrenom(bb.getNewValue().toString());
     cs.updatetab(tab_Demandeselected);
    }

    @FXML
    private void ChangerDis(TableColumn.CellEditEvent bb) throws SQLException {
         ChaufferService cs = new ChaufferService();
           Chauffeur tab_Demandeselected = ListeCh.getSelectionModel().getSelectedItem();
      tab_Demandeselected.setDisp((int) bb.getNewValue());
     cs.updatetab(tab_Demandeselected);
     
    }
    
    
     @FXML
    private void supprimer(ActionEvent event) throws SQLException {

          ListeCh.setItems(data);

             ObservableList<Chauffeur> allDemandes,SingleDemandes ;
             allDemandes=ListeCh.getItems();
             SingleDemandes=ListeCh.getSelectionModel().getSelectedItems();
             Chauffeur A = SingleDemandes.get(0);
             ChaufferService STD = new ChaufferService(); // STD = Service TAB DEMANDE
             STD.deleteCh(A.getId_ch());
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
        
         Chauffeur c = new Chauffeur();
         
       
        
                     c.setNom(txt_nom.getText()); 
             c.setPrenom(txt_pre.getText());
           
                ChaufferService ps = new ChaufferService();
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

    @FXML
    private void creatpdf(ActionEvent event) throws DocumentException {
        
        String path="";
        
        JFileChooser j=new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
       int x=j.showSaveDialog(j);
        if(x==JFileChooser.APPROVE_OPTION)
        {
            path=j.getSelectedFile().getPath();
        
        }
       
        Document doc =new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(path+"/abc123.pdf"));
            doc.open();
            
            PdfPTable table= new PdfPTable(3);
            table.addCell("Nom");
            table.addCell("Prenom");
            table.addCell("Disponibilite");
           
           

           ChaufferService s= new ChaufferService();
            for( int i=0 ; i < s.rowChauffeur();i++)
            {
           
                String Nom =ListeCh.getColumns().get(0).getCellObservableValue(i).getValue().toString();
                 String Prenom =ListeCh.getColumns().get(1).getCellObservableValue(i).getValue().toString();
                  String Disponibilite =ListeCh.getColumns().get(2).getCellObservableValue(i).getValue().toString();
                  
            table.addCell(Nom);
            table.addCell(Prenom);
            table.addCell(Disponibilite);
            
            
            }
            
            doc.add(table);
            
            
            
        } catch (FileNotFoundException ex) {
        } catch (DocumentException ex) {
        }
        
        doc.close();
        
    }

    

    
  
    
}
