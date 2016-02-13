package com.rdupuis.gamingtools.components.physics;


import android.util.Log;

import com.rdupuis.gamingtools.components.Composite;

import java.util.ArrayList;
import java.util.HashMap;

public class ColliderManager {


    //liste des boites de collision
    private ArrayList<CollisionBox> mCollisionBoxList;

    //Liste des objets en collision
    //cette liste est réévaluée à chaque Frame
    private HashMap<Collidable, Collidable> mCollisionList;

    /**
     * Constructeur
     */
    public ColliderManager() {
        mCollisionBoxList = new ArrayList<CollisionBox>();
        mCollisionList = new HashMap<Collidable, Collidable>();
    }

    /**
     * Initialisation des boites de collision d'après
     * les objets chargés dans la scène
     *
     * @param listOfComponents
     */
    public void initBoxes(ArrayList<Composite> listOfComponents) {
        //on vide la liste
        mCollisionBoxList.clear();

        //on crée une boite de collision pour chaque objet qui en necessitent une
        for (Composite composite : listOfComponents) {
            Log.i("amikcal", composite.toString());
            //gameObject, default Offset X, default Offset Y
            if (composite instanceof Collidable) {
                Collidable collidable = (Collidable) composite;
                CollisionBox box = new CollisionBox(collidable);
                this.mCollisionBoxList.add(box);
            }

        }
    }


    private void updateWorldVertices() {
        for (CollisionBox box : this.mCollisionBoxList) {
            box.updateWorldVertices();
        }
    }

    /**
     * mise à jour de la liste des objets entrant en collision
     */
    public void updateCollisionsList() {

        //on met à jour les WorldVertices
        updateWorldVertices();

        //on commence par effacer la liste
        this.mCollisionList.clear();

        //pour toutes les boites, on vérifie si elles entrent en collision
        for (CollisionBox box1 : this.mCollisionBoxList) {
            //Si la Box existe mais que l'on ne souhaites plus tester les collisions
            //on ne traitera pas cet objet
            if (box1.getCollider().isCollisionCheckingEnabled()) {
                for (CollisionBox box2 : this.mCollisionBoxList) {

                    //on évite que la boite se compare à elle-même
                    if (box1 != box2) {
                        if (SAT.isColide(box1, box2)) {
                            this.mCollisionList.put(box1.getCollider(), box2.getCollider());
                        }


                    }
                }
            }
        }

    }

    public boolean isCollide(Collidable A, Collidable B) {
        return this.mCollisionList.get(A) == B;

    }


}
