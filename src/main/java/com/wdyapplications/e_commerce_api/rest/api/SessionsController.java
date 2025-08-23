

/*
 * Java controller for entity table sessions 
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
Controller for table "sessions"
 * 
 * @author SFL Back-End developper
 *
 */

@CrossOrigin("*")
@RestController
@RequestMapping(value="/sessions")
public class SessionsController {

	@Autowired
    private ControllerFactory<SessionsDto> controllerFactory;
	@Autowired
	private SessionsBusiness sessionsBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<SessionsDto> create(@RequestBody Request<SessionsDto> request) {
    	// System.out.println("start method /sessions/create");
        Response<SessionsDto> response = controllerFactory.create(sessionsBusiness, request, FunctionalityEnum.CREATE_SESSIONS);
		// System.out.println("end method /sessions/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<SessionsDto> update(@RequestBody Request<SessionsDto> request) {
    	// System.out.println("start method /sessions/update");
        Response<SessionsDto> response = controllerFactory.update(sessionsBusiness, request, FunctionalityEnum.UPDATE_SESSIONS);
		// System.out.println("end method /sessions/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<SessionsDto> delete(@RequestBody Request<SessionsDto> request) {
    	// System.out.println("start method /sessions/delete");
        Response<SessionsDto> response = controllerFactory.delete(sessionsBusiness, request, FunctionalityEnum.DELETE_SESSIONS);
		// System.out.println("end method /sessions/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<SessionsDto> getByCriteria(@RequestBody Request<SessionsDto> request) {
    	// System.out.println("start method /sessions/getByCriteria");
        Response<SessionsDto> response = controllerFactory.getByCriteria(sessionsBusiness, request, FunctionalityEnum.VIEW_SESSIONS);
		// System.out.println("end method /sessions/getByCriteria");
        return response;
    }
}
