                                    									
/*
 * Java business for entity table categories 
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
import com.wdyapplications.e_commerce_api.dao.entity.Categories;
import com.wdyapplications.e_commerce_api.dao.entity.Categories;
import com.wdyapplications.e_commerce_api.dao.entity.*;
import com.wdyapplications.e_commerce_api.dao.repository.*;

/**
BUSINESS for table "categories"
 * 
 * @author Dorgeddy
 *
 */

@Component
public class CategoriesBusiness implements IBasicBusiness<Request<CategoriesDto>, Response<CategoriesDto>> {

	private Response<CategoriesDto> response;
	@Autowired
	private CategoriesRepository categoriesRepository;
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

	public CategoriesBusiness() {
		dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateTimeFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * create Categories by using CategoriesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CategoriesDto> create(Request<CategoriesDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin create Categories-----");

		Response<CategoriesDto> response = new Response<CategoriesDto>();
		List<Categories>        items    = new ArrayList<Categories>();
			
		for (CategoriesDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("name", dto.getName());
			fieldsToVerify.put("description", dto.getDescription());
			fieldsToVerify.put("iconUrl", dto.getIconUrl());
			//fieldsToVerify.put("parentId", dto.getParentId());
			//fieldsToVerify.put("isActive", dto.getIsActive());
			//fieldsToVerify.put("sortOrder", dto.getSortOrder());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if categories to insert do not exist
			Categories existingEntity = null;

/*
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("categories id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

*/
			// verif unique name in db
			existingEntity = categoriesRepository.findByName(dto.getName());
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("categories name -> " + dto.getName(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique name in items to save
			if (items.stream().anyMatch(a -> a.getName().equalsIgnoreCase(dto.getName()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" name ", locale));
				response.setHasError(true);
				return response;
			}

			// Verify if categories exist
			Categories existingCategories = null;
			if (dto.getParentId() != null && dto.getParentId() > 0){
				existingCategories = categoriesRepository.findOne(dto.getParentId());
				if (existingCategories == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("categories parentId -> " + dto.getParentId(), locale));
					response.setHasError(true);
					return response;
				}
			}
				Categories entityToSave = null;
			entityToSave = CategoriesTransformer.INSTANCE.toEntity(dto, existingCategories);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());

			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Categories> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = categoriesRepository.saveAll((Iterable<Categories>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("categories", locale));
				response.setHasError(true);
				return response;
			}
			List<CategoriesDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? CategoriesTransformer.INSTANCE.toLiteDtos(itemsSaved) : CategoriesTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end create Categories-----");
		return response;
	}

	/**
	 * update Categories by using CategoriesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CategoriesDto> update(Request<CategoriesDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin update Categories-----");

		Response<CategoriesDto> response = new Response<CategoriesDto>();
		List<Categories>        items    = new ArrayList<Categories>();
			
		for (CategoriesDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la categories existe
			Categories entityToSave = null;
			entityToSave = categoriesRepository.findOne(dto.getId());
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("categories id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if categories exist
			if (dto.getParentId() != null && dto.getParentId() > 0){
				Categories existingCategories = categoriesRepository.findOne(dto.getParentId());
				if (existingCategories == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("categories parentId -> " + dto.getParentId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setCategories(existingCategories);
			}
			if (Utilities.notBlank(dto.getName())) {
				entityToSave.setName(dto.getName());
			}
			if (Utilities.notBlank(dto.getDescription())) {
				entityToSave.setDescription(dto.getDescription());
			}
			if (Utilities.notBlank(dto.getIconUrl())) {
				entityToSave.setIconUrl(dto.getIconUrl());
			}
			if (dto.getIsActive() != null) {
				entityToSave.setIsActive(dto.getIsActive());
			}
			if (dto.getSortOrder() != null && dto.getSortOrder() > 0) {
				entityToSave.setSortOrder(dto.getSortOrder());
			}
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Categories> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = categoriesRepository.saveAll((Iterable<Categories>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("categories", locale));
				response.setHasError(true);
				return response;
			}
			List<CategoriesDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? CategoriesTransformer.INSTANCE.toLiteDtos(itemsSaved) : CategoriesTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end update Categories-----");
		return response;
	}

	/**
	 * delete Categories by using CategoriesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CategoriesDto> delete(Request<CategoriesDto> request, Locale locale)  {
		// System.out.println("----begin delete Categories-----");

		Response<CategoriesDto> response = new Response<CategoriesDto>();
		List<Categories>        items    = new ArrayList<Categories>();
			
		for (CategoriesDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la categories existe
			Categories existingEntity = null;

			existingEntity = categoriesRepository.findOne(dto.getId());
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("categories -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// categories
			List<Categories> listOfCategories = categoriesRepository.findByParentId(existingEntity.getId());
			if (listOfCategories != null && !listOfCategories.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfCategories.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// products
			List<Products> listOfProducts = productsRepository.findByCategoryId(existingEntity.getId());
			if (listOfProducts != null && !listOfProducts.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfProducts.size() + ")", locale));
				response.setHasError(true);
				return response;
			}


			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			categoriesRepository.deleteAll((Iterable<Categories>) items);

			response.setHasError(false);
		}

		// System.out.println("----end delete Categories-----");
		return response;
	}

	/**
	 * get Categories by using CategoriesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CategoriesDto> getByCriteria(Request<CategoriesDto> request, Locale locale)  throws Exception {
		// System.out.println("----begin get Categories-----");

		Response<CategoriesDto> response = new Response<CategoriesDto>();
		List<Categories> items 			 = categoriesRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<CategoriesDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? CategoriesTransformer.INSTANCE.toLiteDtos(items) : CategoriesTransformer.INSTANCE.toDtos(items);

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
			response.setCount(categoriesRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("categories", locale));
			response.setHasError(false);
			return response;
		}

		// System.out.println("----end get Categories-----");
		return response;
	}

	/**
	 * get full CategoriesDto by using Categories as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private CategoriesDto getFullInfos(CategoriesDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
