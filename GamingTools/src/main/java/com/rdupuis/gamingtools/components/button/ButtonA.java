package com.rdupuis.gamingtools.components.button;

import android.os.SystemClock;

import com.rdupuis.gamingtools.components.CompositeShape;
import com.rdupuis.gamingtools.components.shapes.Shape;
import com.rdupuis.gamingtools.components.shapes.Rectangle2D;
import com.rdupuis.gamingtools.components.texture.Texture;
import com.rdupuis.gamingtools.enums.DrawingMode;
import com.rdupuis.gamingtools.inputs.UserFinger;
import com.rdupuis.gamingtools.interfaces.Clikable;

import java.util.ArrayList;

public class ButtonA extends CompositeShape implements Clikable {
    public enum ButtonStatus {
        UP, DOWN
    }

    private final String BTN__A_TAG = "BTN_A";
    private final int RECT_A_INDX = 0;
    private final int RECT_B_INDX = 1;
    public Texture textureUp;
    public Texture textureDown;
    public Texture textureBack;
    public ButtonStatus status;
    private float lastTap;
    private float elapsedTime;
    private boolean listening;
    private boolean ON_CLICK_FIRE;
    private final float DELAY_BTWN_TAP = 200; //200ms
    private final float ON_LONG_CLICK_DELAY = 1000;

    private final ArrayList<GLButtonListener> eventListenerList = new ArrayList<GLButtonListener>();

    public ButtonA(float x, float y, float witdth, float height, Texture textureUp, Texture textureDown, Texture textureBack) {

        this.textureUp = textureUp;
        this.textureDown = textureDown;
        this.textureBack = textureBack;

        //définition du premier rectangle : il s'agit du bouton sur lequel on appuie
        Rectangle2D rectangle2D_A = new Rectangle2D(DrawingMode.FILL);
        rectangle2D_A.textureEnabled = true;
     //   rectangle2D_A.setTexture(this.textureUp);

        //il s'agit de la taille initiale. au final, elle va être propotionelle au
        //CompositeShape.
        rectangle2D_A.setWidth(.5f);
        rectangle2D_A.setHeight(.5f);
        rectangle2D_A.enableColision();
        rectangle2D_A.setTagName(BTN__A_TAG + ":A");

        //Définition du second rectangle : dans ce rectangle on va afficher un cercle
        // qui va venir rétrécir au fur et à mesure que l'on laisse le doigt appuyé
        // sur le bouton.
        Rectangle2D rectangle2D_B = new Rectangle2D(DrawingMode.FILL);
        rectangle2D_B.textureEnabled = true;
        rectangle2D_B.setTexture(this.textureBack);
        rectangle2D_B.setVisibility(false);
        rectangle2D_B.setWidth(1);
        rectangle2D_B.setHeight(1);
        rectangle2D_B.setAlpha(0);
        rectangle2D_B.setTagName(BTN__A_TAG + ":B");
        //on ajoute les 2 boutons dans la liste des composants.
        this.getShapeList().add(RECT_A_INDX, rectangle2D_A);
        this.getShapeList().add(RECT_B_INDX, rectangle2D_B);

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

    @Override
    public void update() {

        this.getShapeList().get(RECT_A_INDX).setTexture(this.textureUp);
        //  Log.e("button", "on update");
        if (SystemClock.elapsedRealtime() - this.lastTap != DELAY_BTWN_TAP) {

            Shape uf = (Shape) this.getScene().getGOManager().getGameObjectByTag(UserFinger.USER_FINGER_TAG);
            Shape rect_A = this.getShapeList().get(RECT_A_INDX);




            if (this.getScene().getColliderManager().isCollide(rect_A, uf)) {
                //        Log.e("button", "set texture down");
                this.getShapeList().get(RECT_A_INDX).setTexture(this.textureDown);
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
                this.getShapeList().get(RECT_A_INDX).setTexture(textureUp);
                this.status = ButtonStatus.UP;

            }
        }
        //avec les nouvelle données, je check si on vient de faire un click
        this.checkClick();
    }

    private void checkClick() {
        //si on est en train d'écouter ce que fait l'utilisateur
        if (this.listening) {

            Shape rect_b = this.getShapeList().get(RECT_B_INDX);
            //on rend le second rectangle visible
            rect_b.setVisibility(true);
            //on augmente son alpha
            rect_b.setAlpha(rect_b.getAlpha() + 0.1f);
            //on réduit progressivement sa taille
            rect_b.setHeight((rect_b.getHeight() < 0) ? 0 : rect_b.getHeight() - 8.0f);
            rect_b.setWidth((rect_b.getWidth() < 0) ? 0 : rect_b.getWidth() - 8.0f);

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

        Shape rect_b = this.getShapeList().get(RECT_B_INDX);
        rect_b.setVisibility(false);
        rect_b.setAlpha(0);
        //
        rect_b.setHeight(this.getHeight() );
        rect_b.setWidth(this.getWidth() );


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
