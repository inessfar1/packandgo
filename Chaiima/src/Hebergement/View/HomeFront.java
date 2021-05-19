/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hebergement.View;

import Hebergement.Entitiy.Chambre;
import Hebergement.Services.ServiceChambre;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author 21628
 */
public class HomeFront extends Form{
    Form current ; 
    public HomeFront(Form previous) {
        current = this;
      ServiceChambre sp = new ServiceChambre();
      add(new InfiniteProgress());
                Display.getInstance().scheduleBackgroundTask(()-> {
                    
                    Display.getInstance().callSerially(() -> {
                        removeAll();
                        List<Chambre> listerec = sp.getAllChambres();
                        for(Chambre p : listerec)
                        {
                            try {
                                Image imagee = Image.createImage("file://C:\\Users\\21628\\Desktop\\final web\\koko444\\pi\\public\\images\\chambres\\"+p.getImage()+"").scaledHeight(500);
                                add(imagee);
                            } catch (IOException ex) {

                            }
                            MultiButton m = new MultiButton();
                            m.setTextLine1("Type: "+p.getType());
                            m.setTextLine2("Vue: "+p.getVue());
                            m.setTextLine3("Duree: "+p.getDuree());
                            m.setTextLine4("Prix: "+p.getPrix());
                            
                            add(m);
                            
                            m.addPointerReleasedListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evt) {
                                        if (Dialog.show("Confirmation", "Voulez-vous vraiment réserver cette chambre ?", "Oui", "Non")) {
                                            
                                                if( ServiceChambre.getInstance().resChambre(p,6)){
                                                    {
                                                       Dialog.show("Success","La chambre "+p.getType()+" a été réservé avec succées",new Command("OK"));
                                                       previous.showBack();
                                                    }
                                        }
                                    }
                                        else{ 
                                               
                                              previous.showBack();;
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
