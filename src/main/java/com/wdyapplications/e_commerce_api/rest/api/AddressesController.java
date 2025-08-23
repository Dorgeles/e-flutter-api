

/*
 * Java controller for entity table addresses 
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
Controller for table "addresses"
 * 
 * @author SFL Back-End developper
 *
 */

@CrossOrigin("*")
@RestController
@RequestMapping(value="/addresses")
public class AddressesController {

	@Autowired
    private ControllerFactory<AddressesDto> controllerFactory;
	@Autowired
	private AddressesBusiness addressesBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<AddressesDto> create(@RequestBody Request<AddressesDto> request) {
    	// System.out.println("start method /addresses/create");
        Response<AddressesDto> response = controllerFactory.create(addressesBusiness, request, FunctionalityEnum.CREATE_ADDRESSES);
		// System.out.println("end method /addresses/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<AddressesDto> update(@RequestBody Request<AddressesDto> request) {
    	// System.out.println("start method /addresses/update");
        Response<AddressesDto> response = controllerFactory.update(addressesBusiness, request, FunctionalityEnum.UPDATE_ADDRESSES);
		// System.out.println("end method /addresses/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<AddressesDto> delete(@RequestBody Request<AddressesDto> request) {
    	// System.out.println("start method /addresses/delete");
        Response<AddressesDto> response = controllerFactory.delete(addressesBusiness, request, FunctionalityEnum.DELETE_ADDRESSES);
		// System.out.println("end method /addresses/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<AddressesDto> getByCriteria(@RequestBody Request<AddressesDto> request) {
    	// System.out.println("start method /addresses/getByCriteria");
        Response<AddressesDto> response = controllerFactory.getByCriteria(addressesBusiness, request, FunctionalityEnum.VIEW_ADDRESSES);
		// System.out.println("end method /addresses/getByCriteria");
        return response;
    }
}
