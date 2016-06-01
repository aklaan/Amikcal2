package com.rdupuis.amikcal2;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.widget.TextView;

import com.rdupuis.amikcal2.energy.Act_Food_Editor;
import com.rdupuis.amikcal2.equivalence.Act_EquivalenceList;
import com.rdupuis.amikcal2.scenes.Scene00_SplashScreen;
import com.rdupuis.amikcal2.scenes.Scene01_MainMenu;
import com.rdupuis.gamingtools.components.MySurfaceView;
import com.rdupuis.gamingtools.components.OpenGLActivity;
import com.rdupuis.gamingtools.components.keyboard.Keyboard;


/**
 * This example illustrates a common usage of the DrawerLayout widget in the
 * Android support library.
 * <p/>
 * <p>
 * When a navigation (left) drawer is present, the host activity should detect
 * presses of the action bar's Up affordance as a signal to open and close the
 * navigation drawer. The ActionBarDrawerToggle facilitates this behavior. Items
 * within the drawer should fall into one of two categories:
 * </p>
 * <p/>
 * <ul>
 * <li><strong>View switches</strong>. A view switch follows the same basic
 * policies as list or tab navigation in that a view switch does not create
 * navigation history. This pattern should only be used at the root activity of
 * a task, leaving some form of Up navigation active for activities further down
 * the navigation hierarchy.</li>
 * <li><strong>Selective Up</strong>. The drawer allows the user to choose an
 * alternate parent for Up navigation. This allows a user to jump across an
 * app's navigation hierarchy at will. The application should treat this as it
 * treats Up navigation from a different task, replacing the current task stack
 * using TaskStackBuilder or similar. This is the only form of navigation drawer
 * that should be used outside of the root activity of a task.</li>
 * </ul>
 * <p/>
 * <p>
 * Right side drawers should be used for actions, not navigation. This follows
 * the pattern established by the Action Bar that navigation should be to the
 * left and actions to the right. An action should be an operation performed on
 * the current contents of the window, for example enabling or disabling a data
 * overlay on top of the current content.
 * </p>
 */
public class MainActivity2 extends OpenGLActivity {


    public final static int CALL_MAIN_MENU = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //définition de la scène à rendre
        mGLSurfaceView.setRenderer(new Scene00_SplashScreen(this));
        initHandler();

        //je lance un thread qui va me lancer le menu principal dans x secondes
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep((long) 3000);
                //dans x secondes, je vais envoyer un message à cette activité pour
                //lui demander le menu principal
                Message completeMessage =
                        MainActivity2.this.mHandler.obtainMessage(MainActivity2.CALL_MAIN_MENU);
                completeMessage.sendToTarget();
            }
        }).start();
    }


    private void initHandler() {
        //je crée un Handler et je reféfini la méthose handleMessage
        //de cette manière, je peux capter des informations qui sont émises par d'autres
        //thread pour pouvoir effectuer des actions dans cette scène.
        //notamemnt, la mise à jour des View (textView...etc..)
        //car seul le Thread de la scène peu mettre à jour les vue de la scène
        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message inputMessage) {
                // Gets the image task from the incoming Message object.
                //PhotoTask photoTask = (PhotoTask) inputMessage.obj;

                switch (inputMessage.what) {

                    case CALL_MAIN_MENU:

                        callMenu();

                        break;
                    default:


                }

            }
        };

    }


    private void callMenu() {
        Intent intent = new Intent(this,
                MainMenu.class);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        //je supprime définitivement
        this.finish();
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