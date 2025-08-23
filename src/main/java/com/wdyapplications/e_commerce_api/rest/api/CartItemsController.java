

/*
 * Java controller for entity table cart_items 
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
Controller for table "cart_items"
 * 
 * @author SFL Back-End developper
 *
 */

@CrossOrigin("*")
@RestController
@RequestMapping(value="/cartItems")
public class CartItemsController {

	@Autowired
    private ControllerFactory<CartItemsDto> controllerFactory;
	@Autowired
	private CartItemsBusiness cartItemsBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CartItemsDto> create(@RequestBody Request<CartItemsDto> request) {
    	// System.out.println("start method /cartItems/create");
        Response<CartItemsDto> response = controllerFactory.create(cartItemsBusiness, request, FunctionalityEnum.CREATE_CART_ITEMS);
		// System.out.println("end method /cartItems/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CartItemsDto> update(@RequestBody Request<CartItemsDto> request) {
    	// System.out.println("start method /cartItems/update");
        Response<CartItemsDto> response = controllerFactory.update(cartItemsBusiness, request, FunctionalityEnum.UPDATE_CART_ITEMS);
		// System.out.println("end method /cartItems/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CartItemsDto> delete(@RequestBody Request<CartItemsDto> request) {
    	// System.out.println("start method /cartItems/delete");
        Response<CartItemsDto> response = controllerFactory.delete(cartItemsBusiness, request, FunctionalityEnum.DELETE_CART_ITEMS);
		// System.out.println("end method /cartItems/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CartItemsDto> getByCriteria(@RequestBody Request<CartItemsDto> request) {
    	// System.out.println("start method /cartItems/getByCriteria");
        Response<CartItemsDto> response = controllerFactory.getByCriteria(cartItemsBusiness, request, FunctionalityEnum.VIEW_CART_ITEMS);
		// System.out.println("end method /cartItems/getByCriteria");
        return response;
    }
}
