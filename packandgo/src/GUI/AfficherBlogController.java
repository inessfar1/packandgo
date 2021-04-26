/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;




import connection.Datasource;
import entity.Blog;
import services.ServiceBlog;

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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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




/**
 * FXML Controller class
 *
 * @author asus
 */
public class AfficherBlogController implements Initializable {

    @FXML
    private VBox actualitecontainer;

    private Connection con;
    @FXML
    private TextField search;
    int n=1;
    @FXML
    private AnchorPane pane;
    @FXML
    private VBox sousMenuTaches;
    @FXML
    private VBox sousMenuActivites;
    @FXML
    private Text labelNom;
    @FXML
    private VBox menu;
    @FXML
    private Button trier;
    @FXML
    private ScrollPane scroll;

    public AfficherBlogController() {
        con = Datasource.getInstance().getCnx();
    }
    private Statement ste;
    private PreparedStatement pre;
    String s1 = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
              
       
            actualitecontainer.setSpacing(5);
            try {
                displayActualite();
            } catch (SQLException ex) {
                Logger.getLogger(AfficherBlogController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
             
            
        
    }

    private void displayActualite() throws SQLException {
        ServiceBlog pa = new ServiceBlog();
        String req = "select * from blog  ";
        List<VBox> list = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery(req);
        while (rs.next()) {
          

            Blog a1 = new Blog(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            ImageView va = new ImageView(new Image("http://127.0.0.1/image/"+rs.getString(4)));
            va.setFitHeight(170);
            va.setFitWidth(200);
            Label nom = new Label("Titre : " + a1.getTitle());
            Label contenu = new Label("Le contenu : " + a1.getDescription());

            HBox h1 = new HBox();
            h1.setSpacing(10);
            h1.setAlignment(Pos.CENTER);
            h1.getChildren().addAll(nom);
            HBox h2 = new HBox();
            h2.setSpacing(10);
            h2.setAlignment(Pos.CENTER);
            h2.getChildren().addAll(contenu);
           
            Button bt2=new Button("imprimer" ) ;
            

            VBox v = new VBox();
            v.setAlignment(Pos.CENTER);
            v.setSpacing(10);
            v.getChildren().addAll(h1, h2,bt2);

            VBox vv = new VBox();
            vv.setAlignment(Pos.CENTER);
            vv.setSpacing(10);
            vv.getChildren().addAll(va);
            HBox No = new HBox();
            No.setSpacing(10);
            No.setAlignment(Pos.CENTER);
            No.getChildren().addAll(vv, v);

            VBox v1 = new VBox();
            v1.setAlignment(Pos.CENTER);
            v1.setSpacing(10);
            v1.getChildren().addAll(No);
            list.add(v1);

        }
        actualitecontainer.getChildren().addAll(list);
    }
    
       private void displayActualiteAvancee(String req) throws SQLException {
        ServiceBlog pa = new ServiceBlog();
       
        List<VBox> list = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery(req);
        while (rs.next()) {
           
            Blog a1 = new Blog(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            ImageView va = new ImageView(new Image("http://127.0.0.1/image/"+rs.getString(4)));
            va.setFitHeight(170);
            va.setFitWidth(200);
            Label nom = new Label("Titre : " + a1.getTitle());
            Label contenu = new Label("Le contenu : " + a1.getDescription());

            HBox h1 = new HBox();
            h1.setSpacing(10);
            h1.setAlignment(Pos.CENTER);
            h1.getChildren().addAll(nom);
            HBox h2 = new HBox();
            h2.setSpacing(10);
            h2.setAlignment(Pos.CENTER);
            h2.getChildren().addAll(contenu);
           
            Button bt2=new Button("imprimer" ) ;
           
            VBox v = new VBox();
            v.setAlignment(Pos.CENTER);
            v.setSpacing(10);
            v.getChildren().addAll(h1, h2,bt2);

            VBox vv = new VBox();
            vv.setAlignment(Pos.CENTER);
            vv.setSpacing(10);
            vv.getChildren().addAll(va);
            HBox No = new HBox();
            No.setSpacing(10);
            No.setAlignment(Pos.CENTER);
            No.getChildren().addAll(vv, v);

            VBox v1 = new VBox();
            v1.setAlignment(Pos.CENTER);
            v1.setSpacing(10);
            v1.getChildren().addAll(No);
            list.add(v1);

        }
        actualitecontainer.getChildren().addAll(list);
    }


    @FXML
    private void recherche(ActionEvent event) throws SQLException {
      actualitecontainer.getChildren().removeAll(actualitecontainer.getChildren());
      String search11 = search.getText();
      String req ="select * from blog where title = '"+search11+"' or description = '"+search11+"'";
        displayActualiteAvancee(req);
        if(search11.equals("")){
             req ="select * from blog";
        displayActualiteAvancee(req);
            
        }
      
        
       
        
    }

   

    @FXML
    private void trierrd(ActionEvent event) throws SQLException {
         actualitecontainer.getChildren().removeAll(actualitecontainer.getChildren());
        String req = "select * from blog ORDER BY title  ";
        displayActualiteAvancee(req);
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
