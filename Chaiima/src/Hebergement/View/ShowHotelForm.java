/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hebergement.View;

import Hebergement.Entitiy.Hotel;
import Hebergement.Services.ServiceHotel;
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
public class ShowHotelForm extends Form{
    Form current ; 
    public ShowHotelForm(Form previous) {
        current = this;
      ServiceHotel sp = new ServiceHotel();
      add(new InfiniteProgress());
                Display.getInstance().scheduleBackgroundTask(()-> {
                    
                    Display.getInstance().callSerially(() -> {
                        removeAll();
                        List<Hotel> listerec = sp.getAllHotels();
                        for(Hotel p : listerec)
                        {
                            try {
                                Image imagee = Image.createImage("file://C:\\Users\\21628\\Desktop\\final web\\koko444\\pi\\public\\images\\hotels\\"+p.getImage()+"").scaledHeight(500);
                                add(imagee);
                            } catch (IOException ex) {

                            }
                            MultiButton m = new MultiButton();
                            m.setTextLine1("Nom: "+p.getNom());
                            m.setTextLine2("Etoile: "+p.getEtoile());
                            m.setTextLine3("Adresse: "+p.getAdresse());
                            m.setTextLine4("num: "+p.getNum());
                            
                            add(m);
                            
                             m.addPointerReleasedListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evt) {
                                        if (Dialog.show("Confirmation", "Que voulez vous faire ?", "Supprimer", "Modifier")) {
                                            
                                                if( ServiceHotel.getInstance().delHotel(p)){
                                                    {
                                                       Dialog.show("Success","L'agence "+p.getNom()+" a été supprimé avec succées",new Command("OK"));
                                                       previous.showBack();
                                                    }
                                        }
                                    }
                                        else{ 
                                               
                                                 EditHotelForm t = new EditHotelForm(current,p);
                                                 t.show();
                                        }
                                    }
                                });
                        }
                     revalidate() ;   
                    });
                });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); 
    }
    
    
}
