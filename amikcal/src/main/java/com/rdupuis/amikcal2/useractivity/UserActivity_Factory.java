package com.rdupuis.amikcal2.useractivity;

import com.rdupuis.amikcal2.useractivity.lunch.UserActivity_Lunch;
import com.rdupuis.amikcal2.useractivity.move.UserActivity_Move;
import com.rdupuis.amikcal2.useractivity.weight.UserActivity_Weight;

public final class UserActivity_Factory {

    public static UserActivity create(UA_CLASS_CD ua_class_cd) {

        switch (ua_class_cd) {
            case LUNCH:
                return new UserActivity_Lunch();
            case MOVE:
                return new UserActivity_Move();
            case WEIGHT:
                return new UserActivity_Weight();

        }
        return null;
    }
}
