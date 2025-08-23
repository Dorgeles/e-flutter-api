

/*
 * Java controller for entity table products 
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
Controller for table "products"
 * 
 * @author SFL Back-End developper
 *
 */

@CrossOrigin("*")
@RestController
@RequestMapping(value="/products")
public class ProductsController {

	@Autowired
    private ControllerFactory<ProductsDto> controllerFactory;
	@Autowired
	private ProductsBusiness productsBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ProductsDto> create(@RequestBody Request<ProductsDto> request) {
    	// System.out.println("start method /products/create");
        Response<ProductsDto> response = controllerFactory.create(productsBusiness, request, FunctionalityEnum.CREATE_PRODUCTS);
		// System.out.println("end method /products/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ProductsDto> update(@RequestBody Request<ProductsDto> request) {
    	// System.out.println("start method /products/update");
        Response<ProductsDto> response = controllerFactory.update(productsBusiness, request, FunctionalityEnum.UPDATE_PRODUCTS);
		// System.out.println("end method /products/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ProductsDto> delete(@RequestBody Request<ProductsDto> request) {
    	// System.out.println("start method /products/delete");
        Response<ProductsDto> response = controllerFactory.delete(productsBusiness, request, FunctionalityEnum.DELETE_PRODUCTS);
		// System.out.println("end method /products/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ProductsDto> getByCriteria(@RequestBody Request<ProductsDto> request) {
    	// System.out.println("start method /products/getByCriteria");
        Response<ProductsDto> response = controllerFactory.getByCriteria(productsBusiness, request, FunctionalityEnum.VIEW_PRODUCTS);
		// System.out.println("end method /products/getByCriteria");
        return response;
    }
}
