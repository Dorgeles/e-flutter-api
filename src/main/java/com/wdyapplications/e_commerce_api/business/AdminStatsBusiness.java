                                    									
/*
 * Java business for entity table admin_stats 
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
import com.wdyapplications.e_commerce_api.dao.entity.AdminStats;
import com.wdyapplications.e_commerce_api.dao.entity.*;
import com.wdyapplications.e_commerce_api.dao.repository.*;

/**
BUSINESS for table "admin_stats"
 * 
 * @author Dorgeddy
 *
 */

@Component
public class AdminStatsBusiness implements IBasicBusiness<Request<AdminStatsDto>, Response<AdminStatsDto>> {

	private Response<AdminStatsDto> response;
	@Autowired
	private AdminStatsRepository adminStatsRepository;
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

	public AdminStatsBusiness() {
		dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateTimeFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * create AdminStats by using AdminStatsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<AdminStatsDto> create(Request<AdminStatsDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin create AdminStats-----");

		Response<AdminStatsDto> response = new Response<AdminStatsDto>();
		List<AdminStats>        items    = new ArrayList<AdminStats>();
			
		for (AdminStatsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("statsDate", dto.getStatsDate());
			fieldsToVerify.put("totalRevenue", dto.getTotalRevenue());
			fieldsToVerify.put("totalOrders", dto.getTotalOrders());
			fieldsToVerify.put("activeUsers", dto.getActiveUsers());
			fieldsToVerify.put("newUsers", dto.getNewUsers());
			fieldsToVerify.put("totalProducts", dto.getTotalProducts());
			fieldsToVerify.put("outOfStockProducts", dto.getOutOfStockProducts());
			fieldsToVerify.put("calculatedAt", dto.getCalculatedAt());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if adminStats to insert do not exist
			AdminStats existingEntity = null;

/*
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("adminStats id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

*/
				AdminStats entityToSave = null;
			entityToSave = AdminStatsTransformer.INSTANCE.toEntity(dto);

			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<AdminStats> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = adminStatsRepository.saveAll((Iterable<AdminStats>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("adminStats", locale));
				response.setHasError(true);
				return response;
			}
			List<AdminStatsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? AdminStatsTransformer.INSTANCE.toLiteDtos(itemsSaved) : AdminStatsTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end create AdminStats-----");
		return response;
	}

	/**
	 * update AdminStats by using AdminStatsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<AdminStatsDto> update(Request<AdminStatsDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin update AdminStats-----");

		Response<AdminStatsDto> response = new Response<AdminStatsDto>();
		List<AdminStats>        items    = new ArrayList<AdminStats>();
			
		for (AdminStatsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la adminStats existe
			AdminStats entityToSave = null;
			entityToSave = adminStatsRepository.findOne(dto.getId());
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("adminStats id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			if (dto.getStatsDate() != null) {
				entityToSave.setStatsDate(dto.getStatsDate());
			}
//			if (dto.getTotalRevenue() != null && dto.getTotalRevenue() > 0) {
//				entityToSave.setTotalRevenue(dto.getTotalRevenue());
//			}
			if (dto.getTotalOrders() != null && dto.getTotalOrders() > 0) {
				entityToSave.setTotalOrders(dto.getTotalOrders());
			}
			if (dto.getActiveUsers() != null && dto.getActiveUsers() > 0) {
				entityToSave.setActiveUsers(dto.getActiveUsers());
			}
			if (dto.getNewUsers() != null && dto.getNewUsers() > 0) {
				entityToSave.setNewUsers(dto.getNewUsers());
			}
			if (dto.getTotalProducts() != null && dto.getTotalProducts() > 0) {
				entityToSave.setTotalProducts(dto.getTotalProducts());
			}
			if (dto.getOutOfStockProducts() != null && dto.getOutOfStockProducts() > 0) {
				entityToSave.setOutOfStockProducts(dto.getOutOfStockProducts());
			}
			if (Utilities.notBlank(dto.getCalculatedAt())) {
				entityToSave.setCalculatedAt(dateFormat.parse(dto.getCalculatedAt()));
			}
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<AdminStats> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = adminStatsRepository.saveAll((Iterable<AdminStats>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("adminStats", locale));
				response.setHasError(true);
				return response;
			}
			List<AdminStatsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? AdminStatsTransformer.INSTANCE.toLiteDtos(itemsSaved) : AdminStatsTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end update AdminStats-----");
		return response;
	}

	/**
	 * delete AdminStats by using AdminStatsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<AdminStatsDto> delete(Request<AdminStatsDto> request, Locale locale)  {
		// System.out.println("----begin delete AdminStats-----");

		Response<AdminStatsDto> response = new Response<AdminStatsDto>();
		List<AdminStats>        items    = new ArrayList<AdminStats>();
			
		for (AdminStatsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la adminStats existe
			AdminStats existingEntity = null;

			existingEntity = adminStatsRepository.findOne(dto.getId());
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("adminStats -> " + dto.getId(), locale));
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
			adminStatsRepository.deleteAll((Iterable<AdminStats>) items);

			response.setHasError(false);
		}

		// System.out.println("----end delete AdminStats-----");
		return response;
	}

	/**
	 * get AdminStats by using AdminStatsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<AdminStatsDto> getByCriteria(Request<AdminStatsDto> request, Locale locale)  throws Exception {
		// System.out.println("----begin get AdminStats-----");

		Response<AdminStatsDto> response = new Response<AdminStatsDto>();
		List<AdminStats> items 			 = adminStatsRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<AdminStatsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? AdminStatsTransformer.INSTANCE.toLiteDtos(items) : AdminStatsTransformer.INSTANCE.toDtos(items);

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
			response.setCount(adminStatsRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("adminStats", locale));
			response.setHasError(false);
			return response;
		}

		// System.out.println("----end get AdminStats-----");
		return response;
	}

	/**
	 * get full AdminStatsDto by using AdminStats as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private AdminStatsDto getFullInfos(AdminStatsDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
