package com.rdupuis.amikcal2.commons;

import com.rdupuis.amikcal2.Qty.Qty;

import java.util.ArrayList;

public interface HasEquivalences {
	public ArrayList<Qty> mEquivalences = new ArrayList<Qty>();

	public ArrayList<Qty> getEquivalences();
	public void setEquivalences(ArrayList<Qty> equivalences);

}
