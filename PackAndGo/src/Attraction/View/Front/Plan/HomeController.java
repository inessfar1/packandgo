/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.View.Front.Plan;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter; 
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import Attraction.Entity.Plan;
import Attraction.Entity.Plan_res;
import Attraction.Entity.TextToSpeech;
import Attraction.Services.PlanResServices;
import Attraction.Services.PlanServices;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;



/**
 * FXML Controller class
 *
 * @author 21628
 */
public class HomeController implements Initializable {

    @FXML
    private AnchorPane mainPage;
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
    private TableView<Plan> tabPlan;
    @FXML
    private TableColumn<Plan, String> colImage;
    @FXML
    private TableColumn<Plan, String> colSujet;
    @FXML
    private TableColumn<Plan, String> colDesc;
    @FXML
    private TableColumn<Plan,Date> colDate;
    @FXML
    private TableColumn<Plan, Double> colPrix;
    @FXML
    private Button res;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            afficherPlan();
    }   
    
    private void afficherPlan() {
        PlanServices aS = new PlanServices();
        ObservableList<Plan> liste = aS.showPlanF();
        
        colImage.setCellValueFactory(new PropertyValueFactory<Plan, String>("image"));
        colSujet.setCellValueFactory(new PropertyValueFactory<Plan, String>("sujet"));
        colDesc.setCellValueFactory(new PropertyValueFactory<Plan, String>("description"));
        colDate.setCellValueFactory(new PropertyValueFactory<Plan, Date>("date"));
        colPrix.setCellValueFactory(new PropertyValueFactory<Plan, Double>("prix"));
        tabPlan.setItems(liste);
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
    private void pageOnClick(MouseEvent event) {
    }

    @FXML
    private void Reserver(ActionEvent event) throws IOException {
        PlanResServices rS= new PlanResServices();
        Plan A= tabPlan.getSelectionModel().getSelectedItem();
        if (A==null){Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Il faut selectionner un plan pour cette action");
        alert.showAndWait();   
        afficherPlan();
                }else{
        rS.getPlanId(A.getId());
        int utilisateur_id = 2;
        Plan_res d= new Plan_res(utilisateur_id,A.getId());
        if(rS.Reserver(d)){
            String title = "Reservation";
            String msg ="Reservé avec succées";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle(title);
            tray.setMessage(msg);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(12000));
            rS.QR(A,utilisateur_id);
            rS.sendRes(A, utilisateur_id);
            rS.verifNBR(A);
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
           new TextToSpeech("Reservation has been accepted");
           
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succées");
        alert.setHeaderText(null);
        alert.setContentText("La réservation a été effectué avec succées");
        alert.showAndWait();
        afficherPlan();
        }else
        {
            String title = "Reservation";
            String msg ="Reservé n'a pas été effectué";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle(title);
            tray.setMessage(msg);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(12000));
            Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("La réservation n'a pas été effectué avec succées");
        alert.showAndWait();   
        }
    }}
    }
    

