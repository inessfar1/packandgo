/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.View.Agence;

import Attraction.Entity.Agence;
import Attraction.Services.ServiceAgence;
import com.codename1.components.InfiniteProgress;
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
public class EditAgenceForm extends Form{

    public EditAgenceForm(Form previous,Agence t) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddAgence, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Modifier agence "+t.getNom()+"");
        setLayout(BoxLayout.y());
        
        
        
        
        
        TextField tfNom= new TextField(t.getNom());
        TextField tfEmail = new TextField(t.getEmail());
        TextField tfAdresse= new TextField(t.getAdresse());
        TextField tfTel = new TextField(t.getTel());
        Button btnValider = new Button("Modifier");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)||(tfEmail.getText().length()==0)||(tfAdresse.getText().length()==0)||(tfTel.getText().length()==0))
                    Dialog.show("Alert", "Remplir les champs", new Command("OK"));
                else
                {
                    InfiniteProgress ip = new InfiniteProgress();
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    int tel = Integer.parseInt(tfTel.getText());
                    t.setNom(tfNom.getText());
                    t.setAdresse(tfAdresse.getText());
                    t.setEmail(tfEmail.getText());
                    t.setTel(tel);
                    if(ServiceAgence.getInstance().editAgence(t)){
                        iDialog.dispose();
                        Dialog.show("Success","agence modifié",new Command("OK"));
                        previous.showBack();}
                    else{
                        iDialog.dispose();
                        Dialog.show("ERROR", "Erreur", new Command("OK"));
                        }
                }
                
                
            }
        });
        
        addAll(tfNom,tfEmail,tfAdresse,tfTel,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
    }
    
    
    
}
