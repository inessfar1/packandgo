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
import com.user.test.entities.Utilisateur;
import com.user.test.services.StaticValue;


/**
 *
 * @author ASUS
 */
public class HomeUserForm extends Form {
Form current ; 
    public HomeUserForm() {
        current=this ; 
         //System.out.println(StaticValue.utilisateur.toString());
         Utilisateur ul = StaticValue.utilisateur ; 
        setTitle("Home") ; 
        setLayout(BoxLayout.y()) ; 
        
        add(new Label("Choose an option?")) ; 
        //Button btnAddUtil = new Button("add util") ; 
        Button profile = new Button("profil") ; 
        Button dashboard = new Button("dashboard") ; 
        //Button btnListUtil = new Button("List util") ; 
        Button btnListReclamation = new Button("Reclamer") ; 
        //btnAddUtil.addActionListener(e-> new AddUtilForm(current).show()) ; 
        //btnListUtil.addActionListener(e->new ListUtilForm(current).show());
        btnListReclamation.addActionListener(e->new ReclamationAdd(current).show());
        //dashboard.addActionListener(e->new HomeForm().show());
        profile.addActionListener(e->new profile(current).show());
        addAll(btnListReclamation,profile) ;
        
       
            
    }
    }
    
    

