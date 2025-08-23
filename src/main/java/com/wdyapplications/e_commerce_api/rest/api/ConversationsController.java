

/*
 * Java controller for entity table conversations 
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
Controller for table "conversations"
 * 
 * @author SFL Back-End developper
 *
 */

@CrossOrigin("*")
@RestController
@RequestMapping(value="/conversations")
public class ConversationsController {

	@Autowired
    private ControllerFactory<ConversationsDto> controllerFactory;
	@Autowired
	private ConversationsBusiness conversationsBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ConversationsDto> create(@RequestBody Request<ConversationsDto> request) {
    	// System.out.println("start method /conversations/create");
        Response<ConversationsDto> response = controllerFactory.create(conversationsBusiness, request, FunctionalityEnum.CREATE_CONVERSATIONS);
		// System.out.println("end method /conversations/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ConversationsDto> update(@RequestBody Request<ConversationsDto> request) {
    	// System.out.println("start method /conversations/update");
        Response<ConversationsDto> response = controllerFactory.update(conversationsBusiness, request, FunctionalityEnum.UPDATE_CONVERSATIONS);
		// System.out.println("end method /conversations/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ConversationsDto> delete(@RequestBody Request<ConversationsDto> request) {
    	// System.out.println("start method /conversations/delete");
        Response<ConversationsDto> response = controllerFactory.delete(conversationsBusiness, request, FunctionalityEnum.DELETE_CONVERSATIONS);
		// System.out.println("end method /conversations/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ConversationsDto> getByCriteria(@RequestBody Request<ConversationsDto> request) {
    	// System.out.println("start method /conversations/getByCriteria");
        Response<ConversationsDto> response = controllerFactory.getByCriteria(conversationsBusiness, request, FunctionalityEnum.VIEW_CONVERSATIONS);
		// System.out.println("end method /conversations/getByCriteria");
        return response;
    }
}
