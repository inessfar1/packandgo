/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hebergement.View.Hotel;

import Hebergement.Services.HotelServices;
import Hebergement.Entity.Hotel;
import Hebergement.View.Chambre.ChambreView;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chaima
 */
public class HotelViewController implements Initializable {

    @FXML
    private TextField TFnom;
    @FXML
    private TextField TFadresse;
    @FXML
    private TextField TFetoile;
    @FXML
    private TextField TFnum;
    @FXML
    private TextField TFimage;
    @FXML
    private TableView<Hotel> hotelTab;
    @FXML
    private TableColumn<Hotel, Integer> colId;
    @FXML
    private TableColumn<Hotel, String> colNom;
    @FXML
    private TableColumn<Hotel, String> colAdresse;
    @FXML
    private TableColumn<Hotel, String> colEtoile;
    @FXML
    private TableColumn<Hotel, String> colNum;
    @FXML
    private TableColumn<Hotel, String> colImage;
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherHotel();
    }   
    
    
    private void afficherHotel() {
        HotelServices aS = new HotelServices();
        ObservableList<Hotel> liste = aS.showHotel();
        colId.setCellValueFactory(new PropertyValueFactory<Hotel, Integer>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<Hotel, String>("nom"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<Hotel, String>("adresse"));
        colEtoile.setCellValueFactory(new PropertyValueFactory<Hotel, String>("etoile"));
        colNum.setCellValueFactory(new PropertyValueFactory<Hotel, String>("num"));
        colImage.setCellValueFactory(new PropertyValueFactory<Hotel, String>("image"));
        hotelTab.setItems(liste);
    }
    
    
    
    @FXML
    private void ajouterHotel(ActionEvent event) {
        String nom = TFnom.getText();
        String adresse = TFadresse.getText();
        String etoile = TFetoile.getText();
        String num = TFnum.getText();
        String image = TFimage.getText();
        Hotel A = new Hotel(nom, adresse, etoile, num, image);
        HotelServices aS = new HotelServices();
        if (aS.addHotel(A)){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Succées");
        alert.setHeaderText(null);
        alert.setContentText("L'ajout d'un nouveau hotel a été effectué avec succées");
        alert.showAndWait();
        afficherHotel();
        }else{
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("L'ajout d'un nouveau hotel n'a pas été effectué!");
        alert.showAndWait();   
        afficherHotel();
        }
    }

    @FXML
    private void modifierHotel(ActionEvent event) {
         Hotel A= hotelTab.getSelectionModel().getSelectedItem();
        A.setNom(TFnom.getText());
        A.setAdresse(TFadresse.getText());
        A.setEtoile(TFetoile.getText());
        A.setNum(TFnum.getText());
        A.setImage(TFimage.getText());
        HotelServices aS = new HotelServices();
        if (aS.updateHotel(A)){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Succées");
        alert.setHeaderText(null);
        alert.setContentText("La modification d'hotel a été effectué avec succées");
        alert.showAndWait();
        afficherHotel();
        }else{
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("La modification d'hotel n'a pas été effectué!");
        alert.showAndWait();   
        afficherHotel();
        }
    }

    @FXML
    private void supprimerHotel(ActionEvent event) {
        Hotel A= hotelTab.getSelectionModel().getSelectedItem();
        HotelServices aS = new HotelServices();
        if (aS.deleteHotel(A)){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Succées");
        alert.setHeaderText(null);
        alert.setContentText("La suppression d'hotel a été effectué avec succées");
        alert.showAndWait();
        afficherHotel();
        }else{
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("La supression d'hotel n'a pas été effectué!");
        alert.showAndWait();   
        afficherHotel();
        }
    }
    @FXML
    private void selectHotel(MouseEvent event) {
      Hotel A= hotelTab.getSelectionModel().getSelectedItem();
    TFnom.setText(A.getNom());
    TFadresse.setText(A.getAdresse());
    TFetoile.setText(A.getEtoile());
    TFnum.setText(A.getNum());
    TFimage.setText(A.getImage());
    }

    @FXML
    private void gotochambre(MouseEvent event) {
        
        
        ChambreView v=new ChambreView();
        Stage primaryStage=new Stage();
        v.start(primaryStage);
    }
    
}
