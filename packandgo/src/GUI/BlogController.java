/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Blog;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;
import services.ServiceBlog;

/**
 * FXML Controller class
 *
 * @author hazem
 */
public class BlogController implements Initializable {
    @FXML
    private TableView<Blog> table1;
    @FXML
    private TableColumn<Blog, String> tabletitre;
    @FXML
    private TableColumn<Blog, String> tablecontenu;
    @FXML
    private TableColumn<Blog, String> tableimage;
    @FXML
    private TextField titre;
    @FXML
    private Label erreurtitre;
    @FXML
    private TextField contenu;
    @FXML
    private Label erreurcontenu;
    @FXML
    private Button imagee;
    @FXML
    private ImageView importeimage;
    @FXML
    private Label erreurimg;
 String img="";
    List<String> type;

    private Blog cc=null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       afficher();
           type =new ArrayList();
        type.add("*.jpg");
         type.add("*.png");
         
          table1.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                cc = (Blog)table1.getSelectionModel().getSelectedItem();
                System.out.println(cc);
                titre.setText(cc.getTitle());
                contenu.setText(cc.getDescription());
                importeimage.setImage(new Image("http://127.0.0.1/image/"+cc.getImage()));
               
               
            }
          });
           titre.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\sa-zA-Z*")) {
            titre.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
        }
    });
           titre.textProperty().addListener(new ChangeListener<String>()
            {
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                   if(newValue.isEmpty())
                       erreurtitre.setText("remplir champ titre");
                   else if(newValue.length()>200)
                       erreurtitre.setText("Max champ titre 200");
                   else
                erreurtitre.setText("");
                }
                
                
            });
           titre.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event) {
                    if(titre.getText().length()==0)
                     erreurtitre.setText("remplir champ titre");    
                    
                }
                
            });
            contenu.textProperty().addListener(new ChangeListener<String>()
            {
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                   if(newValue.isEmpty())
                       erreurcontenu.setText("remplir champ contenu");
                   else if(newValue.length()>200)
                       erreurcontenu.setText("Max champ contenu 200");
                   else
                erreurcontenu.setText("");
                }
                
                
            });
           contenu.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event) {
                    if(contenu.getText().length()==0)
                     erreurcontenu.setText("remplir champ contenu");    
                    
                }
                
            });
            imagee.textProperty().addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                   if(newValue.isEmpty())
                       erreurimg.setText("remplir champ image");
                   
                   else
                erreurimg.setText("");
                }
                
                
            });
            imagee.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event) {
                    if(imagee.getText().length()==0)
                     erreurimg.setText("remplir champ image");    
                    
                }
                
            }); 
           
    }    
    
    @FXML
    private void importimage(ActionEvent event) {
        
        FileChooser f=new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpeg,png files", type));
        File fc=f.showOpenDialog(null);
        
        if(fc != null)
        {   
            System.out.println(fc.getName());
            img=fc.getName();
           FileSystem fileSys = FileSystems.getDefault();
           Path srcPath= fc.toPath();
           Path destPath= fileSys.getPath("C:\\wamp64\\www\\image\\"+fc.getName());
            try {
                Files.copy(srcPath, destPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                Logger.getLogger(BlogController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(srcPath.toString());
            Image i = new Image(fc.getAbsoluteFile().toURI().toString());
            importeimage.setImage(i);
            
        }
    }

    @FXML
    private void ajouter(ActionEvent event) {
         try {
              if(titre.getText().isEmpty() ||(img.isEmpty()&&cc.getImage().isEmpty())  || contenu.getText().isEmpty() )
        
        {
           
             
         JOptionPane.showMessageDialog(null, "verifer les champs");   
        }else{
            String titre1 = titre.getText();
            String contenu1 = contenu.getText();
           
     
            
            ServiceBlog sp = new ServiceBlog();
            Blog e = new Blog(titre1,contenu1,img);
            
            sp.ajouter(e);
            JOptionPane.showMessageDialog(null, "ajout avec succes");
            titre.clear();
            contenu.clear();
            importeimage.setImage(null);
            afficher();}
        } catch (SQLException ex) {
            Logger.getLogger(BlogController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
         ServiceBlog cs = new ServiceBlog();
     System.out.println(cc);
        if(cc== null){
            JOptionPane.showMessageDialog(null, "choisir blog");
                   
        }else{
              
                   
                
              try {
                 
                  
                  if(img.length()==0)
                      cs.update(new Blog(titre.getText(),contenu.getText(),img),cc.getId());
                  else
                      cs.update(new Blog(titre.getText(),contenu.getText(),img),cc.getId());
                  
                  afficher();
                  JOptionPane.showMessageDialog(null, "annonce modifier");
                  titre.clear();
                  contenu.clear();
                  importeimage.setImage(null);
                  
                  cc=null;
              } catch (SQLException ex) {
                  Logger.getLogger(BlogController.class.getName()).log(Level.SEVERE, null, ex);
              }
            } 
    }

    @FXML
    private void supprimer(ActionEvent event) {
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("suppression");
                            alert.setHeaderText("Voulez-vous vraiment supprimer ce blog");
                            Optional<ButtonType> option = alert.showAndWait();

                            if (option.get() == ButtonType.OK) {
        ServiceBlog cs = new ServiceBlog();
         Blog cc = (Blog)table1.getSelectionModel().getSelectedItem();
        System.out.println(cc);
        if(cc== null){
            JOptionPane.showMessageDialog(null, "choisir blog");
                   
        }else{
             try {
                 cs.delete(cc.getId());
                 
                 afficher();
                 
                 JOptionPane.showMessageDialog(null, "blog supprimer");
                 titre.clear();
                 contenu.clear();
                 importeimage.setImage(null);
                 
                 cc=null;
             } catch (SQLException ex) {
                 Logger.getLogger(BlogController.class.getName()).log(Level.SEVERE, null, ex);
             }
    }
                            }
    }
     private void afficher()
   {    try {
       ServiceBlog sp = new ServiceBlog();
       List events=sp.readAll();
       ObservableList et=FXCollections.observableArrayList(events);
       table1.setItems(et);
       
       tabletitre.setCellValueFactory(new PropertyValueFactory<>("title"));
       tablecontenu.setCellValueFactory(new PropertyValueFactory<>("description"));
       tableimage.setCellValueFactory(new PropertyValueFactory<>("photo"));
       
       
        } catch (SQLException ex) {
            Logger.getLogger(BlogController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   
   }

    
}
