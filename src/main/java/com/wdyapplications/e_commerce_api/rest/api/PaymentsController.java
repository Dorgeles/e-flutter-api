

/*
 * Java controller for entity table payments 
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
Controller for table "payments"
 * 
 * @author SFL Back-End developper
 *
 */

@CrossOrigin("*")
@RestController
@RequestMapping(value="/payments")
public class PaymentsController {

	@Autowired
    private ControllerFactory<PaymentsDto> controllerFactory;
	@Autowired
	private PaymentsBusiness paymentsBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<PaymentsDto> create(@RequestBody Request<PaymentsDto> request) {
    	// System.out.println("start method /payments/create");
        Response<PaymentsDto> response = controllerFactory.create(paymentsBusiness, request, FunctionalityEnum.CREATE_PAYMENTS);
		// System.out.println("end method /payments/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<PaymentsDto> update(@RequestBody Request<PaymentsDto> request) {
    	// System.out.println("start method /payments/update");
        Response<PaymentsDto> response = controllerFactory.update(paymentsBusiness, request, FunctionalityEnum.UPDATE_PAYMENTS);
		// System.out.println("end method /payments/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<PaymentsDto> delete(@RequestBody Request<PaymentsDto> request) {
    	// System.out.println("start method /payments/delete");
        Response<PaymentsDto> response = controllerFactory.delete(paymentsBusiness, request, FunctionalityEnum.DELETE_PAYMENTS);
		// System.out.println("end method /payments/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<PaymentsDto> getByCriteria(@RequestBody Request<PaymentsDto> request) {
    	// System.out.println("start method /payments/getByCriteria");
        Response<PaymentsDto> response = controllerFactory.getByCriteria(paymentsBusiness, request, FunctionalityEnum.VIEW_PAYMENTS);
		// System.out.println("end method /payments/getByCriteria");
        return response;
    }
}
