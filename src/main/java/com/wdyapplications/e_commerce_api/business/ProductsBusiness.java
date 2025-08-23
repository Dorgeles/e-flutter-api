                                                                    																	
/*
 * Java business for entity table products 
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
import com.wdyapplications.e_commerce_api.dao.entity.Products;
import com.wdyapplications.e_commerce_api.dao.entity.Categories;
import com.wdyapplications.e_commerce_api.dao.entity.Users;
import com.wdyapplications.e_commerce_api.dao.entity.*;
import com.wdyapplications.e_commerce_api.dao.repository.*;

/**
BUSINESS for table "products"
 * 
 * @author Dorgeddy
 *
 */

@Component
public class ProductsBusiness implements IBasicBusiness<Request<ProductsDto>, Response<ProductsDto>> {

	private Response<ProductsDto> response;
	@Autowired
	private ProductsRepository productsRepository;
	@Autowired
	private ProductImagesRepository productImagesRepository;
	@Autowired
	private StockHistoryRepository stockHistoryRepository;
	@Autowired
	private ReviewsRepository reviewsRepository;
	@Autowired
	private CartItemsRepository cartItemsRepository;
	@Autowired
	private OrderItemsRepository orderItemsRepository;
	@Autowired
	private CategoriesRepository categoriesRepository;
	@Autowired
	private PromotionProductsRepository promotionProductsRepository;
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

