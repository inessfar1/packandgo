/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.Services;

import java.sql.Connection;
import Base.MysqlConnect;
import Attraction.Entity.Plan;
import java.io.Closeable;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class PlanServices {
    
    
    public ObservableList<Plan> showPlan(){
        Connection cnx =null;
        Statement st = null;
        ResultSet rs = null;
        ObservableList<Plan> liste = FXCollections.observableArrayList();
        String requette = "SELECT * FROM plan";
        
        try {
            cnx = MysqlConnect.getInstance().getConnection();
            st = cnx.createStatement();
            rs = st.executeQuery(requette);
            Plan plans;
            while (rs.next()){
              
               plans = new Plan(rs.getInt("id"),rs.getInt("pays_id"), rs.getInt("agence_id"), rs.getString("image"),rs.getString("sujet"), rs.getString("description"),rs.getDate("date"), rs.getDouble("prix"));
               liste.add(plans);
            }
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
  
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { /* Ignored */}
    }
    }
        return liste;
        
}
    
    public boolean addPlan(Plan a){
        Connection cnx =null;
        Statement st = null;
        Date d = a.getDate();
        LocalDate date = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String requette = "INSERT INTO Plan (pays_id,agence_id,image,sujet,description,date,prix) VALUES ("+a.getPays_id()+","+a.getAgence_id()+",'"+a.getImage()+"','"+a.getSujet()+"','"+a.getDescription()+"',"+"DATE('"+date+"'),"+a.getPrix()+")";
        try {
            cnx = MysqlConnect.getInstance().getConnection();
            st = cnx.createStatement();
            st.executeUpdate(requette);
            return true;
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }finally {
    
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { /* Ignored */}
    }
    }
        
    }
    
    public boolean deletePlan(Plan a){
        Connection cnx =null;
        Statement st = null;
        
        String requette = "DELETE FROM plan WHERE id="+a.getId()+"";
        try {
            cnx = MysqlConnect.getInstance().getConnection();
            st = cnx.createStatement();
            st.executeUpdate(requette);
            return true;
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }finally {
    
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { /* Ignored */}
    }
    }
        
    }
    
    public boolean updatePlan(Plan a){
        Connection cnx =null;
        Statement st = null;
        Date d = a.getDate();
        LocalDate date = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String requette = "UPDATE plan SET pays_id='"+a.getPays_id()+"',agence_id='"+a.getAgence_id()+"',image='"+a.getImage()+"',sujet='"+a.getSujet()+"',description='"+a.getDescription()+"',date=Date('"+date+"'),prix="+a.getPrix()+" WHERE id="+a.getId()+"";
        try {
            cnx = MysqlConnect.getInstance().getConnection();
            st = cnx.createStatement();
            st.executeUpdate(requette);
            return true;
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }finally {
    
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { /* Ignored */}
    }
    }}
    
       public ObservableList<Plan> searchPlan(String key){
        Connection cnx =null;
        Statement st = null;
        ResultSet rs = null;
        ObservableList<Plan> liste = FXCollections.observableArrayList();
        String requette = "SELECT * FROM plan where sujet like '%"+key+"%'";
        
        try {
            cnx = MysqlConnect.getInstance().getConnection();
            st = cnx.createStatement();
            rs = st.executeQuery(requette);
            Plan plans;
            while (rs.next()){
              
               plans = new Plan(rs.getInt("id"),rs.getInt("pays_id"), rs.getInt("agence_id"), rs.getString("image"), rs.getString("sujet"), rs.getString("description"),rs.getDate("date"), rs.getDouble("prix"));
               liste.add(plans);
            }
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException e) { /* Ignored */}
    }
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { /* Ignored */}
    }
    }
        return liste;
        
}
       
       
       
       public int rowPlan(){
        Connection cnx =null;
        Statement st = null;
        ResultSet rs = null;
        ObservableList<Plan> liste = FXCollections.observableArrayList();
        String requette = "SELECT * FROM plan";
        int i=0;
        
        try {
            cnx = MysqlConnect.getInstance().getConnection();
            st = cnx.createStatement();
            rs = st.executeQuery(requette);
            Plan chambres;
            while (rs.next()){
               i=i+1;
            }
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException e) { /* Ignored */}
    }
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { /* Ignored */}
    }
    }
        return i;
        
}
       
       
       public int showRec(int id){
        
          Connection cnx =null;
        Statement st = null;
        ResultSet rs = null;
        ObservableList<Plan> liste = FXCollections.observableArrayList();
        String requette = "SELECT rec FROM plan WHERE id="+id+"";
        int rec =0;
        try {
            cnx = MysqlConnect.getInstance().getConnection();
            st = cnx.createStatement();
            rs = st.executeQuery(requette);
            Plan plans;
            while (rs.next()){
              
               rec =rs.getInt("rec");
             
            }
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }finally {
            
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException e) { /* Ignored */}
    }
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { /* Ignored */}
    }
    }
   return rec;
}
       
       
       
       
       
       
       public boolean reclamation(int id,int rec){
          Connection cnx =null;
        Statement st = null;
       
        String requette = "UPDATE plan SET rec="+rec+" WHERE id="+id+"";
        try {
            cnx = MysqlConnect.getInstance().getConnection();
            st = cnx.createStatement();
            st.executeUpdate(requette);
            return true;
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }finally {
    
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { /* Ignored */}
    }
    } 
       }
       
       
     public ObservableList<Plan> showPlanF(){
        Connection cnx =null;
        Statement st = null;
        ResultSet rs = null;
        ObservableList<Plan> liste = FXCollections.observableArrayList();
        String requette = "SELECT * FROM plan";
        
        try {
            cnx = MysqlConnect.getInstance().getConnection();
            st = cnx.createStatement();
            rs = st.executeQuery(requette);
            Plan plans;
            while (rs.next()){
              String img =rs.getString("image");
              if (rs.getInt("nbr")>0){
               plans = new Plan(rs.getInt("id"),rs.getInt("pays_id"), rs.getInt("agence_id"), img, rs.getString("sujet"), rs.getString("description"),rs.getDate("date"), rs.getDouble("prix"),rs.getInt("rec"),rs.getInt("nbr"));
               liste.add(plans);}
            }
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
    
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { /* Ignored */}
    }
    }
        return liste;
        
}
     
     public void setToZero(int id){
            Connection cnx =null;
        Statement st = null;
       
        String requette = "UPDATE plan SET nbr=0 WHERE id="+id+"";
        try {
            cnx = MysqlConnect.getInstance().getConnection();
            st = cnx.createStatement();
            st.executeUpdate(requette);
            
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }finally {
    
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { /* Ignored */}
    }
    } 
     }
     
   
}
