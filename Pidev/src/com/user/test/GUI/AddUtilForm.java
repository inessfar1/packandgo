/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user.test.GUI;



import com.codename1.components.SpanLabel;
import com.codename1.ext.filechooser.FileChooser;

import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import com.user.test.entities.Utilisateur;
import com.user.test.services.ServiceUtil;
import com.codename1.ui.CheckBox;
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
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Vector;

/**
 *
 * @author ASUS
 */
public class AddUtilForm extends Form {

    public AddUtilForm(Form previous) {
         Vector<String> vectorRole; 
        vectorRole = new Vector() ; 
        vectorRole.add("ROLE_USER") ; 
        vectorRole.add("ROLE_ADMIN") ;
    ComboBox<String>tfroles = new ComboBox<>(vectorRole) ; 
        setTitle("Add a new Utilisateur") ;
        setLayout(BoxLayout.y()) ; 
        TextField tfname = new TextField("","nom") ; 
        TextField tfprename = new TextField("","prenom") ;
        //TextField tfimage = new TextField("","image") ;
        TextField tfemail = new TextField("","email",20,TextField.EMAILADDR) ;
        TextField tfusername = new TextField("","username") ;
        TextField tfpassword = new TextField("","password",20,TextField.PASSWORD) ;
        TextField tfverif = new TextField("","Verifier password",20,TextField.PASSWORD) ;
        Resources res ; 
        Button img = new Button("chooseFile") ; 
        CheckBox multiSelect = new CheckBox("Multi-select");
        //TextField tfactivation = new TextField("","activation_token") ;
        //TextField tfreset = new TextField("","reset_token") ;
        Utilisateur u = new Utilisateur(); 
        Button btnValid = new Button("add User") ; 
        
        
        
        
        
       img.addActionListener((ActionEvent e) -> {
            if (FileChooser.isAvailable()) {
                FileChooser.setOpenFilesInPlace(true);
                FileChooser.showOpenDialog(multiSelect.isSelected(), ".jpg, .jpeg, .png/plain", (ActionEvent e2) -> {
                    if (e2 == null || e2.getSource() == null) {
                        add("No file was selected");
                        revalidate();
                        return;
                    }
                    if (multiSelect.isSelected()) {
                        String[] paths = (String[]) e2.getSource();
                        for (String path : paths) {
                            System.out.println(path);
                            CN.execute(path);
                        }
                        return;
                    }

                    String file = (String) e2.getSource();
                    if (file == null) {
                        add("No file was selected");
                        revalidate();
                    } else {
                        Image logo;

                        try {
                            logo = Image.createImage(file).scaledHeight(500);;
                            add(logo);
                            String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "photo.png";

                            try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
                                System.out.println(imageFile);
                                ImageIO.getImageIO().save(logo, os, ImageIO.FORMAT_PNG, 1);
                            } catch (IOException err) {
                            }
                        } catch (IOException ex) {
                        }

                        String extension = null;
                        if (file.lastIndexOf(".") > 0) {
                            extension = file.substring(file.lastIndexOf(".") + 1);
                            StringBuilder hi = new StringBuilder(file);
                            if (file.startsWith("file://")) {
                                hi.delete(0, 7);
                            }
                            int lastIndexPeriod = hi.toString().lastIndexOf(".");
                            Log.p(hi.toString());
                            String ext = hi.toString().substring(lastIndexPeriod);
                            String hmore = hi.toString().substring(0, lastIndexPeriod - 1);
                            try {
                                String namePic = saveFileToDevice(file, ext);
                                System.out.println(namePic);
                                u.setImage(namePic);
                            } catch (IOException ex) {
                            }

                            revalidate();

                        
                    }
                    }
                        });
            }
                });
        
        
        
        
        
        
        
        
        
        btnValid.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                
              
               
               if(tfname.getText().equals("")&& tfprename.getText().equals("")&& tfusername.getText().equals("")&& tfpassword.getText().equals(""))
                   Dialog.show("Erreur", "remplir les champs", "ok",null) ; 
               if (!tfpassword.getText().equals(tfverif.getText()))
                   Dialog.show("Erreur", "verifer password", "ok",null) ; 
               u.setNom(tfname.getText());
               u.setPrenom(tfprename.getText());
               
               u.setEmail(tfemail.getText());
               u.setRoles(tfroles.getSelectedItem().toString());
               u.setUsername(tfusername.getText());
               u.setPassword(tfpassword.getText());
               
               if(new ServiceUtil().addUtil(u))
                   Dialog.show("Success", "Connection accepted", new Command("ok")) ; 
               else 
                   Dialog.show("ERROR", "server error", new Command("ok")) ; 
            }
        
        });
        

        addAll(tfname,tfprename,tfemail,tfusername,tfpassword,tfverif,tfroles,img,btnValid) ; 
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,e->previous.showBack()) ; 
    }
    
     
    protected String saveFileToDevice(String hi, String ext) throws IOException {
        URI uri;
        try {
            uri = new URI(hi);
            String path = uri.getPath();
            int index = hi.lastIndexOf("/");
            hi = hi.substring(index + 1);
            return hi;
        } catch (URISyntaxException ex) {
        }
        return "hh";
    }
    
}
