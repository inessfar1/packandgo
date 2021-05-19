/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.View.Agence;


import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;

public class PlanForm extends Form {

    Form current;
    /*Garder traçe de la Form en cours pour la passer en paramètres 
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/
    
    public PlanForm(Form previous) {
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Plan Home");
        setLayout(BoxLayout.y());
           try {
                                Image ban = Image.createImage("file://C:\\Users\\21628\\Desktop\\Mobile\\Final\\src\\Attraction\\Images\\banner.jpg").scaledHeight(1000);
                                add(ban);
                            } catch (IOException ex) {

                            }

        add(new Label("Choose an option"));
        Button btnAddPlan = new Button("Ajouter Plan");
        Button btnListPlans = new Button("Afficher Plan");

        btnAddPlan.addActionListener(e -> new AddPlanForm(current).show());
        btnListPlans.addActionListener(e -> new ListPlansForm(current).show());
        addAll(btnAddPlan, btnListPlans);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());    
    }

    

}
