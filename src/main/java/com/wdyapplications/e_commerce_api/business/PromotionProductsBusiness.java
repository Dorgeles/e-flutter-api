                				
/*
 * Java business for entity table promotion_products 
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
import com.wdyapplications.e_commerce_api.dao.entity.PromotionProducts;
import com.wdyapplications.e_commerce_api.dao.entity.Products;
import com.wdyapplications.e_commerce_api.dao.entity.Promotions;
import com.wdyapplications.e_commerce_api.dao.entity.*;
import com.wdyapplications.e_commerce_api.dao.repository.*;

/**
BUSINESS for table "promotion_products"
 * 
 * @author Dorgeddy
 *
 */

@Component
public class PromotionProductsBusiness implements IBasicBusiness<Request<PromotionProductsDto>, Response<PromotionProductsDto>> {

	private Response<PromotionProductsDto> response;
	@Autowired
	private PromotionProductsRepository promotionProductsRepository;
	@Autowired
	private ProductsRepository productsRepository;
	@Autowired
	private PromotionsRepository promotionsRepository;
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

	public PromotionProductsBusiness() {
		dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateTimeFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * create PromotionProducts by using PromotionProductsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<PromotionProductsDto> create(Request<PromotionProductsDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin create PromotionProducts-----");

		Response<PromotionProductsDto> response = new Response<PromotionProductsDto>();
		List<PromotionProducts>        items    = new ArrayList<PromotionProducts>();
			
		for (PromotionProductsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("promotionId", dto.getPromotionId());
			fieldsToVerify.put("productId", dto.getProductId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if promotionProducts to insert do not exist
			PromotionProducts existingEntity = null;

/*
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("promotionProducts id -> " + dto.getId(), locale));
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
				PromotionProducts entityToSave = null;
			entityToSave = PromotionProductsTransformer.INSTANCE.toEntity(dto, existingProducts, existingPromotions);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());

			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<PromotionProducts> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = promotionProductsRepository.saveAll((Iterable<PromotionProducts>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("promotionProducts", locale));
				response.setHasError(true);
				return response;
			}
			List<PromotionProductsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? PromotionProductsTransformer.INSTANCE.toLiteDtos(itemsSaved) : PromotionProductsTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end create PromotionProducts-----");
		return response;
	}

	/**
	 * update PromotionProducts by using PromotionProductsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<PromotionProductsDto> update(Request<PromotionProductsDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin update PromotionProducts-----");

		Response<PromotionProductsDto> response = new Response<PromotionProductsDto>();
		List<PromotionProducts>        items    = new ArrayList<PromotionProducts>();
			
		for (PromotionProductsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la promotionProducts existe
			PromotionProducts entityToSave = null;
			entityToSave = promotionProductsRepository.findOne(dto.getId());
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("promotionProducts id -> " + dto.getId(), locale));
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
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<PromotionProducts> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = promotionProductsRepository.saveAll((Iterable<PromotionProducts>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("promotionProducts", locale));
				response.setHasError(true);
				return response;
			}
			List<PromotionProductsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? PromotionProductsTransformer.INSTANCE.toLiteDtos(itemsSaved) : PromotionProductsTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end update PromotionProducts-----");
		return response;
	}

	/**
	 * delete PromotionProducts by using PromotionProductsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<PromotionProductsDto> delete(Request<PromotionProductsDto> request, Locale locale)  {
		// System.out.println("----begin delete PromotionProducts-----");

		Response<PromotionProductsDto> response = new Response<PromotionProductsDto>();
		List<PromotionProducts>        items    = new ArrayList<PromotionProducts>();
			
		for (PromotionProductsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la promotionProducts existe
			PromotionProducts existingEntity = null;

			existingEntity = promotionProductsRepository.findOne(dto.getId());
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("promotionProducts -> " + dto.getId(), locale));
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
			promotionProductsRepository.deleteAll((Iterable<PromotionProducts>) items);

			response.setHasError(false);
		}

		// System.out.println("----end delete PromotionProducts-----");
		return response;
	}

	/**
	 * get PromotionProducts by using PromotionProductsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<PromotionProductsDto> getByCriteria(Request<PromotionProductsDto> request, Locale locale)  throws Exception {
		// System.out.println("----begin get PromotionProducts-----");

		Response<PromotionProductsDto> response = new Response<PromotionProductsDto>();
		List<PromotionProducts> items 			 = promotionProductsRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<PromotionProductsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? PromotionProductsTransformer.INSTANCE.toLiteDtos(items) : PromotionProductsTransformer.INSTANCE.toDtos(items);

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
			response.setCount(promotionProductsRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("promotionProducts", locale));
			response.setHasError(false);
			return response;
		}

		// System.out.println("----end get PromotionProducts-----");
		return response;
	}

	/**
	 * get full PromotionProductsDto by using PromotionProducts as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private PromotionProductsDto getFullInfos(PromotionProductsDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
