                            							
/*
 * Java business for entity table cart_items 
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
import com.wdyapplications.e_commerce_api.dao.entity.CartItems;
import com.wdyapplications.e_commerce_api.dao.entity.Products;
import com.wdyapplications.e_commerce_api.dao.entity.Carts;
import com.wdyapplications.e_commerce_api.dao.entity.*;
import com.wdyapplications.e_commerce_api.dao.repository.*;

/**
BUSINESS for table "cart_items"
 * 
 * @author Dorgeddy
 *
 */

@Component
public class CartItemsBusiness implements IBasicBusiness<Request<CartItemsDto>, Response<CartItemsDto>> {

	private Response<CartItemsDto> response;
	@Autowired
	private CartItemsRepository cartItemsRepository;
	@Autowired
	private ProductsRepository productsRepository;
	@Autowired
	private CartsRepository cartsRepository;
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

	public CartItemsBusiness() {
		dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateTimeFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * create CartItems by using CartItemsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CartItemsDto> create(Request<CartItemsDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin create CartItems-----");

		Response<CartItemsDto> response = new Response<CartItemsDto>();
		List<CartItems>        items    = new ArrayList<CartItems>();
			
		for (CartItemsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("cartId", dto.getCartId());
			fieldsToVerify.put("productId", dto.getProductId());
			fieldsToVerify.put("quantity", dto.getQuantity());
			fieldsToVerify.put("unitPrice", dto.getUnitPrice());
			//fieldsToVerify.put("addedAt", dto.getAddedAt());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if cartItems to insert do not exist
			CartItems existingEntity = null;

/*
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("cartItems id -> " + dto.getId(), locale));
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
			// Verify if carts exist
			Carts existingCarts = null;
			if (dto.getCartId() != null && dto.getCartId() > 0){
				existingCarts = cartsRepository.findOne(dto.getCartId());
				if (existingCarts == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("carts cartId -> " + dto.getCartId(), locale));
					response.setHasError(true);
					return response;
				}
			}
				CartItems entityToSave = null;
			entityToSave = CartItemsTransformer.INSTANCE.toEntity(dto, existingProducts, existingCarts);
			entityToSave.setAddedAt(Utilities.getCurrentDate());

			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<CartItems> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = cartItemsRepository.saveAll((Iterable<CartItems>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("cartItems", locale));
				response.setHasError(true);
				return response;
			}
			List<CartItemsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? CartItemsTransformer.INSTANCE.toLiteDtos(itemsSaved) : CartItemsTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end create CartItems-----");
		return response;
	}

	/**
	 * update CartItems by using CartItemsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CartItemsDto> update(Request<CartItemsDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin update CartItems-----");

		Response<CartItemsDto> response = new Response<CartItemsDto>();
		List<CartItems>        items    = new ArrayList<CartItems>();
			
		for (CartItemsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la cartItems existe
			CartItems entityToSave = null;
			entityToSave = cartItemsRepository.findOne(dto.getId());
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("cartItems id -> " + dto.getId(), locale));
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
			// Verify if carts exist
			if (dto.getCartId() != null && dto.getCartId() > 0){
				Carts existingCarts = cartsRepository.findOne(dto.getCartId());
				if (existingCarts == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("carts cartId -> " + dto.getCartId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setCarts(existingCarts);
			}
			if (dto.getQuantity() != null && dto.getQuantity() > 0) {
				entityToSave.setQuantity(dto.getQuantity());
			}
//			if (dto.getUnitPrice() != null && dto.getUnitPrice() > 0) {
//				entityToSave.setUnitPrice(dto.getUnitPrice());
//			}
			if (Utilities.notBlank(dto.getAddedAt())) {
				entityToSave.setAddedAt(dateFormat.parse(dto.getAddedAt()));
			}
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<CartItems> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = cartItemsRepository.saveAll((Iterable<CartItems>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("cartItems", locale));
				response.setHasError(true);
				return response;
			}
			List<CartItemsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? CartItemsTransformer.INSTANCE.toLiteDtos(itemsSaved) : CartItemsTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end update CartItems-----");
		return response;
	}

	/**
	 * delete CartItems by using CartItemsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CartItemsDto> delete(Request<CartItemsDto> request, Locale locale)  {
		// System.out.println("----begin delete CartItems-----");

		Response<CartItemsDto> response = new Response<CartItemsDto>();
		List<CartItems>        items    = new ArrayList<CartItems>();
			
		for (CartItemsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la cartItems existe
			CartItems existingEntity = null;

			existingEntity = cartItemsRepository.findOne(dto.getId());
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("cartItems -> " + dto.getId(), locale));
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
			cartItemsRepository.deleteAll((Iterable<CartItems>) items);

			response.setHasError(false);
		}

		// System.out.println("----end delete CartItems-----");
		return response;
	}

	/**
	 * get CartItems by using CartItemsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CartItemsDto> getByCriteria(Request<CartItemsDto> request, Locale locale)  throws Exception {
		// System.out.println("----begin get CartItems-----");

		Response<CartItemsDto> response = new Response<CartItemsDto>();
		List<CartItems> items 			 = cartItemsRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<CartItemsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? CartItemsTransformer.INSTANCE.toLiteDtos(items) : CartItemsTransformer.INSTANCE.toDtos(items);

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
			response.setCount(cartItemsRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("cartItems", locale));
			response.setHasError(false);
			return response;
		}

		// System.out.println("----end get CartItems-----");
		return response;
	}

	/**
	 * get full CartItemsDto by using CartItems as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private CartItemsDto getFullInfos(CartItemsDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
