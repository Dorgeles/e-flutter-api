                            							
/*
 * Java business for entity table sessions 
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
import com.wdyapplications.e_commerce_api.dao.entity.Sessions;
import com.wdyapplications.e_commerce_api.dao.entity.Users;
import com.wdyapplications.e_commerce_api.dao.entity.*;
import com.wdyapplications.e_commerce_api.dao.repository.*;

/**
BUSINESS for table "sessions"
 * 
 * @author Dorgeddy
 *
 */

@Component
public class SessionsBusiness implements IBasicBusiness<Request<SessionsDto>, Response<SessionsDto>> {

	private Response<SessionsDto> response;
	@Autowired
	private SessionsRepository sessionsRepository;
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

	public SessionsBusiness() {
		dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateTimeFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * create Sessions by using SessionsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<SessionsDto> create(Request<SessionsDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin create Sessions-----");

		Response<SessionsDto> response = new Response<SessionsDto>();
		List<Sessions>        items    = new ArrayList<Sessions>();
			
		for (SessionsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			fieldsToVerify.put("userId", dto.getUserId());
			fieldsToVerify.put("token", dto.getToken());
			fieldsToVerify.put("expiresAt", dto.getExpiresAt());
			fieldsToVerify.put("deviceInfo", dto.getDeviceInfo());
			fieldsToVerify.put("ipAddress", dto.getIpAddress());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if sessions to insert do not exist
			Sessions existingEntity = null;

			existingEntity = sessionsRepository.findOne(dto.getId());
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("sessions id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}
/*
*/
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
				Sessions entityToSave = null;
			entityToSave = SessionsTransformer.INSTANCE.toEntity(dto, existingUsers);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());

			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Sessions> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = sessionsRepository.saveAll((Iterable<Sessions>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("sessions", locale));
				response.setHasError(true);
				return response;
			}
			List<SessionsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? SessionsTransformer.INSTANCE.toLiteDtos(itemsSaved) : SessionsTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end create Sessions-----");
		return response;
	}

	/**
	 * update Sessions by using SessionsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<SessionsDto> update(Request<SessionsDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin update Sessions-----");

		Response<SessionsDto> response = new Response<SessionsDto>();
		List<Sessions>        items    = new ArrayList<Sessions>();
			
		for (SessionsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la sessions existe
			Sessions entityToSave = null;
			entityToSave = sessionsRepository.findOne(dto.getId());
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("sessions id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
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
			if (Utilities.notBlank(dto.getToken())) {
				entityToSave.setToken(dto.getToken());
			}
			if (Utilities.notBlank(dto.getExpiresAt())) {
				entityToSave.setExpiresAt(dateFormat.parse(dto.getExpiresAt()));
			}
			if (Utilities.notBlank(dto.getDeviceInfo())) {
				entityToSave.setDeviceInfo(dto.getDeviceInfo());
			}
			if (Utilities.notBlank(dto.getIpAddress())) {
				entityToSave.setIpAddress(dto.getIpAddress());
			}
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Sessions> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = sessionsRepository.saveAll((Iterable<Sessions>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("sessions", locale));
				response.setHasError(true);
				return response;
			}
			List<SessionsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? SessionsTransformer.INSTANCE.toLiteDtos(itemsSaved) : SessionsTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end update Sessions-----");
		return response;
	}

	/**
	 * delete Sessions by using SessionsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<SessionsDto> delete(Request<SessionsDto> request, Locale locale)  {
		// System.out.println("----begin delete Sessions-----");

		Response<SessionsDto> response = new Response<SessionsDto>();
		List<Sessions>        items    = new ArrayList<Sessions>();
			
		for (SessionsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la sessions existe
			Sessions existingEntity = null;

			existingEntity = sessionsRepository.findOne(dto.getId());
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("sessions -> " + dto.getId(), locale));
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
			sessionsRepository.deleteAll((Iterable<Sessions>) items);

			response.setHasError(false);
		}

		// System.out.println("----end delete Sessions-----");
		return response;
	}

	/**
	 * get Sessions by using SessionsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<SessionsDto> getByCriteria(Request<SessionsDto> request, Locale locale)  throws Exception {
		// System.out.println("----begin get Sessions-----");

		Response<SessionsDto> response = new Response<SessionsDto>();
		List<Sessions> items 			 = sessionsRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<SessionsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? SessionsTransformer.INSTANCE.toLiteDtos(items) : SessionsTransformer.INSTANCE.toDtos(items);

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
			response.setCount(sessionsRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("sessions", locale));
			response.setHasError(false);
			return response;
		}

		// System.out.println("----end get Sessions-----");
		return response;
	}

	/**
	 * get full SessionsDto by using Sessions as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private SessionsDto getFullInfos(SessionsDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
