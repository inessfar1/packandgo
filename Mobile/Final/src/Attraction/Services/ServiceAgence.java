/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.Services;

import Attraction.Entity.Agence;
import Attraction.Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 21628
 */
public class ServiceAgence {
    public ArrayList<Agence> agences;
    
    public static ServiceAgence instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceAgence() {
         req = new ConnectionRequest();
    }

    public static ServiceAgence getInstance() {
        if (instance == null) {
            instance = new ServiceAgence();
        }
        return instance;
    }
    public ArrayList<Agence> parseAgences(String jsonText){
        try {
            agences=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> agencesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)agencesListJson.get("root");
            for(Map<String,Object> obj : list){
               Agence t = new Agence();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setTel(((int)Float.parseFloat(obj.get("tel").toString())));
                t.setLogo(obj.get("logo").toString());
                t.setNom(obj.get("nom").toString());
                t.setAdresse(obj.get("adresse").toString());
                t.setEmail(obj.get("email").toString());
                agences.add(t);
            }
        } catch (IOException ex) {}
        return agences;
    }
    
    public ArrayList<Agence> getAllAgences(){
        String url = Statics.BASE_URL+"/agence/allJSON";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                agences = parseAgences(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return agences;
    }
    
    public boolean addAgence(Agence t) {
        String url = Statics.BASE_URL + "/agence/newJSON?logo="+t.getLogo()+"&nom="+t.getNom()+"&adresse="+t.getAdresse()+"&email="+t.getEmail()+"&tel="+t.getTel()+"";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this); 
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public boolean delAgence(Agence t) {
        String url = Statics.BASE_URL + "/agence/delJSON?id="+t.getId()+"";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this); 
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public boolean editAgence(Agence t) {
        String url = Statics.BASE_URL + "/agence/updateJSON?id="+t.getId()+"&logo="+t.getLogo()+"&nom="+t.getNom()+"&adresse="+t.getAdresse()+"&email="+t.getEmail()+"&tel="+t.getTel()+"";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this); 
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
     public ArrayList<Agence> getAgences(String search){
        String url = Statics.BASE_URL+"/agence/search?search="+search+"";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                agences = parseAgences(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return agences;
    }
    
    
    
    
    
    
    
}
