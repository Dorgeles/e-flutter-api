                                                                        																		
/*
 * Java business for entity table orders 
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
import com.wdyapplications.e_commerce_api.dao.entity.Orders;
import com.wdyapplications.e_commerce_api.dao.entity.Users;
import com.wdyapplications.e_commerce_api.dao.entity.*;
import com.wdyapplications.e_commerce_api.dao.repository.*;

/**
BUSINESS for table "orders"
 * 
 * @author Dorgeddy
 *
 */

@Component
public class OrdersBusiness implements IBasicBusiness<Request<OrdersDto>, Response<OrdersDto>> {

	private Response<OrdersDto> response;
	@Autowired
	private OrdersRepository ordersRepository;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private PromotionUsageRepository promotionUsageRepository;
	@Autowired
	private PaymentsRepository paymentsRepository;
	@Autowired
	private OrderItemsRepository orderItemsRepository;
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

	public OrdersBusiness() {
		dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateTimeFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * create Orders by using OrdersDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<OrdersDto> create(Request<OrdersDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin create Orders-----");

		Response<OrdersDto> response = new Response<OrdersDto>();
		List<Orders>        items    = new ArrayList<Orders>();
			
		for (OrdersDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("orderNumber", dto.getOrderNumber());
			fieldsToVerify.put("customerId", dto.getCustomerId());
			//fieldsToVerify.put("status", dto.getStatus());
			//fieldsToVerify.put("subtotal", dto.getSubtotal());
			fieldsToVerify.put("taxAmount", dto.getTaxAmount());
			fieldsToVerify.put("shippingCost", dto.getShippingCost());
			fieldsToVerify.put("discountAmount", dto.getDiscountAmount());
			fieldsToVerify.put("totalAmount", dto.getTotalAmount());
			fieldsToVerify.put("shippingAddress", dto.getShippingAddress());
			fieldsToVerify.put("billingAddress", dto.getBillingAddress());
			//fieldsToVerify.put("paymentMethod", dto.getPaymentMethod());
			//fieldsToVerify.put("paymentStatus", dto.getPaymentStatus());
			fieldsToVerify.put("trackingNumber", dto.getTrackingNumber());
			//fieldsToVerify.put("shippedAt", dto.getShippedAt());
			//fieldsToVerify.put("deliveredAt", dto.getDeliveredAt());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if orders to insert do not exist
			Orders existingEntity = null;

/*
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("orders id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

*/
			// Verify if users exist
			Users existingUsers = null;
			if (dto.getCustomerId() != null && dto.getCustomerId() > 0){
				existingUsers = usersRepository.findOne(dto.getCustomerId());
				if (existingUsers == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("users customerId -> " + dto.getCustomerId(), locale));
					response.setHasError(true);
					return response;
				}
			}
				Orders entityToSave = null;
			entityToSave = OrdersTransformer.INSTANCE.toEntity(dto, existingUsers);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());

			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Orders> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = ordersRepository.saveAll((Iterable<Orders>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("orders", locale));
				response.setHasError(true);
				return response;
			}
			List<OrdersDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? OrdersTransformer.INSTANCE.toLiteDtos(itemsSaved) : OrdersTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end create Orders-----");
		return response;
	}

