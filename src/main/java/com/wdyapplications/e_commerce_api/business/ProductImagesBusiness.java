                            							
/*
 * Java business for entity table product_images 
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
import com.wdyapplications.e_commerce_api.dao.entity.ProductImages;
import com.wdyapplications.e_commerce_api.dao.entity.Products;
import com.wdyapplications.e_commerce_api.dao.entity.*;
import com.wdyapplications.e_commerce_api.dao.repository.*;

/**
BUSINESS for table "product_images"
 * 
 * @author Dorgeddy
 *
 */

@Component
public class ProductImagesBusiness implements IBasicBusiness<Request<ProductImagesDto>, Response<ProductImagesDto>> {

	private Response<ProductImagesDto> response;
	@Autowired
	private ProductImagesRepository productImagesRepository;
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

	public ProductImagesBusiness() {
		dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateTimeFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * create ProductImages by using ProductImagesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ProductImagesDto> create(Request<ProductImagesDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin create ProductImages-----");

		Response<ProductImagesDto> response = new Response<ProductImagesDto>();
		List<ProductImages>        items    = new ArrayList<ProductImages>();
			
		for (ProductImagesDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("productId", dto.getProductId());
			fieldsToVerify.put("imageUrl", dto.getImageUrl());
			fieldsToVerify.put("altText", dto.getAltText());
			//fieldsToVerify.put("isPrimary", dto.getIsPrimary());
			//fieldsToVerify.put("sortOrder", dto.getSortOrder());
			//fieldsToVerify.put("uploadedAt", dto.getUploadedAt());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if productImages to insert do not exist
			ProductImages existingEntity = null;

/*
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("productImages id -> " + dto.getId(), locale));
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
				ProductImages entityToSave = null;
			entityToSave = ProductImagesTransformer.INSTANCE.toEntity(dto, existingProducts);

			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<ProductImages> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = productImagesRepository.saveAll((Iterable<ProductImages>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("productImages", locale));
				response.setHasError(true);
				return response;
			}
			List<ProductImagesDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ProductImagesTransformer.INSTANCE.toLiteDtos(itemsSaved) : ProductImagesTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end create ProductImages-----");
		return response;
	}

	/**
	 * update ProductImages by using ProductImagesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ProductImagesDto> update(Request<ProductImagesDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin update ProductImages-----");

		Response<ProductImagesDto> response = new Response<ProductImagesDto>();
		List<ProductImages>        items    = new ArrayList<ProductImages>();
			
		for (ProductImagesDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la productImages existe
			ProductImages entityToSave = null;
			entityToSave = productImagesRepository.findOne(dto.getId());
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("productImages id -> " + dto.getId(), locale));
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
			if (Utilities.notBlank(dto.getImageUrl())) {
				entityToSave.setImageUrl(dto.getImageUrl());
			}
			if (Utilities.notBlank(dto.getAltText())) {
				entityToSave.setAltText(dto.getAltText());
			}
			if (dto.getIsPrimary() != null) {
				entityToSave.setIsPrimary(dto.getIsPrimary());
			}
			if (dto.getSortOrder() != null && dto.getSortOrder() > 0) {
				entityToSave.setSortOrder(dto.getSortOrder());
			}
			if (Utilities.notBlank(dto.getUploadedAt())) {
				entityToSave.setUploadedAt(dateFormat.parse(dto.getUploadedAt()));
			}
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<ProductImages> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = productImagesRepository.saveAll((Iterable<ProductImages>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("productImages", locale));
				response.setHasError(true);
				return response;
			}
			List<ProductImagesDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ProductImagesTransformer.INSTANCE.toLiteDtos(itemsSaved) : ProductImagesTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end update ProductImages-----");
		return response;
	}

	/**
	 * delete ProductImages by using ProductImagesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ProductImagesDto> delete(Request<ProductImagesDto> request, Locale locale)  {
		// System.out.println("----begin delete ProductImages-----");

		Response<ProductImagesDto> response = new Response<ProductImagesDto>();
		List<ProductImages>        items    = new ArrayList<ProductImages>();
			
		for (ProductImagesDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la productImages existe
			ProductImages existingEntity = null;

			existingEntity = productImagesRepository.findOne(dto.getId());
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("productImages -> " + dto.getId(), locale));
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
			productImagesRepository.deleteAll((Iterable<ProductImages>) items);

			response.setHasError(false);
		}

		// System.out.println("----end delete ProductImages-----");
		return response;
	}

	/**
	 * get ProductImages by using ProductImagesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ProductImagesDto> getByCriteria(Request<ProductImagesDto> request, Locale locale)  throws Exception {
		// System.out.println("----begin get ProductImages-----");

		Response<ProductImagesDto> response = new Response<ProductImagesDto>();
		List<ProductImages> items 			 = productImagesRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<ProductImagesDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ProductImagesTransformer.INSTANCE.toLiteDtos(items) : ProductImagesTransformer.INSTANCE.toDtos(items);

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
			response.setCount(productImagesRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("productImages", locale));
			response.setHasError(false);
			return response;
		}

		// System.out.println("----end get ProductImages-----");
		return response;
	}

	/**
	 * get full ProductImagesDto by using ProductImages as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private ProductImagesDto getFullInfos(ProductImagesDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
