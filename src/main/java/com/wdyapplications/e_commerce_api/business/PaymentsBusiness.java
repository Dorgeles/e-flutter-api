                                        										
/*
 * Java business for entity table payments 
 * Created on 2025-08-23 ( Time 06:18:36 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package com.wdyapplications.e_commerce_api.business;

import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.wdyapplications.e_commerce_api.utils.*;
import com.wdyapplications.e_commerce_api.utils.dto.*;
import com.wdyapplications.e_commerce_api.utils.enums.*;
import com.wdyapplications.e_commerce_api.utils.contract.*;
import com.wdyapplications.e_commerce_api.utils.contract.IBasicBusiness;
import com.wdyapplications.e_commerce_api.utils.contract.Request;
import com.wdyapplications.e_commerce_api.utils.contract.Response;
import com.wdyapplications.e_commerce_api.utils.dto.transformer.*;
import com.wdyapplications.e_commerce_api.dao.entity.Payments;
import com.wdyapplications.e_commerce_api.dao.entity.Orders;
import com.wdyapplications.e_commerce_api.dao.entity.*;
import com.wdyapplications.e_commerce_api.dao.repository.*;

/**
BUSINESS for table "payments"
 * 
 * @author Dorgeddy
 *
 */

@Component
public class PaymentsBusiness implements IBasicBusiness<Request<PaymentsDto>, Response<PaymentsDto>> {

	private Response<PaymentsDto> response;
	@Autowired
	private PaymentsRepository paymentsRepository;
	@Autowired
	private OrdersRepository ordersRepository;
	@Autowired
	private FunctionalError functionalError;
	@Autowired
	private TechnicalError technicalError;
	@Autowired
	private ExceptionUtils exceptionUtils;
	@PersistenceContext
	private EntityManager em;

	private SimpleDateFormat dateFormat;
	private SimpleDateFormat dateTimeFormat;

	public PaymentsBusiness() {
		dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateTimeFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * create Payments by using PaymentsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<PaymentsDto> create(Request<PaymentsDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin create Payments-----");

		Response<PaymentsDto> response = new Response<PaymentsDto>();
		List<Payments>        items    = new ArrayList<Payments>();
			
		for (PaymentsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("orderId", dto.getOrderId());
			fieldsToVerify.put("paymentMethod", dto.getPaymentMethod());
			fieldsToVerify.put("amount", dto.getAmount());
			fieldsToVerify.put("currency", dto.getCurrency());
			fieldsToVerify.put("status", dto.getStatus());
			fieldsToVerify.put("transactionId", dto.getTransactionId());
			fieldsToVerify.put("gatewayResponse", dto.getGatewayResponse());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if payments to insert do not exist
			Payments existingEntity = null;

/*
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("payments id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

*/
			// Verify if orders exist
			Orders existingOrders = null;
			if (dto.getOrderId() != null && dto.getOrderId() > 0){
				existingOrders = ordersRepository.findOne(dto.getOrderId());
				if (existingOrders == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("orders orderId -> " + dto.getOrderId(), locale));
					response.setHasError(true);
					return response;
				}
			}
				Payments entityToSave = null;
			entityToSave = PaymentsTransformer.INSTANCE.toEntity(dto, existingOrders);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());

			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Payments> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = paymentsRepository.saveAll((Iterable<Payments>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("payments", locale));
				response.setHasError(true);
				return response;
			}
			List<PaymentsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? PaymentsTransformer.INSTANCE.toLiteDtos(itemsSaved) : PaymentsTransformer.INSTANCE.toDtos(itemsSaved);

			final int size = itemsSaved.size();
			List<String>  listOfError      = Collections.synchronizedList(new ArrayList<String>());
			itemsDto.parallelStream().forEach(dto -> {
				try {
					dto = getFullInfos(dto, size, request.getIsSimpleLoading(), locale);
				} catch (Exception e) {
					listOfError.add(e.getMessage());
					e.printStackTrace();
				}
			});
			if (Utilities.isNotEmpty(listOfError)) {
				Object[] objArray = listOfError.stream().distinct().toArray();
				throw new RuntimeException(StringUtils.join(objArray, ", "));
			}
			response.setItems(itemsDto);
			response.setHasError(false);
		}