	/**
	 * update Orders by using OrdersDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<OrdersDto> update(Request<OrdersDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin update Orders-----");

		Response<OrdersDto> response = new Response<OrdersDto>();
		List<Orders>        items    = new ArrayList<Orders>();
			
		for (OrdersDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la orders existe
			Orders entityToSave = null;
			entityToSave = ordersRepository.findOne(dto.getId());
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("orders id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if users exist
			if (dto.getCustomerId() != null && dto.getCustomerId() > 0){
				Users existingUsers = usersRepository.findOne(dto.getCustomerId());
				if (existingUsers == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("users customerId -> " + dto.getCustomerId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setUsers(existingUsers);
			}
			if (Utilities.notBlank(dto.getOrderNumber())) {
				entityToSave.setOrderNumber(dto.getOrderNumber());
			}
			if (Utilities.notBlank(dto.getStatus())) {
				entityToSave.setStatus(dto.getStatus());
			}
//			if (dto.getSubtotal() != null && dto.getSubtotal() > 0) {
//				entityToSave.setSubtotal(dto.getSubtotal());
//			}
//			if (dto.getTaxAmount() != null && dto.getTaxAmount() > 0) {
//				entityToSave.setTaxAmount(dto.getTaxAmount());
//			}
//			if (dto.getShippingCost() != null && dto.getShippingCost() > 0) {
//				entityToSave.setShippingCost(dto.getShippingCost());
//			}
//			if (dto.getDiscountAmount() != null && dto.getDiscountAmount() > 0) {
//				entityToSave.setDiscountAmount(dto.getDiscountAmount());
//			}
//			if (dto.getTotalAmount() != null && dto.getTotalAmount() > 0) {
//				entityToSave.setTotalAmount(dto.getTotalAmount());
//			}
			if (Utilities.notBlank(dto.getShippingAddress())) {
				entityToSave.setShippingAddress(dto.getShippingAddress());
			}
			if (Utilities.notBlank(dto.getBillingAddress())) {
				entityToSave.setBillingAddress(dto.getBillingAddress());
			}
			if (Utilities.notBlank(dto.getPaymentMethod())) {
				entityToSave.setPaymentMethod(dto.getPaymentMethod());
			}
			if (Utilities.notBlank(dto.getPaymentStatus())) {
				entityToSave.setPaymentStatus(dto.getPaymentStatus());
			}
			if (Utilities.notBlank(dto.getTrackingNumber())) {
				entityToSave.setTrackingNumber(dto.getTrackingNumber());
			}
			if (Utilities.notBlank(dto.getShippedAt())) {
				entityToSave.setShippedAt(dateFormat.parse(dto.getShippedAt()));
			}
			if (Utilities.notBlank(dto.getDeliveredAt())) {
				entityToSave.setDeliveredAt(dateFormat.parse(dto.getDeliveredAt()));
			}
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Orders> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = ordersRepository.saveAll((Iterable<Orders>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("orders", locale));
				response.setHasError(true);
				return response;
			}
			List<OrdersDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? OrdersTransformer.INSTANCE.toLiteDtos(itemsSaved) : OrdersTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end update Orders-----");
		return response;
	}

	/**
	 * delete Orders by using OrdersDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<OrdersDto> delete(Request<OrdersDto> request, Locale locale)  {
		// System.out.println("----begin delete Orders-----");

		Response<OrdersDto> response = new Response<OrdersDto>();
		List<Orders>        items    = new ArrayList<Orders>();
			
		for (OrdersDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la orders existe
			Orders existingEntity = null;

			existingEntity = ordersRepository.findOne(dto.getId());
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("orders -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// promotionUsage
			List<PromotionUsage> listOfPromotionUsage = promotionUsageRepository.findByOrderId(existingEntity.getId());
			if (listOfPromotionUsage != null && !listOfPromotionUsage.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfPromotionUsage.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// payments
			List<Payments> listOfPayments = paymentsRepository.findByOrderId(existingEntity.getId());
			if (listOfPayments != null && !listOfPayments.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfPayments.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// orderItems
			List<OrderItems> listOfOrderItems = orderItemsRepository.findByOrderId(existingEntity.getId());
			if (listOfOrderItems != null && !listOfOrderItems.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfOrderItems.size() + ")", locale));
				response.setHasError(true);
				return response;
			}


			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			ordersRepository.deleteAll((Iterable<Orders>) items);

			response.setHasError(false);
		}

		// System.out.println("----end delete Orders-----");
		return response;
	}

	/**
	 * get Orders by using OrdersDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<OrdersDto> getByCriteria(Request<OrdersDto> request, Locale locale)  throws Exception {
		// System.out.println("----begin get Orders-----");

		Response<OrdersDto> response = new Response<OrdersDto>();
		List<Orders> items 			 = ordersRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<OrdersDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? OrdersTransformer.INSTANCE.toLiteDtos(items) : OrdersTransformer.INSTANCE.toDtos(items);

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
			response.setCount(ordersRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("orders", locale));
			response.setHasError(false);
			return response;
		}

		// System.out.println("----end get Orders-----");
		return response;
	}

	/**
	 * get full OrdersDto by using Orders as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private OrdersDto getFullInfos(OrdersDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
