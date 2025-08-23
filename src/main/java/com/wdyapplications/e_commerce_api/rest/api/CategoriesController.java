

/*
 * Java controller for entity table categories 
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
Controller for table "categories"
 * 
 * @author SFL Back-End developper
 *
 */

@CrossOrigin("*")
@RestController
@RequestMapping(value="/categories")
public class CategoriesController {

	@Autowired
    private ControllerFactory<CategoriesDto> controllerFactory;
	@Autowired
	private CategoriesBusiness categoriesBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CategoriesDto> create(@RequestBody Request<CategoriesDto> request) {
    	// System.out.println("start method /categories/create");
        Response<CategoriesDto> response = controllerFactory.create(categoriesBusiness, request, FunctionalityEnum.CREATE_CATEGORIES);
		// System.out.println("end method /categories/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CategoriesDto> update(@RequestBody Request<CategoriesDto> request) {
    	// System.out.println("start method /categories/update");
        Response<CategoriesDto> response = controllerFactory.update(categoriesBusiness, request, FunctionalityEnum.UPDATE_CATEGORIES);
		// System.out.println("end method /categories/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CategoriesDto> delete(@RequestBody Request<CategoriesDto> request) {
    	// System.out.println("start method /categories/delete");
        Response<CategoriesDto> response = controllerFactory.delete(categoriesBusiness, request, FunctionalityEnum.DELETE_CATEGORIES);
		// System.out.println("end method /categories/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CategoriesDto> getByCriteria(@RequestBody Request<CategoriesDto> request) {
    	// System.out.println("start method /categories/getByCriteria");
        Response<CategoriesDto> response = controllerFactory.getByCriteria(categoriesBusiness, request, FunctionalityEnum.VIEW_CATEGORIES);
		// System.out.println("end method /categories/getByCriteria");
        return response;
    }
}
