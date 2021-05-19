/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hebergement.Services;

import Hebergement.Entitiy.Chambre;
import Statics.Statics;
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
public class ServiceChambre {
    public ArrayList<Chambre> chambre;
    
    public static ServiceChambre instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    public ServiceChambre() {
         req = new ConnectionRequest();
}
    public static ServiceChambre getInstance() {
        if (instance == null) {
            instance = new ServiceChambre();
        }
        return instance;
    }
    public ArrayList<Chambre> parseChambres(String jsonText){
        try {
            chambre=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> agencesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)agencesListJson.get("root");
            for(Map<String,Object> obj : list){
               Chambre t = new Chambre();
                float id = Float.parseFloat(obj.get("id").toString());
                Double prix = Double.parseDouble(obj.get("prix").toString());
                t.setId((int)id);
                t.setType(obj.get("type").toString());
                t.setVue(obj.get("vue").toString());
                t.setDuree(obj.get("duree").toString());
                t.setPrix(prix);
                t.setImage(obj.get("image").toString());
                chambre.add(t);
            }
        } catch (IOException ex) {}
        return chambre;
    }
    
    public ArrayList<Chambre> getAllChambres(){
        String url = Statics.BASE_URL+"/admin/chambre/allJSON";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                chambre = parseChambres(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return chambre;
    }
    public boolean delChambre(Chambre t) {
        String url = Statics.BASE_URL + "/admin/chambre/delJSON?id="+t.getId()+"";
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
     public boolean editChambre(Chambre t) {
        String url = Statics.BASE_URL + "/admin/chambre/updateJSON?id="+t.getId()+"&image="+t.getImage()+"&type="+t.getType()+"&vue="+t.getVue()+"&prix="+t.getPrix()+"&hotel="+t.getHotel_id()+"&duree="+t.getDuree()+"";
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
     
     
     public boolean addChambre(Chambre t) {
        String url = Statics.BASE_URL + "/admin/chambreSON?image="+t.getImage()+"&type="+t.getType()+"&vue="+t.getVue()+"&prix="+t.getPrix()+"&hotel="+t.getHotel_id()+"&duree="+t.getDuree()+"";
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
     
      public boolean resChambre(Chambre t,int user) {
        String url = Statics.BASE_URL + "/chambre/res/chambre/resJSON?id="+t.getId()+"&user="+user+"";
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
