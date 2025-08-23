                                                        														
/*
 * Java business for entity table promotions 
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
import com.wdyapplications.e_commerce_api.dao.entity.Promotions;
import com.wdyapplications.e_commerce_api.dao.entity.*;
import com.wdyapplications.e_commerce_api.dao.repository.*;

/**
BUSINESS for table "promotions"
 * 
 * @author Dorgeddy
 *
 */

@Component
public class PromotionsBusiness implements IBasicBusiness<Request<PromotionsDto>, Response<PromotionsDto>> {

	private Response<PromotionsDto> response;
	@Autowired
	private PromotionsRepository promotionsRepository;
	@Autowired
	private PromotionProductsRepository promotionProductsRepository;
	@Autowired
	private PromotionUsageRepository promotionUsageRepository;
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

	public PromotionsBusiness() {
		dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateTimeFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * create Promotions by using PromotionsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<PromotionsDto> create(Request<PromotionsDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin create Promotions-----");

		Response<PromotionsDto> response = new Response<PromotionsDto>();
		List<Promotions>        items    = new ArrayList<Promotions>();
			
		for (PromotionsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("code", dto.getCode());
			fieldsToVerify.put("name", dto.getName());
			fieldsToVerify.put("description", dto.getDescription());
			fieldsToVerify.put("type", dto.getType());
			fieldsToVerify.put("value", dto.getValue());
			fieldsToVerify.put("minOrderAmount", dto.getMinOrderAmount());
			fieldsToVerify.put("maxUses", dto.getMaxUses());
			fieldsToVerify.put("usedCount", dto.getUsedCount());
			fieldsToVerify.put("startDate", dto.getStartDate());
			fieldsToVerify.put("endDate", dto.getEndDate());
			fieldsToVerify.put("isActive", dto.getIsActive());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if promotions to insert do not exist
			Promotions existingEntity = null;

/*
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("promotions id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

*/
			// verif unique code in db
			existingEntity = promotionsRepository.findByCode(dto.getCode());
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("promotions code -> " + dto.getCode(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique code in items to save
			if (items.stream().anyMatch(a -> a.getCode().equalsIgnoreCase(dto.getCode()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" code ", locale));
				response.setHasError(true);
				return response;
			}

			// verif unique name in db
			existingEntity = promotionsRepository.findByName(dto.getName());
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("promotions name -> " + dto.getName(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique name in items to save
			if (items.stream().anyMatch(a -> a.getName().equalsIgnoreCase(dto.getName()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" name ", locale));
				response.setHasError(true);
				return response;
			}

				Promotions entityToSave = null;
			entityToSave = PromotionsTransformer.INSTANCE.toEntity(dto);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());

			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Promotions> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = promotionsRepository.saveAll((Iterable<Promotions>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("promotions", locale));
				response.setHasError(true);
				return response;
			}
			List<PromotionsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? PromotionsTransformer.INSTANCE.toLiteDtos(itemsSaved) : PromotionsTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end create Promotions-----");
		return response;
	}

	/**
	 * update Promotions by using PromotionsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<PromotionsDto> update(Request<PromotionsDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin update Promotions-----");

		Response<PromotionsDto> response = new Response<PromotionsDto>();
		List<Promotions>        items    = new ArrayList<Promotions>();
			
		for (PromotionsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la promotions existe
			Promotions entityToSave = null;
			entityToSave = promotionsRepository.findOne(dto.getId());
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("promotions id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			if (Utilities.notBlank(dto.getCode())) {
				entityToSave.setCode(dto.getCode());
			}
			if (Utilities.notBlank(dto.getName())) {
				entityToSave.setName(dto.getName());
			}
			if (Utilities.notBlank(dto.getDescription())) {
				entityToSave.setDescription(dto.getDescription());
			}
			if (Utilities.notBlank(dto.getType())) {
				entityToSave.setType(dto.getType());
			}
//			if (dto.getValue() != null && dto.getValue() > 0) {
//				entityToSave.setValue(dto.getValue());
//			}
//			if (dto.getMinOrderAmount() != null && dto.getMinOrderAmount() > 0) {
//				entityToSave.setMinOrderAmount(dto.getMinOrderAmount());
//			}
			if (dto.getMaxUses() != null && dto.getMaxUses() > 0) {
				entityToSave.setMaxUses(dto.getMaxUses());
			}
			if (dto.getUsedCount() != null && dto.getUsedCount() > 0) {
				entityToSave.setUsedCount(dto.getUsedCount());
			}
			if (Utilities.notBlank(dto.getStartDate())) {
				entityToSave.setStartDate(dateFormat.parse(dto.getStartDate()));
			}
			if (Utilities.notBlank(dto.getEndDate())) {
				entityToSave.setEndDate(dateFormat.parse(dto.getEndDate()));
			}
			if (dto.getIsActive() != null) {
				entityToSave.setIsActive(dto.getIsActive());
			}
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Promotions> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = promotionsRepository.saveAll((Iterable<Promotions>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("promotions", locale));
				response.setHasError(true);
				return response;
			}
			List<PromotionsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? PromotionsTransformer.INSTANCE.toLiteDtos(itemsSaved) : PromotionsTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end update Promotions-----");
		return response;
	}

	/**
	 * delete Promotions by using PromotionsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<PromotionsDto> delete(Request<PromotionsDto> request, Locale locale)  {
		// System.out.println("----begin delete Promotions-----");

		Response<PromotionsDto> response = new Response<PromotionsDto>();
		List<Promotions>        items    = new ArrayList<Promotions>();
			
		for (PromotionsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la promotions existe
			Promotions existingEntity = null;

			existingEntity = promotionsRepository.findOne(dto.getId());
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("promotions -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// promotionProducts
			List<PromotionProducts> listOfPromotionProducts = promotionProductsRepository.findByPromotionId(existingEntity.getId());
			if (listOfPromotionProducts != null && !listOfPromotionProducts.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfPromotionProducts.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// promotionUsage
			List<PromotionUsage> listOfPromotionUsage = promotionUsageRepository.findByPromotionId(existingEntity.getId());
			if (listOfPromotionUsage != null && !listOfPromotionUsage.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfPromotionUsage.size() + ")", locale));
				response.setHasError(true);
				return response;
			}


			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			promotionsRepository.deleteAll((Iterable<Promotions>) items);

			response.setHasError(false);
		}

		// System.out.println("----end delete Promotions-----");
		return response;
	}

	/**
	 * get Promotions by using PromotionsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<PromotionsDto> getByCriteria(Request<PromotionsDto> request, Locale locale)  throws Exception {
		// System.out.println("----begin get Promotions-----");

		Response<PromotionsDto> response = new Response<PromotionsDto>();
		List<Promotions> items 			 = promotionsRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<PromotionsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? PromotionsTransformer.INSTANCE.toLiteDtos(items) : PromotionsTransformer.INSTANCE.toDtos(items);

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
			response.setCount(promotionsRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("promotions", locale));
			response.setHasError(false);
			return response;
		}

		// System.out.println("----end get Promotions-----");
		return response;
	}

	/**
	 * get full PromotionsDto by using Promotions as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private PromotionsDto getFullInfos(PromotionsDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
