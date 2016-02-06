package com.rdupuis.gamingtools.components.physics;


import java.util.ArrayList;
import java.util.HashMap;

import com.rdupuis.gamingtools.components.AbstractGameObject;
import com.rdupuis.gamingtools.components.CompositeShape;
import com.rdupuis.gamingtools.components.GameObject;
import com.rdupuis.gamingtools.components.shapes.Shape;
import com.rdupuis.gamingtools.providers.GameObjectManager;

public class ColliderManager {


    //liste des boites de collision
    private ArrayList<CollisionBox> mCollisionBoxList;

    //Liste des objets en collision
    //cette liste est réévaluée à chaque Frame
    private HashMap<Collider, Collider> mCollisionList;

    /**
     * Constructeur
     */
    public ColliderManager() {
        mCollisionBoxList = new ArrayList<CollisionBox>();
        mCollisionList = new HashMap<Collider, Collider>();
    }

    /**
     * Initialisation des boites de collision d'après
     * les objets chargés dans la scène
     *
     * @param gom
     */
    public void initBoxes(GameObjectManager gom) {
        //on vide la liste
        mCollisionBoxList.clear();

        //on crée une boite de collision pour chaque objet qui en necessitent une
        for (Collider collider : gom.getActiveColliderList()) {
            //gameObject, default Offset X, default Offset Y
            CollisionBox box = new CollisionBox(collider);
            this.mCollisionBoxList.add(box);
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
            if (box1.getCollider().getCollisionStatus()) {
                for (CollisionBox box2 : this.mCollisionBoxList) {
                    if (box2.getCollider().getCollisionStatus()) {
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

    }

    public boolean isCollide(Shape shapeA, Shape shapeB) {
        return this.mCollisionList.get(shapeA) == shapeB;

    }


}
