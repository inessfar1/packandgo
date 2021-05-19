/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user.test.GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.user.test.services.SigninService;
import java.io.IOException;

/**
 *
 * @author ASUS
 */
public class SigninForm extends Form{
Form current  ; 
    public SigninForm() {
        current=this ; 
        setTitle("Sign In") ; 
        
        try {
                                Image imagee = Image.createImage("file://C:\\Users\\ASUS\\Desktop\\images\\log.jpg").scaledHeight(800);
                                add(imagee);
                            } catch (IOException ex) {

                            }
        TextField username = new TextField("","Username"); 
        TextField password = new TextField("","Password",20,TextField.PASSWORD); 
        Button signin = new Button("Sign In") ; 
        Button forgot = new Button("mot de passe oublie") ; 
        Button cr = new Button("cree un compte") ; 
        signin.addActionListener((e)->{ 
        
            SigninService.getIstance().signin(username, password);
        });
        
        forgot.addActionListener((e)->{
           new ForgetpassForm().show();
        
        });
        
        cr.addActionListener((e)->{
           new AdduserForm(current).show();
        
        });
         addAll(username,password,signin,forgot,cr) ;
         
    }
    
}
