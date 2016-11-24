package com.routeone.interview;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.routeone.interview.impl.ReceiptImpl;
import com.routeone.interview.util.PriceComparator;
import com.routeone.interview.util.ReceiptUtil;

public class StoreRegister {

	// Adds all the elements from csv file
	List<OrderItem> unsortedPriceList = new ArrayList<OrderItem>();

	public void loadInventory(File inventoryFile) {
		BufferedReader fileReader = null;

		// Delimiter used in CSV file
		final String DELIMITER = ",";
		try {
			String line = "";
			// Create the file reader
			fileReader = new BufferedReader(new FileReader(inventoryFile));

			// Read the file line by line
			while ((line = fileReader.readLine()) != null) {
				// Get all tokens available in line
				String[] tokens = line.split(DELIMITER);
				OrderItem orderItem = new OrderItem();
				for (int i = 0; i <= tokens.length; i++) {
					orderItem.setItemName(tokens[0]);
					orderItem.setItemPrice(Double.valueOf(tokens[1]));
				}
				unsortedPriceList.add(orderItem);
			}

		} catch (Exception e) {
			e.printStackTrace();
			unsortedPriceList = null;
			System.out.println("There is some problem reading the file");
			// throw new
			// ReceiptException("There is some problem reading the file");
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
				unsortedPriceList = null;
				System.out.println("There is some problem reading the file");
			}
		}
	}

	public Receipt checkoutOrder(List<String> items) {
		ReceiptImpl receiptImpl = new ReceiptImpl();

		if (unsortedPriceList != null) {

			List<OrderItem> sortedPriceList = new ArrayList<OrderItem>();
			List<String> finalOrderList = new ArrayList<String>();
			double totalPrice = 0.0;

			// Loading inventory from csv file
			loadInventory(new File("sample-inventory.csv"));

			for (Iterator iterator = items.iterator(); iterator.hasNext();) {
				String orderName = (String) iterator.next();

				for (Iterator unOrdIterator = unsortedPriceList.iterator(); unOrdIterator
						.hasNext();) {
					OrderItem orderItem = (OrderItem) unOrdIterator.next();
					if (orderName.equalsIgnoreCase(orderItem.getItemName())) {
						sortedPriceList.add(orderItem);
					}
				}
			}

			// Sorting the list based on the comparator
			Collections.sort(sortedPriceList, new PriceComparator());

			// Getting the final Ordered list from OrderItem list
			for (Iterator iterator = sortedPriceList.iterator(); iterator
					.hasNext();) {
				OrderItem orderItem = (OrderItem) iterator.next();
				totalPrice = totalPrice + (Double) orderItem.getItemPrice();
				finalOrderList.add(orderItem.getItemName());
			}
			// Formatting the total price
			String formattedTotalPrice = ReceiptUtil.formattedValue(totalPrice);

			if (finalOrderList.isEmpty()) {
				System.out
						.println("No matching records found in the inventory");
				return null;
			}
			receiptImpl.setFormattedTotal(formattedTotalPrice);
			receiptImpl.setOrderedItems(finalOrderList);
		} else {
			System.out.println("There is some problem reading the file");
			return null;
		}

		return receiptImpl;
	}

	// Main method to test the functionality
	public static void main(String[] args) {
		StoreRegister register = new StoreRegister();
		List<String> list = new ArrayList<String>();
		list.add("PC1033");
		list.add("GenericMotherboard");
		list.add("Mouse");
		list.add("LCD");
		System.out.println("Input : " + list);
		Receipt receipt = register.checkoutOrder(list);
		System.out.println("Receipt.getFormattedTotal(): "
				+ receipt.getFormattedTotal());
		System.out.println("Receipt.getOrderedItems() : "
				+ receipt.getOrderedItems());
		
		/*Output
		Input : [PC1033, GenericMotherboard, Mouse, LCD]
		Receipt.getFormattedTotal(): $738.98
		Receipt.getOrderedItems() : [GenericMotherboard, LCD, Mouse, PC1033]*/
	}
}