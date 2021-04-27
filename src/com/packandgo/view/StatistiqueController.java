/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packandgo.view;

import com.packandgo.utils.ConnexionSingleton;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author hazem
 */
public class StatistiqueController implements Initializable {
    @FXML
    private Label caption;
    @FXML
    private PieChart piechart;
     private Connection con;
    public StatistiqueController() {
       con = ConnexionSingleton.getInstance().getCnx();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Statement stmt1;
        try {
            stmt1 = con.createStatement();
        
        ObservableList<PieChart.Data>pieData = FXCollections.observableArrayList();
                              String SQL1 = "SELECT trajet.pt_depart, trajet.nb_like FROM trajet";
                               ResultSet rs1 = stmt1.executeQuery(SQL1);
                               while(rs1.next())
                                {
                                   pieData.add(new PieChart.Data("point depart  trajet : "+rs1.getString(1)+"\n"+"nb like trajet : "+rs1.getString(2),rs1.getDouble(2)));
                                           
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
} catch (SQLException ex) {
            Logger.getLogger(StatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
