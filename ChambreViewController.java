/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hebergement.View.Chambre;

import Hebergement.Services.ChambreServices;
import Hebergement.Entity.Chambre;
import Hebergement.Entity.Hotel;
import Hebergement.Services.HotelServices;
import Hebergement.View.Hotel.HotelView;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import static com.lowagie.text.Font.NORMAL;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author Chaima
 */
public class ChambreViewController implements Initializable {

    @FXML
    private ComboBox<Hotel> CBhotel;
    @FXML
    private ComboBox<String> CBtype;
    @FXML
    private ComboBox<String> CBvue;
    @FXML
    private TextField TFnb;
    @FXML
    private TextField TFprix;
    @FXML
    private TableView<Chambre> tabChambre;
    @FXML
    private TableColumn<Chambre, Integer> colId;
    @FXML
    private TableColumn<Chambre, Integer> colHotel;
    @FXML
    private TableColumn<Chambre, String> colType;
    @FXML
    private TableColumn<Chambre, String> colVue;
    @FXML
    private TableColumn<Chambre, String> colNb;
    @FXML
    private TableColumn<Chambre, Double> colPrix;
    @FXML
    private TableColumn<Chambre, String> colImage;
    @FXML
    private Button ajouer;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private TextField TFimage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherChambre();
         afficherCombo();
         //CBtype.getItems().(
    
        CBtype.getItems().addAll("Single","Double","Triple");
        CBvue.getItems().addAll("vue de mer","vue de terrase","vue de piscine");
        
                 
    } 
    
    
    private void afficherCombo(){
        HotelServices AC= new HotelServices();
        ObservableList<Hotel> liste = AC.showHotel();
        CBhotel.setItems(liste);
    }
    
    
    

    private void afficherChambre() {
        ChambreServices aS = new ChambreServices();
        ObservableList<Chambre> liste = aS.showChambre();
        colId.setCellValueFactory(new PropertyValueFactory<Chambre, Integer>("id"));
        colHotel.setCellValueFactory(new PropertyValueFactory<Chambre, Integer>("hotel_id"));
        colType.setCellValueFactory(new PropertyValueFactory<Chambre, String>("type"));
        colVue.setCellValueFactory(new PropertyValueFactory<Chambre, String>("vue"));
        colNb.setCellValueFactory(new PropertyValueFactory<Chambre, String>("duree"));
        colPrix.setCellValueFactory(new PropertyValueFactory<Chambre, Double>("prix"));
        colImage.setCellValueFactory(new PropertyValueFactory<Chambre, String>("image"));
        tabChambre.setItems(liste);
    }
    
    
    @FXML
    private void selectChambre(MouseEvent event) {
       Chambre A= tabChambre.getSelectionModel().getSelectedItem();
    TFnb.setText(A.getDuree());
    TFprix.setText(String.valueOf(A.getPrix()));
    TFimage.setText(A.getImage());
    
    }

    @FXML
    private void ajouterChambre(ActionEvent event) {
        int hotel_id = CBhotel.getValue().getId();
        String vue = CBvue.getValue();
        String type = CBtype.getValue();
        String duree = TFnb.getText();
        Double prix = Double.parseDouble(TFprix.getText());
        String image = TFimage.getText();Chambre A = new Chambre(hotel_id, type, vue, duree, prix, image);
        ChambreServices aS = new ChambreServices();
        if (aS.addChambre(A)){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Succées");
        alert.setHeaderText(null);
        alert.setContentText("L'ajout d'une nouvelle chambre a été effectué avec succées");
        alert.showAndWait();
        afficherChambre();
        }else{
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("L'ajout d'une nouvelle chambre n'a pas été effectué!");
        alert.showAndWait();   
        afficherChambre();
        }
        
    }

    @FXML
    private void modifierChambre(ActionEvent event) {
        Chambre A= tabChambre.getSelectionModel().getSelectedItem();
               int hotel_id = CBhotel.getValue().getId();
            

        A.setHotel_id(hotel_id);
        A.setDuree(TFnb.getText());
        A.setPrix(Double.parseDouble(TFprix.getText()));
        A.setImage(TFimage.getText());
        ChambreServices aS = new ChambreServices();
        if (aS.updateChambre(A)){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Succées");
        alert.setHeaderText(null);
        alert.setContentText("La modification de la chambre a été effectué avec succées");
        alert.showAndWait();
        afficherChambre();
        }else{
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("La modification de la chambre n'a pas été effectué!");
        alert.showAndWait();   
        afficherChambre();
        } 
    }

    @FXML
    private void supprimerChambre(ActionEvent event) {
         Chambre A= tabChambre.getSelectionModel().getSelectedItem();
        ChambreServices aS = new ChambreServices();
        if (aS.deleteChambre(A)){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Succées");
        alert.setHeaderText(null);
        alert.setContentText("La suppression de la chambre a été effectué avec succées");
        alert.showAndWait();
        afficherChambre();
        }else{
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("La supression de la chambre n'a pas été effectué!");
        alert.showAndWait();   
        afficherChambre();
        }
        
    }

    @FXML
    private void pdfChambre(ActionEvent event) {
        
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
            
            PdfPTable table= new PdfPTable(6);
            table.addCell("Id");
            table.addCell("Hotel");
            table.addCell("Type");
            table.addCell("Vue");
            table.addCell("NB Chambres");
            table.addCell("Prix");
           

           ChambreServices s= new ChambreServices();
            for( int i=0 ; i < s.rowChambre();i++)
            {
           
                String ID =tabChambre.getColumns().get(0).getCellObservableValue(i).getValue().toString();
                 String Hotel =tabChambre.getColumns().get(1).getCellObservableValue(i).getValue().toString();
                  String Type =tabChambre.getColumns().get(2).getCellObservableValue(i).getValue().toString();
                   String Vue =tabChambre.getColumns().get(3).getCellObservableValue(i).getValue().toString();
                    String NB_Chambres =tabChambre.getColumns().get(4).getCellObservableValue(i).getValue().toString();
                     String Prix =tabChambre.getColumns().get(5).getCellObservableValue(i).getValue().toString();
                      String Image =tabChambre.getColumns().get(6).getCellObservableValue(i).getValue().toString();
             table.addCell(ID);
            table.addCell(Hotel);
            table.addCell(Type);
            table.addCell(Vue);
            table.addCell(NB_Chambres);
            table.addCell(Prix);
            
            }
            
            doc.add(table);
            
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ChambreViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ChambreViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        doc.close();
        
    }

    @FXML
    private void gotohotel(MouseEvent event) {
        
        HotelView v=new HotelView();
        Stage primaryStage=new Stage();
        v.start(primaryStage);
        
    }
    
}
