package com.org.util;

import com.org.model.Pharmacy;

public class BusinessUtil {
	//private static final String COMMA_DELIMITER = ",";
	private static final String LINE_SEPARATOR = "\n";

public static String setPharamcyAddress(Pharmacy pharmacy) {
		
		if (pharmacy == null) {
			return "";
		}
		
		StringBuilder address = new StringBuilder(getDisplayString(pharmacy.getPharmacyName()));
		//address.append(LINE_SEPARATOR);
		address.append(getDisplayString(pharmacy.getAddress()));
		//address.append(LINE_SEPARATOR);
		address.append(getDisplayString(pharmacy.getCity()));
		//address.append(LINE_SEPARATOR);
		address.append(getDisplayString(pharmacy.getState())).append(" - ");
		address.append(getDisplayString(pharmacy.getZipcode()));
		
		return address.toString();
	}

	public static String getDisplayString (Object obj) {
		if (obj == null) {
			return "";
		} else {
			return obj.toString();
		}
	}
}
