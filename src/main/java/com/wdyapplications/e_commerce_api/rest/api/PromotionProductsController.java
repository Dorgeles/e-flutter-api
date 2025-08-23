

/*
 * Java controller for entity table promotion_products 
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
Controller for table "promotion_products"
 * 
 * @author SFL Back-End developper
 *
 */

@CrossOrigin("*")
@RestController
@RequestMapping(value="/promotionProducts")
public class PromotionProductsController {

	@Autowired
    private ControllerFactory<PromotionProductsDto> controllerFactory;
	@Autowired
	private PromotionProductsBusiness promotionProductsBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<PromotionProductsDto> create(@RequestBody Request<PromotionProductsDto> request) {
    	// System.out.println("start method /promotionProducts/create");
        Response<PromotionProductsDto> response = controllerFactory.create(promotionProductsBusiness, request, FunctionalityEnum.CREATE_PROMOTION_PRODUCTS);
		// System.out.println("end method /promotionProducts/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<PromotionProductsDto> update(@RequestBody Request<PromotionProductsDto> request) {
    	// System.out.println("start method /promotionProducts/update");
        Response<PromotionProductsDto> response = controllerFactory.update(promotionProductsBusiness, request, FunctionalityEnum.UPDATE_PROMOTION_PRODUCTS);
		// System.out.println("end method /promotionProducts/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<PromotionProductsDto> delete(@RequestBody Request<PromotionProductsDto> request) {
    	// System.out.println("start method /promotionProducts/delete");
        Response<PromotionProductsDto> response = controllerFactory.delete(promotionProductsBusiness, request, FunctionalityEnum.DELETE_PROMOTION_PRODUCTS);
		// System.out.println("end method /promotionProducts/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<PromotionProductsDto> getByCriteria(@RequestBody Request<PromotionProductsDto> request) {
    	// System.out.println("start method /promotionProducts/getByCriteria");
        Response<PromotionProductsDto> response = controllerFactory.getByCriteria(promotionProductsBusiness, request, FunctionalityEnum.VIEW_PROMOTION_PRODUCTS);
		// System.out.println("end method /promotionProducts/getByCriteria");
        return response;
    }
}
