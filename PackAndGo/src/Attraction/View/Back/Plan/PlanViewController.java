/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.View.Back.Plan;

import Attraction.Services.AgenceServices;
import Attraction.Services.PlanServices;
import Attraction.Entity.Plan;
import Attraction.Entity.Agence;
import Attraction.Entity.Test;
import Attraction.Entity.TextToSpeech;
import Attraction.View.Back.Agence.AgenceView;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import org.apache.commons.io.FileUtils;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author 21628
 */
public class PlanViewController implements Initializable {

    @FXML
    private ComboBox<Agence> CBagence;
    @FXML
    private TextField TFimage;
    @FXML
    private TextField TFsujet;
    @FXML
    private TextField TFdescription;
    @FXML
    private DatePicker DPdate;
    @FXML
    private TextField TFprix;
    @FXML
    private TableView<Plan> tabPlan;
    @FXML
    private TableColumn<Plan, Integer> colId;
    @FXML
    private TableColumn<Plan, Integer> colPays;
    @FXML
    private TableColumn<Plan, Integer> colAgence;
    @FXML
    private TableColumn<Plan, String> colImage;
    @FXML
    private TableColumn<Plan, String> colSujet;
    @FXML
    private TableColumn<Plan, String> colDescription;
    @FXML
    private TableColumn<Plan, Date> colDate;
    @FXML
    private TableColumn<Plan, Double> colPrix;
    @FXML
    private Button PAjouter;
    @FXML
    private Button Pupdate;
    @FXML
    private Button Pdelete;
    @FXML
    private ComboBox<?> CBpays;
    @FXML
    private TextField TFrecherche;
    @FXML
    private Button reclamer;
    @FXML
    private Button browse;
    public FileChooser filechooser;
    public File file;
   
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       afficherCombo(); 
       afficherPlan();
    }   
    
    private void afficherCombo(){
        AgenceServices AC= new AgenceServices();
        ObservableList<Agence> liste = AC.showAgence();
        CBagence.setItems(liste);
    }

    private void afficherPlan() {
        PlanServices aS = new PlanServices();
        ObservableList<Plan> liste = aS.showPlan();
        colId.setCellValueFactory(new PropertyValueFactory<Plan, Integer>("id"));
        colPays.setCellValueFactory(new PropertyValueFactory<Plan, Integer>("pays_id"));
        colAgence.setCellValueFactory(new PropertyValueFactory<Plan , Integer>("agence_id"));
        colImage.setCellValueFactory(new PropertyValueFactory<Plan, String>("image"));
        colSujet.setCellValueFactory(new PropertyValueFactory<Plan, String>("sujet"));
        colDescription.setCellValueFactory(new PropertyValueFactory<Plan, String>("description"));
        colDate.setCellValueFactory(new PropertyValueFactory<Plan, Date>("date"));
        colPrix.setCellValueFactory(new PropertyValueFactory<Plan, Double>("prix"));
        tabPlan.setItems(liste);
    }
    @FXML
    private void ajouterPlan(ActionEvent event) {
        
        String image = TFimage.getText();
        int pays_id = 1;
        
        String sujet = TFsujet.getText();
        String description = TFdescription.getText();
        if (DPdate.getValue()==null){Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Date non valide");
        alert.setHeaderText(null);
        alert.setContentText("Il faut choisir une date");
        alert.showAndWait();}else{
        LocalDate d = DPdate.getValue();
        LocalDate today = java.time.LocalDate.now();
        int v= d.compareTo(today);
        Instant instant = d.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date date= Date.from(instant);
        if (CBagence.getValue()==null) {Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Agence non valide");
        alert.setHeaderText(null);
        alert.setContentText("Il faut choisir une agence");
        alert.showAndWait();}
        else if (!Test.isAlpha(sujet)) {Test.msgAlpha("sujet");}
        else if (sujet.length()<5 || sujet.length()>20) {Test.msgLength("sujet", "5", "20");}
        else if (description.length()<10 || description.length()>50) {Test.msgLength("description", "10", "50");}
        else if (TFprix.getText().length()<1 || TFprix.getText().length()>20) {Test.msgLength("prix", "1", "20");}
       
        else if  (v<=0){
            Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Date incorrecte");
        alert.setHeaderText(null);
        alert.setContentText("Il faut que la date soit supérieur à la date d'aujourd'hui");
        alert.showAndWait();
        }else{
            int agence_id = CBagence.getValue().getId();
        Double prix = Double.parseDouble(TFprix.getText());
        Plan A = new Plan(pays_id,agence_id, image, sujet, description,date, prix);
        PlanServices aS = new PlanServices();
        if ((aS.addPlan(A))){
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
           new TextToSpeech("A new plan has been added");
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Succées");
        alert.setHeaderText(null);
        alert.setContentText("L'ajout d'un nouvel plan a été effectué avec succées");
        alert.showAndWait();
        afficherPlan();
        afficherCombo();
        }else{
            String title = "Plan";
            String msg ="Plan n'a pas été ajouté";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle(title);
            tray.setMessage(msg);
            tray.setNotificationType(NotificationType.ERROR);
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("L'ajout d'un nouvel plan n'a pas été effectué!");
        alert.showAndWait();   
        afficherPlan();
        afficherCombo();

        }
    }}}

    @FXML
    private void modifierPlan(ActionEvent event) {
        Plan A= tabPlan.getSelectionModel().getSelectedItem();
        if (A==null){Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Il faut selectionner un plan pour cette action");
        alert.showAndWait();   
        afficherPlan();
                afficherCombo();}else{
       String image = TFimage.getText();
        int pays_id = 1;
        
        String sujet = TFsujet.getText();
        String description = TFdescription.getText();
        if (DPdate.getValue()==null){Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Date non valide");
        alert.setHeaderText(null);
        alert.setContentText("Il faut choisir une date");
        alert.showAndWait();}else{
        LocalDate d = DPdate.getValue();
        LocalDate today = java.time.LocalDate.now();
        int v= d.compareTo(today);
        Instant instant = d.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date date= Date.from(instant);
        if (CBagence.getValue()==null) {Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Agence non valide");
        alert.setHeaderText(null);
        alert.setContentText("Il faut choisir une agence");
        alert.showAndWait();}
        else if (!Test.isAlpha(sujet)) {Test.msgAlpha("sujet");}
        else if (sujet.length()<5 || sujet.length()>20) {Test.msgLength("sujet", "5", "20");}
        else if (description.length()<10 || description.length()>50) {Test.msgLength("description", "10", "50");}
        else if (TFprix.getText().length()<1 || TFprix.getText().length()>20) {Test.msgLength("prix", "1", "20");}
       
        else if  (v<=0){
            Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Date incorrecte");
        alert.setHeaderText(null);
        alert.setContentText("Il faut que la date soit supérieur à la date d'aujourd'hui");
        alert.showAndWait();
        }else{
            int agence_id = CBagence.getValue().getId();
        Double prix = Double.parseDouble(TFprix.getText());
        A.setPays_id(pays_id);
        A.setAgence_id(agence_id);
        A.setImage(image);
        A.setSujet(sujet);
        A.setDescription(description);
        A.setDate(date);
        A.setPrix(prix);
        PlanServices aS = new PlanServices();
        if (aS.updatePlan(A)){
            
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
           new TextToSpeech("Plan has been updated");
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Succées");
        alert.setHeaderText(null);
        alert.setContentText("La modification d'agence a été effectué avec succées");
        alert.showAndWait();
        afficherPlan();
                afficherCombo();

        }else{
            String title = "Plan";
            String msg ="Plan n'a pas été modifié";
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
        afficherPlan();
                afficherCombo();

        }
    }}}}

    @FXML
    private void supprimerPlan(ActionEvent event) {
        Plan A= tabPlan.getSelectionModel().getSelectedItem();
        if (A==null){Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Il faut selectionner un plan pour cette action");
        alert.showAndWait();   
        afficherPlan();
                afficherCombo();}else{
            Alert mm = new Alert(AlertType.WARNING, 
                        "Voulez-vous vraiment supprimer ce plan?", 
                        ButtonType.YES, ButtonType.NO);

Optional<ButtonType> result = mm.showAndWait();
if (result.get() == ButtonType.YES){
        PlanServices aS = new PlanServices();
        if (aS.deletePlan(A)){
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
           new TextToSpeech("Plan has been deleted");
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Succées");
        alert.setHeaderText(null);
        alert.setContentText("La suppression d'agence a été effectué avec succées");
        alert.showAndWait();
        afficherPlan();
                afficherCombo();

        }else{
            String title = "Plan";
            String msg ="Plan n'a pas été supprimé";
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
        afficherPlan();
                afficherCombo();

        }
    }else{afficherPlan();
                afficherCombo();}}}

    @FXML
    private void selectPlan(MouseEvent event) {
        Plan A= tabPlan.getSelectionModel().getSelectedItem();
        
        TFimage.setText(A.getImage());
        TFsujet.setText(A.getSujet());
        TFdescription.setText(A.getDescription());
        TFprix.setText(String.valueOf(A.getPrix()));
        //Date d = A.getDate();
        //LocalDate date = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        //DPdate.setValue(date);
    }

    @FXML
    private void rechercherAgence(MouseEvent event) {
        String key = TFrecherche.getText();
        PlanServices aS = new PlanServices();
        ObservableList<Plan> liste = aS.searchPlan(key);
        colId.setCellValueFactory(new PropertyValueFactory<Plan, Integer>("id"));
        colPays.setCellValueFactory(new PropertyValueFactory<Plan, Integer>("pays_id"));
        colAgence.setCellValueFactory(new PropertyValueFactory<Plan , Integer>("agence_id"));
        colImage.setCellValueFactory(new PropertyValueFactory<Plan, String>("image"));
        colSujet.setCellValueFactory(new PropertyValueFactory<Plan, String>("sujet"));
        colDescription.setCellValueFactory(new PropertyValueFactory<Plan, String>("description"));
        colDate.setCellValueFactory(new PropertyValueFactory<Plan, Date>("date"));
        colPrix.setCellValueFactory(new PropertyValueFactory<Plan, Double>("prix"));
        tabPlan.setItems(liste);
    }

    @FXML
    private void goToAgence(MouseEvent event) {
         Stage stage = (Stage) Pupdate.getScene().getWindow();
        AgenceView v=new AgenceView();
        Stage primaryStage=new Stage();
        v.start(primaryStage);
       stage.close();
     
        
    
        
    }

    @FXML
    private void goToPlan(MouseEvent event) {
    }

    @FXML
    private void PDF(ActionEvent event) throws DocumentException {
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
            table.addCell("Pays");
            table.addCell("Agence");
            table.addCell("Sujet");
            table.addCell("Description");
            table.addCell("Date");
                        table.addCell("Prix");

           

           PlanServices s= new PlanServices();
            for( int i=0 ; i < s.rowPlan();i++)
            {
           
                String ID =tabPlan.getColumns().get(0).getCellObservableValue(i).getValue().toString();
                 String Pays =tabPlan.getColumns().get(1).getCellObservableValue(i).getValue().toString();
                  String Agence =tabPlan.getColumns().get(2).getCellObservableValue(i).getValue().toString();
                   String Sujet =tabPlan.getColumns().get(4).getCellObservableValue(i).getValue().toString();
                    String Description =tabPlan.getColumns().get(5).getCellObservableValue(i).getValue().toString();
                     String Date =tabPlan.getColumns().get(6).getCellObservableValue(i).getValue().toString();
                      String Prix =tabPlan.getColumns().get(7).getCellObservableValue(i).getValue().toString();
                                       
             table.addCell(ID);
            table.addCell(Pays);
            table.addCell(Agence);
            table.addCell(Sujet);
            table.addCell(Description);
            table.addCell(Date);
            table.addCell(Prix);
            
            }
            
            doc.add(table);
            
            
            
        } catch (FileNotFoundException ex) {
            
        } catch (DocumentException ex) {
            
        }
        
        doc.close();
    }

    @FXML
    private void reclamerPlan(ActionEvent event) {
                Plan A= tabPlan.getSelectionModel().getSelectedItem();
                if (A==null){Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Il faut selectionner un plan pour cette action");
        alert.showAndWait();   
        afficherPlan();
                afficherCombo();}else{
                PlanServices aS = new PlanServices();
                int id=A.getId();
                int rec = aS.showRec(id);
                
                if (rec==2){
                   
                   aS.setToZero(A.getId());
                   afficherPlan();
                }else {
                    rec=rec+1;
                    
                    if(aS.reclamation(A.getId(),rec)){
                        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Succées");
        alert.setHeaderText(null);
        alert.setContentText("La reclamation a été envoyé avec succées");
        alert.showAndWait();
        afficherPlan();
                afficherCombo();
                    }else{
                        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("La reclamation n'a pas été envoyé!");
        alert.showAndWait();   
        afficherPlan();
                afficherCombo();
                    }

                }

    }}

    @FXML
    private void browse(ActionEvent event) throws IOException {
        
        filechooser = new FileChooser();
       filechooser.getExtensionFilters().addAll(
       new ExtensionFilter("jpg files (.jpg)",".png",".PNG",".jpg","*.JPG"));
       file=filechooser.showOpenDialog(null);
       if(file !=null)
       {
           TFimage.setText(file.getName());
                Path src = Paths.get(file.getAbsolutePath());
                Path dst = Paths.get("C:/Users/21628/Desktop/final web/koko444/pi/public/images/logos/"+file.getName()+"");
                Files.copy(src, dst, StandardCopyOption.REPLACE_EXISTING); 
                 
    }

    
    
}
}
