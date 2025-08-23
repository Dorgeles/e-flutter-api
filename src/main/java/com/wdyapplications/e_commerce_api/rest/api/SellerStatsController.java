

/*
 * Java controller for entity table seller_stats 
 * Created on 2025-08-23 ( Time 06:18:37 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2017 Savoir Faire Linux. All Rights Reserved.
 */

package com.wdyapplications.e_commerce_api.rest.api;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wdyapplications.e_commerce_api.utils.*;
import com.wdyapplications.e_commerce_api.utils.dto.*;
import com.wdyapplications.e_commerce_api.utils.contract.*;
import com.wdyapplications.e_commerce_api.utils.contract.Request;
import com.wdyapplications.e_commerce_api.utils.contract.Response;
import com.wdyapplications.e_commerce_api.utils.enums.FunctionalityEnum;
import com.wdyapplications.e_commerce_api.business.*;
import com.wdyapplications.e_commerce_api.rest.fact.ControllerFactory;

/**
Controller for table "seller_stats"
 * 
 * @author SFL Back-End developper
 *
 */

@CrossOrigin("*")
@RestController
@RequestMapping(value="/sellerStats")
public class SellerStatsController {

	@Autowired
    private ControllerFactory<SellerStatsDto> controllerFactory;
	@Autowired
	private SellerStatsBusiness sellerStatsBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<SellerStatsDto> create(@RequestBody Request<SellerStatsDto> request) {
    	// System.out.println("start method /sellerStats/create");
        Response<SellerStatsDto> response = controllerFactory.create(sellerStatsBusiness, request, FunctionalityEnum.CREATE_SELLER_STATS);
		// System.out.println("end method /sellerStats/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<SellerStatsDto> update(@RequestBody Request<SellerStatsDto> request) {
    	// System.out.println("start method /sellerStats/update");
        Response<SellerStatsDto> response = controllerFactory.update(sellerStatsBusiness, request, FunctionalityEnum.UPDATE_SELLER_STATS);
		// System.out.println("end method /sellerStats/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<SellerStatsDto> delete(@RequestBody Request<SellerStatsDto> request) {
    	// System.out.println("start method /sellerStats/delete");
        Response<SellerStatsDto> response = controllerFactory.delete(sellerStatsBusiness, request, FunctionalityEnum.DELETE_SELLER_STATS);
		// System.out.println("end method /sellerStats/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<SellerStatsDto> getByCriteria(@RequestBody Request<SellerStatsDto> request) {
    	// System.out.println("start method /sellerStats/getByCriteria");
        Response<SellerStatsDto> response = controllerFactory.getByCriteria(sellerStatsBusiness, request, FunctionalityEnum.VIEW_SELLER_STATS);
		// System.out.println("end method /sellerStats/getByCriteria");
        return response;
    }
}
