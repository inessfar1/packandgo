/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.View.Agence;

import Attraction.Entity.Agence;
import Attraction.Entity.Plan;
import Attraction.Services.ServiceAgence;
import Attraction.Services.ServicePlan;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;

/**
 *
 * @author 21628
 */
public class AddPlanForm extends Form{

    public AddPlanForm(Form previous) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddPlan, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Add a new plan");
        setLayout(BoxLayout.y());
        ComboBox<Agence> cb= new ComboBox();
        ServiceAgence AGG = new ServiceAgence();
        ArrayList<Agence> list = AGG.getAllAgences();
        for(int i = 0 ; i < list.size(); i++){
            cb.addItem(list.get(i));
        }
        
        TextField tfLogo = new TextField("","Image");
        TextField tfNom= new TextField("", "Sujet");
        TextField tfEmail = new TextField("","Description");
        TextField tfAdresse= new TextField("", "Prix");
        Button btnValider = new Button("Ajouter");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfLogo.getText().length()==0)||(tfNom.getText().length()==0)||(tfEmail.getText().length()==0)||(tfAdresse.getText().length()==0))
                    Dialog.show("Alert", "Remplir les champs", new Command("OK"));
                else
                {
                   
                      
                        Plan t = new Plan();
                        t.setImage(tfLogo.getText());
                        t.setSujet(tfNom.getText());
                        t.setDescription(tfEmail.getText());
                        t.setPrix(Double.parseDouble(tfAdresse.getText()));
                        t.setAgence_id(cb.getSelectedItem().getId());
                        
                        if( ServicePlan.getInstance().addPlan(t))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    
                         
                        
                        
                    
                }
                
                
            }
        });
        
        addAll(tfLogo,cb,tfNom,tfEmail,tfAdresse,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
    }
    
    
}
