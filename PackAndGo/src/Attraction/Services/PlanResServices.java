/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.Services;
import java.sql.Connection;
import Base.MysqlConnect;
import Attraction.Entity.Plan;
import Attraction.Entity.Plan_res;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.File;
import java.io.IOException;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
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
 * @author 21628
 */
public class PlanResServices {
      public boolean Reserver(Plan_res r)
    {
        Connection cnx =null;
        Statement st = null;
        String requette = "INSERT INTO plan_res (plan_id,user_id) VALUES ("+r.getPlan_id()+","+r.getUtilisateur_id()+")";
        
        try {
            cnx = MysqlConnect.getInstance().getConnection();
            st = cnx.createStatement();
            st.executeUpdate(requette);
            System.out.println("reservation effectuée");
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
    
    
    public void getPlanId(int id)
    {
        Connection cnx =null;
        Statement st = null;
        ResultSet rs = null;
        ObservableList<Plan> liste = FXCollections.observableArrayList();
        String requette = "SELECT * FROM plan Where id="+id+"";
        try {
            cnx = MysqlConnect.getInstance().getConnection();
            st = cnx.createStatement();
            rs = st.executeQuery(requette);
            int a;
            while (rs.next()){
              
               a=rs.getInt("nbr")-1;
               String req="UPDATE plan SET nbr='"+a+"' WHERE id="+id+"";
               st.executeUpdate(req);
               System.out.println("update effectuée");
               
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
      
    }
    
    
    public void QR (Plan A, int id){
        
         Connection cnx =null;
        Statement st = null;
        ResultSet rs = null;
        String requette = "SELECT * FROM utilisateur Where id="+id+"";
        try {
            cnx = MysqlConnect.getInstance().getConnection();
            st = cnx.createStatement();
            rs = st.executeQuery(requette);
            int a;
            while (rs.next()){
              
             try {
            String qrCodeData = "Plan "+A.getSujet()+"Client "+rs.getString("username")+"";
            String filePath = "C:\\Users\\21628\\Desktop\\PackAndGo\\src\\images\\"+A.getSujet()+rs.getString("username")+".png";
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                new String(qrCodeData.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code image created successfully!");
        } catch (Exception e) {
            System.err.println(e);
        }
               
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
    }
    
    
 public void sendRes(Plan A, int id) throws IOException {
      
         Connection cnx =null;
        Statement st = null;
        ResultSet rs = null;
        String requette = "SELECT * FROM utilisateur Where id="+id+"";
        try {
            cnx = MysqlConnect.getInstance().getConnection();
            st = cnx.createStatement();
            rs = st.executeQuery(requette);
            int a;
            while (rs.next()){
              
           
                
                
                
                final String username ="sokalie1000@gmail.com";
        final String password ="faglvebmnbpjbabh";
        String from = "sokalie1000@gmail.com";
        String to = rs.getString("email");

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
            msg.setSubject("Réservation Plan "+A.getSujet()+"");
            Multipart con = new MimeMultipart();
            MimeBodyPart text =new MimeBodyPart();
            text.setText("Présentez ce QRcode le jour du plan");
            MimeBodyPart img = new MimeBodyPart();
            img.attachFile("C:/Users/21628/Desktop/PackAndGo/src/images/"+A.getSujet()+rs.getString("username")+".png");
            con.addBodyPart(text);
            con.addBodyPart(img);
            msg.setContent(con);
            
            Transport.send(msg);
        }catch(MessagingException e){}
                
                
                
                
                
                
                
                
                
                
                
                
                
               
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
     
 } 
 
 
 
 
 public void sendNBR (String p){
    Connection cnx =null;
        Statement st = null;
        ResultSet rs = null;
       
        String requette = "SELECT * FROM utilisateur";
        try {
            cnx = MysqlConnect.getInstance().getConnection();
            st = cnx.createStatement();
            rs = st.executeQuery(requette);
            int a;
            while (rs.next()){
              if(rs.getString("roles").equals("ROLE_ADMIN")){
                  
                  final String username ="sokalie1000@gmail.com";
        final String password ="faglvebmnbpjbabh";
        String from = "sokalie1000@gmail.com";
        String to = rs.getString("email");

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
            msg.setSubject("Nombre de place limitée");
            msg.setText("Il reste que deux places disponible dans le plan "+p+".");
            Transport.send(msg);
        }catch(MessagingException e){}
                  
              }
               
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
 }
 
 
 
 
 
 
 
 
 
 
 


public void verifNBR(Plan A) {
     Connection cnx =null;
        Statement st = null;
        ResultSet rs = null;
        
        String requette = "SELECT * FROM plan Where id="+A.getId()+"";
        try {
            cnx = MysqlConnect.getInstance().getConnection();
            st = cnx.createStatement();
            rs = st.executeQuery(requette);
            int a;
            while (rs.next()){
              if (rs.getInt("nbr")==2) {
                  sendNBR(A.getSujet());
              }
             
               
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
}





}
