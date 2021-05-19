/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hebergement.View;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;

/**
 *
 * @author 21628
 */
public class HomeBack extends Form{
    Form current;
    public HomeBack(Form previous){
        
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Back Attraction");
        setLayout(BoxLayout.y());
           try {
                                Image ban = Image.createImage("file://C:\\Users\\21628\\Desktop\\Mobile\\Final\\src\\Attraction\\Images\\banner.jpg").scaledHeight(1000);
                                add(ban);
                            } catch (IOException ex) {

                            }
        Container ct =new Container(BoxLayout.y());
            Button btAg =new Button ("Hotel");
            Button btChambre =new Button ("Chambres");
            btAg.addActionListener((evt)-> { new HotelForm(current).show(); });
            btChambre.addActionListener((evt)-> { new ChambreForm(current).show(); });
            ct.addAll(btAg,btChambre);
            add(ct);
              getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack()); 
    }
}