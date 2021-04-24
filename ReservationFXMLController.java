/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hebergement.View.Chambre;

import Hebergement.Entity.Chambre;
import Hebergement.Entity.Hotel;
import Hebergement.Entity.Reservation;
import Hebergement.Services.HotelServices;
import Hebergement.Services.ReservationService;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.util.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author Chaima
 */
public class ReservationFXMLController implements Initializable {

    @FXML
    private VBox sousMenuTaches;
    @FXML
    private VBox sousMenuActivites;
    @FXML
    private ImageView imageNotificaction;
    @FXML
    private Text nombreNotification;
    @FXML
    private Text labelNom;
    @FXML
    private Pane buttonMenuLogOut;
    @FXML
    private VBox menu;
    @FXML
    private AnchorPane body;
    @FXML
    private VBox menuLogOut;
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
    private ComboBox<String> type;
    @FXML
    private DatePicker datedeb;
    @FXML
    private DatePicker datefin;
    @FXML
    private TableView<Reservation> tableReserv;
    @FXML
    private TableColumn<Reservation, String> hotelName;
    @FXML
    private TableColumn<Reservation, String> typechamb;
    @FXML
    private TableColumn<Reservation, Date> daterev;
    @FXML
    private TableColumn<Reservation, Date> datefinres;
    

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherHotel();
        afficherReservation();
        type.getItems().addAll("Single","Double","Triple");
         
    }    

    @FXML
    private void programmerReunion(ActionEvent event) {
    }

    @FXML
    private void afficherReunions(ActionEvent event) {
    }

    @FXML
    private void programmerEvent(ActionEvent event) {
    }

    @FXML
    private void afficherNotification(MouseEvent event) {
    }

    @FXML
    private void buttonMenuLogOut(MouseEvent event) {
    }

    @FXML
    private void afficherProjet(ActionEvent event) {
    }

    @FXML
    private void afficherReclamation(ActionEvent event) {
    }

    @FXML
    private void gestionTache(ActionEvent event) {
    }

    @FXML
    private void gestionDesSprints(ActionEvent event) {
    }

    @FXML
    private void gestionActivitesOnClick(ActionEvent event) {
    }

    @FXML
    private void selectHotel(MouseEvent event) {
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
    
     private void afficherReservation() {
        ReservationService rS = new ReservationService();
        ObservableList<Reservation> liste = rS.showReserv();
     
         hotelName.setCellValueFactory(new PropertyValueFactory<Reservation, String>("user"));
        typechamb.setCellValueFactory(new PropertyValueFactory<Reservation, String>("type"));
        daterev.setCellValueFactory(new PropertyValueFactory<Reservation, Date>("date_deb"));
        datefinres.setCellValueFactory(new PropertyValueFactory<Reservation, Date>("date_fin"));
        tableReserv.setItems(liste);
    }

    @FXML
    private void reserver(ActionEvent event) {
        ReservationService rS= new ReservationService();
        Hotel A= hotelTab.getSelectionModel().getSelectedItem();
        LocalDate datedebut= datedeb.getValue();
        LocalDate datefin= this.datefin.getValue();
        Instant instant = datedebut.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date db= Date.from(instant);
        Instant instantt = datefin.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date df= Date.from(instantt);
        String typee = type.getValue();
        Chambre ch=new Chambre();
        int chID=rS.getChambreId(typee, A.getId());
        if(chID!=-1){
        ch.setId(chID);
        Reservation reserv= new Reservation(3,ch,db,df);
        rS.Reserver(reserv);
        rS.sendmail();
                JOptionPane.showMessageDialog(null, "reservation effectuée");

        }else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Verifier vos parametres!");
        alert.showAndWait();   
        }

    }
   
    
    
}
