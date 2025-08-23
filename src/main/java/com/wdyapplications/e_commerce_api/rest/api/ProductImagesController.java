

/*
 * Java controller for entity table product_images 
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
Controller for table "product_images"
 * 
 * @author SFL Back-End developper
 *
 */

@CrossOrigin("*")
@RestController
@RequestMapping(value="/productImages")
public class ProductImagesController {

	@Autowired
    private ControllerFactory<ProductImagesDto> controllerFactory;
	@Autowired
	private ProductImagesBusiness productImagesBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ProductImagesDto> create(@RequestBody Request<ProductImagesDto> request) {
    	// System.out.println("start method /productImages/create");
        Response<ProductImagesDto> response = controllerFactory.create(productImagesBusiness, request, FunctionalityEnum.CREATE_PRODUCT_IMAGES);
		// System.out.println("end method /productImages/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ProductImagesDto> update(@RequestBody Request<ProductImagesDto> request) {
    	// System.out.println("start method /productImages/update");
        Response<ProductImagesDto> response = controllerFactory.update(productImagesBusiness, request, FunctionalityEnum.UPDATE_PRODUCT_IMAGES);
		// System.out.println("end method /productImages/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ProductImagesDto> delete(@RequestBody Request<ProductImagesDto> request) {
    	// System.out.println("start method /productImages/delete");
        Response<ProductImagesDto> response = controllerFactory.delete(productImagesBusiness, request, FunctionalityEnum.DELETE_PRODUCT_IMAGES);
		// System.out.println("end method /productImages/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ProductImagesDto> getByCriteria(@RequestBody Request<ProductImagesDto> request) {
    	// System.out.println("start method /productImages/getByCriteria");
        Response<ProductImagesDto> response = controllerFactory.getByCriteria(productImagesBusiness, request, FunctionalityEnum.VIEW_PRODUCT_IMAGES);
		// System.out.println("end method /productImages/getByCriteria");
        return response;
    }
}
