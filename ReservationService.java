/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hebergement.Services;

import Base.MysqlConnect;
import Hebergement.Entity.Chambre;
import Hebergement.Entity.Reservation;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Chaima
 */
public class ReservationService {
    
    
    public ObservableList<Reservation> showReserv(){
        Connection cnx =null;
        Statement st = null;
        ResultSet rs = null;
        ObservableList<Reservation> liste = FXCollections.observableArrayList();
        String requette = "SELECT * FROM chambre_res";
        
        try {
            cnx = MysqlConnect.getInstance().getConnection();
            st = cnx.createStatement();
            rs = st.executeQuery(requette);
            Reservation reservations;
            while (rs.next()){
                Chambre ch = new Chambre();
                ch.setId(rs.getInt("chambre_id"));
                reservations = new Reservation(rs.getInt("id"),rs.getInt("user_id"),ch,rs.getDate("date_deb"),rs.getDate("date_fin"));
               liste.add(reservations);
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
    
    public void sendmail(){
        final String username ="sokalie1000@gmail.com";
        final String password ="faglvebmnbpjbabh";
        String from = "sokalie1000@gmail.com";
        String to = "chaima.chakroun@esprit.tn";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        Session session = Session.getInstance(properties, new javax.mail.Authenticator(){
        
           protected PasswordAuthentication getPasswordAuthentication(){
               return new PasswordAuthentication(username,password);
           }
        });
        MimeMessage msg =new MimeMessage(session);
        try{
            msg.setFrom(from);
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject("Réservation effectuee");
            Multipart con = new MimeMultipart();
            MimeBodyPart text =new MimeBodyPart();
            text.setText("votre réservation a été effectuée");
            
            con.addBodyPart(text);
            
            msg.setContent(con);
            
            Transport.send(msg);
        }catch(MessagingException e){}
    }
    
    public boolean Reserver(Reservation r)
    {
        Connection cnx =null;
        Statement st = null;
        LocalDate date = r.getDate_deb().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate datefin = r.getDate_fin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String requette = "INSERT INTO chambre_res (chambre_id,date_deb,date_fin,user_id) VALUES ("+r.getChambre().getId()+",DATE('"+date+"'),DATE('"+datefin+"'),"+r.getUser_id()+")";
        
        try {
            cnx = MysqlConnect.getInstance().getConnection();
            st = cnx.createStatement();
            st.executeUpdate(requette);
            System.out.println("reservation effectuer");
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
    
    
    public int getChambreId(String type,int idHotel)
    {
        Connection cnx =null;
        Statement st = null;
        ResultSet rs = null;
        ObservableList<Chambre> liste = FXCollections.observableArrayList();
        String requette = "SELECT * FROM Chambre Where type='"+type+"' and hotel_id="+idHotel+"";
        try {
            cnx = MysqlConnect.getInstance().getConnection();
            st = cnx.createStatement();
            rs = st.executeQuery(requette);
            Chambre ch;
            while (rs.next()){
               ch = new Chambre(rs.getInt("id"),rs.getInt("hotel_id"),rs.getString("type"),rs.getString("vue"),rs.getString("duree"),rs.getDouble("prix"),rs.getString("image"));
               liste.add(ch);
               System.out.println("get chambre effectuer");
               int dur= Integer.parseInt(ch.getDuree());
               dur--;
               ch.setDuree(Integer.toString(dur));
               String req="UPDATE chambre SET duree='"+ch.getDuree()+"' WHERE id="+ch.getId()+"";
               st.executeUpdate(req);
               System.out.println("update effectuer");
               return ch.getId();
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
      return -1;
    }
    
    
    public boolean ModifierReservation(Reservation r)
    {
        Connection cnx =null;
        Statement st = null;
        String requette = "UPDATE  chambre_res SET chambre_id="+r.getChambre().getId()+",date_deb="+r.getDate_deb()+",date_fin="+r.getDate_fin()+",user_id="+r.getUser_id()+" WHERE id="+r.getId()+" ";
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
}
