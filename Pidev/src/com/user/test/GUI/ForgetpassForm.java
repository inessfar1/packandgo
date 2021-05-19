/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user.test.GUI;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.user.test.services.ServiceUtil;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



/**
 *
 * @author ASUS
 */
public class ForgetpassForm extends Form {
    TextField email ; 
    public ForgetpassForm() {
        TextField email = new TextField("","saisir votre email") ; 
        email.setSingleLineTextArea(false);
        
        Button valider = new Button("valider") ; 
        Label haveanaccount = new Label("se connecter?") ; 
        Button signin = new Button("renouveler votre mot de passe ") ; 
        signin.addActionListener((e)->{
        new SigninForm().show();
        
        });
        //signin.setUIID("CenterLink");
        
        valider.requestFocus();
        valider.addActionListener((e)->{
        InfiniteProgress ip = new InfiniteProgress() ; 
        final Dialog ipDialog = ip.showInfiniteBlocking(); 
        
        //api Mailing
        sendmail() ; 
        ipDialog.dispose();
        Dialog.show("Mot de passe", "votre mot de passe est envoyee a votre email", new Command("ok")) ; 
        new SigninForm().show();
        
        });
        addAll(email,valider,haveanaccount) ; 
    }
        
        public void sendmail(){
            try{
                
                    String to = email.getText() ; 
                    String subject="reseting code" ; 
                    String message = ServiceUtil.getIstance().getPasswordByEmail(to); 
                    String username ="sokalie1000@gmail.com";
                    final String password ="faglvebmnbpjbabh";
                    String from = "sokalie1000@gmail.com";    
                
                
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
                    msg.setFrom(from);
                    msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                    msg.setSubject(subject);
                    msg.setText(message);
                    Transport.send(msg);
                    
                    System.out.println("message envoyer");
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
            
        }
}
    
    
    

