package com.rdupuis.gamingtools.components.button;

import android.os.SystemClock;

import com.gamingtools.rdupuis.gamingtools.R;
import com.rdupuis.gamingtools.components.GroupOfGameObject;
import com.rdupuis.gamingtools.components.shapes.GlChar;
import com.rdupuis.gamingtools.components.shapes.GlFont;
import com.rdupuis.gamingtools.components.shapes.GlString;
import com.rdupuis.gamingtools.components.shapes.Rectangle2D;
import com.rdupuis.gamingtools.components.shapes.Shape;
import com.rdupuis.gamingtools.components.texture.Texture;
import com.rdupuis.gamingtools.enums.DrawingMode;
import com.rdupuis.gamingtools.inputs.UserFinger;
import com.rdupuis.gamingtools.interfaces.Clikable;

import java.util.ArrayList;

/**
 * un bouton avec texte
 */

public class ButtonWithText extends GroupOfGameObject implements Clikable {
    public enum ButtonStatus {
        UP, DOWN
    }

    private final String BTN__A_TAG = "BTN_A";
    private final String TEXT = "TEXT";
    private Rectangle2D rectangle2D_A;
    private GlString mText;

    public Texture textureUp;
    public Texture textureDown;

    public ButtonStatus status;
    private float lastTap;
    private float elapsedTime;
    private boolean listening;
    private boolean ON_CLICK_FIRE;
    private final float DELAY_BTWN_TAP = 200; //200ms
    private final float ON_LONG_CLICK_DELAY = 1000;

    private final ArrayList<GLButtonListener> eventListenerList = new ArrayList<GLButtonListener>();

    public ButtonWithText(float x, float y, float witdth, float height, Texture textureUp, Texture textureDown,Texture fontmap) {

        this.textureUp = textureUp;
        this.textureDown = textureDown;

        //définition du premier rectangle : il s'agit du bouton sur lequel on appuie
        this.rectangle2D_A = new Rectangle2D(DrawingMode.FILL);
        this.rectangle2D_A.enableTexturing();

        //il s'agit de la taille initiale. au final, elle va être propotionelle au
        //GroupOfGameObject.
        this.rectangle2D_A.setWidth(1f);
        this.rectangle2D_A.setHeight(1f);
        this.rectangle2D_A.enableCollisions();
        this.rectangle2D_A.setTagName(BTN__A_TAG + ":A");

        //Définition du texte.
        this.mText = new GlString();

        //font
        GlFont glFont = new GlFont();
        //TODO : intéger la map directement dans la font.
        glFont.setMap(fontmap);
        mText.setGlFont(glFont);


        //on ajoute les 2 boutons dans la liste des composants.
        this.add(rectangle2D_A);
        this.add(mText);

        this.status = ButtonStatus.UP;
        this.setX(x);
        this.setY(y);
        this.setHeight(height);
        this.setWidth(witdth);

        this.listening = false;

        //this.isStatic = false;

    }

    public void addGLButtonListener(GLButtonListener glButtonListener) {
        this.eventListenerList.add(glButtonListener);
    }

    public void setText(String string){

                mText.setText(string);
    }


    @Override
    public void update() {

        //this.getGameObjectsList().get(RECT_A_INDX).setTexture(this.textureUp);

        rectangle2D_A.setTexture(this.textureUp);

        //  Log.e("button", "on update");


        if (SystemClock.elapsedRealtime() - this.lastTap != DELAY_BTWN_TAP) {

            Shape uf = (Shape) this.getScene().getGOManager().getGameObjectByTag(UserFinger.USER_FINGER_TAG);

            if (this.getScene().getColliderManager().isCollide(uf,rectangle2D_A)) {
                //        Log.e("button", "set texture down");
                rectangle2D_A.setTexture(this.textureDown);
                this.status = ButtonStatus.DOWN;

                // si je n'étais en train d'écouler, j'initialise le compteur delai
                if (!this.listening) {
                    lastTap = SystemClock.elapsedRealtime();
                    this.elapsedTime = 0f;
                    this.ON_CLICK_FIRE = false;
                    //on commence à écouter ce que fait l'utilisateur
                    this.listening = true;
                } else {

                    // si je suis en train d'écouter, l'incrémente le compteur de temps
                    // pour avoir une idée du temp laissé appuyé sur le bouton
                    //ceci pour detecter les longClick
                    this.elapsedTime = SystemClock.elapsedRealtime() - this.lastTap;
                }


            } else {
                rectangle2D_A.setTexture(textureUp);
                this.status = ButtonStatus.UP;

            }
        }
        //avec les nouvelle données, je check si on vient de faire un click
        this.checkClick();


         }

    private void checkClick() {
        //si on est en train d'écouter ce que fait l'utilisateur
        if (this.listening) {



            //si l'utilisateur a levé le doigt
            if (this.status == ButtonStatus.UP) {
                //on a levé le doigt avant de délai d'un long click
                //c'est donc un click
                if (elapsedTime < ON_LONG_CLICK_DELAY) {
                    onClick();
                    this.stopListening();
                }
                //sinon, on arrête d'écouter
                this.stopListening();
            }

            //si l'utilisateur a toujours le doigt appuyé sur l'écran
            // et qu'il a le doit appuyé depuis le temps necessaire pour un longClick
            if ((this.status == ButtonStatus.DOWN) && elapsedTime > ON_LONG_CLICK_DELAY && !ON_CLICK_FIRE) {

                ON_CLICK_FIRE = true;
                //on appel la méthode onclick
                onLongClick();

                this.elapsedTime = 0f;
            }
        }
    }

    private void stopListening() {
        this.listening = false;




    }




    /**
     * pour tous les objets qui écoutent le onClick(), on leur passe
     * l'info
     */
    public void onClick() {
        for (GLButtonListener listener : eventListenerList) {
            listener.onClick();

        }
    }


    public void onLongClick() {
        for (GLButtonListener listener : eventListenerList) {
            listener.onLongClick();
        }
    }


}
