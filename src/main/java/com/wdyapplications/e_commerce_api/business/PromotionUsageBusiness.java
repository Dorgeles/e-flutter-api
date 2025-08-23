                        						
/*
 * Java business for entity table promotion_usage 
 * Created on 2025-08-23 ( Time 06:18:37 )
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
import com.wdyapplications.e_commerce_api.dao.entity.PromotionUsage;
import com.wdyapplications.e_commerce_api.dao.entity.Orders;
import com.wdyapplications.e_commerce_api.dao.entity.Promotions;
import com.wdyapplications.e_commerce_api.dao.entity.Users;
import com.wdyapplications.e_commerce_api.dao.entity.*;
import com.wdyapplications.e_commerce_api.dao.repository.*;

/**
BUSINESS for table "promotion_usage"
 * 
 * @author Dorgeddy
 *
 */

@Component
public class PromotionUsageBusiness implements IBasicBusiness<Request<PromotionUsageDto>, Response<PromotionUsageDto>> {

	private Response<PromotionUsageDto> response;
	@Autowired
	private PromotionUsageRepository promotionUsageRepository;
	@Autowired
	private OrdersRepository ordersRepository;
	@Autowired
	private PromotionsRepository promotionsRepository;
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

	public PromotionUsageBusiness() {
		dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateTimeFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * create PromotionUsage by using PromotionUsageDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<PromotionUsageDto> create(Request<PromotionUsageDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin create PromotionUsage-----");

		Response<PromotionUsageDto> response = new Response<PromotionUsageDto>();
		List<PromotionUsage>        items    = new ArrayList<PromotionUsage>();
			
		for (PromotionUsageDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("promotionId", dto.getPromotionId());
			fieldsToVerify.put("orderId", dto.getOrderId());
			fieldsToVerify.put("userId", dto.getUserId());
			fieldsToVerify.put("discountAmount", dto.getDiscountAmount());
			fieldsToVerify.put("usedAt", dto.getUsedAt());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if promotionUsage to insert do not exist
			PromotionUsage existingEntity = null;

/*
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("promotionUsage id -> " + dto.getId(), locale));
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
			// Verify if promotions exist
			Promotions existingPromotions = null;
			if (dto.getPromotionId() != null && dto.getPromotionId() > 0){
				existingPromotions = promotionsRepository.findOne(dto.getPromotionId());
				if (existingPromotions == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("promotions promotionId -> " + dto.getPromotionId(), locale));
					response.setHasError(true);
					return response;
				}
			}
			// Verify if users exist
			Users existingUsers = null;
			if (dto.getUserId() != null && dto.getUserId() > 0){
				existingUsers = usersRepository.findOne(dto.getUserId());
				if (existingUsers == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("users userId -> " + dto.getUserId(), locale));
					response.setHasError(true);
					return response;
				}
			}
				PromotionUsage entityToSave = null;
			entityToSave = PromotionUsageTransformer.INSTANCE.toEntity(dto, existingOrders, existingPromotions, existingUsers);

			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<PromotionUsage> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = promotionUsageRepository.saveAll((Iterable<PromotionUsage>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("promotionUsage", locale));
				response.setHasError(true);
				return response;
			}
			List<PromotionUsageDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? PromotionUsageTransformer.INSTANCE.toLiteDtos(itemsSaved) : PromotionUsageTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end create PromotionUsage-----");
		return response;
	}

	/**
	 * update PromotionUsage by using PromotionUsageDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<PromotionUsageDto> update(Request<PromotionUsageDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin update PromotionUsage-----");

		Response<PromotionUsageDto> response = new Response<PromotionUsageDto>();
		List<PromotionUsage>        items    = new ArrayList<PromotionUsage>();
			
		for (PromotionUsageDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la promotionUsage existe
			PromotionUsage entityToSave = null;
			entityToSave = promotionUsageRepository.findOne(dto.getId());
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("promotionUsage id -> " + dto.getId(), locale));
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
			// Verify if promotions exist
			if (dto.getPromotionId() != null && dto.getPromotionId() > 0){
				Promotions existingPromotions = promotionsRepository.findOne(dto.getPromotionId());
				if (existingPromotions == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("promotions promotionId -> " + dto.getPromotionId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setPromotions(existingPromotions);
			}
			// Verify if users exist
			if (dto.getUserId() != null && dto.getUserId() > 0){
				Users existingUsers = usersRepository.findOne(dto.getUserId());
				if (existingUsers == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("users userId -> " + dto.getUserId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setUsers(existingUsers);
			}
//			if (dto.getDiscountAmount() != null && dto.getDiscountAmount() > 0) {
//				entityToSave.setDiscountAmount(dto.getDiscountAmount());
//			}
			if (Utilities.notBlank(dto.getUsedAt())) {
				entityToSave.setUsedAt(dateFormat.parse(dto.getUsedAt()));
			}
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<PromotionUsage> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = promotionUsageRepository.saveAll((Iterable<PromotionUsage>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("promotionUsage", locale));
				response.setHasError(true);
				return response;
			}
			List<PromotionUsageDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? PromotionUsageTransformer.INSTANCE.toLiteDtos(itemsSaved) : PromotionUsageTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end update PromotionUsage-----");
		return response;
	}

	/**
	 * delete PromotionUsage by using PromotionUsageDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<PromotionUsageDto> delete(Request<PromotionUsageDto> request, Locale locale)  {
		// System.out.println("----begin delete PromotionUsage-----");

		Response<PromotionUsageDto> response = new Response<PromotionUsageDto>();
		List<PromotionUsage>        items    = new ArrayList<PromotionUsage>();
			
		for (PromotionUsageDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la promotionUsage existe
			PromotionUsage existingEntity = null;

			existingEntity = promotionUsageRepository.findOne(dto.getId());
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("promotionUsage -> " + dto.getId(), locale));
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
			promotionUsageRepository.deleteAll((Iterable<PromotionUsage>) items);

			response.setHasError(false);
		}

		// System.out.println("----end delete PromotionUsage-----");
		return response;
	}

	/**
	 * get PromotionUsage by using PromotionUsageDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<PromotionUsageDto> getByCriteria(Request<PromotionUsageDto> request, Locale locale)  throws Exception {
		// System.out.println("----begin get PromotionUsage-----");

		Response<PromotionUsageDto> response = new Response<PromotionUsageDto>();
		List<PromotionUsage> items 			 = promotionUsageRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<PromotionUsageDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? PromotionUsageTransformer.INSTANCE.toLiteDtos(items) : PromotionUsageTransformer.INSTANCE.toDtos(items);

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
			response.setCount(promotionUsageRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("promotionUsage", locale));
			response.setHasError(false);
			return response;
		}

		// System.out.println("----end get PromotionUsage-----");
		return response;
	}

	/**
	 * get full PromotionUsageDto by using PromotionUsage as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private PromotionUsageDto getFullInfos(PromotionUsageDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
