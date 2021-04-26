/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import connection.Datasource;
import entity.Blog;
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
public class ServiceBlog implements Iservice<Blog> {
    private  Connection con;
     private Statement ste;
    private PreparedStatement pre;
    
     public ServiceBlog(){
    con = Datasource.getInstance().getCnx();
    }
    @Override
    public void ajouter(Blog t) throws SQLException {
        pre=con.prepareStatement("INSERT INTO `packandgo`.`blog` ( `title`, `description`, `image`) VALUES ( ?, ?,?);");
    pre.setString(1, t.getTitle());
    pre.setString(2, t.getDescription());
    pre.setString(3, t.getImage());   
   pre.executeUpdate();
    }

    @Override
    public boolean delete(int id) throws SQLException {
       if(chercher(id)){
            System.out.println("exist");
        pre=con.prepareStatement("delete from `packandgo`.`blog` where id  = (?);");
        pre.setInt(1,id);
            System.out.println(pre.execute());
       return true;}
        else 
        {System.out.println("nexiste pas");
            return false;}
    }

    @Override
    public boolean chercher(int id) throws SQLException {
        String req="select * from blog";
        List<Integer> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                list.add(rs.getInt(1));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceBlog.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        return list.contains(id);
    }

    @Override
    public boolean chercher_ajout(Blog t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Blog t, int id) throws SQLException {
         if(chercher(id)){
       
        pre=con.prepareStatement("UPDATE blog SET   title =?,description =?,image =? WHERE id = ?");
         
    pre.setString(1, t.getTitle());
    pre.setString(2, t.getDescription());
    pre.setString(3, t.getImage());
    pre.setInt(4, id);
    pre.executeUpdate();
  pre.executeUpdate();
     
         return true;}
        System.out.println("update invalid: blog nexiste pas");
        return false; 
    }

    @Override
    public List<Blog> readAll() throws SQLException {
       String req="select * from blog  ";
        List<Blog> list = new ArrayList<>();
         ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                
               
               
                ImageView v = new ImageView();
                System.out.println(rs.getString(4));
                
                v.setImage(new Image("http://127.0.0.1/image/"+rs.getString(4)));
                v.setFitWidth(100);
                v.setFitHeight(100);
               
                //ystem.out.println(v.getImage().toString());
                Blog p2=new Blog(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
                
                p2.setPhoto(v);
                list.add(p2);
            
    
}
        return list;
    }
   
}
