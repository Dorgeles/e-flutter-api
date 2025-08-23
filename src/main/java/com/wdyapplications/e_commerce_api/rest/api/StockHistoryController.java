

/*
 * Java controller for entity table stock_history 
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
Controller for table "stock_history"
 * 
 * @author SFL Back-End developper
 *
 */

@CrossOrigin("*")
@RestController
@RequestMapping(value="/stockHistory")
public class StockHistoryController {

	@Autowired
    private ControllerFactory<StockHistoryDto> controllerFactory;
	@Autowired
	private StockHistoryBusiness stockHistoryBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<StockHistoryDto> create(@RequestBody Request<StockHistoryDto> request) {
    	// System.out.println("start method /stockHistory/create");
        Response<StockHistoryDto> response = controllerFactory.create(stockHistoryBusiness, request, FunctionalityEnum.CREATE_STOCK_HISTORY);
		// System.out.println("end method /stockHistory/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<StockHistoryDto> update(@RequestBody Request<StockHistoryDto> request) {
    	// System.out.println("start method /stockHistory/update");
        Response<StockHistoryDto> response = controllerFactory.update(stockHistoryBusiness, request, FunctionalityEnum.UPDATE_STOCK_HISTORY);
		// System.out.println("end method /stockHistory/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<StockHistoryDto> delete(@RequestBody Request<StockHistoryDto> request) {
    	// System.out.println("start method /stockHistory/delete");
        Response<StockHistoryDto> response = controllerFactory.delete(stockHistoryBusiness, request, FunctionalityEnum.DELETE_STOCK_HISTORY);
		// System.out.println("end method /stockHistory/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<StockHistoryDto> getByCriteria(@RequestBody Request<StockHistoryDto> request) {
    	// System.out.println("start method /stockHistory/getByCriteria");
        Response<StockHistoryDto> response = controllerFactory.getByCriteria(stockHistoryBusiness, request, FunctionalityEnum.VIEW_STOCK_HISTORY);
		// System.out.println("end method /stockHistory/getByCriteria");
        return response;
    }
}
