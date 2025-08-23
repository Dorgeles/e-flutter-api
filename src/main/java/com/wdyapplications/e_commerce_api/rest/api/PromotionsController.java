

/*
 * Java controller for entity table promotions 
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
Controller for table "promotions"
 * 
 * @author SFL Back-End developper
 *
 */

@CrossOrigin("*")
@RestController
@RequestMapping(value="/promotions")
public class PromotionsController {

	@Autowired
    private ControllerFactory<PromotionsDto> controllerFactory;
	@Autowired
	private PromotionsBusiness promotionsBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<PromotionsDto> create(@RequestBody Request<PromotionsDto> request) {
    	// System.out.println("start method /promotions/create");
        Response<PromotionsDto> response = controllerFactory.create(promotionsBusiness, request, FunctionalityEnum.CREATE_PROMOTIONS);
		// System.out.println("end method /promotions/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<PromotionsDto> update(@RequestBody Request<PromotionsDto> request) {
    	// System.out.println("start method /promotions/update");
        Response<PromotionsDto> response = controllerFactory.update(promotionsBusiness, request, FunctionalityEnum.UPDATE_PROMOTIONS);
		// System.out.println("end method /promotions/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<PromotionsDto> delete(@RequestBody Request<PromotionsDto> request) {
    	// System.out.println("start method /promotions/delete");
        Response<PromotionsDto> response = controllerFactory.delete(promotionsBusiness, request, FunctionalityEnum.DELETE_PROMOTIONS);
		// System.out.println("end method /promotions/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<PromotionsDto> getByCriteria(@RequestBody Request<PromotionsDto> request) {
    	// System.out.println("start method /promotions/getByCriteria");
        Response<PromotionsDto> response = controllerFactory.getByCriteria(promotionsBusiness, request, FunctionalityEnum.VIEW_PROMOTIONS);
		// System.out.println("end method /promotions/getByCriteria");
        return response;
    }
}
