package com.rdupuis.amikcal2.commons;

import android.app.Activity;
import android.widget.Switch;

import com.rdupuis.amikcal2.Qty.Qty;
import com.rdupuis.amikcal2.Qty.Qty_Manager;
import com.rdupuis.amikcal2.components.Component;
import com.rdupuis.amikcal2.components.Component_Manager;
import com.rdupuis.amikcal2.components.food.Component_Food;
import com.rdupuis.amikcal2.components.food.Component_Food_Manager;
import com.rdupuis.amikcal2.components.move.Component_Move;
import com.rdupuis.amikcal2.components.move.Component_Move_Manager;
import com.rdupuis.amikcal2.energy.EnergySource;
import com.rdupuis.amikcal2.energy.Energy_Food_Manager;
import com.rdupuis.amikcal2.energy.Food;
import com.rdupuis.amikcal2.relations.Relation;
import com.rdupuis.amikcal2.useractivity.UserActivity;
import com.rdupuis.amikcal2.useractivity.UserActivity_Manager;
import com.rdupuis.amikcal2.useractivity.lunch.UserActivity_Lunch;
import com.rdupuis.amikcal2.useractivity.lunch.UserActivity_Lunch_Manager;
import com.rdupuis.amikcal2.useractivity.move.UserActivity_Move;
import com.rdupuis.amikcal2.useractivity.move.UserActivity_Move_Manager;
/**
 * Created by rodol on 01/09/2015.
 */
public final class ManagerBuilder {


    public static Manager build(Activity activity, ManagedElement element) {

        if (UserActivity_Lunch.class.isInstance(element)) {
            return new UserActivity_Lunch_Manager(activity);
        }


        if (UserActivity_Move.class.isInstance(element)) {
            return new UserActivity_Move_Manager(activity);
        }

        if (Component_Food.class.isInstance(element)) {
            return new Component_Food_Manager(activity);
        }

        if (Qty.class.isInstance(element)) {
            return new Qty_Manager(activity);
        }

        if (Food.class.isInstance(element)) {
            return new Energy_Food_Manager(activity);
        }

        return null;
    }






}