		// System.out.println("----end create Payments-----");
		return response;
	}

	/**
	 * update Payments by using PaymentsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<PaymentsDto> update(Request<PaymentsDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin update Payments-----");

		Response<PaymentsDto> response = new Response<PaymentsDto>();
		List<Payments>        items    = new ArrayList<Payments>();
			
		for (PaymentsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la payments existe
			Payments entityToSave = null;
			entityToSave = paymentsRepository.findOne(dto.getId());
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("payments id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if orders exist
			if (dto.getOrderId() != null && dto.getOrderId() > 0){
				Orders existingOrders = ordersRepository.findOne(dto.getOrderId());
				if (existingOrders == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("orders orderId -> " + dto.getOrderId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setOrders(existingOrders);
			}
			if (Utilities.notBlank(dto.getPaymentMethod())) {
				entityToSave.setPaymentMethod(dto.getPaymentMethod());
			}
//			if (dto.getAmount() != null && dto.getAmount() > 0) {
//				entityToSave.setAmount(dto.getAmount());
//			}
			if (Utilities.notBlank(dto.getCurrency())) {
				entityToSave.setCurrency(dto.getCurrency());
			}
			if (Utilities.notBlank(dto.getStatus())) {
				entityToSave.setStatus(dto.getStatus());
			}
			if (Utilities.notBlank(dto.getTransactionId())) {
				entityToSave.setTransactionId(dto.getTransactionId());
			}
			if (Utilities.notBlank(dto.getGatewayResponse())) {
				entityToSave.setGatewayResponse(dto.getGatewayResponse());
			}
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Payments> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = paymentsRepository.saveAll((Iterable<Payments>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("payments", locale));
				response.setHasError(true);
				return response;
			}
			List<PaymentsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? PaymentsTransformer.INSTANCE.toLiteDtos(itemsSaved) : PaymentsTransformer.INSTANCE.toDtos(itemsSaved);

			final int size = itemsSaved.size();
			List<String>  listOfError      = Collections.synchronizedList(new ArrayList<String>());
			itemsDto.parallelStream().forEach(dto -> {
				try {
					dto = getFullInfos(dto, size, request.getIsSimpleLoading(), locale);
				} catch (Exception e) {
					listOfError.add(e.getMessage());
					e.printStackTrace();
				}
			});
			if (Utilities.isNotEmpty(listOfError)) {
				Object[] objArray = listOfError.stream().distinct().toArray();
				throw new RuntimeException(StringUtils.join(objArray, ", "));
			}
			response.setItems(itemsDto);
			response.setHasError(false);
		}

		// System.out.println("----end update Payments-----");
		return response;
	}

	/**
	 * delete Payments by using PaymentsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<PaymentsDto> delete(Request<PaymentsDto> request, Locale locale)  {
		// System.out.println("----begin delete Payments-----");

		Response<PaymentsDto> response = new Response<PaymentsDto>();
		List<Payments>        items    = new ArrayList<Payments>();
			
		for (PaymentsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la payments existe
			Payments existingEntity = null;

			existingEntity = paymentsRepository.findOne(dto.getId());
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("payments -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------



			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			paymentsRepository.deleteAll((Iterable<Payments>) items);

			response.setHasError(false);
		}

		// System.out.println("----end delete Payments-----");
		return response;
	}

	/**
	 * get Payments by using PaymentsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<PaymentsDto> getByCriteria(Request<PaymentsDto> request, Locale locale)  throws Exception {
		// System.out.println("----begin get Payments-----");

		Response<PaymentsDto> response = new Response<PaymentsDto>();
		List<Payments> items 			 = paymentsRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<PaymentsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? PaymentsTransformer.INSTANCE.toLiteDtos(items) : PaymentsTransformer.INSTANCE.toDtos(items);

			final int size = items.size();
			List<String>  listOfError      = Collections.synchronizedList(new ArrayList<String>());
			itemsDto.parallelStream().forEach(dto -> {
				try {
					dto = getFullInfos(dto, size, request.getIsSimpleLoading(), locale);
				} catch (Exception e) {
					listOfError.add(e.getMessage());
					e.printStackTrace();
				}
			});
			if (Utilities.isNotEmpty(listOfError)) {
				Object[] objArray = listOfError.stream().distinct().toArray();
				throw new RuntimeException(StringUtils.join(objArray, ", "));
			}
			response.setItems(itemsDto);
			response.setCount(paymentsRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("payments", locale));
			response.setHasError(false);
			return response;
		}

		// System.out.println("----end get Payments-----");
		return response;
	}

	/**
	 * get full PaymentsDto by using Payments as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private PaymentsDto getFullInfos(PaymentsDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
		// put code here

		if (Utilities.isTrue(isSimpleLoading)) {
			return dto;
		}
		if (size > 1) {
			return dto;
		}

		return dto;
	}
}
