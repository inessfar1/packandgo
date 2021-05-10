/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.View.Agence;

import Attraction.Entity.Agence;
import Attraction.Services.ServiceAgence;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author 21628
 */
public class ListAgencesForm extends Form{
    Form current;
    public ListAgencesForm(Form previous) {
        current = this;
        setTitle("List agences");
        SpanLabel sp = new SpanLabel();
        sp.setText(ServiceAgence.getInstance().getAllAgences().toString());
        add(sp);
        ComboBox<Agence> cb= new ComboBox();
        ServiceAgence AGG = new ServiceAgence();
        ArrayList<Agence> list = AGG.getAllAgences();
        for(int i = 0 ; i < list.size(); i++){
            cb.addItem(list.get(i));
        }
        add(cb);
        Button btnModifier = new Button("Modifier");
        Button btnSupprimer = new Button("Supprimer");
        btnSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (cb.getSelectedItem()==null)
                    Dialog.show("Alert", "Selectionner une agence pour supprimer", new Command("OK"));
                else
                {
                   if(ServiceAgence.getInstance().delAgence(cb.getSelectedItem())){
                        Dialog.show("Success","Agence a ete supprimee",new Command("OK"));
                        previous.showBack();}
                    else{
                        Dialog.show("ERROR", "Erreur", new Command("OK"));
                        }
                }
                
                
            }
        });
        
        btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (cb.getSelectedItem()==null)
                    Dialog.show("Alert", "Selectionner une agence pour modifier", new Command("OK"));
                else
                {
                    Agence a =  cb.getSelectedItem();
                    EditAgenceForm t = new EditAgenceForm(current,a);
                   t.show();
                }
                
                
            }
        });
       
        addAll(btnModifier,btnSupprimer);
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
}
