/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hebergement.View;

import Hebergement.Entitiy.Chambre;
import Hebergement.Entitiy.Hotel;
import Hebergement.Services.ServiceChambre;
import Hebergement.Services.ServiceHotel;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
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
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 *
 * @author 21628
 */
public class AddChambreForm extends Form{

    public AddChambreForm(Form previous) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddChambre, on peut y revenir
        en utilisant le bouton back
        */
        Chambre t = new Chambre();
        setTitle("ajouter chambre ");
        setLayout(BoxLayout.y());
        Button logos = new Button("Image");
        TextField tfPrix= new TextField("","Prix");
                TextField tfDuree= new TextField("","Nombre de chambre");

                ComboBox<String> cbType= new ComboBox();
                cbType.addItem("Single");
                                cbType.addItem("Double");
                cbType.addItem("Triple");

        ComboBox<String> cbVue= new ComboBox();
cbVue.addItem("Vue de mer");
cbVue.addItem("Vue de terrasse");
cbVue.addItem("Vue de picine");


        ComboBox<Hotel> cb= new ComboBox();
        ServiceHotel AGG = new ServiceHotel();
        ArrayList<Hotel> list = AGG.getAllHotels();
           try {
                                Image ban = Image.createImage("file://C:\\Users\\21628\\Desktop\\Mobile\\Final\\src\\Attraction\\Images\\banner.jpg").scaledHeight(1000);
                                add(ban);
                            } catch (IOException ex) {

                            }
        for(int i = 0 ; i < list.size(); i++){
            cb.addItem(list.get(i));
        }
        Button btnValider = new Button("Ajouter");
        
        
        
          CheckBox multiSelect =new CheckBox("Multi-select");

        
        
        
        
        
        logos.addActionListener((ActionEvent e) -> {
           if (FileChooser.isAvailable()) {
                FileChooser.setOpenFilesInPlace(true);
                FileChooser.showOpenDialog(multiSelect.isSelected(), ".jpg, .jpeg, .png , .png/plain", (ActionEvent e2) -> {
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
                if ((tfPrix.getText().length()==0))
                    Dialog.show("Alert", "Remplir les champs", new Command("OK"));
                else
                {       
                        
                        
                        t.setVue(cbVue.getSelectedItem());
                                                t.setType(cbType.getSelectedItem());

                        t.setDuree(tfDuree.getText());
                        t.setPrix(Double.parseDouble(tfPrix.getText()));
                          t.setHotel_id(cb.getSelectedItem().getId());
                        if( ServiceChambre.getInstance().addChambre(t)){
                            Dialog.show("Success","chambre ajoutée",new Command("OK"));
                        previous.showBack();}
                        else
                            Dialog.show("ERROR", "Erreur", new Command("OK"));
                }
            }
        });
        
        addAll(logos,cbVue,cbType,tfPrix,tfDuree,cb,btnValider);
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
