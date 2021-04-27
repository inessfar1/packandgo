
package Attraction.Services;

import java.sql.Connection;
import Base.MysqlConnect;
import Attraction.Entity.Agence;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class AgenceServices {
    public boolean addAgence(Agence a){
        Connection cnx =null;
        Statement st = null;
        String requette = "INSERT INTO agence (pays_id,logo,nom,adresse,email,tel) VALUES ("+a.getPays_id()+",'"+a.getLogo()+"','"+a.getNom()+"','"+a.getAdresse()+"','"+a.getEmail()+"',"+a.getTel()+")";
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
    public ObservableList<Agence> showAgence(){
        Connection cnx =null;
        Statement st = null;
        ResultSet rs = null;
        ObservableList<Agence> liste = FXCollections.observableArrayList();
        String requette = "SELECT * FROM agence";
        
        try {
            cnx = MysqlConnect.getInstance().getConnection();
            st = cnx.createStatement();
            rs = st.executeQuery(requette);
            Agence agences;
            while (rs.next()){
               agences = new Agence(rs.getInt("id"), rs.getString("logo"), rs.getString("nom"), rs.getString("adresse"), rs.getString("email"), rs.getInt("tel"));
               liste.add(agences);
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

    public boolean updateAgence(Agence a){
        Connection cnx =null;
        Statement st = null;
        String requette = "UPDATE agence SET logo='"+a.getLogo()+"',nom='"+a.getNom()+"',adresse='"+a.getAdresse()+"',email='"+a.getEmail()+"',tel="+a.getTel()+" WHERE id="+a.getId()+"";
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
        
        public boolean deleteAgence(Agence a){
        Connection cnx =null;
        Statement st = null;
        String requette = "DELETE FROM agence WHERE id="+a.getId()+"";
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
    public ObservableList<Agence> searchAgence(String key){
        Connection cnx =null;
        Statement st = null;
        ResultSet rs = null;
        ObservableList<Agence> liste = FXCollections.observableArrayList();
        String requette = "SELECT * FROM agence where nom like '%"+key+"%'";
        
        try {
            cnx = MysqlConnect.getInstance().getConnection();
            st = cnx.createStatement();
            rs = st.executeQuery(requette);
            Agence agences;
            while (rs.next()){
               agences = new Agence(rs.getInt("id"), rs.getString("logo"), rs.getString("nom"), rs.getString("adresse"), rs.getString("email"), rs.getInt("tel"));
               liste.add(agences);
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

    
    }


