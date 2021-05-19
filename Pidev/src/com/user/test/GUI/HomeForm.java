/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user.test.GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.user.test.entities.Reclamation;
import com.user.test.entities.Utilisateur;
import com.user.test.services.StaticValue;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class HomeForm extends Form {
    Form current  ; 
    
    public HomeForm(){
       
        current=this ; 
         //System.out.println(StaticValue.utilisateur.toString());
        setTitle("Home") ; 
        setLayout(BoxLayout.y()) ; 
        
        add(new Label("Choose an option?")) ; 
        Button btnAddUtil = new Button("add util") ; 
        Button profile = new Button("profil") ; 
        Button btnListUtil = new Button("List util") ; 
        Button btnListReclamation = new Button("List reclamation") ; 
        btnAddUtil.addActionListener(e-> new AddUtilForm(current).show()) ; 
        btnListUtil.addActionListener(e->new ListUtilForm(current).show());
        btnListReclamation.addActionListener(e->new ReclamationAdminForm(current).show());
        profile.addActionListener(e->new profile(current).show());
        addAll(btnAddUtil,btnListUtil,btnListReclamation,profile) ;
       
            
    }
}
