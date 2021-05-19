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
import com.codename1.ui.util.Resources;
import com.user.test.entities.Reclamation;
import com.user.test.entities.Utilisateur;
import com.user.test.services.ServiceReclamation;
import java.io.IOException;
import java.util.Vector;

/**
 *
 * @author ASUS
 */
public class ReclamationAdd extends Form {

    public ReclamationAdd(Form previous) {
        setTitle("Add a new Reclamation") ;
        Vector<String> vectorRole; 
        vectorRole = new Vector() ; 
        vectorRole.add("Hotel") ; 
        vectorRole.add("Plant") ;
        vectorRole.add("Chambres") ;
    ComboBox<String>tfobject = new ComboBox<>(vectorRole) ; 
        TextField tfdescription = new TextField("","description") ; 
        try {
                                Image imagee = Image.createImage("file://C:\\Users\\ASUS\\Desktop\\images\\rec.jpg").scaledHeight(800);
                                add(imagee);
                            } catch (IOException ex) {

                            }
        Resources res ; 
        //TextField tfactivation = new TextField("","activation_token") ;
        //TextField tfreset = new TextField("","reset_token") ;
        
        Button btnValid = new Button("add Reclamation") ; 
        btnValid.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                
                Utilisateur u = new Utilisateur() ; 
               Reclamation r = new Reclamation(); 
               if(tfdescription.getText().equals(""))
                   Dialog.show("Erreur", "remplir les champs", "ok",null) ; 
               
               r.setObject(tfobject.getSelectedItem().toString());
               r.setDescription(tfdescription.getText());
               r.setStatut("Encours");
               u.setId(18);
              
               
               if(new ServiceReclamation().addRec(r,u))
                   Dialog.show("Success", "Connection accepted", new Command("ok")) ; 
               else 
                   Dialog.show("ERROR", "server error", new Command("ok")) ; 
            }
        
        });
        addAll(tfobject,tfdescription,btnValid) ; 
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,e->previous.showBack()) ; 
    }
    }
    

