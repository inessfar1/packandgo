/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hebergement.View;

import Hebergement.Entitiy.Hotel;
import Hebergement.Services.ServiceHotel;
import com.codename1.components.InfiniteProgress;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import com.codename1.ui.CheckBox;
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
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author 21628
 */
public class addHotelForm extends Form {

    public addHotelForm(Form previous) {
        setTitle("Add a new agence");
        setLayout(BoxLayout.y());
        
              Button logos = new Button ("Image");

        //TextField tfimage = new TextField("","image");
        TextField tfNom= new TextField("", "Nom");
        TextField tfetoile = new TextField("","Etoile");
        TextField tfAdresse= new TextField("", "Adresse");
        TextField tfnum = new TextField("","num");
        Button btnValider = new Button("Ajouter");
        
        
        
        
        
        
        
        CheckBox multiSelect =new CheckBox("Multi-select");
        
        Hotel t = new Hotel();
       
        
        
        
        
        
logos.addActionListener((ActionEvent e) -> {
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
                            logo = Image.createImage(file).scaledHeight(500);
                            System.out.println(file);
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
                                t.setImage(namePic);
                            } catch (IOException ex) {
                            }

                            revalidate();

                        
                    }
                    }
                        });
            }
                });
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)||(tfetoile.getText().length()==0)||(tfAdresse.getText().length()==0)||(tfnum.getText().length()==0))
                    Dialog.show("Alert", "Remplir les champs", new Command("OK"));
                else
                {
                    InfiniteProgress ip = new InfiniteProgress();
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    t.setNom(tfNom.getText());
                    t.setEtoile(tfetoile.getText());
                    t.setAdresse(tfAdresse.getText());
                    t.setNum(tfnum.getText());
                    if(ServiceHotel.getInstance().addHotel(t)){
                        iDialog.dispose();
                        Dialog.show("Success","Connection accepted",new Command("OK"));
                        previous.showBack();}
                    else{
                        iDialog.dispose();
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                }
                
                
            }
        });
        
        addAll(logos,tfNom,tfetoile,tfAdresse,tfnum,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
    }
    
        protected String saveFileToDevice(String hi, String ext) throws IOException {
        try {
            URI uri;
            
            uri = new URI(hi);
            String path = uri.getPath();
            int index = hi.lastIndexOf("/");
            hi = hi.substring(index + 1);
            return hi;
            
           
        } catch (URISyntaxException ex) {
             return "hh";
        }
    }
    
    }


