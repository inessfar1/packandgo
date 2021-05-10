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
public class ListPlansForm extends Form{
Form current;
    public ListPlansForm(Form previous) {
        current=this;
        setTitle("List plans");
        
        SpanLabel sp = new SpanLabel();
        sp.setText(ServicePlan.getInstance().getAllPlans().toString());
        add(sp);
        ComboBox<Plan> cb= new ComboBox();
        ServicePlan AGG = new ServicePlan();
        ArrayList<Plan> list = AGG.getAllPlans();
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
                    Dialog.show("Alert", "Selectionner un plan pour modifier", new Command("OK"));
                else
                {
                   if(ServicePlan.getInstance().delPlan(cb.getSelectedItem())){
                        Dialog.show("Success","Plan a ete supprimer",new Command("OK"));
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
                    Dialog.show("Alert", "Selectionner un plan à modifier", new Command("OK"));
                else
                {
                    Plan a =  cb.getSelectedItem();
                    EditPlanForm t = new EditPlanForm(current,a);
                   t.show();
                }
                
                
            }
        });
       
        addAll(btnModifier,btnSupprimer);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
}
