/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hebergement.Services;

import Hebergement.Entitiy.Hotel;
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
public class ServiceHotel {
    public ArrayList<Hotel> hotel;
    
    public static ServiceHotel instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    public ServiceHotel() {
         req = new ConnectionRequest();
}
    public static ServiceHotel getInstance() {
        if (instance == null) {
            instance = new ServiceHotel();
        }
        return instance;
    }
    public ArrayList<Hotel> parseHotels(String jsonText){
        try {
            hotel=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> agencesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)agencesListJson.get("root");
            for(Map<String,Object> obj : list){
               Hotel t = new Hotel();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setEtoile(obj.get("etoile").toString());
                t.setImage(obj.get("image").toString());
                t.setNom(obj.get("nom").toString());
                t.setAdresse(obj.get("adresse").toString());
                t.setNum(obj.get("num").toString());
                hotel.add(t);
            }
        } catch (IOException ex) {}
        return hotel;
    }
    
    public ArrayList<Hotel> getAllHotels(){
        String url = Statics.BASE_URL+"/admin/hotel";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                hotel = parseHotels(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return hotel;
    }
    
    public boolean delHotel(Hotel t) {
        String url = Statics.BASE_URL + "/admin/hoteldel?id="+t.getId()+"";
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
    
    public boolean editHotel(Hotel t) {
        String url = Statics.BASE_URL + "/admin/hotelupp?id="+t.getId()+"&nom="+t.getNom()+"&image="+t.getImage()+"&adresse="+t.getAdresse()+"&etoile="+t.getEtoile()+"&num="+t.getNum()+"";
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
    
    public boolean addHotel(Hotel t) {
        String url = Statics.BASE_URL + "/admin/hotelnew?image="+t.getImage()+"&nom="+t.getNom()+"&adresse="+t.getAdresse()+"&etoile="+t.getEtoile()+"&num="+t.getNum()+"";
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
