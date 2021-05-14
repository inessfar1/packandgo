/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.View.Agence;

import Attraction.Entity.Agence;
import Attraction.Services.ServiceAgence;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 21628
 */
public class ListAgencesForm extends Form{
    Form current;
    public ListAgencesForm(Form previous) {
        current = this;
      ServiceAgence sp = new ServiceAgence();
      add(new InfiniteProgress());
                Display.getInstance().scheduleBackgroundTask(()-> {
                    
                    Display.getInstance().callSerially(() -> {
                        removeAll();
                        List<Agence> listerec = sp.getAllAgences();
                        for(Agence p : listerec)
                        {
                            MultiButton m = new MultiButton();
                            m.setTextLine1("Nom: "+p.getNom());
                            m.setTextLine2("Email: "+p.getEmail());
                            m.setTextLine3("Adresse: "+p.getAdresse());
                            m.setTextLine4("Tel: "+p.getTel());
                            add(m);
                            
                             m.addPointerReleasedListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evt) {
                                        if (Dialog.show("Confirmation", "Que voulez vous faire ?", "Supprimer", "Modifier")) {
                                            
                                                if( ServiceAgence.getInstance().delAgence(p)){
                                                    {
                                                       Dialog.show("Success","L'agence "+p.getNom()+" a été supprimé avec succées",new Command("OK"));
                                                       previous.showBack();
                                                    }
                                        }
                                    }
                                        else{ 
                                               
                                                 EditAgenceForm t = new EditAgenceForm(current,p);
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
