/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.View.Agence;

import Attraction.Services.ServicePlan;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;

/**
 *
 * @author 21628
 */
public class ListPlansForm extends Form{

    public ListPlansForm(Form previous) {
        setTitle("List plans");
        
        SpanLabel sp = new SpanLabel();
        sp.setText(ServicePlan.getInstance().getAllPlans().toString());
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
}