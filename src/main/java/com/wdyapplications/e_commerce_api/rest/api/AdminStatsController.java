

/*
 * Java controller for entity table admin_stats 
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
Controller for table "admin_stats"
 * 
 * @author SFL Back-End developper
 *
 */

@CrossOrigin("*")
@RestController
@RequestMapping(value="/adminStats")
public class AdminStatsController {

	@Autowired
    private ControllerFactory<AdminStatsDto> controllerFactory;
	@Autowired
	private AdminStatsBusiness adminStatsBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<AdminStatsDto> create(@RequestBody Request<AdminStatsDto> request) {
    	// System.out.println("start method /adminStats/create");
        Response<AdminStatsDto> response = controllerFactory.create(adminStatsBusiness, request, FunctionalityEnum.CREATE_ADMIN_STATS);
		// System.out.println("end method /adminStats/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<AdminStatsDto> update(@RequestBody Request<AdminStatsDto> request) {
    	// System.out.println("start method /adminStats/update");
        Response<AdminStatsDto> response = controllerFactory.update(adminStatsBusiness, request, FunctionalityEnum.UPDATE_ADMIN_STATS);
		// System.out.println("end method /adminStats/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<AdminStatsDto> delete(@RequestBody Request<AdminStatsDto> request) {
    	// System.out.println("start method /adminStats/delete");
        Response<AdminStatsDto> response = controllerFactory.delete(adminStatsBusiness, request, FunctionalityEnum.DELETE_ADMIN_STATS);
		// System.out.println("end method /adminStats/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<AdminStatsDto> getByCriteria(@RequestBody Request<AdminStatsDto> request) {
    	// System.out.println("start method /adminStats/getByCriteria");
        Response<AdminStatsDto> response = controllerFactory.getByCriteria(adminStatsBusiness, request, FunctionalityEnum.VIEW_ADMIN_STATS);
		// System.out.println("end method /adminStats/getByCriteria");
        return response;
    }
}
