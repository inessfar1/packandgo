/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.View.Agence;


import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

public class AgenceForm extends Form {

    Form current;
    /*Garder traçe de la Form en cours pour la passer en paramètres 
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/
    
    public AgenceForm() {
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Agence Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnAddAgence = new Button("Ajouter Agence");
        Button btnListAgences = new Button("Afficher Agence");

        btnAddAgence.addActionListener(e -> new AddAgenceForm(current).show());
        btnListAgences.addActionListener(e -> new ListAgencesForm(current).show());
        addAll(btnAddAgence, btnListAgences);

    }

    

}
