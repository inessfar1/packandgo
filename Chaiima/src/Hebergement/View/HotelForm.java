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
public class HotelForm extends Form{

     Form current  ; 
    public HotelForm(Form previous){
        current=this ; 
         
        setTitle("Home") ; 
        setLayout(BoxLayout.y()) ; 
        
        add(new Label("Choose an option?")) ; 
        Button btnAddUtil = new Button("List des hotel") ; 
        
        Button btnListUtil = new Button("Ajouter un Hotel") ; 
        btnAddUtil.addActionListener(e-> new ShowHotelForm(current).show()) ; 
        btnListUtil.addActionListener(e->new addHotelForm(current).show());
        
        addAll(btnAddUtil,btnListUtil) ; 
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());   
    }
    
    
}
