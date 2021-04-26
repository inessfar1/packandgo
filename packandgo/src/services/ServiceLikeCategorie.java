/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;



import java.sql.SQLException;
import java.util.List;

import connection.Datasource;
import entity.likecategorie;
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
 * @author walid
 */
public class ServiceLikeCategorie implements Iservice<likecategorie> {

     private Connection con;
    private Statement ste;
    private PreparedStatement pre;
   
    
    public ServiceLikeCategorie(){
    con = Datasource.getInstance().getCnx();
    }
    @Override
    public void ajouter(likecategorie t) throws SQLException {
         if(!chercher(t.getId())){
         pre=con.prepareStatement("INSERT INTO `packandgo`.`likecategorie` ( `categorie_id`, `utilisateur_id`, `date`) VALUES ( ?, ?, ?);");
    pre.setInt(1, t.getCategorie_id());
    pre.setInt(2, t.getUtilisateur_id());
    Date sDate = new java.sql.Date(t.getDate().getTime());

    pre.setDate(3, sDate);
    pre.executeUpdate();
            System.out.println("ajout valide");
    }
        else System.out.println("ajout invalide");
    }

    @Override
    public boolean delete(int id) throws SQLException {
        if(chercher(id))
        {pre=con.prepareStatement("delete from `packandgo`.`likecategorie` where id  = (?);");
        pre.setInt(1,id);
       
                pre.execute();
                System.out.println("valide");
                 return true;}
        System.out.println("n'existe pas");
        return false;
    }
     public boolean delete1(int id) throws SQLException {
      
        pre=con.prepareStatement("delete from `packandgo`.`likecategorie` where id  = (?);");
        pre.setInt(1,id);
       
                pre.execute();
                System.out.println("valide");
                 return true;
    }

    @Override
    public boolean chercher(int id) throws SQLException {
 String req="select * from likecategorie";
        List<Integer> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                list.add(rs.getInt(1));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLikeCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list.contains(id);    }

    @Override
    public boolean chercher_ajout(likecategorie t) throws SQLException {
  String req="select * from likecategorie where categorie_id= '"+t.getCategorie_id()+ "' AND utilisateur_id ='"+t.getUtilisateur_id()+ "'";
        List<likecategorie> list = new ArrayList<>();
       
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                 java.util.Date d1 = new java.util.Date(rs.getDate(4).getTime());
                list.add(new likecategorie(rs.getInt(1),rs.getInt(2),rs.getInt(3),d1));
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLikeCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return (list.size()!=0);
    }    

    @Override
    public boolean update(likecategorie t, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<likecategorie> readAll() throws SQLException {
  String req="select * from likecategorie  ";
        List<likecategorie> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                
               java.util.Date d1 = new java.util.Date(rs.getDate(4).getTime());
                list.add(new likecategorie(rs.getInt(1),rs.getInt(2),rs.getInt(3),d1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLikeCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list  ;    }
      
}
