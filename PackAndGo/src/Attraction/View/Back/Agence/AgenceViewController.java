/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.View.Back.Agence;

import Attraction.Entity.Agence;
import Attraction.Entity.Test;
import Attraction.Entity.TextToSpeech;
import Attraction.Services.AgenceServices;
import Attraction.View.Back.Plan.PlanView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author 21628
 */
public class AgenceViewController implements Initializable {
    
    @FXML
    private TextField TFlogo;
    @FXML
    private TextField TFnom;
    @FXML
    private TextField TFemail;
    @FXML
    private TextField TFtel;
    @FXML
    private TextField TFaddresse;
    @FXML
    private Button TFAjouter;
    @FXML
    private TableView<Agence> tabAgence;
    @FXML
    private TableColumn<Agence, String> colLogo;
    @FXML
    private TableColumn<Agence, String> colNom;
    @FXML
    private TableColumn<Agence, String> colEmail;
    @FXML
    private TableColumn<Agence, String> colAddresse;
    @FXML
    private TableColumn<Agence, Integer> colTel;
    @FXML
    private TableColumn<Agence, Integer> colID;
    @FXML
    private Button Aupdate;
    @FXML
    private Button Adelete;
    @FXML
    private TextField TFrecherche;
    @FXML
    private Button browse;
    
