/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import connection.Datasource;
import entity.Blog;
import entity.Categorie;
import entity.ForumSession;
import entity.likecategorie;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;
import services.ServiceBlog;
import services.ServiceCategorie;
import services.ServiceLikeCategorie;

/**
 * FXML Controller class
 *
 * @author hazem
 */
public class AfficherCategorieController implements Initializable {
    @FXML
    private VBox actualitecontainer;
    @FXML
    private TextField contenu;
    @FXML
    private TextField titre;
     private Connection con;
    @FXML
    private AnchorPane pane;
    @FXML
    private ChoiceBox combo;
    @FXML
    private VBox sousMenuTaches;
    @FXML
    private VBox sousMenuActivites;
    @FXML
    private TextField search1;
    @FXML
    private Text labelNom;
    @FXML
    private Button trier1;
    @FXML
    private VBox menu;
    @FXML
    private VBox menuLogOut;
 public AfficherCategorieController() {
        con = Datasource.getInstance().getCnx();
    }
    private Statement ste;
    private PreparedStatement pre;
    String s1 = "";
     String s;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> list = FXCollections.observableArrayList("A1","A2","A3");
        combo.setItems(list);
         actualitecontainer.setSpacing(5);
            try {
                displayActualite();
            } catch (SQLException ex) {
                Logger.getLogger(AfficherBlogController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }    
 private void displayActualite() throws SQLException {
        ServiceCategorie pa = new ServiceCategorie();
         ServiceLikeCategorie oo=new ServiceLikeCategorie();
                     java.util.Date dd = new java.util.Date();

        String req = "select * from categorie  ";
        List<VBox> list = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery(req);
        while (rs.next()) {
          

            Categorie a1 = new Categorie(rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getString(4),rs.getString(5));
     
            Label nom = new Label("Titre : " + a1.getTitle());
            Label contenu = new Label("Le contenu : " + a1.getDescription());
             Label categorie = new Label("La categorie : " + a1.getCategorie());
 HBox h1 = new HBox();
            h1.setSpacing(10);
            h1.setAlignment(Pos.CENTER);
            h1.getChildren().addAll(nom);
            HBox h2 = new HBox();
            h2.setSpacing(10);
            h2.setAlignment(Pos.CENTER);
            h2.getChildren().addAll(contenu);
            HBox h3 = new HBox();
            h3.setSpacing(10);
            h3.setAlignment(Pos.CENTER);
            h3.getChildren().addAll(categorie);
            Button bt2=new Button("commenter" ) ;
                 bt2.setOnAction(new EventHandler<ActionEvent>() {
                 @Override
                 public void handle(ActionEvent event) {
      
                      AnchorPane page;
                     try {
                          ForumSession.getInstace(a1.getId()); 
                         page = FXMLLoader.load(getClass().getResource("ForumEtCommentaire.fxml"));
                         pane.getChildren().setAll(page); 
                           
                            System.out.println(a1.getId());
                     } catch (IOException ex) {
                         Logger.getLogger(AfficherCategorieController.class.getName()).log(Level.SEVERE, null, ex);
                     }
        
                 }
             });
                  Button bt3=new Button("like" ) ;
            
        if (oo.chercher_ajout(new likecategorie(a1.getId(),2,dd)))
                         {
                   bt3.setDisable(true);
              }
             bt3.setOnAction(new EventHandler<ActionEvent>() {
                 @Override
                 public void handle(ActionEvent event) { //bitha heki chas
                     
                        try {
            if (!oo.chercher_ajout(new likecategorie(a1.getId(),2,dd))){
                
                try {
                    oo.ajouter(new likecategorie(a1.getId(),2,dd));
                     
                    String SQL1 = "UPDATE packandgo.categorie SET  nb_like=nb_like+1 WHERE id ='"+a1.getId()+"'";
                               int rs1 = ste.executeUpdate(SQL1);
                               
                    
                } catch (SQLException ex) {
                    Logger.getLogger(AfficherCategorieController.class.getName()).log(Level.SEVERE, null, ex);
                }
                bt2.setDisable(true);
            }else System.out.println("forum DEJA AIMER");
        } catch (SQLException ex) {
            Logger.getLogger(AfficherCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        } 
                      
                 }
             });
           
            VBox v = new VBox();
            v.setAlignment(Pos.CENTER);
            v.setSpacing(10);
            v.getChildren().addAll(h1, h2,h3,bt2,bt3);

           
            HBox No = new HBox();
            No.setSpacing(10);
            No.setAlignment(Pos.CENTER);
            No.getChildren().addAll( v);

            VBox v1 = new VBox();
            v1.setAlignment(Pos.CENTER);
            v1.setSpacing(10);
            v1.getChildren().addAll(No);
            list.add(v1);

        }
        actualitecontainer.getChildren().addAll(list);
    }
    
       private void displayActualiteAvancee(String req) throws SQLException {
        ServiceCategorie pa = new ServiceCategorie();
        
         ServiceLikeCategorie oo=new ServiceLikeCategorie();
                     java.util.Date dd = new java.util.Date();
       
        List<VBox> list = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery(req);
        while (rs.next()) {
           
            Categorie a1 = new Categorie(rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getString(4),rs.getString(5));
      
            Label nom = new Label("Titre : " + a1.getTitle());
            Label contenu = new Label("Le contenu : " + a1.getDescription());
 Label categorie = new Label("La categorie : " + a1.getCategorie());
            HBox h1 = new HBox();
            h1.setSpacing(10);
            h1.setAlignment(Pos.CENTER);
            h1.getChildren().addAll(nom);
            HBox h2 = new HBox();
            h2.setSpacing(10);
            h2.setAlignment(Pos.CENTER);
            h2.getChildren().addAll(contenu);
            HBox h3 = new HBox();
            h3.setSpacing(10);
            h3.setAlignment(Pos.CENTER);
            h3.getChildren().addAll(categorie);
           Button bt2=new Button("commenter" ) ;
                 bt2.setOnAction(new EventHandler<ActionEvent>() {
                 @Override
                 public void handle(ActionEvent event) {
      
                      AnchorPane page;
                     try {
                          ForumSession.getInstace(a1.getId()); 
                         page = FXMLLoader.load(getClass().getResource("ForumEtCommentaire.fxml"));
                         pane.getChildren().setAll(page); 
                           
                            System.out.println(a1.getId());
                     } catch (IOException ex) {
                         Logger.getLogger(AfficherCategorieController.class.getName()).log(Level.SEVERE, null, ex);
                     }
        
                 }
             });
                  Button bt3=new Button("like" ) ;
            
        if (oo.chercher_ajout(new likecategorie(a1.getId(),2,dd)))
                         {
                   bt3.setDisable(true);
              }
             bt3.setOnAction(new EventHandler<ActionEvent>() {
                 @Override
                 public void handle(ActionEvent event) { //bitha heki chas
                     
                        try {
            if (!oo.chercher_ajout(new likecategorie(a1.getId(),2,dd))){
                
                try {
                    oo.ajouter(new likecategorie(a1.getId(),2,dd));
                     
                    String SQL1 = "UPDATE packandgo.categorie SET  nb_like=nb_like+1 WHERE id ='"+a1.getId()+"'";
                               int rs1 = ste.executeUpdate(SQL1);
                               
                    
                } catch (SQLException ex) {
                    Logger.getLogger(AfficherCategorieController.class.getName()).log(Level.SEVERE, null, ex);
                }
                bt2.setDisable(true);
            }else System.out.println("forum DEJA AIMER");
        } catch (SQLException ex) {
            Logger.getLogger(AfficherCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        } 
                      
                 }
             });
           
            VBox v = new VBox();
            v.setAlignment(Pos.CENTER);
            v.setSpacing(10);
            v.getChildren().addAll(h1, h2,h3,bt2,bt3);

          
            HBox No = new HBox();
            No.setSpacing(10);
            No.setAlignment(Pos.CENTER);
            No.getChildren().addAll( v);

            VBox v1 = new VBox();
            v1.setAlignment(Pos.CENTER);
            v1.setSpacing(10);
            v1.getChildren().addAll(No);
            list.add(v1);

        }
        actualitecontainer.getChildren().addAll(list);
    }




    @FXML
    private void ajouter(ActionEvent event) {
        
          try {
              if(titre.getText().isEmpty()   || contenu.getText().isEmpty() )
        
        {
           
             
         JOptionPane.showMessageDialog(null, "verifer les champs");   
        }else{
            String titre1 = titre.getText();
            String contenu1 = contenu.getText();
             s=combo.getSelectionModel().getSelectedItem().toString();
           
     
            
            ServiceCategorie sp = new ServiceCategorie();
            Categorie e = new Categorie(2,titre1,contenu1,s);
            
            sp.ajouter(e);
            JOptionPane.showMessageDialog(null, "ajout avec succes");
            titre.clear();
            contenu.clear();
            actualitecontainer.getChildren().clear();
            displayActualite();
              }
        } catch (SQLException ex) {
            Logger.getLogger(BlogController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void selct(MouseEvent event) {
         s= combo.getSelectionModel().getSelectedItem().toString();
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
    
}
