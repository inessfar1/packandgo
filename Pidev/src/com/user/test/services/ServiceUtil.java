/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user.test.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.user.test.GUI.HomeForm;

import com.user.test.entities.Utilisateur;
import com.user.test.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;







/**
 *
 * @author ASUS
 */
public class ServiceUtil {
    public ArrayList<Utilisateur> utilisateur ; 
    public boolean result ; 
    public static ServiceUtil instance = null ; 
    private ConnectionRequest req ; 
    String json ; 
    
    public ServiceUtil(){
    
        req = new ConnectionRequest() ; 
    }
    public static ServiceUtil getIstance(){
        if(instance == null ){
            instance = new ServiceUtil() ; 
        }
        return instance ; 
    
    }
    
    public boolean addUtil(Utilisateur u){
       
    String url = Statics.BASE_URL+"/user/apiadd?nom=" + u.getNom()+ "&username=" + u.getUsername()+ "&prenom=" + u.getPrenom()+ "&email=" + u.getEmail()+ "&password=" + u.getPassword()+ "&roles="+u.getRoles()+"&image="+u.getImage();
            req.setUrl(url) ; 
            req.addResponseListener(new ActionListener<NetworkEvent>(){
                
                @Override 
                public void actionPerformed(NetworkEvent evt){
                
                    result = req.getResponseCode()==200 ; 
                    req.removeResponseListener(this) ; 
                }
            });
            NetworkManager.getInstance().addToQueueAndWait(req);
            return result ; 
            }
    
    public ArrayList<Utilisateur> pareseUtilisateur(String jsonText){
        try {
            utilisateur = new ArrayList<>();
            JSONParser j = new JSONParser() ; 
            Map<String,Object> utillistJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray())) ;
            List<Map<String,Object>> list = (List<Map<String,Object>>)utillistJson.get("root") ; 
            for(Map<String,Object> obj : list){
                Utilisateur u = new Utilisateur() ; 
                float id = Float.parseFloat(obj.get("id").toString()) ; 
                u.setId((int)id); 
                u.setNom(obj.get("nom").toString()) ; 
                u.setPrenom(obj.get("prenom").toString());
                u.setImage(obj.get("image").toString());
                u.setEmail(obj.get("email").toString());
                u.setUsername(obj.get("username").toString());
                u.setPassword(obj.get("password").toString());
                u.setRoles(obj.get("roles").toString());
                
                
                utilisateur.add(u) ;
               
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return utilisateur ; 
        
    }
    
    public ArrayList<Utilisateur> getallusers(){
   
            String url = Statics.BASE_URL+"/apiaff" ;
            req.setUrl(url);
            req.setPost(false);
            req.addResponseListener(new ActionListener<NetworkEvent>(){
            
                @Override
                public void actionPerformed(NetworkEvent evt){
                    utilisateur = pareseUtilisateur(new String(req.getResponseData())) ;
                    req.removeResponseListener(this);
                }
            });
            NetworkManager.getInstance().addToQueueAndWait(req); 
            return utilisateur ;
        
    }
    
    public boolean supputil(String username){
        String url = Statics.BASE_URL+"/admin/utilisateur/"+username+"" ; 
         req.setUrl(url) ;
         req.setPost(false);
          NetworkManager.getInstance().addToQueueAndWait(req);
          return true ; 
    }
    
    
    
    public String getPasswordByEmail(String email){
        
        String url = Statics.BASE_URL+"/user/getPasswordByEmail?email="+email ; 
        req = new ConnectionRequest(url,false) ; 
        req.setUrl(url);
        
        req.addResponseListener((e)->{
        
            JSONParser j = new JSONParser() ; 
             String json = new String(req.getResponseData()) + "" ; 
             
             try{
             
                 Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray())) ;
             }catch(Exception ex) {
             
                 ex.printStackTrace();
             }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return json ; 
    }
    
    public boolean editUtilisateur(Utilisateur u) {
        String url = Statics.BASE_URL + "/updateUtill?id="+u.getId()+"&nom=" + u.getNom()+ "&username=" + u.getUsername()+ "&prenom=" + u.getPrenom()+ "&email=" + u.getEmail()+ "&roles="+u.getRoles();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                result = req.getResponseCode() == 200; 
                req.removeResponseListener(this); 
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
    
   
    
}
