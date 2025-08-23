

/*
 * Java controller for entity table notifications 
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
Controller for table "notifications"
 * 
 * @author SFL Back-End developper
 *
 */

@CrossOrigin("*")
@RestController
@RequestMapping(value="/notifications")
public class NotificationsController {

	@Autowired
    private ControllerFactory<NotificationsDto> controllerFactory;
	@Autowired
	private NotificationsBusiness notificationsBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<NotificationsDto> create(@RequestBody Request<NotificationsDto> request) {
    	// System.out.println("start method /notifications/create");
        Response<NotificationsDto> response = controllerFactory.create(notificationsBusiness, request, FunctionalityEnum.CREATE_NOTIFICATIONS);
		// System.out.println("end method /notifications/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<NotificationsDto> update(@RequestBody Request<NotificationsDto> request) {
    	// System.out.println("start method /notifications/update");
        Response<NotificationsDto> response = controllerFactory.update(notificationsBusiness, request, FunctionalityEnum.UPDATE_NOTIFICATIONS);
		// System.out.println("end method /notifications/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<NotificationsDto> delete(@RequestBody Request<NotificationsDto> request) {
    	// System.out.println("start method /notifications/delete");
        Response<NotificationsDto> response = controllerFactory.delete(notificationsBusiness, request, FunctionalityEnum.DELETE_NOTIFICATIONS);
		// System.out.println("end method /notifications/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<NotificationsDto> getByCriteria(@RequestBody Request<NotificationsDto> request) {
    	// System.out.println("start method /notifications/getByCriteria");
        Response<NotificationsDto> response = controllerFactory.getByCriteria(notificationsBusiness, request, FunctionalityEnum.VIEW_NOTIFICATIONS);
		// System.out.println("end method /notifications/getByCriteria");
        return response;
    }
}
