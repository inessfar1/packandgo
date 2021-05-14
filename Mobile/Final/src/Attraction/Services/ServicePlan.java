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
import java.text.SimpleDateFormat;
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

    public ServicePlan() {
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
                t.setImage(obj.get("image").toString());
                t.setSujet(obj.get("sujet").toString());
                t.setDescription(obj.get("description").toString());
                String DateConverter = obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp")+10,obj.get("date").toString().lastIndexOf("}"));
                Date currentTime = new Date(Double.valueOf(DateConverter).longValue()*1000) ;
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = formatter.format(currentTime);
                t.setDate(dateString);
                t.setPrix(Double.parseDouble(obj.get("prix").toString()));
                float nbr = Float.parseFloat(obj.get("nbr").toString());
                if (nbr>0) plans.add(t);
            }
            } catch (IOException ex) {}
        return plans;
    }
    
    public ArrayList<Plan> getAllPlans(){
        String url = Statics.BASE_URL+"/plan/allJSON";
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
    
    public boolean delPlan(Plan t) {
        String url = Statics.BASE_URL + "/plan/delJSON?id="+t.getId()+"";
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
    
    public boolean editPlan(Plan t) {
        String url = Statics.BASE_URL + "/plan/updateJSON?id="+t.getId()+"&sujet="+t.getSujet()+"&desc="+t.getDescription()+"&prix="+t.getPrix()+"";
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
    
    
    public boolean recPlan(Plan t) {
        String url = Statics.BASE_URL + "/plan/recJSON?id="+t.getId()+"";
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
    
    public boolean resPlan(Plan t,int user) {
        String url = Statics.BASE_URL + "/plan/resJSON?id="+t.getId()+"&user="+user+"";
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
    
   
    
    
    
    
    
    
    
}
