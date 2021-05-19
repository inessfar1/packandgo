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
import com.codename1.ui.events.ActionListener;
import com.user.test.entities.Reclamation;
import com.user.test.entities.Utilisateur;
import com.user.test.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ServiceReclamation {
     //public ArrayList<Utilisateur> utilisateur ;
     public ArrayList<Reclamation> reclamation ; 
    public boolean result ; 
    public static ServiceReclamation instance = null ; 
    private ConnectionRequest req ; 
    
    public ServiceReclamation(){
    
        req = new ConnectionRequest() ; 
    }
    public static ServiceReclamation getIstance(){
        if(instance == null ){
            instance = new ServiceReclamation() ; 
        }
        return instance ; 
    
    }
    public boolean addRec(Reclamation r, Utilisateur u){
       
    String url = Statics.BASE_URL+"/reclamation/new/mobile?object=" + r.getObject()+ "&description=" + r.getDescription()+ "&statut=" + r.getStatut()+ "&iduser=" + u.getId();
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
    
    public ArrayList<Reclamation> pareseReclamation(String jsonText){
        try {
            reclamation = new ArrayList<>();
            JSONParser j = new JSONParser() ; 
            Map<String,Object> reclistJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray())) ;
            List<Map<String,Object>> list = (List<Map<String,Object>>)reclistJson.get("root") ; 
            for(Map<String,Object> obj : list){
                Reclamation u = new Reclamation() ;
                //Utilisateur r = new Utilisateur() ; 
                float id = Float.parseFloat(obj.get("id").toString()) ; 
                u.setId((int)id); 
                u.setObject(obj.get("object").toString()) ; 
                u.setDescription(obj.get("description").toString());
                u.setStatut(obj.get("statut").toString());
                //r.setUsername(obj.get("iduser").toString());
                
                
                reclamation.add(u) ;
               
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamation ; 
        
    }
    
    public ArrayList<Reclamation> getallreclamations(){
        String url = Statics.BASE_URL+"/apiaffi/reclamation" ; 
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            
            @Override 
            public void actionPerformed(NetworkEvent evt){
                reclamation = pareseReclamation(new String(req.getResponseData())) ; 
                req.removeResponseListener(this); 
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    return reclamation ; 
    }
    
    public boolean supprec(Reclamation r){
        String url = Statics.BASE_URL+"/admin/reclamation/suppmobile?id="+r.getId()+"" ; 
         req.setUrl(url) ;
         req.setPost(false);
          NetworkManager.getInstance().addToQueueAndWait(req);
          return true ; 
    }
    
    public boolean editRecelamation(Reclamation r) {
        String url = Statics.BASE_URL + "/updateRec?id="+r.getId()+"&object=" + r.getObject()+ "&description=" + r.getDescription()+ "&statut=" + r.getStatut();
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
