

/*
 * Java controller for entity table messages 
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
Controller for table "messages"
 * 
 * @author SFL Back-End developper
 *
 */

@CrossOrigin("*")
@RestController
@RequestMapping(value="/messages")
public class MessagesController {

	@Autowired
    private ControllerFactory<MessagesDto> controllerFactory;
	@Autowired
	private MessagesBusiness messagesBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<MessagesDto> create(@RequestBody Request<MessagesDto> request) {
    	// System.out.println("start method /messages/create");
        Response<MessagesDto> response = controllerFactory.create(messagesBusiness, request, FunctionalityEnum.CREATE_MESSAGES);
		// System.out.println("end method /messages/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<MessagesDto> update(@RequestBody Request<MessagesDto> request) {
    	// System.out.println("start method /messages/update");
        Response<MessagesDto> response = controllerFactory.update(messagesBusiness, request, FunctionalityEnum.UPDATE_MESSAGES);
		// System.out.println("end method /messages/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<MessagesDto> delete(@RequestBody Request<MessagesDto> request) {
    	// System.out.println("start method /messages/delete");
        Response<MessagesDto> response = controllerFactory.delete(messagesBusiness, request, FunctionalityEnum.DELETE_MESSAGES);
		// System.out.println("end method /messages/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<MessagesDto> getByCriteria(@RequestBody Request<MessagesDto> request) {
    	// System.out.println("start method /messages/getByCriteria");
        Response<MessagesDto> response = controllerFactory.getByCriteria(messagesBusiness, request, FunctionalityEnum.VIEW_MESSAGES);
		// System.out.println("end method /messages/getByCriteria");
        return response;
    }
}
