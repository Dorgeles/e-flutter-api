                                								
/*
 * Java business for entity table order_items 
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
import com.wdyapplications.e_commerce_api.dao.entity.OrderItems;
import com.wdyapplications.e_commerce_api.dao.entity.Products;
import com.wdyapplications.e_commerce_api.dao.entity.Orders;
import com.wdyapplications.e_commerce_api.dao.entity.Users;
import com.wdyapplications.e_commerce_api.dao.entity.*;
import com.wdyapplications.e_commerce_api.dao.repository.*;

/**
BUSINESS for table "order_items"
 * 
 * @author Dorgeddy
 *
 */

@Component
public class OrderItemsBusiness implements IBasicBusiness<Request<OrderItemsDto>, Response<OrderItemsDto>> {

	private Response<OrderItemsDto> response;
	@Autowired
	private OrderItemsRepository orderItemsRepository;
	@Autowired
	private ProductsRepository productsRepository;
	@Autowired
	private OrdersRepository ordersRepository;
	@Autowired
	private UsersRepository usersRepository;
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

	public OrderItemsBusiness() {
		dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateTimeFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * create OrderItems by using OrderItemsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<OrderItemsDto> create(Request<OrderItemsDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin create OrderItems-----");

		Response<OrderItemsDto> response = new Response<OrderItemsDto>();
		List<OrderItems>        items    = new ArrayList<OrderItems>();
			
		for (OrderItemsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("orderId", dto.getOrderId());
			fieldsToVerify.put("productId", dto.getProductId());
			fieldsToVerify.put("sellerId", dto.getSellerId());
			fieldsToVerify.put("quantity", dto.getQuantity());
			fieldsToVerify.put("unitPrice", dto.getUnitPrice());
			//fieldsToVerify.put("totalPrice", dto.getTotalPrice());
			//fieldsToVerify.put("status", dto.getStatus());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if orderItems to insert do not exist
			OrderItems existingEntity = null;

/*
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("orderItems id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

*/
			// Verify if products exist
			Products existingProducts = null;
			if (dto.getProductId() != null && dto.getProductId() > 0){
				existingProducts = productsRepository.findOne(dto.getProductId());
				if (existingProducts == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("products productId -> " + dto.getProductId(), locale));
					response.setHasError(true);
					return response;
				}
			}
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
			// Verify if users exist
			Users existingUsers = null;
			if (dto.getSellerId() != null && dto.getSellerId() > 0){
				existingUsers = usersRepository.findOne(dto.getSellerId());
				if (existingUsers == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("users sellerId -> " + dto.getSellerId(), locale));
					response.setHasError(true);
					return response;
				}
			}
				OrderItems entityToSave = null;
			entityToSave = OrderItemsTransformer.INSTANCE.toEntity(dto, existingProducts, existingOrders, existingUsers);

			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<OrderItems> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = orderItemsRepository.saveAll((Iterable<OrderItems>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("orderItems", locale));
				response.setHasError(true);
				return response;
			}
			List<OrderItemsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? OrderItemsTransformer.INSTANCE.toLiteDtos(itemsSaved) : OrderItemsTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end create OrderItems-----");
		return response;
	}

	/**
	 * update OrderItems by using OrderItemsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<OrderItemsDto> update(Request<OrderItemsDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin update OrderItems-----");

		Response<OrderItemsDto> response = new Response<OrderItemsDto>();
		List<OrderItems>        items    = new ArrayList<OrderItems>();
			
		for (OrderItemsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la orderItems existe
			OrderItems entityToSave = null;
			entityToSave = orderItemsRepository.findOne(dto.getId());
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("orderItems id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if products exist
			if (dto.getProductId() != null && dto.getProductId() > 0){
				Products existingProducts = productsRepository.findOne(dto.getProductId());
				if (existingProducts == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("products productId -> " + dto.getProductId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setProducts(existingProducts);
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
			// Verify if users exist
			if (dto.getSellerId() != null && dto.getSellerId() > 0){
				Users existingUsers = usersRepository.findOne(dto.getSellerId());
				if (existingUsers == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("users sellerId -> " + dto.getSellerId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setUsers(existingUsers);
			}
			if (dto.getQuantity() != null && dto.getQuantity() > 0) {
				entityToSave.setQuantity(dto.getQuantity());
			}

			if (Utilities.notBlank(dto.getStatus())) {
				entityToSave.setStatus(dto.getStatus());
			}
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<OrderItems> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = orderItemsRepository.saveAll((Iterable<OrderItems>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("orderItems", locale));
				response.setHasError(true);
				return response;
			}
			List<OrderItemsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? OrderItemsTransformer.INSTANCE.toLiteDtos(itemsSaved) : OrderItemsTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end update OrderItems-----");
		return response;
	}

	/**
	 * delete OrderItems by using OrderItemsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<OrderItemsDto> delete(Request<OrderItemsDto> request, Locale locale)  {
		// System.out.println("----begin delete OrderItems-----");

		Response<OrderItemsDto> response = new Response<OrderItemsDto>();
		List<OrderItems>        items    = new ArrayList<OrderItems>();
			
		for (OrderItemsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la orderItems existe
			OrderItems existingEntity = null;

			existingEntity = orderItemsRepository.findOne(dto.getId());
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("orderItems -> " + dto.getId(), locale));
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
			orderItemsRepository.deleteAll((Iterable<OrderItems>) items);

			response.setHasError(false);
		}

		// System.out.println("----end delete OrderItems-----");
		return response;
	}

	/**
	 * get OrderItems by using OrderItemsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<OrderItemsDto> getByCriteria(Request<OrderItemsDto> request, Locale locale)  throws Exception {
		// System.out.println("----begin get OrderItems-----");

		Response<OrderItemsDto> response = new Response<OrderItemsDto>();
		List<OrderItems> items 			 = orderItemsRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<OrderItemsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? OrderItemsTransformer.INSTANCE.toLiteDtos(items) : OrderItemsTransformer.INSTANCE.toDtos(items);

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
			response.setCount(orderItemsRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("orderItems", locale));
			response.setHasError(false);
			return response;
		}

		// System.out.println("----end get OrderItems-----");
		return response;
	}

	/**
	 * get full OrderItemsDto by using OrderItems as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private OrderItemsDto getFullInfos(OrderItemsDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
