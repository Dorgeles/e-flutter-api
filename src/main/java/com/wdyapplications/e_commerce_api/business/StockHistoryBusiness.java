                                								
/*
 * Java business for entity table stock_history 
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
import com.wdyapplications.e_commerce_api.dao.entity.StockHistory;
import com.wdyapplications.e_commerce_api.dao.entity.Products;
import com.wdyapplications.e_commerce_api.dao.entity.*;
import com.wdyapplications.e_commerce_api.dao.repository.*;

/**
BUSINESS for table "stock_history"
 * 
 * @author Dorgeddy
 *
 */

@Component
public class StockHistoryBusiness implements IBasicBusiness<Request<StockHistoryDto>, Response<StockHistoryDto>> {

	private Response<StockHistoryDto> response;
	@Autowired
	private StockHistoryRepository stockHistoryRepository;
	@Autowired
	private ProductsRepository productsRepository;
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

	public StockHistoryBusiness() {
		dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateTimeFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * create StockHistory by using StockHistoryDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<StockHistoryDto> create(Request<StockHistoryDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin create StockHistory-----");

		Response<StockHistoryDto> response = new Response<StockHistoryDto>();
		List<StockHistory>        items    = new ArrayList<StockHistory>();
			
		for (StockHistoryDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("productId", dto.getProductId());
			fieldsToVerify.put("oldQuantity", dto.getOldQuantity());
			fieldsToVerify.put("newQuantity", dto.getNewQuantity());
			fieldsToVerify.put("changeQuantity", dto.getChangeQuantity());
			fieldsToVerify.put("reason", dto.getReason());
			fieldsToVerify.put("referenceId", dto.getReferenceId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if stockHistory to insert do not exist
			StockHistory existingEntity = null;

/*
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("stockHistory id -> " + dto.getId(), locale));
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
				StockHistory entityToSave = null;
			entityToSave = StockHistoryTransformer.INSTANCE.toEntity(dto, existingProducts);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());

			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<StockHistory> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = stockHistoryRepository.saveAll((Iterable<StockHistory>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("stockHistory", locale));
				response.setHasError(true);
				return response;
			}
			List<StockHistoryDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? StockHistoryTransformer.INSTANCE.toLiteDtos(itemsSaved) : StockHistoryTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end create StockHistory-----");
		return response;
	}

	/**
	 * update StockHistory by using StockHistoryDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<StockHistoryDto> update(Request<StockHistoryDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin update StockHistory-----");

		Response<StockHistoryDto> response = new Response<StockHistoryDto>();
		List<StockHistory>        items    = new ArrayList<StockHistory>();
			
		for (StockHistoryDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la stockHistory existe
			StockHistory entityToSave = null;
			entityToSave = stockHistoryRepository.findOne(dto.getId());
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("stockHistory id -> " + dto.getId(), locale));
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
			if (dto.getOldQuantity() != null && dto.getOldQuantity() > 0) {
				entityToSave.setOldQuantity(dto.getOldQuantity());
			}
			if (dto.getNewQuantity() != null && dto.getNewQuantity() > 0) {
				entityToSave.setNewQuantity(dto.getNewQuantity());
			}
			if (dto.getChangeQuantity() != null && dto.getChangeQuantity() > 0) {
				entityToSave.setChangeQuantity(dto.getChangeQuantity());
			}
			if (Utilities.notBlank(dto.getReason())) {
				entityToSave.setReason(dto.getReason());
			}
			if (Utilities.notBlank(dto.getReferenceId())) {
				entityToSave.setReferenceId(dto.getReferenceId());
			}
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<StockHistory> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = stockHistoryRepository.saveAll((Iterable<StockHistory>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("stockHistory", locale));
				response.setHasError(true);
				return response;
			}
			List<StockHistoryDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? StockHistoryTransformer.INSTANCE.toLiteDtos(itemsSaved) : StockHistoryTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end update StockHistory-----");
		return response;
	}

	/**
	 * delete StockHistory by using StockHistoryDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<StockHistoryDto> delete(Request<StockHistoryDto> request, Locale locale)  {
		// System.out.println("----begin delete StockHistory-----");

		Response<StockHistoryDto> response = new Response<StockHistoryDto>();
		List<StockHistory>        items    = new ArrayList<StockHistory>();
			
		for (StockHistoryDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la stockHistory existe
			StockHistory existingEntity = null;

			existingEntity = stockHistoryRepository.findOne(dto.getId());
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("stockHistory -> " + dto.getId(), locale));
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
			stockHistoryRepository.deleteAll((Iterable<StockHistory>) items);

			response.setHasError(false);
		}

		// System.out.println("----end delete StockHistory-----");
		return response;
	}

	/**
	 * get StockHistory by using StockHistoryDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<StockHistoryDto> getByCriteria(Request<StockHistoryDto> request, Locale locale)  throws Exception {
		// System.out.println("----begin get StockHistory-----");

		Response<StockHistoryDto> response = new Response<StockHistoryDto>();
		List<StockHistory> items 			 = stockHistoryRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<StockHistoryDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? StockHistoryTransformer.INSTANCE.toLiteDtos(items) : StockHistoryTransformer.INSTANCE.toDtos(items);

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
			response.setCount(stockHistoryRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("stockHistory", locale));
			response.setHasError(false);
			return response;
		}

		// System.out.println("----end get StockHistory-----");
		return response;
	}

	/**
	 * get full StockHistoryDto by using StockHistory as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private StockHistoryDto getFullInfos(StockHistoryDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