	public ProductsBusiness() {
		dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateTimeFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * create Products by using ProductsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ProductsDto> create(Request<ProductsDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin create Products-----");

		Response<ProductsDto> response = new Response<ProductsDto>();
		List<Products>        items    = new ArrayList<Products>();
			
		for (ProductsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("sellerId", dto.getSellerId());
			fieldsToVerify.put("categoryId", dto.getCategoryId());
			fieldsToVerify.put("name", dto.getName());
			fieldsToVerify.put("description", dto.getDescription());
			fieldsToVerify.put("price", dto.getPrice());
			fieldsToVerify.put("promoPrice", dto.getPromoPrice());
			fieldsToVerify.put("stockQuantity", dto.getStockQuantity());
			fieldsToVerify.put("minStockAlert", dto.getMinStockAlert());
			//fieldsToVerify.put("status", dto.getStatus());
			fieldsToVerify.put("averageRating", dto.getAverageRating());
			fieldsToVerify.put("totalReviews", dto.getTotalReviews());
			//fieldsToVerify.put("totalSales", dto.getTotalSales());
			//fieldsToVerify.put("isFeatured", dto.getIsFeatured());
			//fieldsToVerify.put("isRecommended", dto.getIsRecommended());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if products to insert do not exist
			Products existingEntity = null;

/*
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("products id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

*/
			// verif unique name in db
			existingEntity = productsRepository.findByName(dto.getName());
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("products name -> " + dto.getName(), locale));
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
			if (dto.getCategoryId() != null && dto.getCategoryId() > 0){
				existingCategories = categoriesRepository.findOne(dto.getCategoryId());
				if (existingCategories == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("categories categoryId -> " + dto.getCategoryId(), locale));
					response.setHasError(true);
					return response;
				}
			}
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
				Products entityToSave = null;
			entityToSave = ProductsTransformer.INSTANCE.toEntity(dto, existingCategories, existingUsers);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());

			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Products> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = productsRepository.saveAll((Iterable<Products>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("products", locale));
				response.setHasError(true);
				return response;
			}
			List<ProductsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ProductsTransformer.INSTANCE.toLiteDtos(itemsSaved) : ProductsTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end create Products-----");
		return response;
	}

	/**
	 * update Products by using ProductsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ProductsDto> update(Request<ProductsDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin update Products-----");

		Response<ProductsDto> response = new Response<ProductsDto>();
		List<Products>        items    = new ArrayList<Products>();
			
		for (ProductsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la products existe
			Products entityToSave = null;
			entityToSave = productsRepository.findOne(dto.getId());
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("products id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if categories exist
			if (dto.getCategoryId() != null && dto.getCategoryId() > 0){
				Categories existingCategories = categoriesRepository.findOne(dto.getCategoryId());
				if (existingCategories == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("categories categoryId -> " + dto.getCategoryId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setCategories(existingCategories);
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
			if (Utilities.notBlank(dto.getName())) {
				entityToSave.setName(dto.getName());
			}
			if (Utilities.notBlank(dto.getDescription())) {
				entityToSave.setDescription(dto.getDescription());
			}
//			if (dto.getPrice() != null && dto.getPrice() > 0) {
//				entityToSave.setPrice(dto.getPrice());
//			}
//			if (dto.getPromoPrice() != null && dto.getPromoPrice() > 0) {
//				entityToSave.setPromoPrice(dto.getPromoPrice());
//			}
			if (dto.getStockQuantity() != null && dto.getStockQuantity() > 0) {
				entityToSave.setStockQuantity(dto.getStockQuantity());
			}
			if (dto.getMinStockAlert() != null && dto.getMinStockAlert() > 0) {
				entityToSave.setMinStockAlert(dto.getMinStockAlert());
			}
			if (Utilities.notBlank(dto.getStatus())) {
				entityToSave.setStatus(dto.getStatus());
			}
//			if (dto.getAverageRating() != null && dto.getAverageRating() > 0) {
//				entityToSave.setAverageRating(dto.getAverageRating());
//			}
			if (dto.getTotalReviews() != null && dto.getTotalReviews() > 0) {
				entityToSave.setTotalReviews(dto.getTotalReviews());
			}
			if (dto.getTotalSales() != null && dto.getTotalSales() > 0) {
				entityToSave.setTotalSales(dto.getTotalSales());
			}
			if (dto.getIsFeatured() != null) {
				entityToSave.setIsFeatured(dto.getIsFeatured());
			}
			if (dto.getIsRecommended() != null) {
				entityToSave.setIsRecommended(dto.getIsRecommended());
			}
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Products> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = productsRepository.saveAll((Iterable<Products>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("products", locale));
				response.setHasError(true);
				return response;
			}
			List<ProductsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ProductsTransformer.INSTANCE.toLiteDtos(itemsSaved) : ProductsTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end update Products-----");
		return response;
	}

	/**
	 * delete Products by using ProductsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ProductsDto> delete(Request<ProductsDto> request, Locale locale)  {
		// System.out.println("----begin delete Products-----");

		Response<ProductsDto> response = new Response<ProductsDto>();
		List<Products>        items    = new ArrayList<Products>();
			
		for (ProductsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la products existe
			Products existingEntity = null;

			existingEntity = productsRepository.findOne(dto.getId());
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("products -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// productImages
			List<ProductImages> listOfProductImages = productImagesRepository.findByProductId(existingEntity.getId());
			if (listOfProductImages != null && !listOfProductImages.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfProductImages.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// stockHistory
			List<StockHistory> listOfStockHistory = stockHistoryRepository.findByProductId(existingEntity.getId());
			if (listOfStockHistory != null && !listOfStockHistory.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfStockHistory.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// reviews
			List<Reviews> listOfReviews = reviewsRepository.findByProductId(existingEntity.getId());
			if (listOfReviews != null && !listOfReviews.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfReviews.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// cartItems
			List<CartItems> listOfCartItems = cartItemsRepository.findByProductId(existingEntity.getId());
			if (listOfCartItems != null && !listOfCartItems.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfCartItems.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// orderItems
			List<OrderItems> listOfOrderItems = orderItemsRepository.findByProductId(existingEntity.getId());
			if (listOfOrderItems != null && !listOfOrderItems.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfOrderItems.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// promotionProducts
			List<PromotionProducts> listOfPromotionProducts = promotionProductsRepository.findByProductId(existingEntity.getId());
			if (listOfPromotionProducts != null && !listOfPromotionProducts.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfPromotionProducts.size() + ")", locale));
				response.setHasError(true);
				return response;
			}


			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			productsRepository.deleteAll((Iterable<Products>) items);

			response.setHasError(false);
		}

		// System.out.println("----end delete Products-----");
		return response;
	}

	/**
	 * get Products by using ProductsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ProductsDto> getByCriteria(Request<ProductsDto> request, Locale locale)  throws Exception {
		// System.out.println("----begin get Products-----");

		Response<ProductsDto> response = new Response<ProductsDto>();
		List<Products> items 			 = productsRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<ProductsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ProductsTransformer.INSTANCE.toLiteDtos(items) : ProductsTransformer.INSTANCE.toDtos(items);

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
			response.setCount(productsRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("products", locale));
			response.setHasError(false);
			return response;
		}

		// System.out.println("----end get Products-----");
		return response;
	}

	/**
	 * get full ProductsDto by using Products as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private ProductsDto getFullInfos(ProductsDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
