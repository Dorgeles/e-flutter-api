

/*
 * Java controller for entity table orders 
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
Controller for table "orders"
 * 
 * @author SFL Back-End developper
 *
 */

@CrossOrigin("*")
@RestController
@RequestMapping(value="/orders")
public class OrdersController {

	@Autowired
    private ControllerFactory<OrdersDto> controllerFactory;
	@Autowired
	private OrdersBusiness ordersBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<OrdersDto> create(@RequestBody Request<OrdersDto> request) {
    	// System.out.println("start method /orders/create");
        Response<OrdersDto> response = controllerFactory.create(ordersBusiness, request, FunctionalityEnum.CREATE_ORDERS);
		// System.out.println("end method /orders/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<OrdersDto> update(@RequestBody Request<OrdersDto> request) {
    	// System.out.println("start method /orders/update");
        Response<OrdersDto> response = controllerFactory.update(ordersBusiness, request, FunctionalityEnum.UPDATE_ORDERS);
		// System.out.println("end method /orders/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<OrdersDto> delete(@RequestBody Request<OrdersDto> request) {
    	// System.out.println("start method /orders/delete");
        Response<OrdersDto> response = controllerFactory.delete(ordersBusiness, request, FunctionalityEnum.DELETE_ORDERS);
		// System.out.println("end method /orders/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<OrdersDto> getByCriteria(@RequestBody Request<OrdersDto> request) {
    	// System.out.println("start method /orders/getByCriteria");
        Response<OrdersDto> response = controllerFactory.getByCriteria(ordersBusiness, request, FunctionalityEnum.VIEW_ORDERS);
		// System.out.println("end method /orders/getByCriteria");
        return response;
    }
}
