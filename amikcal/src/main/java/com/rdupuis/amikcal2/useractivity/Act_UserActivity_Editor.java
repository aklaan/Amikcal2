package com.rdupuis.amikcal2.useractivity;

import java.util.Calendar;

import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;

import com.rdupuis.amikcal2.commons.Act_Editor;
import com.rdupuis.amikcal2.commons.AmiKcalFactory;
import com.rdupuis.amikcal2.commons.AppConsts;
import com.rdupuis.amikcal2.commons.AppConsts.UA_CLASS_CD_MAP;
import com.rdupuis.amikcal2.commons.Manager;
import com.rdupuis.amikcal2.commons.ManagerBuilder;
import com.rdupuis.amikcal2.commons.ToolBox;
import com.rdupuis.amikcal2.data.ContentDescriptorObj;

/**
 * <b>Ecran d'édition des activitées de l'utilisateur.</b>
 * <p>
 * les activitées sont :
 * <ul>
 * <li>les repas</li>
 * <li>les activitées physiques</li>
 * <li>les pesées</li>
 * <p/>
 * </ul>
 * </p>
 *
 * @author Rodolphe Dupuis
 * @version 0.1
 */
public class Act_UserActivity_Editor extends Act_Editor {

    //zone possible dans l'intent:
    //par rapport à la classe de base EDITOR, pour les UA on a besoin de gérer une date.
    public static final String INPUT____EDITED_UA = "_edited_ua";
    private Calendar input_day;
    private UserActivity edited_UserActivity;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.edited_UserActivity = getIntent().getExtras().getParcelable(Act_UserActivity_Editor.INPUT____EDITED_UA);

    }




    /*******************************************************************************************
     * Méthode : closeActivity() ferme l'activité
     *******************************************************************************************/
    protected void closeEditor() {
        // on appelle setResult pour déclancher le onActivityResult de
        // l'activity mère.
        setResult(RESULT_OK, this.getIntent());
        // On termine l'Actvity
        finish();
    }

    public Calendar getInput_day() {
        return input_day;
    }

    public void setInput_day(Calendar input_day) {
        this.input_day = input_day;
    }

    public UserActivity getEdited_UserActivity() {
        return edited_UserActivity;
    }

    public void setEdited_UserActivity(UserActivity edited_UserActivity) {
        this.edited_UserActivity = edited_UserActivity;
    }

}