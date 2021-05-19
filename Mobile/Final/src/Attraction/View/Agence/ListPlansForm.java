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
import com.codename1.ui.Image;
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
public class ListPlansForm extends Form{
Form current;
    public ListPlansForm(Form previous) {
        current = this;
      ServicePlan sp = new ServicePlan();
      
      add(new InfiniteProgress());
                Display.getInstance().scheduleBackgroundTask(()-> {
                    
                    Display.getInstance().callSerially(() -> {
                        removeAll();
                           try {
                                Image ban = Image.createImage("file://C:\\Users\\21628\\Desktop\\Mobile\\Final\\src\\Attraction\\Images\\banner.jpg").scaledHeight(1000);
                                add(ban);
                            } catch (IOException ex) {

                            }
                        List<Plan> listerec = sp.getAllPlans();
                        for(Plan p : listerec)
                        {
                            try {
                                Image imagee = Image.createImage("file://C:\\Users\\21628\\Desktop\\final web\\koko444\\pi\\public\\images\\logos\\"+p.getImage()+"").scaledHeight(350);
                                add(imagee);
                            } catch (IOException ex) {

                            }
                            MultiButton m = new MultiButton();
                            m.setTextLine1("Sujet: "+p.getSujet());
                            m.setTextLine2("Prix: "+p.getPrix());
                            m.setTextLine3("Date: "+p.getDate());
                            m.setTextLine4("Description : "+p.getDescription());
                            add(m);
                            
                             m.addPointerReleasedListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evt) {
                                        if (Dialog.show("Confirmation", "Que voulez vous faire ?", "Supprimer", "Modifier")) {
                                            
                                                if( ServicePlan.getInstance().delPlan(p)){
                                                    {
                                                       Dialog.show("Success","Le plan "+p.getSujet()+" a été supprimé avec succées",new Command("OK"));
                                                       previous.showBack();
                                                    }
                                        }
                                    }
                                        else{ 
                                               
                                                 EditPlanForm t = new EditPlanForm(current,p);
                                                 t.show();
                                        }
                                    }
                                });
                        }
                     revalidate() ;   
                    });
                });
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }  
}
