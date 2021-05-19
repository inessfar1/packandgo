/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user.test.GUI;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.user.test.entities.Reclamation;
import com.user.test.services.ServiceReclamation;
import java.util.List;



/**
 *
 * @author ASUS
 */
public class ReclamationAdminForm extends Form {
 Form current  ; 
  //private Resources theme;
    public ReclamationAdminForm(Form previous) {
        current = this ;
        //this.theme = theme ; 
         setTitle(" List des reclamation") ;
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,e->previous.showBack()) ;
         //Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
         //Label img = new Label(theme.getImage("rec.jpg")) ; 
         //c2.add(img); 
         //add(c2) ; 
        Component[] result = new Component[50];
        ServiceReclamation sp = new ServiceReclamation();
         Toolbar.setGlobalToolbar(true);
        add(new InfiniteProgress());
                Display.getInstance().scheduleBackgroundTask(()-> {
                    // this will take a while...
                    Display.getInstance().callSerially(() -> {
                        removeAll();
                        List<Reclamation> listerec = sp.getallreclamations();
                        for(Reclamation p : listerec)
                        {
                            //Container ct = new Container(BoxLayout.y());
                            
                            MultiButton m = new MultiButton();
                            m.setTextLine1("object:"+p.getObject());
                            m.setTextLine2("description:"+p.getDescription());
                            m.setTextLine3("statut:"+p.getStatut());
                           
                            add(m);
                            
                             m.addPointerReleasedListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evt) {
                                        if (Dialog.show("Confirmation", "Que voulez vous faire ?", "Supprimer", "Modifier")) {
                                            Reclamation k = new Reclamation(p.getId());
                                                if( ServiceReclamation.getIstance().supprec(k)){
                                                    {
                                                        Dialog.show("Success","supprimer",new Command("OK"));
                                                    }
                                        }
                                    }
                                        else{ 
                                               Reclamation k = p ; 
                                                 k.setStatut("Confirmer");
                                                 ServiceReclamation.getIstance().editRecelamation(k) ; 
                                                  Dialog.show("Success","confirmation accepter",new Command("OK"));
                                        }
                                    }
                                });
         
        
    }
                     revalidate() ;    
});
                    
                    });
                 getToolbar().addSearchCommand(e -> {
    String text = (String)e.getSource();
    if(text == null || text.length() == 0) {
        // clear search
        for(Component cmp : getContentPane()) {
            cmp.setHidden(false);
            cmp.setVisible(true);
        }
        getContentPane().animateLayout(150);
    } else {
        text = text.toLowerCase();
        for(Component cmp : getContentPane()) {
            MultiButton mb = (MultiButton)cmp;
            String line1 = mb.getTextLine1();
            String line2 = mb.getTextLine2();
            boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
            line2 != null && line2.toLowerCase().indexOf(text) > -1;
            mb.setHidden(!show);
            mb.setVisible(show);
        }
        getContentPane().animateLayout(150);
    }
        }, 4);
                        }

}
                        