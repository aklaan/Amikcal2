package com.rdupuis.amikcal2.components;

import android.os.Parcelable;

import java.util.ArrayList;

import com.rdupuis.amikcal2.Qty.Qty;
import com.rdupuis.amikcal2.commons.ManagedElement;
import com.rdupuis.amikcal2.energy.EnergySource;
import com.rdupuis.amikcal2.relations.Relation;
import com.rdupuis.amikcal2.relations.REL_TYP_CD;


/**
 * Un composant = une relation entre une quantité et une source d'énergie.
 * 
 * par exemple :
 * 
 * 150 grammes de pommes
 * 
 * 
 * @author Rodolphe
 * 
 */
public abstract class Component extends Relation implements ManagedElement,Parcelable{

    public  abstract void setEnergy(EnergySource energy) ;

    public abstract EnergySource getEnergy() ;

    public abstract Qty getQty() ;

    public abstract void setQty(Qty mQty) ;

    public abstract REL_TYP_CD getRelationClass() ;

    public abstract long getParentId() ;
    public abstract void setParentId(long databaseId);
    
}