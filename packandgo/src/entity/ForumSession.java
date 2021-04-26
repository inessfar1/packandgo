/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author EYA
 */
public class ForumSession {
    private static ForumSession instance;
 
    private int id; 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

   
   

  

   

   
    public ForumSession(int id) {
        this.id = id;
    }

    public static ForumSession getInstance() {
        return instance;
    }

   

    public static void setInstance(ForumSession instance) {
        ForumSession.instance = instance; 
    }

   

  

    
    
    public static ForumSession getInstace(int id) {
        if(instance == null) {
         instance = new ForumSession(id);
        }
        return instance;
    }  
     
     
        public void cleanForumSession() {
        id = 0;
    
      instance = null;
        
       // or null
    }
     
    
      
      

   

   
   

  
}
