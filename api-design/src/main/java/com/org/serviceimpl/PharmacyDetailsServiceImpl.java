package com.org.serviceimpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.org.model.Pharmacy;
import com.org.service.PharmacyDetailsService;

@Service
public class PharmacyDetailsServiceImpl implements PharmacyDetailsService {

	public static final String COMMA_DELIMITER = ",";
	public static final Logger logger = Logger.getLogger(PharmacyDetailsServiceImpl.class);

	@Override
	public List<Pharmacy> getAllPharamcies() {
		List<Pharmacy> pharmaciesList = new ArrayList<>();
		BufferedReader br = null;
		try {

			br = new BufferedReader(new FileReader("src/main/resources/pharmacies.csv"));
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(COMMA_DELIMITER);
				Pharmacy store = new Pharmacy(values[0].trim(), values[1], values[2], values[3], new Integer(values[4]),
						new Double(values[5]), new Double(values[6]));
				pharmaciesList.add(store);
			}
	
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return pharmaciesList;
	}

}
