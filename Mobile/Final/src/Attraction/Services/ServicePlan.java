/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.Services;

import Attraction.Entity.Plan;
import Attraction.Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 21628
 */
public class ServicePlan {
    public ArrayList<Plan> plans;
    
    public static ServicePlan instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServicePlan() {
         req = new ConnectionRequest();
    }

    public static ServicePlan getInstance() {
        if (instance == null) {
            instance = new ServicePlan();
        }
        return instance;
    }
    public ArrayList<Plan> parsePlans(String jsonText){
        try {
            plans=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> plansListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
           
            List<Map<String,Object>> list = (List<Map<String,Object>>)plansListJson.get("root");
            
            
            for(Map<String,Object> obj : list){
               
                Plan t = new Plan();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                //t.setAgence_id(((int)Float.parseFloat(obj.get("agence_id").toString())));
                t.setImage(obj.get("image").toString());
                t.setSujet(obj.get("sujet").toString());
                t.setDescription(obj.get("description").toString());
                //t.setDate((Date) obj.get("date"));
                t.setPrix(Double.parseDouble(obj.get("prix").toString()));
                plans.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        
        return plans;
    }
    
    public ArrayList<Plan> getAllPlans(){
        String url = Statics.BASE_URL+"/plan/showJSON";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                plans = parsePlans(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return plans;
    }
    
    public boolean addPlan(Plan t) {
        String url = Statics.BASE_URL + "/plan/newJSON?agence="+t.getAgence_id()+"&image="+t.getImage()+"&sujet="+t.getSujet()+"&desc="+t.getDescription()+"&prix="+t.getPrix()+"";
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service plan, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
}
