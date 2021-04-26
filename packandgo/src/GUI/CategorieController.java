/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import connection.Datasource;
import entity.Categorie;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;
import services.ServiceCategorie;

/**
 * FXML Controller class
 *
 * @author hazem
 */
public class CategorieController implements Initializable {
    @FXML
    private AnchorPane ap;
    @FXML
    private TableView<Categorie> table1;
    @FXML
    private TableColumn<Categorie, String> tabletitre;
    @FXML
    private TableColumn<Categorie, String> tablecontenu;
    @FXML
    private TextField titre;
    @FXML
    private Label erreurtitre;
    @FXML
    private TextField contenu;
    @FXML
    private Label erreurcontenu;
    String s;
     String img="";
    List<String> type;

    private Categorie cc=null;
    @FXML
    private ChoiceBox combo;
    @FXML
    private TableColumn<Categorie, String> tablecategorie;
    @FXML
    private PieChart piechart;
    @FXML
    private Label caption;
   private Connection con;
    public CategorieController() {
       con = Datasource.getInstance().getCnx();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         // TODO
         try {
                  Statement stmt1 = con.createStatement();
        ObservableList<PieChart.Data>pieData = FXCollections.observableArrayList();
                              String SQL1 = "SELECT categorie.title, categorie.nb_like FROM categorie";
                               ResultSet rs1 = stmt1.executeQuery(SQL1);
                               while(rs1.next())
                                {
                                   pieData.add(new PieChart.Data("NOM categorie : "+rs1.getString(1)+"\n"+"nb like categorie : "+rs1.getString(2),rs1.getDouble(2)));
                                           
                                }
       
       piechart.setData(pieData);
                        
caption.setTextFill(Color.DARKORANGE);
caption.setStyle("-fx-font: 24 arial;");

for (final PieChart.Data data : piechart.getData()) {
    data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
        new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                int i = (int) data.getPieValue();
                caption.setText(String.valueOf("Nb_like : "+i));
             }
        });}
       afficher();
           type =new ArrayList();
        type.add("*.jpg");
         type.add("*.png");
         ObservableList<String> list = FXCollections.observableArrayList("A1","A2","A3");
        combo.setItems(list);
          table1.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                cc = (Categorie)table1.getSelectionModel().getSelectedItem();
                System.out.println(cc);
                titre.setText(cc.getTitle());
                contenu.setText(cc.getDescription());
               
               
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
           
             } catch (SQLException ex) {
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    

    @FXML
    private void ajouter(ActionEvent event) {
        try {
              if(titre.getText().isEmpty()  || contenu.getText().isEmpty() )
        
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
           
            afficher();}
        } catch (SQLException ex) {
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
         ServiceCategorie cs = new ServiceCategorie();
     System.out.println(cc);
        if(cc== null){
            JOptionPane.showMessageDialog(null, "choisir categorie");
                   
        }else{
              
                   
                
              try {
                 
                  
                  if(img.length()==0)
                      cs.update(new Categorie(2,titre.getText(),contenu.getText(),s),cc.getId());
                  else
                      cs.update(new Categorie(2,titre.getText(),contenu.getText(),s),cc.getId());
                  
                  afficher();
                  JOptionPane.showMessageDialog(null, "categorie modifier");
                  titre.clear();
                  contenu.clear();
                  
                  cc=null;
              } catch (SQLException ex) {
                  Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);
              }
            } 
    }

    @FXML
    private void supprimer(ActionEvent event) {
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("suppression");
                            alert.setHeaderText("Voulez-vous vraiment supprimer cette categorie");
                            Optional<ButtonType> option = alert.showAndWait();

                            if (option.get() == ButtonType.OK) {
        ServiceCategorie cs = new ServiceCategorie();
         Categorie cc = (Categorie)table1.getSelectionModel().getSelectedItem();
        System.out.println(cc);
        if(cc== null){
            JOptionPane.showMessageDialog(null, "choisir blog");
                   
        }else{
             try {
                 cs.delete(cc.getId());
                 
                 afficher();
                 
                 JOptionPane.showMessageDialog(null, "Categorie supprimer");
                 titre.clear();
                 contenu.clear();
                 
                 cc=null;
             } catch (SQLException ex) {
                 Logger.getLogger(BlogController.class.getName()).log(Level.SEVERE, null, ex);
             }
    }
                            }
    }
     private void afficher()
   {   
       try {
       ServiceCategorie sp = new ServiceCategorie();
       List events=sp.readAll();
       ObservableList et=FXCollections.observableArrayList(events);
       table1.setItems(et);
       
       tabletitre.setCellValueFactory(new PropertyValueFactory<>("title"));
       tablecontenu.setCellValueFactory(new PropertyValueFactory<>("description"));
       tablecategorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
       
       
        } catch (SQLException ex) {
            Logger.getLogger(BlogController.class.getName()).log(Level.SEVERE, null, ex);
        }
   }

    @FXML
    private void selct(MouseEvent event) {
        s= combo.getSelectionModel().getSelectedItem().toString();
    }
    
}
