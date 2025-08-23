

/*
 * Java controller for entity table promotion_usage 
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
Controller for table "promotion_usage"
 * 
 * @author SFL Back-End developper
 *
 */

@CrossOrigin("*")
@RestController
@RequestMapping(value="/promotionUsage")
public class PromotionUsageController {

	@Autowired
    private ControllerFactory<PromotionUsageDto> controllerFactory;
	@Autowired
	private PromotionUsageBusiness promotionUsageBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<PromotionUsageDto> create(@RequestBody Request<PromotionUsageDto> request) {
    	// System.out.println("start method /promotionUsage/create");
        Response<PromotionUsageDto> response = controllerFactory.create(promotionUsageBusiness, request, FunctionalityEnum.CREATE_PROMOTION_USAGE);
		// System.out.println("end method /promotionUsage/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<PromotionUsageDto> update(@RequestBody Request<PromotionUsageDto> request) {
    	// System.out.println("start method /promotionUsage/update");
        Response<PromotionUsageDto> response = controllerFactory.update(promotionUsageBusiness, request, FunctionalityEnum.UPDATE_PROMOTION_USAGE);
		// System.out.println("end method /promotionUsage/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<PromotionUsageDto> delete(@RequestBody Request<PromotionUsageDto> request) {
    	// System.out.println("start method /promotionUsage/delete");
        Response<PromotionUsageDto> response = controllerFactory.delete(promotionUsageBusiness, request, FunctionalityEnum.DELETE_PROMOTION_USAGE);
		// System.out.println("end method /promotionUsage/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<PromotionUsageDto> getByCriteria(@RequestBody Request<PromotionUsageDto> request) {
    	// System.out.println("start method /promotionUsage/getByCriteria");
        Response<PromotionUsageDto> response = controllerFactory.getByCriteria(promotionUsageBusiness, request, FunctionalityEnum.VIEW_PROMOTION_USAGE);
		// System.out.println("end method /promotionUsage/getByCriteria");
        return response;
    }
}
