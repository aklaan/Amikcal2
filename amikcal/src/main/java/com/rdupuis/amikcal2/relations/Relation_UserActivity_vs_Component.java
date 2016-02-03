package com.rdupuis.amikcal2.relations;

import android.content.ContentResolver;

import com.rdupuis.amikcal2.commons.AppConsts;
import com.rdupuis.amikcal2.components.Component;
import com.rdupuis.amikcal2.useractivity.UserActivity;

public class Relation_UserActivity_vs_Component extends Relation {
    private UserActivity mUserActivity;
    private Component mComponent;
    private long _id;

    public Relation_UserActivity_vs_Component(UserActivity userActivity, Component component) {
	this.mUserActivity = userActivity;
	this.mComponent = component;
	this._id = AppConsts.NO_ID;
    }

    @Override
    public String getParty1() {

	return String.valueOf(mUserActivity.getDatabaseId());
    }

    @Override
    public String getParty2() {

	return String.valueOf(mComponent.getDatabaseId());
    }

    @Override
    public long getDatabaseId() {

	return _id;
    }

    @Override
    public void setDatabaseId(long rel_id) {
	this._id = rel_id;

    }

    @Override
    public REL_TYP_CD getRelationClass() {
	// TODO Auto-generated method stub
	return REL_TYP_CD.UA_COMP;
    }


}
