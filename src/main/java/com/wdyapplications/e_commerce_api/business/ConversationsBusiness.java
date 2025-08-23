                            							
/*
 * Java business for entity table conversations 
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
import com.wdyapplications.e_commerce_api.dao.entity.Conversations;
import com.wdyapplications.e_commerce_api.dao.entity.Users;
import com.wdyapplications.e_commerce_api.dao.entity.Users;
import com.wdyapplications.e_commerce_api.dao.entity.*;
import com.wdyapplications.e_commerce_api.dao.repository.*;

/**
BUSINESS for table "conversations"
 * 
 * @author Dorgeddy
 *
 */

@Component
public class ConversationsBusiness implements IBasicBusiness<Request<ConversationsDto>, Response<ConversationsDto>> {

	private Response<ConversationsDto> response;
	@Autowired
	private ConversationsRepository conversationsRepository;
	@Autowired
	private UsersRepository users2Repository;
	@Autowired
	private MessagesRepository messagesRepository;
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

	public ConversationsBusiness() {
		dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateTimeFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * create Conversations by using ConversationsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ConversationsDto> create(Request<ConversationsDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin create Conversations-----");

		Response<ConversationsDto> response = new Response<ConversationsDto>();
		List<Conversations>        items    = new ArrayList<Conversations>();
			
		for (ConversationsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("clientId", dto.getClientId());
			fieldsToVerify.put("sellerId", dto.getSellerId());
			fieldsToVerify.put("lastMessageAt", dto.getLastMessageAt());
			fieldsToVerify.put("isActive", dto.getIsActive());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if conversations to insert do not exist
			Conversations existingEntity = null;

/*
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("conversations id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

*/
			// Verify if users2 exist
			Users existingUsers2 = null;
			if (dto.getSellerId() != null && dto.getSellerId() > 0){
				existingUsers2 = users2Repository.findOne(dto.getSellerId());
				if (existingUsers2 == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("users2 sellerId -> " + dto.getSellerId(), locale));
					response.setHasError(true);
					return response;
				}
			}
			// Verify if users exist
			Users existingUsers = null;
			if (dto.getClientId() != null && dto.getClientId() > 0){
				existingUsers = usersRepository.findOne(dto.getClientId());
				if (existingUsers == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("users clientId -> " + dto.getClientId(), locale));
					response.setHasError(true);
					return response;
				}
			}
				Conversations entityToSave = null;
			entityToSave = ConversationsTransformer.INSTANCE.toEntity(dto, existingUsers2, existingUsers);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());

			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Conversations> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = conversationsRepository.saveAll((Iterable<Conversations>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("conversations", locale));
				response.setHasError(true);
				return response;
			}
			List<ConversationsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ConversationsTransformer.INSTANCE.toLiteDtos(itemsSaved) : ConversationsTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end create Conversations-----");
		return response;
	}

	/**
	 * update Conversations by using ConversationsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ConversationsDto> update(Request<ConversationsDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin update Conversations-----");

		Response<ConversationsDto> response = new Response<ConversationsDto>();
		List<Conversations>        items    = new ArrayList<Conversations>();
			
		for (ConversationsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la conversations existe
			Conversations entityToSave = null;
			entityToSave = conversationsRepository.findOne(dto.getId());
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("conversations id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if users2 exist
			if (dto.getSellerId() != null && dto.getSellerId() > 0){
				Users existingUsers2 = users2Repository.findOne(dto.getSellerId());
				if (existingUsers2 == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("users2 sellerId -> " + dto.getSellerId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setUsers2(existingUsers2);
			}
			// Verify if users exist
			if (dto.getClientId() != null && dto.getClientId() > 0){
				Users existingUsers = usersRepository.findOne(dto.getClientId());
				if (existingUsers == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("users clientId -> " + dto.getClientId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setUsers(existingUsers);
			}
			if (Utilities.notBlank(dto.getLastMessageAt())) {
				entityToSave.setLastMessageAt(dateFormat.parse(dto.getLastMessageAt()));
			}
			if (dto.getIsActive() != null) {
				entityToSave.setIsActive(dto.getIsActive());
			}
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Conversations> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = conversationsRepository.saveAll((Iterable<Conversations>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("conversations", locale));
				response.setHasError(true);
				return response;
			}
			List<ConversationsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ConversationsTransformer.INSTANCE.toLiteDtos(itemsSaved) : ConversationsTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end update Conversations-----");
		return response;
	}

	/**
	 * delete Conversations by using ConversationsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ConversationsDto> delete(Request<ConversationsDto> request, Locale locale)  {
		// System.out.println("----begin delete Conversations-----");

		Response<ConversationsDto> response = new Response<ConversationsDto>();
		List<Conversations>        items    = new ArrayList<Conversations>();
			
		for (ConversationsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la conversations existe
			Conversations existingEntity = null;

			existingEntity = conversationsRepository.findOne(dto.getId());
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("conversations -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// messages
			List<Messages> listOfMessages = messagesRepository.findByConversationId(existingEntity.getId());
			if (listOfMessages != null && !listOfMessages.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfMessages.size() + ")", locale));
				response.setHasError(true);
				return response;
			}


			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			conversationsRepository.deleteAll((Iterable<Conversations>) items);

			response.setHasError(false);
		}

		// System.out.println("----end delete Conversations-----");
		return response;
	}

	/**
	 * get Conversations by using ConversationsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ConversationsDto> getByCriteria(Request<ConversationsDto> request, Locale locale)  throws Exception {
		// System.out.println("----begin get Conversations-----");

		Response<ConversationsDto> response = new Response<ConversationsDto>();
		List<Conversations> items 			 = conversationsRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<ConversationsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ConversationsTransformer.INSTANCE.toLiteDtos(items) : ConversationsTransformer.INSTANCE.toDtos(items);

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
			response.setCount(conversationsRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("conversations", locale));
			response.setHasError(false);
			return response;
		}

		// System.out.println("----end get Conversations-----");
		return response;
	}

	/**
	 * get full ConversationsDto by using Conversations as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private ConversationsDto getFullInfos(ConversationsDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
