package com.routeone.interview.util;

import java.util.Comparator;

import com.routeone.interview.OrderItem;

//Comparator class is used to compare the price and if the prices are same, compare the component name
public class PriceComparator implements Comparator<OrderItem> {

	public PriceComparator() {
	}


	/**
	 * The method compares two OrderItems
	 * 
	 * @param OrderItem a
	 * @param OrderItem b
	 * @return
	 */
	public int compare(OrderItem a, OrderItem b) {
		int retVal = 0;
		// To check if the price value is different
		if (a.getItemPrice() != b.getItemPrice()) {
			if (a.getItemPrice() >= b.getItemPrice()) {
				retVal = -1;
			} else {
				retVal = 1;
			}
		}
		// If the price values are same, compare the item name
		if (a.getItemPrice() == b.getItemPrice()) {
			retVal = compareString(a.getItemName(), b.getItemName());
		}
		return retVal;
	}

	/**
	 * The method compares the item name in case of same price value
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	private int compareString(String str1, String str2) {
		int x = String.CASE_INSENSITIVE_ORDER.compare(str1, str2);
		if (x == 0) {
			x = str1.compareTo(str2);
		}
		return x;
	}

}
