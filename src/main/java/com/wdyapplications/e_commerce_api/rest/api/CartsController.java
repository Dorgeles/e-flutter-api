

/*
 * Java controller for entity table carts 
 * Created on 2025-08-23 ( Time 06:18:36 )
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
Controller for table "carts"
 * 
 * @author SFL Back-End developper
 *
 */

@CrossOrigin("*")
@RestController
@RequestMapping(value="/carts")
public class CartsController {

	@Autowired
    private ControllerFactory<CartsDto> controllerFactory;
	@Autowired
	private CartsBusiness cartsBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CartsDto> create(@RequestBody Request<CartsDto> request) {
    	// System.out.println("start method /carts/create");
        Response<CartsDto> response = controllerFactory.create(cartsBusiness, request, FunctionalityEnum.CREATE_CARTS);
		// System.out.println("end method /carts/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CartsDto> update(@RequestBody Request<CartsDto> request) {
    	// System.out.println("start method /carts/update");
        Response<CartsDto> response = controllerFactory.update(cartsBusiness, request, FunctionalityEnum.UPDATE_CARTS);
		// System.out.println("end method /carts/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CartsDto> delete(@RequestBody Request<CartsDto> request) {
    	// System.out.println("start method /carts/delete");
        Response<CartsDto> response = controllerFactory.delete(cartsBusiness, request, FunctionalityEnum.DELETE_CARTS);
		// System.out.println("end method /carts/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CartsDto> getByCriteria(@RequestBody Request<CartsDto> request) {
    	// System.out.println("start method /carts/getByCriteria");
        Response<CartsDto> response = controllerFactory.getByCriteria(cartsBusiness, request, FunctionalityEnum.VIEW_CARTS);
		// System.out.println("end method /carts/getByCriteria");
        return response;
    }
}
