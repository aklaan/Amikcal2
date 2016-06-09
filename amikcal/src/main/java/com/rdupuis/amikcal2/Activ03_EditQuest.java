package com.rdupuis.amikcal2;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rdupuis.amikcal2.scenes.Scene02_EditUser;
import com.rdupuis.gamingtools.components.OpenGLActivity;

public class Activ03_EditQuest extends OpenGLActivity {

    public static final byte UPDATE_FPS = 1;
    public static final byte ON_CLICK_OK = 2;
    public static final byte ON_CLICK_CLEAR = 3;
    public static final byte ON_CLICK = 4;
    private int tvId;
    private int textViewUserEntryId;
    private final String defaultDate = "DD/MM/YY";
    private String birthDateEntry = "";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //définition de la scène à rendre
        mGLSurfaceView.setRenderer(new Scene02_EditUser(this));

        //------------------
        TextView tv = new TextView(this);
        tvId = View.generateViewId();
        tv.setId(tvId);
        tv.setText("FPS");
        tv.setTag("FPS");
        tv.setTextColor(Color.RED);
        addContentView(tv, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        //----------
        this.textViewUserEntryId = View.generateViewId();
        TextView userEntry = new TextView(this);
        userEntry.setId(textViewUserEntryId);
        userEntry.setText("00/00/00");
        userEntry.setTextColor(Color.WHITE);
        userEntry.setX(100);
        userEntry.setY(400);
        userEntry.setTextSize(50f);
        addContentView(userEntry, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));


        initHandler();
    }


    private void initHandler() {
        //je crée un Handler et je reféfini la méthose handleMessage
        //de cette manière, je peux capter des informations qui sont émises par d'autres
        //thread pour pouvoir effectuer des actions dans cette scène.
        //notamemnt, la mise à jour des View (textView...etc..)
        //car seul le Thread de la scène peu mettre à jour les vue de la scène
        setHandler(new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message inputMessage) {
                // Gets the image task from the incoming Message object.
                //PhotoTask photoTask = (PhotoTask) inputMessage.obj;

                switch (inputMessage.what) {

                    case UPDATE_FPS:
                        TextView tv = (TextView) findViewById(tvId);

                        int value = inputMessage.getData().getInt(Scene02_EditUser.BUNDLE_FPS_VALUE);

                        tv.setText("FPS: " + String.valueOf(value));
                        break;
                    case ON_CLICK:

                        char entry = inputMessage.getData().getChar((Scene02_EditUser.BUNDLE_ENTRY_VALUE));

                        //on ne doit pas pouvoir entrer plus de 6 chiffres
                        if (birthDateEntry.length() < 6) {
                            birthDateEntry = birthDateEntry + String.valueOf(entry);
                            refreshBirthdate();
                        }
                        break;
                    case ON_CLICK_CLEAR:
                        birthDateEntry = "";
                        refreshBirthdate();
                        break;
                    case ON_CLICK_OK:
                        //TODO
                        break;
                    default:
//                        onClick_bt_amount();

                }

            }
        });

    }


    private void callMenu() {
        Intent intent = new Intent(this,
                MainActivity.class);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }


    private void refreshBirthdate()

    {
        String value = "";


        switch (birthDateEntry.length()) {
            case 0:
                value = "--/--/--";
                break;
            case 1:
                value = String.valueOf(birthDateEntry.charAt(0))
                        + "-/--/--";
                break;
            case 2:
                value = String.valueOf(birthDateEntry.charAt(0))
                        + String.valueOf(birthDateEntry.charAt(1))
                        + "/--/--";
                break;
            case 3:
                value = String.valueOf(birthDateEntry.charAt(0))
                        + String.valueOf(birthDateEntry.charAt(1))
                        + "/"
                        + String.valueOf(birthDateEntry.charAt(2))
                        + "-/--";
                break;

            case 4:
                value = String.valueOf(birthDateEntry.charAt(0))
                        + String.valueOf(birthDateEntry.charAt(1))
                        + "/"
                        + String.valueOf(birthDateEntry.charAt(2))
                        + String.valueOf(birthDateEntry.charAt(3))
                        + "/--";
                break;

            case 5:
                value = String.valueOf(birthDateEntry.charAt(0))
                        + String.valueOf(birthDateEntry.charAt(1))
                        + "/"
                        + String.valueOf(birthDateEntry.charAt(2))
                        + String.valueOf(birthDateEntry.charAt(3))
                        + "/"
                        + String.valueOf(birthDateEntry.charAt(4))
                        + "-";
                break;
            case 6:
                value = String.valueOf(birthDateEntry.charAt(0))
                        + String.valueOf(birthDateEntry.charAt(1))
                        + "/"
                        +String.valueOf( birthDateEntry.charAt(2))
                        + String.valueOf(birthDateEntry.charAt(3))
                        + "/"
                        +String.valueOf(birthDateEntry.charAt(4))
                        +String.valueOf(birthDateEntry.charAt(5));
                break;
        }


        TextView userEntry = (TextView) findViewById(textViewUserEntryId);
        userEntry.setText(value);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        //mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        //mDrawerToggle.onConfigurationChanged(newConfig);
    }


}