  public FileChooser filechooser;
    public File file;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherAgence(); 
    }  
    private void afficherAgence() {
        AgenceServices aS = new AgenceServices();
        ObservableList<Agence> liste = aS.showAgence();
        colID.setCellValueFactory(new PropertyValueFactory<Agence, Integer>("id"));
        colLogo.setCellValueFactory(new PropertyValueFactory<Agence, String>("logo"));
        colNom.setCellValueFactory(new PropertyValueFactory<Agence, String>("nom"));
        colAddresse.setCellValueFactory(new PropertyValueFactory<Agence, String>("adresse"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Agence, String>("email"));
        colTel.setCellValueFactory(new PropertyValueFactory<Agence, Integer>("tel"));
        tabAgence.setItems(liste);
    }
    
    
    @FXML
    private void ajouterAgence(ActionEvent event) {
        String logo = TFlogo.getText();
        String nom = TFnom.getText();
        String email = TFemail.getText();
        int pays_id = 1;
        String addresse = TFaddresse.getText();
        
        
        if (!Test.isAlpha(nom)) {Test.msgAlpha("nom");}
        else if (nom.length()<5 || nom.length()>20) {Test.msgLength("nom", "5", "20");}
        else if (!Test.isValidEmailAddress(email)) {Test.msgEmail();}
        else if (addresse.length()<10 || nom.length()>50) {Test.msgLength("adresse", "10", "50");}
        else if (!Test.isNumeric(TFtel.getText())) {Test.msgNumeric("tel");}
        else if (TFtel.getText().length()<8 || TFtel.getText().length()>20) {Test.msgLength("tel", "8", "20");}
        
        
        
        else{
            int tel = Integer.parseInt(TFtel.getText());
        Agence A = new Agence(pays_id,logo,nom,addresse,email,tel);
        AgenceServices aS = new AgenceServices();
        if (aS.addAgence(A)){
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
           new TextToSpeech("a new agency has been added");
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Succées");
        alert.setHeaderText(null);
        alert.setContentText("L'ajout d'une nouvelle agence a été effectué avec succées");
        alert.showAndWait();
        afficherAgence();
        }else{String title = "Agence";
            String msg ="Agence n'a pas été ajouté";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle(title);
            tray.setMessage(msg);
            tray.setNotificationType(NotificationType.ERROR);
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("L'ajout d'une nouvelle agence n'a pas été effectué!");
        alert.showAndWait();   
        afficherAgence();
        }
    }}
    

    @FXML
    private void modifierAgence(ActionEvent event) {
        Agence A= tabAgence.getSelectionModel().getSelectedItem();
        if (A==null){Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Il faut selectionner une agence pour cette action");
        alert.showAndWait();   
        afficherAgence();
                }else {   
            
            
             if (!Test.isAlpha(TFnom.getText())) {Test.msgAlpha("nom");}
        else if (TFnom.getText().length()<5 || TFnom.getText().length()>20) {Test.msgLength("nom", "5", "20");}
        else if (!Test.isValidEmailAddress(TFemail.getText())) {Test.msgEmail();}
        else if (TFaddresse.getText().length()<10 || TFaddresse.getText().length()>50) {Test.msgLength("adresse", "10", "50");}
        if (!Test.isNumeric(TFtel.getText())) {Test.msgNumeric("tel");}
        else if (TFtel.getText().length()<8 || TFtel.getText().length()>20) {Test.msgLength("tel", "8", "20");}
            
        else{   
            
        A.setLogo(TFlogo.getText());
        A.setEmail(TFemail.getText());
        A.setAdresse(TFaddresse.getText());
        A.setTel(Integer.parseInt(TFtel.getText()));
        A.setNom(TFnom.getText());
        AgenceServices aS = new AgenceServices();
        if (aS.updateAgence(A)){
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
           new TextToSpeech("agency has been updated");
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Succées");
        alert.setHeaderText(null);
        alert.setContentText("La modification d'agence a été effectué avec succées");
        alert.showAndWait();
        afficherAgence();
        }else{
            String title = "Agence";
            String msg ="Agence n'a pas été modifié";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle(title);
            tray.setMessage(msg);
            tray.setNotificationType(NotificationType.ERROR);
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("La modification d'agence n'a pas été effectué!");
        alert.showAndWait();   
        afficherAgence();
        }
    }}}

    @FXML
    private void supprimerAgence(ActionEvent event) {
        Agence A= tabAgence.getSelectionModel().getSelectedItem();
        if (A==null){Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Il faut selectionner une agence pour cette action");
        alert.showAndWait();   
        afficherAgence();
                }else{
             Alert mm = new Alert(AlertType.WARNING, 
                        "Voulez-vous vraiment supprimer cette agence?", 
                        ButtonType.YES, ButtonType.NO);

Optional<ButtonType> result = mm.showAndWait();
if (result.get() == ButtonType.YES){
        AgenceServices aS = new AgenceServices();
        if (aS.deleteAgence(A)){
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
           new TextToSpeech("agency has been deleted");
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Succées");
        alert.setHeaderText(null);
        alert.setContentText("La suppression d'agence a été effectué avec succées");
        alert.showAndWait();
        afficherAgence();
        }else{
            String title = "Agence";
            String msg ="Agence n'a pas été supprimé";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle(title);
            tray.setMessage(msg);
            tray.setNotificationType(NotificationType.ERROR);
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("La supression d'agence n'a pas été effectué!");
        alert.showAndWait();   
        afficherAgence();
        }
    }else {afficherAgence();}}}

    @FXML
    private void selectAgence(MouseEvent event) {
      Agence A= tabAgence.getSelectionModel().getSelectedItem();
    TFlogo.setText(A.getLogo());
    TFnom.setText(A.getNom());
    TFemail.setText(A.getEmail());
    TFtel.setText(String.valueOf(A.getTel()));
    TFaddresse.setText(A.getAdresse());
    }

    @FXML
    public void rechercherAgence(MouseEvent event) {
        String key = TFrecherche.getText();
        AgenceServices aS = new AgenceServices();
        ObservableList<Agence> liste = aS.searchAgence(key);
        colID.setCellValueFactory(new PropertyValueFactory<Agence, Integer>("id"));
        colLogo.setCellValueFactory(new PropertyValueFactory<Agence, String>("logo"));
        colNom.setCellValueFactory(new PropertyValueFactory<Agence, String>("nom"));
        colAddresse.setCellValueFactory(new PropertyValueFactory<Agence, String>("adresse"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Agence, String>("email"));
        colTel.setCellValueFactory(new PropertyValueFactory<Agence, Integer>("tel"));
        tabAgence.setItems(liste);
    }

    @FXML
    private void goToPlan(MouseEvent event) {
        Stage stage = (Stage) Aupdate.getScene().getWindow();
        PlanView v=new PlanView();
        Stage primaryStage=new Stage();
        v.start(primaryStage);
       stage.close();
    }

    @FXML
    private void browse(ActionEvent event) throws IOException {
         filechooser = new FileChooser();
       filechooser.getExtensionFilters().addAll(
       new FileChooser.ExtensionFilter("jpg files (.jpg)",".png",".PNG",".jpg","*.JPG"));
       file=filechooser.showOpenDialog(null);
       if(file !=null)
       {
           TFlogo.setText(file.getName());
                Path src = Paths.get(file.getAbsolutePath());
                Path dst = Paths.get("C:/Users/21628/Desktop/final web/koko444/pi/public/images/logos/"+file.getName()+"");
                Files.copy(src, dst, StandardCopyOption.REPLACE_EXISTING); 
                 
    }
    }

    
    
}
