/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import connection.Datasource;
import entity.Blog;
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
public class ServiceCategorie implements Iservice<Categorie> {
    private  Connection con;
     private Statement ste;
    private PreparedStatement pre;
    
     public ServiceCategorie(){
    con = Datasource.getInstance().getCnx();
    }
  

    @Override
    public boolean delete(int id) throws SQLException {
       if(chercher(id)){
            System.out.println("exist");
        pre=con.prepareStatement("delete from `packandgo`.`categorie` where id  = (?);");
        pre.setInt(1,id);
            System.out.println(pre.execute());
       return true;}
        else 
        {System.out.println("nexiste pas");
            return false;}
    }

    @Override
    public boolean chercher(int id) throws SQLException {
        String req="select * from categorie";
        List<Integer> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                list.add(rs.getInt(1));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        return list.contains(id);
    }



    @Override
    public void ajouter(Categorie t) throws SQLException {
pre=con.prepareStatement("INSERT INTO `packandgo`.`categorie` ( `utilisateur_id`,`title`, `description`, `categorie`, `nb_like`) VALUES ( ?, ?, ?,?,?);");
   pre.setInt(1, t.getUtilisateur_id());  
pre.setString(2, t.getTitle());
    pre.setString(3, t.getDescription()); 
    pre.setString(4, t.getCategorie()); 
     pre.setInt(5,0); 
     
   pre.executeUpdate();    }

    @Override
    public boolean chercher_ajout(Categorie t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Categorie t, int id) throws SQLException {
         if(chercher(id)){
       
        pre=con.prepareStatement("UPDATE categorie SET   title =?,description =? ,categorie =? WHERE id = ?");
         
    pre.setString(1, t.getTitle());
    pre.setString(2, t.getDescription());
    pre.setString(3, t.getCategorie());
    pre.setInt(4, id);
    pre.executeUpdate();
  pre.executeUpdate();
     
         return true;}
        System.out.println("update invalid: categorie nexiste pas");
        return false; 
    }

    @Override
    public List<Categorie> readAll() throws SQLException {
String req="select * from categorie  ";
        List<Categorie> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
           
                list.add(new Categorie(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list  ;    }

  
   
}
