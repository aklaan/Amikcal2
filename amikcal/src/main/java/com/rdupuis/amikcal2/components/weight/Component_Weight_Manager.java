package com.rdupuis.amikcal2.components.weight;

import android.app.Activity;

import com.rdupuis.amikcal2.commons.ManagedElement;
import com.rdupuis.amikcal2.commons.Manager;
import com.rdupuis.amikcal2.commons.Manager_commons;


public class Component_Weight_Manager extends Manager_commons {
    
    
    public Component_Weight_Manager(Activity activity) {
	super(activity);
	
    }

    // Dans le cas d'une mise à jour on appelle l'éditeur avec l'ID de
    // l'activité à modifier
@Override
    public void edit(ManagedElement element) {

	//Intent intent = new Intent(this.mActivity, Act_Component_Weight_Editor.class);
	//intent.putExtra(Act_Component_Weight_Editor.INPUT____COMPONENT_ID, this.mComponent.getId());
	//this.mActivity.startActivityForResult(intent, 0);
    }


    public void delete() {
	// TODO Auto-generated method stub

    }


}
