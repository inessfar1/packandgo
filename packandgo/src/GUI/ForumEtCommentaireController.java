/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.github.sarxos.webcam.Webcam;
import connection.Datasource;
import entity.comment;
import entity.Categorie;
import entity.ForumSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import services.ServiceCommentaire;
import services.ServiceCategorie;

/**
 * FXML Controller class
 *
 * @author hazem
 */
public class ForumEtCommentaireController implements Initializable {
      String imgG = "";
    @FXML
    private ScrollPane scroll;
    @FXML
    private VBox actualitecontainer;
    @FXML
    private ScrollPane scroll1;
    @FXML
    private VBox actualitecontainer1;
    @FXML
    private Button retourner;
    @FXML
    private TextField contenu;
   private Connection con;
    private AnchorPane pane;
    @FXML
    private ImageView imageview;
    @FXML
    private Button cam;
 public ForumEtCommentaireController() {
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
       // TODO
         actualitecontainer.setSpacing(5);
            try {
                displayActualite();
                displayCommentaire();
            } catch (SQLException ex) {
                Logger.getLogger(AfficherBlogController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }  
    
    public Categorie get() throws SQLException
{

      ForumSession n = ForumSession.getInstance();
      System.out.println(n.getId());
      Categorie cc=new Categorie();
                              int s1 = n.getId();
                               Statement stmt1 = con.createStatement();
                              String SQL1 = "SELECT * FROM categorie  WHERE id ='"+s1+"'";
                               ResultSet rs1 = stmt1.executeQuery(SQL1);
                               while(rs1.next())
                                {
                                    cc.setId(rs1.getInt(1));
                                   
                                   
                                           
                                }
        return cc;
                              
    
}
private void displayActualite() throws SQLException {
        ServiceCategorie pa = new ServiceCategorie();
        Categorie f=get();
        System.out.println(f);
        String req = "select * from categorie  WHERE id ='" +f.getId()+"'";
        
        List<VBox> list = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery(req);
        while (rs.next()) {
          

            Categorie a1 = new Categorie(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5));
     
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
          
           
           
            VBox v = new VBox();
            v.setAlignment(Pos.CENTER);
            v.setSpacing(10);
            v.getChildren().addAll(h1,h2);
           
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
    
       private void displayCommentaire() throws SQLException {
        ServiceCommentaire pa = new ServiceCommentaire();
       Categorie f=get();
        System.out.println(f);
        String req = "select * from comment  WHERE categorie_id ='" +f.getId()+"'";
        List<VBox> list = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery(req);
        while (rs.next()) {
           
            comment a1 = new comment(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getDate(5));
      
            Label nom = new Label("contenu : " + a1.getText());
             Label date = new Label("date de creation : " + a1.getDate());
             
             
             
              Button bt2=new Button("supprimmer" ) ;
                 bt2.setOnAction(new EventHandler<ActionEvent>() {
                 @Override
                 public void handle(ActionEvent event) {
                      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("suppression");
                            alert.setHeaderText("Voulez-vous vraiment supprimer ce commentaire ?");
                            Optional<ButtonType> option = alert.showAndWait();

                            if (option.get() == ButtonType.OK) {
                     try {
                         System.out.println(a1);
                         String SQL1 = "delete from `packandgo`.`comment` where id  = '"+a1.getId()+"'";
                         
                               boolean rs1 = ste.execute(SQL1);
                        actualitecontainer1.getChildren().clear();
                         displayCommentaire();
                     } catch (SQLException ex) {
                         Logger.getLogger(ForumEtCommentaireController.class.getName()).log(Level.SEVERE, null, ex);
                     }
          }
                 }
             });
                      Button bt3=new Button("modifier" ) ;
                 bt3.setOnAction(new EventHandler<ActionEvent>() {
                 @Override
                 public void handle(ActionEvent event) {
      
                    
                     try {
                         System.out.println(a1);
                         contenu.setText(a1.getText());
                         String SQL1 = "delete from `packandgo`.`comment` where id  = '"+a1.getId()+"'";
                         
                               boolean rs1 = ste.execute(SQL1);
                  
                     } catch (SQLException ex) {
                         Logger.getLogger(ForumEtCommentaireController.class.getName()).log(Level.SEVERE, null, ex);
                     }
        
                 }
             });
            HBox h1 = new HBox();
            h1.setSpacing(10);
            h1.setAlignment(Pos.CENTER);
            h1.getChildren().addAll(nom);
            HBox h2 = new HBox();
            h2.setSpacing(10);
            h2.setAlignment(Pos.CENTER);
            h2.getChildren().addAll(date);
           
           
           
            VBox v = new VBox();
            v.setAlignment(Pos.CENTER);
            v.setSpacing(10);
            v.getChildren().addAll(h1,h2,bt2,bt3);

          
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
        actualitecontainer1.getChildren().addAll(list);
    }




    @FXML
    private void ajouter(ActionEvent event) {
      
          try {
              if(contenu.getText().isEmpty() )
        
        {
           
             
         JOptionPane.showMessageDialog(null, "verifer les champs");   
        }else{
             
            String contenu1 = contenu.getText();
      
                 java.util.Date date = new java.util.Date();
java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
             Categorie f=get();
        System.out.println(f);     
            ServiceCommentaire sp = new ServiceCommentaire();
            if(imgG=="")
            {
            comment e = new comment(f.getId(),2,contenu1,sqlDate);
                sp.ajouter(e);
            }
            else 
            {
                comment e = new comment(f.getId(),2,contenu1,sqlDate,imgG);
                 sp.ajouter(e);
            }
       
          
           
            contenu.clear();
            
            actualitecontainer1.getChildren().clear();
                  displayCommentaire();
              }
        } catch (SQLException ex) {
            Logger.getLogger(ForumEtCommentaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void retournerf(ActionEvent event) throws IOException, SQLException {
        AnchorPane page;
           page = FXMLLoader.load(getClass().getResource("AfficherCategorie.fxml"));
                         pane.getChildren().setAll(page); 
                      ForumSession.setInstance(null);
    }
    
      @FXML
    private void webcam(ActionEvent event) {
        Webcam webcam = Webcam.getDefault();
        webcam.open();
        Random rand = new Random();
        int alea = rand.nextInt(200000 - 10 + 1) + 200000;
        // get image
        BufferedImage image = webcam.getImage();

        try {
            // save image to PNG file

            File f = new File("C:\\Users\\anest\\Desktop\\packandgo\\src\\GUI\\images\\" + alea + ".png");
         
            imgG =alea+".png";
            
            ImageIO.write(image, "PNG", f);

            webcam.close();
                imageview.setImage(new javafx.scene.image.Image(f.toURI().toString()));

        } catch (IOException ex) {
            Logger.getLogger(ForumEtCommentaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
