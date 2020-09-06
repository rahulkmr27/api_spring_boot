package com.org.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.org.model.Pharmacy;
import com.org.model.PharmacyLocationResponse;
import com.org.model.UserLocationRequestContract;
import com.org.service.PharmacyDetailsService;
import com.org.service.PharmacyRetrievalService;

@RestController
public class PharmacyController {

	@Autowired
	private PharmacyRetrievalService retrievalService;
	@Autowired
	private PharmacyDetailsService detailService;

	@RequestMapping("/allPharamcies")
	public List<Pharmacy> retrieveAllPharamcies() {

		return detailService.getAllPharamcies();
	}

	@RequestMapping("/retrievePharmacy")
	@ResponseBody
	public PharmacyLocationResponse retrieveNearByPharmacy(@RequestParam Double userLatitude,
			@RequestParam Double userLongitude) {
		UserLocationRequestContract requestContract = new UserLocationRequestContract();
		requestContract.setUserLatitude(userLatitude);
		requestContract.setUserLongitude(userLongitude);
		return retrievalService.retrieveNearestPharmacy(requestContract);
	}
	
	@RequestMapping("/retrieveNearByPharmacies")
	@ResponseBody
	public List<PharmacyLocationResponse> retrievePharmaciesSortedBasedOnDistanceFromUser(@RequestParam Double userLatitude,
			@RequestParam Double userLongitude) {
		UserLocationRequestContract requestContract = new UserLocationRequestContract();
		requestContract.setUserLatitude(userLatitude);
		requestContract.setUserLongitude(userLongitude);
		return retrievalService.retrievePharmaciesSortedBasedOnDistance(requestContract);
	}

}
