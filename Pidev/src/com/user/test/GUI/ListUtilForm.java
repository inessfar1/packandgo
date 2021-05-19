/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user.test.GUI;

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
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.user.test.entities.Utilisateur;
import com.user.test.services.ServiceUtil;
import java.io.IOException;
import java.util.List;


/**
 *
 * @author ASUS
 */
public class ListUtilForm extends Form {
        Form current ; 
    public ListUtilForm(Form previous) {
        current = this ; 
        setTitle(" List des Utilisateur") ;
        Component[] result = new Component[50];
        ServiceUtil sp = new ServiceUtil();
         Toolbar.setGlobalToolbar(true);
        add(new InfiniteProgress());
                Display.getInstance().scheduleBackgroundTask(()-> {
                    // this will take a while...
                    Display.getInstance().callSerially(() -> {
                        removeAll();
                        List<Utilisateur> listerec = sp.getallusers();
                        for(Utilisateur p : listerec)
                        {
                            try {
                                Image imagee = Image.createImage("file://C:\\Users\\ASUS\\Desktop\\pi\\public\\images\\aliments\\"+p.getImage()+"").scaledHeight(400);
                                add(imagee);
                            } catch (IOException ex) {

                            }
                            
                            MultiButton m = new MultiButton();
                            m.setTextLine1("username:"+p.getUsername());
                            m.setTextLine2("email:"+p.getEmail());
                            m.setTextLine3("role:"+p.getRoles());
                           
                            add(m);
                            
                             m.addPointerReleasedListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evt) {
                                        if (Dialog.show("Confirmation", "Que voulez vous faire ?", "Supprimer", "Modifier")) {
                                            
                                                if( ServiceUtil.getIstance().supputil(p.getUsername())){
                                                    {
                                                        Dialog.show("Success","supprimer",new Command("OK"));
                                                    }
                                        }
                                    }
                                        else{ 
                                                 
                                               new EditUtiForml(current,p).show() ;   
                                        }
                                    }
                                });
         
        
    }
                     revalidate() ;    
});
                    
                    });
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,e->previous.showBack()) ; 
        
    }
    
     
        
    
    
}
