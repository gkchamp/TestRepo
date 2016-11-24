package com.routeone.interview.impl;

import java.util.List;

import com.routeone.interview.Receipt;


/**
 * Class implements Receipt interface
 *
 */
public class ReceiptImpl implements Receipt {

	private String formattedTotal = null;
	private List<String> orderedItems = null;

	public ReceiptImpl() {
		// TODO Auto-generated constructor stub
	}

	public String getFormattedTotal() {
		return formattedTotal;
	}

	public void setFormattedTotal(String formattedTotal) {
		this.formattedTotal = formattedTotal;
	}



	public void setOrderedItems(List<String> orderedItems) {
		this.orderedItems = orderedItems;
	}

	public List<String> getOrderedItems() {
		return orderedItems;
	}

}
