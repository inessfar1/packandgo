/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import connection.Datasource;
import entity.Blog;
import entity.comment;
import entity.Categorie;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.activation.DataSource;

/**
 *
 * @author asus
 */
public class ServiceCommentaire implements Iservice<comment> {
    private  Connection con;
     private Statement ste;
    private PreparedStatement pre;
    
     public ServiceCommentaire(){
    con = Datasource.getInstance().getCnx();
    }
  

    @Override
    public boolean delete(int id) throws SQLException {
       if(chercher(id)){
            System.out.println("exist");
        pre=con.prepareStatement("delete from `packandgo`.`comment` where id  = (?);");
        pre.setInt(1,id);
            System.out.println(pre.execute());
       return true;}
        else 
        {System.out.println("nexiste pas");
            return false;}
    }

    @Override
    public boolean chercher(int id) throws SQLException {
        String req="select * from comment";
        List<Integer> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                list.add(rs.getInt(1));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        return list.contains(id);
    }


    @Override
    public void ajouter(comment t) throws SQLException {
pre=con.prepareStatement("INSERT INTO `packandgo`.`comment` ( `categorie_id`, `utilisateur_id`,`text`, `date` ,`image` ) VALUES ( ?,?, ?,?,?);");
    pre.setInt(1, t.getCategorie_id());
    pre.setInt(2, t.getUtilisateur_id());
    pre.setString(3, t.getText()); 
   
     Date sDate = new java.sql.Date(t.getDate().getTime());
     pre.setDate(4,sDate); 
        pre.setString(5, t.getImage()); 
   
   pre.executeUpdate();     }

    @Override
    public boolean chercher_ajout(comment t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(comment t, int id) throws SQLException {
    if(chercher(id)){
       
        pre=con.prepareStatement("UPDATE comment SET   categorie_id =?,`categorie_id`,=?,text =?,date =? WHERE id = ?");
         
   pre.setInt(1, t.getCategorie_id());
    pre.setString(2, t.getText()); 
   
     Date sDate = new java.sql.Date(t.getDate().getTime());
     pre.setDate(3,sDate);
    pre.setInt(4, id);
    pre.executeUpdate();
  pre.executeUpdate();
     
         return true;}
        System.out.println("update invalid: commentaire nexiste pas");
        return false;     }

    @Override
    public List<comment> readAll() throws SQLException {
String req="select * from comment  ";
        List<comment> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
           
                list.add(new comment(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getDate(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list  ; 
    }

   


   
  
   
}
