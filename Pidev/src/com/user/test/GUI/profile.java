/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user.test.GUI;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.user.test.entities.Utilisateur;
import com.user.test.services.StaticValue;
import java.io.IOException;

/**
 *
 * @author ASUS
 */
public class profile extends Form{

    public profile(Form previous) {
        Utilisateur ul = StaticValue.utilisateur ; 
        try {
                                Image imagee = Image.createImage("file://C:\\Users\\ASUS\\Desktop\\pi\\public\\images\\aliments\\"+ul.getImage()+"").scaledHeight(400);
                                add(imagee);
                            } catch (IOException ex) {

                            }
        Label tfname = new Label(ul.getNom()) ; 
        Label tfprename = new Label(ul.getPrenom()) ;
        Label tfimage = new Label(ul.getImage()) ;
        Label tfemail = new Label(ul.getEmail()) ;
        Label tfusername = new Label(ul.getUsername()) ;
        addAll(tfname,tfprename,tfimage,tfemail,tfusername) ; 
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,e->previous.showBack()) ;
    }
    
    
        
        
        
        
    
}
