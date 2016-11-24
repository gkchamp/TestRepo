package com.routeone.interview.util;

import java.text.DecimalFormat;

public class ReceiptUtil {

	/**
	 * Method formats the total value in a particular format
	 * 
	 * @param totalVal
	 * @return
	 */
	public static String formattedValue(double totalVal) {
		String totalPrice = null;
		String formatStr = "#,###,##0.00";
		DecimalFormat df = new DecimalFormat(formatStr);
		String formattedPrice = null;

		formattedPrice = df.format(totalVal);
		totalPrice = "$" + formattedPrice;
		return totalPrice;
	}
}
