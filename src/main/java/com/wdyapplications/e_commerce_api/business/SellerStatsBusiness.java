                                    									
/*
 * Java business for entity table seller_stats 
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
import com.wdyapplications.e_commerce_api.dao.entity.SellerStats;
import com.wdyapplications.e_commerce_api.dao.entity.Users;
import com.wdyapplications.e_commerce_api.dao.entity.*;
import com.wdyapplications.e_commerce_api.dao.repository.*;

/**
BUSINESS for table "seller_stats"
 * 
 * @author Dorgeddy
 *
 */

@Component
public class SellerStatsBusiness implements IBasicBusiness<Request<SellerStatsDto>, Response<SellerStatsDto>> {

	private Response<SellerStatsDto> response;
	@Autowired
	private SellerStatsRepository sellerStatsRepository;
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

	public SellerStatsBusiness() {
		dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateTimeFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * create SellerStats by using SellerStatsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<SellerStatsDto> create(Request<SellerStatsDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin create SellerStats-----");

		Response<SellerStatsDto> response = new Response<SellerStatsDto>();
		List<SellerStats>        items    = new ArrayList<SellerStats>();
			
		for (SellerStatsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("sellerId", dto.getSellerId());
			fieldsToVerify.put("year", dto.getYear());
			fieldsToVerify.put("month", dto.getMonth());
			fieldsToVerify.put("totalRevenue", dto.getTotalRevenue());
			fieldsToVerify.put("totalOrders", dto.getTotalOrders());
			fieldsToVerify.put("totalProductsSold", dto.getTotalProductsSold());
			fieldsToVerify.put("averageRating", dto.getAverageRating());
			fieldsToVerify.put("calculatedAt", dto.getCalculatedAt());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if sellerStats to insert do not exist
			SellerStats existingEntity = null;

/*
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("sellerStats id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

*/
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
				SellerStats entityToSave = null;
			entityToSave = SellerStatsTransformer.INSTANCE.toEntity(dto, existingUsers);

			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<SellerStats> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = sellerStatsRepository.saveAll((Iterable<SellerStats>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("sellerStats", locale));
				response.setHasError(true);
				return response;
			}
			List<SellerStatsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? SellerStatsTransformer.INSTANCE.toLiteDtos(itemsSaved) : SellerStatsTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end create SellerStats-----");
		return response;
	}

	/**
	 * update SellerStats by using SellerStatsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<SellerStatsDto> update(Request<SellerStatsDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin update SellerStats-----");

		Response<SellerStatsDto> response = new Response<SellerStatsDto>();
		List<SellerStats>        items    = new ArrayList<SellerStats>();
			
		for (SellerStatsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la sellerStats existe
			SellerStats entityToSave = null;
			entityToSave = sellerStatsRepository.findOne(dto.getId());
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("sellerStats id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
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
			if (dto.getYear() != null && dto.getYear() > 0) {
				entityToSave.setYear(dto.getYear());
			}
			if (dto.getMonth() != null && dto.getMonth() > 0) {
				entityToSave.setMonth(dto.getMonth());
			}
//			if (dto.getTotalRevenue() != null && dto.getTotalRevenue() > 0) {
//				entityToSave.setTotalRevenue(dto.getTotalRevenue());
//			}
			if (dto.getTotalOrders() != null && dto.getTotalOrders() > 0) {
				entityToSave.setTotalOrders(dto.getTotalOrders());
			}
			if (dto.getTotalProductsSold() != null && dto.getTotalProductsSold() > 0) {
				entityToSave.setTotalProductsSold(dto.getTotalProductsSold());
			}
//			if (dto.getAverageRating() != null && dto.getAverageRating() > 0) {
//				entityToSave.setAverageRating(dto.getAverageRating());
//			}
			if (Utilities.notBlank(dto.getCalculatedAt())) {
				entityToSave.setCalculatedAt(dateFormat.parse(dto.getCalculatedAt()));
			}
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<SellerStats> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = sellerStatsRepository.saveAll((Iterable<SellerStats>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("sellerStats", locale));
				response.setHasError(true);
				return response;
			}
			List<SellerStatsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? SellerStatsTransformer.INSTANCE.toLiteDtos(itemsSaved) : SellerStatsTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end update SellerStats-----");
		return response;
	}

	/**
	 * delete SellerStats by using SellerStatsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<SellerStatsDto> delete(Request<SellerStatsDto> request, Locale locale)  {
		// System.out.println("----begin delete SellerStats-----");

		Response<SellerStatsDto> response = new Response<SellerStatsDto>();
		List<SellerStats>        items    = new ArrayList<SellerStats>();
			
		for (SellerStatsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la sellerStats existe
			SellerStats existingEntity = null;

			existingEntity = sellerStatsRepository.findOne(dto.getId());
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("sellerStats -> " + dto.getId(), locale));
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
			sellerStatsRepository.deleteAll((Iterable<SellerStats>) items);

			response.setHasError(false);
		}

		// System.out.println("----end delete SellerStats-----");
		return response;
	}

	/**
	 * get SellerStats by using SellerStatsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<SellerStatsDto> getByCriteria(Request<SellerStatsDto> request, Locale locale)  throws Exception {
		// System.out.println("----begin get SellerStats-----");

		Response<SellerStatsDto> response = new Response<SellerStatsDto>();
		List<SellerStats> items 			 = sellerStatsRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<SellerStatsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? SellerStatsTransformer.INSTANCE.toLiteDtos(items) : SellerStatsTransformer.INSTANCE.toDtos(items);

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
			response.setCount(sellerStatsRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("sellerStats", locale));
			response.setHasError(false);
			return response;
		}

		// System.out.println("----end get SellerStats-----");
		return response;
	}

	/**
	 * get full SellerStatsDto by using SellerStats as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private SellerStatsDto getFullInfos(SellerStatsDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
