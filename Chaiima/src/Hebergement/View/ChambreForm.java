/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hebergement.View;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author 21628
 */
public class ChambreForm extends Form{

     Form current  ; 
    public ChambreForm(Form previous){
        current=this ; 
         
        setTitle("Home") ; 
        setLayout(BoxLayout.y()) ; 
        
        add(new Label("Choose an option?")) ; 
        Button btnAddUtil = new Button("List des chambre") ; 
        
        Button btnListUtil = new Button("Ajouter un Chambre") ; 
        btnAddUtil.addActionListener(e-> new ShowChambreForm(current).show()) ; 
        btnListUtil.addActionListener(e->new AddChambreForm(current).show());
        
        addAll(btnAddUtil,btnListUtil) ; 
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());   
    }
}
