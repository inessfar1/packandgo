/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user.test.GUI;

import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.user.test.entities.Utilisateur;
import com.user.test.services.ServiceUtil;
import java.io.IOException;
import java.util.Vector;

/**
 *
 * @author ASUS
 */
public class EditUtiForml extends Form {

     public EditUtiForml(Form previous,Utilisateur u) {
         try {
                                Image imagee = Image.createImage("file://C:\\Users\\ASUS\\Desktop\\images\\update.jpg").scaledHeight(500);
                                add(imagee);
                            } catch (IOException ex) {

                            }
         Vector<String> vectorRole; 
        vectorRole = new Vector() ; 
        vectorRole.add("ROLE_USER") ; 
        vectorRole.add("ROLE_ADMIN") ;
    ComboBox<String>tfroles = new ComboBox<>(vectorRole) ; 
        setTitle("Modifier l Utilisateur"+" "+u.getNom()) ;
        setLayout(BoxLayout.y()) ; 
        TextField tfname = new TextField("","nom") ; 
        TextField tfprename = new TextField("","prenom") ;
        //TextField tfimage = new TextField("","image") ;
        TextField tfemail = new TextField("","email",20,TextField.EMAILADDR) ;
        TextField tfusername = new TextField("","username") ;
        //TextField tfpassword = new TextField("","password",20,TextField.PASSWORD) ;
        //TextField tfverif = new TextField("","Verifier password",20,TextField.PASSWORD) ;
        Resources res ; 
        //TextField tfactivation = new TextField("","activation_token") ;
        //TextField tfreset = new TextField("","reset_token") ;
        
        Button btnValid = new Button("Edit User") ; 
        
        btnValid.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
              
               if(tfname.getText().equals("")&& tfprename.getText().equals("")&& tfusername.getText().equals(""))
                   Dialog.show("Erreur", "remplir les champs", "ok",null) ; 
               
               u.setNom(tfname.getText());
               u.setPrenom(tfprename.getText());
               //u.setImage(tfimage.getText()); 
               u.setEmail(tfemail.getText());
               u.setRoles(tfroles.getSelectedItem().toString());
               u.setUsername(tfusername.getText());
               //u.setPassword(tfpassword.getText());
               
               if(new ServiceUtil().editUtilisateur(u)){
                   Dialog.show("Success", "Modification accepted", new Command("ok")) ; 
                   previous.showBack();
               }
               else 
                   Dialog.show("ERROR", "server error", new Command("ok")) ; 
            }
        
        });
        addAll(tfname,tfprename,tfemail,tfusername,tfroles,btnValid) ; 
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,e->previous.showBack()) ; 
    }
    
     
    
}
