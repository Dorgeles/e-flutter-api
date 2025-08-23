                                            											
/*
 * Java business for entity table addresses 
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
import com.wdyapplications.e_commerce_api.dao.entity.Addresses;
import com.wdyapplications.e_commerce_api.dao.entity.Users;
import com.wdyapplications.e_commerce_api.dao.entity.*;
import com.wdyapplications.e_commerce_api.dao.repository.*;

/**
BUSINESS for table "addresses"
 * 
 * @author Dorgeddy
 *
 */

@Component
public class AddressesBusiness implements IBasicBusiness<Request<AddressesDto>, Response<AddressesDto>> {

	private Response<AddressesDto> response;
	@Autowired
	private AddressesRepository addressesRepository;
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

	public AddressesBusiness() {
		dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateTimeFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * create Addresses by using AddressesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<AddressesDto> create(Request<AddressesDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin create Addresses-----");

		Response<AddressesDto> response = new Response<AddressesDto>();
		List<Addresses>        items    = new ArrayList<Addresses>();
			
		for (AddressesDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("userId", dto.getUserId());
			fieldsToVerify.put("label", dto.getLabel());
			fieldsToVerify.put("streetAddress", dto.getStreetAddress());
			fieldsToVerify.put("city", dto.getCity());
			fieldsToVerify.put("state", dto.getState());
			fieldsToVerify.put("postalCode", dto.getPostalCode());
			fieldsToVerify.put("country", dto.getCountry());
			fieldsToVerify.put("isDefault", dto.getIsDefault());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if addresses to insert do not exist
			Addresses existingEntity = null;

/*
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("addresses id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

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
				Addresses entityToSave = null;
			entityToSave = AddressesTransformer.INSTANCE.toEntity(dto, existingUsers);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());

			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Addresses> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = addressesRepository.saveAll((Iterable<Addresses>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("addresses", locale));
				response.setHasError(true);
				return response;
			}
			List<AddressesDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? AddressesTransformer.INSTANCE.toLiteDtos(itemsSaved) : AddressesTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end create Addresses-----");
		return response;
	}

	/**
	 * update Addresses by using AddressesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<AddressesDto> update(Request<AddressesDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin update Addresses-----");

		Response<AddressesDto> response = new Response<AddressesDto>();
		List<Addresses>        items    = new ArrayList<Addresses>();
			
		for (AddressesDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la addresses existe
			Addresses entityToSave = null;
			entityToSave = addressesRepository.findOne(dto.getId());
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("addresses id -> " + dto.getId(), locale));
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
			if (Utilities.notBlank(dto.getLabel())) {
				entityToSave.setLabel(dto.getLabel());
			}
			if (Utilities.notBlank(dto.getStreetAddress())) {
				entityToSave.setStreetAddress(dto.getStreetAddress());
			}
			if (Utilities.notBlank(dto.getCity())) {
				entityToSave.setCity(dto.getCity());
			}
			if (Utilities.notBlank(dto.getState())) {
				entityToSave.setState(dto.getState());
			}
			if (Utilities.notBlank(dto.getPostalCode())) {
				entityToSave.setPostalCode(dto.getPostalCode());
			}
			if (Utilities.notBlank(dto.getCountry())) {
				entityToSave.setCountry(dto.getCountry());
			}
			if (dto.getIsDefault() != null) {
				entityToSave.setIsDefault(dto.getIsDefault());
			}
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Addresses> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = addressesRepository.saveAll((Iterable<Addresses>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("addresses", locale));
				response.setHasError(true);
				return response;
			}
			List<AddressesDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? AddressesTransformer.INSTANCE.toLiteDtos(itemsSaved) : AddressesTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end update Addresses-----");
		return response;
	}

	/**
	 * delete Addresses by using AddressesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<AddressesDto> delete(Request<AddressesDto> request, Locale locale)  {
		// System.out.println("----begin delete Addresses-----");

		Response<AddressesDto> response = new Response<AddressesDto>();
		List<Addresses>        items    = new ArrayList<Addresses>();
			
		for (AddressesDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la addresses existe
			Addresses existingEntity = null;

			existingEntity = addressesRepository.findOne(dto.getId());
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("addresses -> " + dto.getId(), locale));
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
			addressesRepository.deleteAll((Iterable<Addresses>) items);

			response.setHasError(false);
		}

		// System.out.println("----end delete Addresses-----");
		return response;
	}

	/**
	 * get Addresses by using AddressesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<AddressesDto> getByCriteria(Request<AddressesDto> request, Locale locale)  throws Exception {
		// System.out.println("----begin get Addresses-----");

		Response<AddressesDto> response = new Response<AddressesDto>();
		List<Addresses> items 			 = addressesRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<AddressesDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? AddressesTransformer.INSTANCE.toLiteDtos(items) : AddressesTransformer.INSTANCE.toDtos(items);

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
			response.setCount(addressesRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("addresses", locale));
			response.setHasError(false);
			return response;
		}

		// System.out.println("----end get Addresses-----");
		return response;
	}

	/**
	 * get full AddressesDto by using Addresses as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private AddressesDto getFullInfos(AddressesDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
