package com.org.serviceimpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.model.Pharmacy;
import com.org.model.PharmacyLocationResponse;
import com.org.model.UserLocationRequestContract;
import com.org.service.PharmacyDetailsService;
import com.org.service.PharmacyRetrievalService;
import com.org.util.BusinessUtil;

@Service
public class PharmacyRetrievalServiceImpl implements PharmacyRetrievalService {

	@Autowired
	private PharmacyDetailsService pharmacyDetailService;
	/**
	 * Service method to return the nearest pharmacy from the based on the latitude and longitude of the user
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */

	public PharmacyLocationResponse retrieveNearestPharmacy( UserLocationRequestContract requestContract) {
		
		// Load the Pharmacy List from the resource "pharmacies.csv"
		List<Pharmacy> pharamcies = pharmacyDetailService.getAllPharamcies();
		
		calculateAndSortPharmcyDistancesFromUser(requestContract, pharamcies);
		Pharmacy pharmacy = pharamcies.get(0);
		
		return convertToUserLocationResponseContract(pharmacy);
		
	}
	
	/**
	 * Service method to return the pharmacy List sorted nearest from the based on the latitude and longitude of the user
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public List<PharmacyLocationResponse> retrievePharmaciesSortedBasedOnDistance(UserLocationRequestContract requestContract) {
			
			List<Pharmacy> pharamcies = pharmacyDetailService.getAllPharamcies();
			List<PharmacyLocationResponse> pharmacyResponse = new ArrayList<>();
			calculateAndSortPharmcyDistancesFromUser(requestContract, pharamcies); 
			pharamcies.forEach(pharmacy -> pharmacyResponse.add(this.convertToUserLocationResponseContract(pharmacy)));
			return pharmacyResponse;
	}

	/**
	 *  helper method to convert Pharmacy object to API response object
	 * @param pharmacy
	 * @return PharmacyLocationResponseContract
	 */
	private PharmacyLocationResponse convertToUserLocationResponseContract(Pharmacy pharmacy) {
		
		PharmacyLocationResponse pharmacyResponse = new PharmacyLocationResponse();
		pharmacyResponse.setPharmcyName(pharmacy.getPharmacyName());
		pharmacyResponse.setPharamcyNameWithAddress(BusinessUtil.setPharamcyAddress(pharmacy));
		pharmacyResponse.setDistance(pharmacy.getDistanceFromUser()
				.setScale(2, RoundingMode.HALF_UP));
		
		return pharmacyResponse;
	}
	
	/**
	 * helper method to calculate the pharmacy distance and sort according to the nearest from the user location
	 * @param requestContract
	 * @param pharamcies
	 */
	private void calculateAndSortPharmcyDistancesFromUser(final UserLocationRequestContract requestContract,
			List<Pharmacy> pharamcies) {
		
		pharamcies.forEach(pharmacy -> pharmacy.setDistanceFromUser(this.calculatePharmacyDistanceFromUserLocation(
				requestContract, pharmacy)));
		pharamcies.sort((Pharmacy p1, Pharmacy p2) -> p1.getDistanceFromUser().compareTo(
				p2.getDistanceFromUser()));
		
	}

	/**
	 * Method to calculate the distance from the user location and pharmacy location. Uses  Haversine formula  
	 * @param request
	 * @param pharmacy
	 * @return
	 */
	private BigDecimal calculatePharmacyDistanceFromUserLocation(UserLocationRequestContract request, Pharmacy pharmacy) {
		BigDecimal distance = BigDecimal.ZERO;
		
		double userLatitude = request.getUserLatitude();
		double userLongitude = request.getUserLongitude();
		double pharmacyLatitude = pharmacy.getStoreLatitude();
		double pharmacyLongitude = pharmacy.getStoreLongitude();
		
		// convert degrees to radians ((PI / 180 )*degrees i.e.(3.141592653589793/180)* lat/long value) 
		userLatitude = Math.toRadians(userLatitude); 
		userLongitude = Math.toRadians(userLongitude); 
		pharmacyLatitude = Math.toRadians(pharmacyLatitude); 
		pharmacyLongitude = Math.toRadians(pharmacyLongitude); 
  
        // Haversine formula  
        double differenceinLatitudes = pharmacyLatitude - userLatitude;  
        double differenceinLongitudes = pharmacyLongitude - userLongitude;
        
        double a = Math.pow(Math.sin(differenceinLatitudes / 2), 2) 
                 + Math.cos(userLatitude) * Math.cos(pharmacyLatitude) 
                 * Math.pow(Math.sin(differenceinLongitudes / 2),2); 
              
        double c = 2 * Math.asin(Math.sqrt(a)); 
  
        // Radius of earth in miles. incase for KMS multiply by 6371 
        double r = 3956; 
  
        // calculate the result 
        distance = BigDecimal.valueOf(c * r);
		return distance;
	}

}
