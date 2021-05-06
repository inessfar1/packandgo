/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.View.Agence;

import Attraction.Entity.Agence;
import Attraction.Services.ServiceAgence;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author 21628
 */
public class AddAgenceForm extends Form{

    public AddAgenceForm(Form previous) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddAgence, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Add a new agence");
        setLayout(BoxLayout.y());
        
        TextField tfLogo = new TextField("","Logo");
        TextField tfNom= new TextField("", "Nom");
        TextField tfEmail = new TextField("","Email");
        TextField tfAdresse= new TextField("", "Adresse");
        TextField tfTel = new TextField("","Tel");
        Button btnValider = new Button("Ajouter");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfLogo.getText().length()==0)||(tfNom.getText().length()==0)||(tfEmail.getText().length()==0)||(tfAdresse.getText().length()==0)||(tfTel.getText().length()==0))
                    Dialog.show("Alert", "Remplir les champs", new Command("OK"));
                else
                {
                   
                        int tel = Integer.parseInt(tfTel.getText());
                        Agence t = new Agence(tfLogo.getText(), tfNom.getText(), tfEmail.getText(), tfAdresse.getText(),tel);
                        if( ServiceAgence.getInstance().addAgence(t))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    
                         
                        
                        
                    
                }
                
                
            }
        });
        
        addAll(tfLogo,tfNom,tfEmail,tfAdresse,tfTel,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
    }
    
    
}
