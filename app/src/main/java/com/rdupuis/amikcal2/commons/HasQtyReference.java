package com.rdupuis.amikcal2.commons;

import com.rdupuis.amikcal2.Qty.Qty;

public interface HasQtyReference {


	public Qty qtyReference = new Qty();
	
	public void setQtyReference(Qty qtyReference);
	public Qty getQtyReference();

}
