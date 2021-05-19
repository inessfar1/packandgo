/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user.test.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.user.test.GUI.HomeForm;
import com.user.test.GUI.HomeUserForm;
import com.user.test.entities.Utilisateur;
import com.user.test.utils.Statics;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class SigninService {
    public static SigninService instance = null ; 
    private ConnectionRequest req ; 
    String json ; 

    public SigninService() {
         req = new ConnectionRequest() ; 
    }
    public static SigninService getIstance(){
        if(instance == null ){
            instance = new SigninService() ; 
        }
        return instance ; 
    
    }
    public void signin(TextField username, TextField password)  {
        String url = Statics.BASE_URL+"/user/signin?username="+username.getText()+"&password="+password.getText() ; 
        //req = new ConnectionRequest() ;
        req.setUrl(url);
        req.setPost(false);
        
            req.addResponseListener((e)->{
            
            JSONParser j = new JSONParser() ; 
            String json = new String(req.getResponseData()) + "" ; 
            if(json.equals("failed")){
                Dialog.show("Echec", "username ou password eronne", "OK",null) ; 
            }else if(json.equals("passowrd not found")){Dialog.show("Echec", " password incorrecte ou compte desactiver", "OK",null) ; }
            else{
                
                try {
                    // System.out.println("data =="+json);
                    Map<String,Object> obj = j.parseJSON(new CharArrayReader(json.toCharArray())) ;
                    if(obj.size() > 0){
                   System.out.println(obj.get("nom").toString());
                Utilisateur u = new Utilisateur() ; 
                float id = Float.parseFloat(obj.get("id").toString()) ; 
                u.setId((int)id); 
                u.setNom(obj.get("nom").toString()) ; 
                u.setPrenom(obj.get("prenom").toString());
                u.setImage(obj.get("image").toString());
                u.setImage(obj.get("email").toString());
                u.setUsername(obj.get("username").toString());
                u.setPassword(obj.get("password").toString());
                u.setRoles(obj.get("roles").toString());
                        System.out.println(u.getRoles());
                connect(u);
                      
                        Dialog.show("success", "welcome", "OK",null) ;
                            if(!u.getRoles().equals("[ROLE_ADMIN]")){
                           new HomeUserForm().show() ; }
                            else{
                                new HomeForm().show() ;}
                    }
                } catch (IOException ex) {
                   
                }
            }
        
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
     public void connect(Utilisateur p){
        
              StaticValue.utilisateur = p ; }
          
}
