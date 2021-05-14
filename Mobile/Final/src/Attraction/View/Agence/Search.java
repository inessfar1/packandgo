/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.View.Agence;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/**
 *
 * @author 21628
 */
public class Search extends Form {
         Form current;   
        public Search(Form previous){ 
            current=this;
        TextField search = new TextField("","Rechercher une agence");
        Button btnRechercher = new Button("Rechercher");
        btnRechercher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (search.getText().length()==0)
                    Dialog.show("Alert", "Remplir le champ de recherche", new Command("OK"));
                else
                {
                   
                    SearchAgenceForm t = new SearchAgenceForm(current,search.getText());
                   t.show();
                }
                
                
            }
        });
        addAll(search,btnRechercher);         
                
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack());        
                
}       
                
    
}
