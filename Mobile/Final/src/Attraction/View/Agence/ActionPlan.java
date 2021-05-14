/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.View.Agence;
import Attraction.Entity.Plan;
import Attraction.Services.ServicePlan;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * @author 21628
 */
public class ActionPlan extends Form{
	Form current;
    public ActionPlan(Form previous) {
        current=this;
		ComboBox<Plan> cb= new ComboBox();
        ServicePlan AGG = new ServicePlan();
        ArrayList<Plan> list = AGG.getAllPlans();
        for(int i = 0 ; i < list.size(); i++){
            cb.addItem(list.get(i));
        }
        add(cb);
		Button btnReclamer = new Button ("Reclamer");
        Button btnReserver = new Button ("Reserver");
		 btnReclamer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (cb.getSelectedItem()==null)
                    Dialog.show("Alert", "Selectionner un plan à réclamer", new Command("OK"));
                else
                {
                    if(ServicePlan.getInstance().recPlan(cb.getSelectedItem())){
                        Dialog.show("Success","Plan a été réclamé",new Command("OK"));
                        previous.showBack();}
                    else{
                        Dialog.show("ERROR", "Erreur", new Command("OK"));
                        }
                }
                
                
            }
        });
       
        btnReserver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (cb.getSelectedItem()==null)
                    Dialog.show("Alert", "Selectionner un plan à réserver", new Command("OK"));
                else
                {   
                    int user= 6;
                    if(ServicePlan.getInstance().resPlan(cb.getSelectedItem(),user)){
                       
                        Dialog.show("Success","Votre réservation a été effectué",new Command("OK"));
                        previous.showBack();}
                    else{
                        Dialog.show("ERROR", "Erreur", new Command("OK"));
                        }
                }
                
                
            }
        });
       
        addAll(btnReclamer,btnReserver);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
}}
