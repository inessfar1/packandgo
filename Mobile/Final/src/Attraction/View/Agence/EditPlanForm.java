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
public class EditPlanForm extends Form{

    public EditPlanForm(Form previous,Plan t) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddPlan, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("modifier plan");
        setLayout(BoxLayout.y());
        
        TextField tfNom= new TextField("", "Sujet");
        TextField tfEmail = new TextField("","Description");
        TextField tfAdresse= new TextField("", "Prix");
        Button btnValider = new Button("Modifier");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)||(tfEmail.getText().length()==0)||(tfAdresse.getText().length()==0))
                    Dialog.show("Alert", "Remplir les champs", new Command("OK"));
                else
                {       
                        
                        
                        t.setSujet(tfNom.getText());
                        t.setDescription(tfEmail.getText());
                        t.setPrix(Double.parseDouble(tfAdresse.getText()));
                        if( ServicePlan.getInstance().editPlan(t)){
                            Dialog.show("Success","plan modifié",new Command("OK"));
                        previous.showBack();}
                        else
                            Dialog.show("ERROR", "Erreur", new Command("OK"));
                }
            }
        });
        
        addAll(tfNom,tfEmail,tfAdresse,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
    }
    
    
}
