

/*
 * Java controller for entity table reviews 
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
Controller for table "reviews"
 * 
 * @author SFL Back-End developper
 *
 */

@CrossOrigin("*")
@RestController
@RequestMapping(value="/reviews")
public class ReviewsController {

	@Autowired
    private ControllerFactory<ReviewsDto> controllerFactory;
	@Autowired
	private ReviewsBusiness reviewsBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ReviewsDto> create(@RequestBody Request<ReviewsDto> request) {
    	// System.out.println("start method /reviews/create");
        Response<ReviewsDto> response = controllerFactory.create(reviewsBusiness, request, FunctionalityEnum.CREATE_REVIEWS);
		// System.out.println("end method /reviews/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ReviewsDto> update(@RequestBody Request<ReviewsDto> request) {
    	// System.out.println("start method /reviews/update");
        Response<ReviewsDto> response = controllerFactory.update(reviewsBusiness, request, FunctionalityEnum.UPDATE_REVIEWS);
		// System.out.println("end method /reviews/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ReviewsDto> delete(@RequestBody Request<ReviewsDto> request) {
    	// System.out.println("start method /reviews/delete");
        Response<ReviewsDto> response = controllerFactory.delete(reviewsBusiness, request, FunctionalityEnum.DELETE_REVIEWS);
		// System.out.println("end method /reviews/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ReviewsDto> getByCriteria(@RequestBody Request<ReviewsDto> request) {
    	// System.out.println("start method /reviews/getByCriteria");
        Response<ReviewsDto> response = controllerFactory.getByCriteria(reviewsBusiness, request, FunctionalityEnum.VIEW_REVIEWS);
		// System.out.println("end method /reviews/getByCriteria");
        return response;
    }
}
