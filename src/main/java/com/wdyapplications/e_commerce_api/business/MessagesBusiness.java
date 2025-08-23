                                    									
/*
 * Java business for entity table messages 
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
import com.wdyapplications.e_commerce_api.dao.entity.Messages;
import com.wdyapplications.e_commerce_api.dao.entity.Users;
import com.wdyapplications.e_commerce_api.dao.entity.Conversations;
import com.wdyapplications.e_commerce_api.dao.entity.*;
import com.wdyapplications.e_commerce_api.dao.repository.*;

/**
BUSINESS for table "messages"
 * 
 * @author Dorgeddy
 *
 */

@Component
public class MessagesBusiness implements IBasicBusiness<Request<MessagesDto>, Response<MessagesDto>> {

	private Response<MessagesDto> response;
	@Autowired
	private MessagesRepository messagesRepository;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private ConversationsRepository conversationsRepository;
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

	public MessagesBusiness() {
		dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateTimeFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * create Messages by using MessagesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<MessagesDto> create(Request<MessagesDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin create Messages-----");

		Response<MessagesDto> response = new Response<MessagesDto>();
		List<Messages>        items    = new ArrayList<Messages>();
			
		for (MessagesDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("conversationId", dto.getConversationId());
			fieldsToVerify.put("senderId", dto.getSenderId());
			fieldsToVerify.put("content", dto.getContent());
			fieldsToVerify.put("type", dto.getType());
			//fieldsToVerify.put("attachmentUrl", dto.getAttachmentUrl());
			//fieldsToVerify.put("isRead", dto.getIsRead());
			//fieldsToVerify.put("readAt", dto.getReadAt());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if messages to insert do not exist
			Messages existingEntity = null;

/*
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("messages id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

*/
			// Verify if users exist
			Users existingUsers = null;
			if (dto.getSenderId() != null && dto.getSenderId() > 0){
				existingUsers = usersRepository.findOne(dto.getSenderId());
				if (existingUsers == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("users senderId -> " + dto.getSenderId(), locale));
					response.setHasError(true);
					return response;
				}
			}
			// Verify if conversations exist
			Conversations existingConversations = null;
			if (dto.getConversationId() != null && dto.getConversationId() > 0){
				existingConversations = conversationsRepository.findOne(dto.getConversationId());
				if (existingConversations == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("conversations conversationId -> " + dto.getConversationId(), locale));
					response.setHasError(true);
					return response;
				}
			}
				Messages entityToSave = null;
			entityToSave = MessagesTransformer.INSTANCE.toEntity(dto, existingUsers, existingConversations);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());

			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Messages> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = messagesRepository.saveAll((Iterable<Messages>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("messages", locale));
				response.setHasError(true);
				return response;
			}
			List<MessagesDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? MessagesTransformer.INSTANCE.toLiteDtos(itemsSaved) : MessagesTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end create Messages-----");
		return response;
	}

	/**
	 * update Messages by using MessagesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<MessagesDto> update(Request<MessagesDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin update Messages-----");

		Response<MessagesDto> response = new Response<MessagesDto>();
		List<Messages>        items    = new ArrayList<Messages>();
			
		for (MessagesDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la messages existe
			Messages entityToSave = null;
			entityToSave = messagesRepository.findOne(dto.getId());
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("messages id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if users exist
			if (dto.getSenderId() != null && dto.getSenderId() > 0){
				Users existingUsers = usersRepository.findOne(dto.getSenderId());
				if (existingUsers == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("users senderId -> " + dto.getSenderId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setUsers(existingUsers);
			}
			// Verify if conversations exist
			if (dto.getConversationId() != null && dto.getConversationId() > 0){
				Conversations existingConversations = conversationsRepository.findOne(dto.getConversationId());
				if (existingConversations == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("conversations conversationId -> " + dto.getConversationId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setConversations(existingConversations);
			}
			if (Utilities.notBlank(dto.getContent())) {
				entityToSave.setContent(dto.getContent());
			}
			if (Utilities.notBlank(dto.getType())) {
				entityToSave.setType(dto.getType());
			}
			if (Utilities.notBlank(dto.getAttachmentUrl())) {
				entityToSave.setAttachmentUrl(dto.getAttachmentUrl());
			}
			if (dto.getIsRead() != null) {
				entityToSave.setIsRead(dto.getIsRead());
			}
			if (Utilities.notBlank(dto.getReadAt())) {
				entityToSave.setReadAt(dateFormat.parse(dto.getReadAt()));
			}
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Messages> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = messagesRepository.saveAll((Iterable<Messages>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("messages", locale));
				response.setHasError(true);
				return response;
			}
			List<MessagesDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? MessagesTransformer.INSTANCE.toLiteDtos(itemsSaved) : MessagesTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end update Messages-----");
		return response;
	}

	/**
	 * delete Messages by using MessagesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<MessagesDto> delete(Request<MessagesDto> request, Locale locale)  {
		// System.out.println("----begin delete Messages-----");

		Response<MessagesDto> response = new Response<MessagesDto>();
		List<Messages>        items    = new ArrayList<Messages>();
			
		for (MessagesDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la messages existe
			Messages existingEntity = null;

			existingEntity = messagesRepository.findOne(dto.getId());
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("messages -> " + dto.getId(), locale));
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
			messagesRepository.deleteAll((Iterable<Messages>) items);

			response.setHasError(false);
		}

		// System.out.println("----end delete Messages-----");
		return response;
	}

	/**
	 * get Messages by using MessagesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<MessagesDto> getByCriteria(Request<MessagesDto> request, Locale locale)  throws Exception {
		// System.out.println("----begin get Messages-----");

		Response<MessagesDto> response = new Response<MessagesDto>();
		List<Messages> items 			 = messagesRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<MessagesDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? MessagesTransformer.INSTANCE.toLiteDtos(items) : MessagesTransformer.INSTANCE.toDtos(items);

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
			response.setCount(messagesRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("messages", locale));
			response.setHasError(false);
			return response;
		}

		// System.out.println("----end get Messages-----");
		return response;
	}

	/**
	 * get full MessagesDto by using Messages as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private MessagesDto getFullInfos(MessagesDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
