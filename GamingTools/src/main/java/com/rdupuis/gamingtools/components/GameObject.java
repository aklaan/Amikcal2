package com.rdupuis.gamingtools.components;

import com.rdupuis.gamingtools.components.shapes.Shape;

import java.util.ArrayList;


public class GameObject extends AbstractGameObject implements Composite{

    //Tag de l'objet
    private String mTagName;

    //top pour activer/désactiver le rendu de l'objet
    public Boolean mVisibility;

    //scène auquel appartient l'objet
    private Scene mScene;

    //Couleur ambiante de l'objet R,G,B,A
    private ColorRGBA ambiantColor = new ColorRGBA();

    // top permettant de savoir si l'objet est statique à l'écran ou s'il
    // a la possibilité d'être en mouvement. ceci va servir
    // pour le calcul des collisions
    public Boolean isStatic = true;

    //Top pour activer/désactiver la gestion des colissions
    public Boolean canCollide = false;

    // coordonnées du centre de l'objet
    public float X = 0;
    public float Y = 0;
    public float Z = 0;

    //Taille de l'objet
    private float width;


    private float height;

    //liste des objets à écouter
    public ArrayList<Shape> mShapeToListenList;

    public float angleRADX = 0.0f;
    public float angleRADY = 0.0f;
    public float angleRADZ = 0.0f;


    /******************************************************************
     * getter & setter
     ****************************************************************/

    public float getHeight() {
        return height;
    }
    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }
    public void setWidth(float width) {

        this.width = width;
    }

    public void setScene(Scene mScene) {
        this.mScene = mScene;
    }
    public Scene getScene() {
        return this.mScene;
    }


    public String getTagName() {
        return mTagName;
    }
    public void setTagName(String tagid) {
        mTagName = tagid;
    }

    public Boolean getVisibility() {
        return mVisibility;
    }
    public void setVisibility(Boolean mVisibility) {
        this.mVisibility = mVisibility;
    }

    @Override
    public ColorRGBA getAmbiantColor() {
        return ambiantColor;
    }

    public float[] getRGBA() {
        float[] result = new float[4];
        result[0] = this.getAmbiantColor().getRed();
        result[1] = this.getAmbiantColor().getGreen();
        result[2] = this.getAmbiantColor().getBlue();
        result[3] = this.getAmbiantColor().getAlpha();


        return result;
    }

    public void setAmbiantColor(ColorRGBA ambiantColor) {
        this.ambiantColor = ambiantColor;
    }

    public float getAlpha() {
        return this.getAmbiantColor().getAlpha();
    }

    public void setCoord(float x, float y) {
        this.X = x;
        this.Y = y;
    }

    /**
     * @param x
     * @param y
     * @param z
     */
    public void setCoord(float x, float y, float z) {
        this.X = x;
        this.Y = y;
        this.Z = z;
    }

    public float getX() { return X; }
    public void setX(float x) { this.X = x; }

    public float getY() {return Y; }
    public void setY(float y) {
        this.Y = y;
    }


    public void setZ(float z) { this.Z = z; }
    public float getZ() {return Z; }

    /**
     * @return
     */
    public ArrayList<Shape> getGameObjectToListenList() {
        return this.mShapeToListenList;
    }


    /********************************************************************
     * Constructeur
     *******************************************************************/
    public GameObject() {

        //visible par défaut
        setVisibility(true);

    }


    @Override
    public void updateModelView() {

    }



    public ArrayList<Composite> getComponent(){
        ArrayList<Composite> result = new ArrayList<Composite>();
        result.add(this);
        return result;
    }


    /**
     * Activer la gestion des colisions
     */
    public void enableColision() {
        this.canCollide = true;
    }

    /**
     * désactiver la gestion des colisions
     */
    public void disableColision() {
        this.canCollide = false;

    }

    /**
     * getter & setter
     *
     * @return
     */

    @Override
    public void update() {

    }




    /**
     * @return
     * @throws CloneNotSupportedException
     */
    public Shape clone() throws CloneNotSupportedException {
        Shape gameobject = (Shape) super.clone();

        gameobject.mShapeToListenList = new ArrayList<Shape>();


        // on r�initialise le lien de parent� avec l'animation
        /**
         if (gameobject.getAnimation() != null) {
         Animation anim = (Animation) gameobject.getAnimation().clone();

         anim.setParent(gameobject);

         gameobject.setAnimation(anim);
         }

         // si l'objet source peu entrer en collision on
         // red�fini un nouvelle boite de colision pour la cible
         // sinon elle va avoir la m�me que la source
         if (gameobject.canCollide) {
         gameobject.enableColision();

         }
         */
        return gameobject;
    }

    public void setAlpha(float alpha) {

        this.getAmbiantColor().setAlpha(alpha);
    }


    /**
     * Mise à jour de la ModelView pour prendre en compte les
     * modification apportées à l'ojet
     * taille - position - rotation
     * <p/>
     * /!\ l'ordre où on applique les transformation et hyper important
     * il faut toujours faire : translation*rotation*scale
     */


}
