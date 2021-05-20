/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packandgo.services;



import java.sql.SQLException;
import java.util.List;

import com.packandgo.utils.ConnexionSingleton;
import com.packandgo.entity.Participer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oumaima
 */
public class ServiceParticiper implements Iservice<Participer> {

     private Connection con;
    private Statement ste;
    private PreparedStatement pre;
   
    
    public ServiceParticiper(){
    con = ConnexionSingleton.getInstance().getCnx();
    }
    @Override
    public void ajouter(Participer t) throws SQLException {
         if(!chercher(t.getId())){
         pre=con.prepareStatement("INSERT INTO `pidev`.`participationt` ( `id_trajet`, `id_user`) VALUES ( ?, ?);");
    pre.setInt(1, t.getId_trajet());
    pre.setInt(2, t.getId_user());
    pre.executeUpdate();
            System.out.println("ajout valide");
    }
        else System.out.println("ajout invalide");
    }

    @Override
    public boolean delete(int id) throws SQLException {
        if(chercher(id))
        {pre=con.prepareStatement("delete from `pidev`.`participationt` where id_trajet  = (?);");
        pre.setInt(1,id);
       
                pre.execute();
                System.out.println("valide");
                 return true;}
        System.out.println("n'existe pas");
        return false;
    }
     public boolean delete1(int id) throws SQLException {
      
        pre=con.prepareStatement("delete from `pidev`.`participationt` where id  = (?);");
        pre.setInt(1,id);
       
                pre.execute();
                System.out.println("valide");
                 return true;
    }

    @Override
    public boolean chercher(int id) throws SQLException {
 String req="select * from participationt";
        List<Integer> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                list.add(rs.getInt(1));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceParticiper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list.contains(id);    }

    @Override
    public boolean chercher_ajout(Participer t) throws SQLException {
  String req="select * from participationt where id_trajet= '"+t.getId_trajet()+ "' AND id_user ='"+t.getId_user()+ "'";
        List<Participer> list = new ArrayList<>();
       
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                
                list.add(new Participer(rs.getInt(1),rs.getInt(2),rs.getInt(3)));
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceParticiper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return (list.size()!=0);
    }    

    @Override
    public boolean update(Participer t, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Participer> readAll() throws SQLException {
  String req="select * from participationt  ";
        List<Participer> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                
                list.add(new Participer(rs.getInt(1),rs.getInt(2),rs.getInt(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceParticiper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list  ;    }
      
}
