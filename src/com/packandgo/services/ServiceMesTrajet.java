/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packandgo.services;



import com.packandgo.entity.MesTrajet;
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
public class ServiceMesTrajet implements Iservice<MesTrajet> {

     private Connection con;
    private Statement ste;
    private PreparedStatement pre;
   
    
    public ServiceMesTrajet(){
    con = ConnexionSingleton.getInstance().getCnx();
    }
    @Override
    public void ajouter(MesTrajet p) throws SQLException {
         if(!chercher(p.getId())){
         pre=con.prepareStatement("INSERT INTO `pidev`.`mestrajet` ( `date_depart`, `date_arrive`, `pt_depart`, `pt_arrive`, `prix`, `id_trajet`)"
                    + " VALUES ('"+p.getDate_depart()+"','"+p.getDate_arrive()+"','"+p.getPt_depart()+"','"+p.getPt_arrive()+"','"+p.getPrix()+"','"+p.getId_trajet()+"')");
    
    pre.executeUpdate();
            System.out.println("ajout valide");
    }
        else System.out.println("ajout invalide");
    }

    @Override
    public boolean delete(int id) throws SQLException {
        if(chercher(id))
        {pre=con.prepareStatement("delete from `pidev`.`mestrajet` where id  = (?);");
        pre.setInt(1,id);
       
                pre.execute();
                System.out.println("valide");
                 return true;}
        System.out.println("n'existe pas");
        return false;
    }
     public boolean delete1(int id) throws SQLException {
      
        pre=con.prepareStatement("delete from `pidev`.`mestrajet` where id  = (?);");
        pre.setInt(1,id);
       
                pre.execute();
                System.out.println("valide");
                 return true;
    }

    @Override
    public boolean chercher(int id) throws SQLException {
 String req="select * from mestrajet";
        List<Integer> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                list.add(rs.getInt(1));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMesTrajet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list.contains(id);    }

    @Override
    public boolean chercher_ajout(MesTrajet t) throws SQLException {
  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     
    }    

    @Override
    public boolean update(MesTrajet t, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MesTrajet> readAll() throws SQLException {
  String req="select * from mestrajet  ";
        List<MesTrajet> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                
                list.add(new MesTrajet(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMesTrajet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list  ;    }
      
}
