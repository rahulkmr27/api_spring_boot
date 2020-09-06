package com.org.service;

import java.util.List;

import com.org.model.PharmacyLocationResponse;
import com.org.model.UserLocationRequestContract;

public interface PharmacyRetrievalService {

	/**
	 *  Service to determine the nearest pharmacy based on the latitude and longitude of the user.
	 * @param UserLocationRequestContract
	 * @return PharmacyLocationResponseContract
	 */
	
	public PharmacyLocationResponse retrieveNearestPharmacy(UserLocationRequestContract requestContract);
	
	/**
	 * Service which returns the list of pharmacies sort based on nearest distance to the user
	 * @param UserLocationRequestContract
	 * @return List<PharmacyLocationResponseContract>
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	
	public List<PharmacyLocationResponse> retrievePharmaciesSortedBasedOnDistance(UserLocationRequestContract requestContract);
}
