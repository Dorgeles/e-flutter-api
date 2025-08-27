                                                        														
/*
 * Java business for entity table users 
 * Created on 2025-08-23 ( Time 06:18:37 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package com.wdyapplications.e_commerce_api.business;

import com.wdyapplications.e_commerce_api.utils.security.PasswordUtils;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.PermissionDeniedDataAccessException;
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
import com.wdyapplications.e_commerce_api.dao.entity.Users;
import com.wdyapplications.e_commerce_api.dao.entity.*;
import com.wdyapplications.e_commerce_api.dao.repository.*;

/**
BUSINESS for table "users"
 * 
 * @author Dorgeddy
 *
 */

@Component
public class UsersBusiness implements IBasicBusiness<Request<UsersDto>, Response<UsersDto>> {

	private Response<UsersDto> response;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private PasswordUtils passwordUtils;
	@Autowired
	private ConversationsRepository conversationsRepository;
	@Autowired
	private ProductsRepository productsRepository;
	@Autowired
	private AddressesRepository addressesRepository;
	@Autowired
	private MessagesRepository messagesRepository;
	@Autowired
	private NotificationsRepository notificationsRepository;
	@Autowired
	private SellerStatsRepository sellerStatsRepository;
	@Autowired
	private SessionsRepository sessionsRepository;
	@Autowired
	private ReviewsRepository reviewsRepository;
	@Autowired
	private PromotionUsageRepository promotionUsageRepository;
	@Autowired
	private OrderItemsRepository orderItemsRepository;
	@Autowired
	private CartsRepository cartsRepository;
	@Autowired
	private OrdersRepository ordersRepository;
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

	public UsersBusiness() {
		dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateTimeFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * create Users by using UsersDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<UsersDto> create(Request<UsersDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin create Users-----");

		Response<UsersDto> response = new Response<UsersDto>();
		List<Users>        items    = new ArrayList<Users>();
			
		for (UsersDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("email", dto.getEmail());
			fieldsToVerify.put("passwordHash", dto.getPasswordHash());
			fieldsToVerify.put("firstName", dto.getFirstName());
			fieldsToVerify.put("lastName", dto.getLastName());
			fieldsToVerify.put("phone", dto.getPhone());
			//fieldsToVerify.put("avatarUrl", dto.getAvatarUrl());
			fieldsToVerify.put("role", dto.getRole());
			//fieldsToVerify.put("lastLogin", dto.getLastLogin());
			//fieldsToVerify.put("isActive", dto.getIsActive());
			//fieldsToVerify.put("resetToken", dto.getResetToken());
			//fieldsToVerify.put("resetTokenExpires", dto.getResetTokenExpires());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if users to insert do not exist
			Users existingEntity = null;

/*
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("users id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

*/
			// verif unique email in db
			existingEntity = usersRepository.findByEmail(dto.getEmail());
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("users email -> " + dto.getEmail(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique email in items to save
			if (items.stream().anyMatch(a -> a.getEmail().equalsIgnoreCase(dto.getEmail()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" email ", locale));
				response.setHasError(true);
				return response;
			}

				Users entityToSave = null;
			entityToSave = UsersTransformer.INSTANCE.toEntity(dto);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());

			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Users> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = usersRepository.saveAll((Iterable<Users>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("users", locale));
				response.setHasError(true);
				return response;
			}
			List<UsersDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? UsersTransformer.INSTANCE.toLiteDtos(itemsSaved) : UsersTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end create Users-----");
		return response;
	}

	public Response<UsersDto> login(Request<UsersDto> request, Locale locale)  throws ParseException {
		Response<UsersDto> response = new Response<UsersDto>();
		try {
			UsersDto dto                 = request.getData();
			Integer  existingSessionTime = null;
			Users    users               = null;
			if (dto != null) {
				HashMap<String, Object> fieldsToVerify = new HashMap<>();
				fieldsToVerify.put("email", dto.getEmail());
				fieldsToVerify.put("password", dto.getPasswordHash());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}
				List<UsersDto> itemsSaved = new ArrayList<UsersDto>();
				Date           targetTime = Calendar.getInstance().getTime();
				String         newDate    = dateFormat.format(targetTime);
				Date           dateNow    = dateFormat.parse(newDate);
				Users userNormal = null;

				Users	findUsers = usersRepository.findByEmail(dto.getEmail());
				if (findUsers == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST(
							"Email et/ou mot de passe incorrect", locale));
					response.setHasError(Boolean.TRUE);
					return response;
				}

				boolean isPasswordMatch = passwordUtils.matches(dto.getPasswordHash(), userNormal.getPasswordHash());
				if (!isPasswordMatch) {
					response.setStatus(functionalError.LOGIN_FAIL(locale));
					response.setHasError(true);
					return response;
				}
				UsersDto usersDto = getFullInfos(UsersTransformer.INSTANCE.toDto(userNormal), 1, false,
						locale
				);
				users = userNormal;
				users.setUpdatedAt(Utilities.getCurrentDate());
				usersRepository.save(users);
				response.setItems(Arrays.asList(usersDto));
				response.setStatus(functionalError.SUCCESS("Utilisateur connecté",locale));
				response.setHasError(false);
			}
		} catch (PermissionDeniedDataAccessException e)
		{
			exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (DataAccessResourceFailureException e)
		{
			exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
		} catch (DataAccessException e)
		{
			exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (RuntimeException e)
		{
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{

		}
		// Definir les parametres obligatoires

		return response;
	}
	public Response<UsersDto> changePassword(Request<UsersDto> request, Locale locale)  throws ParseException {
		Response<UsersDto> response = new Response<UsersDto>();
		try {
			UsersDto dto                 = request.getData();
			Integer  existingSessionTime = null;
			Users    users               = null;
			if (dto != null) {
				HashMap<String, Object> fieldsToVerify = new HashMap<>();
				fieldsToVerify.put("password", dto.getPassword());
				fieldsToVerify.put("oldPassword", dto.getOldPassword());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}
				users = usersRepository.findOne(dto.getId());
				boolean isPasswordMatch = passwordUtils.matches(dto.getOldPassword(), users.getPasswordHash());
				if (!isPasswordMatch) {
					Status status = new Status();
					status.setCode("700");
					status.setMessage("Old password does not match");
					response.setStatus(status);
					response.setHasError(true);
					return response;
				}
//				List<Otp> validOtps = null;
//				validOtps = otpRepository.findByToken(dto.getToken(), false);
//				if (Utilities.isEmpty(validOtps)) {
//					Status status = new Status();
//					status.setCode("444");
//					status.setMessage("Action réfusée: vous n'avez pas de demande de changement de mot de passe valide");
//					response.setStatus(status);
//					response.setHasError(Boolean.TRUE);
//					return response;
//				}
//				for (Otp otp : validOtps) {
//					if (otp.getIsExpired() == Boolean.FALSE) {
//						Status status = new Status();
//						status.setCode("444");
//						status.setMessage("Action réfusée: vous n'avez pas de demande de changement de mot de passe valide");
//						response.setStatus(status);
//						response.setHasError(Boolean.TRUE);
//						return response;
//					}
//				}
//				users = usersRepository.findOne(validOtps.get(0).getCreatedBy(), false);
//				if (users == null) {
//					response.setStatus(functionalError.DATA_NOT_EXIST(
//							"Action refusée : Utilisateur n'existe pas", locale));
//					response.setHasError(Boolean.TRUE);
//					return response;
//				}
//				//String dec = passwordUtils.decryptPassword(users.getPassword());
//				boolean isPasswordMatch = passwordUtils.matches(dto.getPassword(), users.getPassword());
//				if (isPasswordMatch) {
//					response.setStatus(functionalError.AUTH_FAIL("Vos mots de passe sont identique" , locale));
//					response.setHasError(Boolean.TRUE);
//					return response;
//				}

				String newPassword = passwordUtils.hashPassword(dto.getPassword());
				// hashed the password
				users.setPasswordHash(newPassword);
				usersRepository.save(users);
				response.setStatus(functionalError.SUCCESS("Mot de passe modifié",locale));
				response.setHasError(false);
				System.out.println("----end connexion Users-----");
			}
		} catch (PermissionDeniedDataAccessException e) {
			exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (DataAccessResourceFailureException e) {
			exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
		} catch (DataAccessException e) {
			exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		// Definir les parametres obligatoires

		return response;
	}


	/**
	 * update Users by using UsersDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<UsersDto> update(Request<UsersDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin update Users-----");

		Response<UsersDto> response = new Response<UsersDto>();
		List<Users>        items    = new ArrayList<Users>();
			
		for (UsersDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la users existe
			Users entityToSave = null;
			entityToSave = usersRepository.findOne(dto.getId());
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("users id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			if (Utilities.notBlank(dto.getEmail())) {
				entityToSave.setEmail(dto.getEmail());
			}
			if (Utilities.notBlank(dto.getPassword())) {
				entityToSave.setPasswordHash(passwordUtils.hashPassword(dto.getPassword()));
			}
			if (Utilities.notBlank(dto.getFirstName())) {
				entityToSave.setFirstName(dto.getFirstName());
			}
			if (Utilities.notBlank(dto.getLastName())) {
				entityToSave.setLastName(dto.getLastName());
			}
			if (Utilities.notBlank(dto.getPhone())) {
				entityToSave.setPhone(dto.getPhone());
			}
			if (Utilities.notBlank(dto.getAvatarUrl())) {
				entityToSave.setAvatarUrl(dto.getAvatarUrl());
			}
			if (Utilities.notBlank(dto.getRole())) {
				entityToSave.setRole(dto.getRole());
			}
			if (Utilities.notBlank(dto.getLastLogin())) {
				entityToSave.setLastLogin(dateFormat.parse(dto.getLastLogin()));
			}
			if (dto.getIsActive() != null) {
				entityToSave.setIsActive(dto.getIsActive());
			}
			if (Utilities.notBlank(dto.getResetToken())) {
				entityToSave.setResetToken(dto.getResetToken());
			}
			if (Utilities.notBlank(dto.getResetTokenExpires())) {
				entityToSave.setResetTokenExpires(dateFormat.parse(dto.getResetTokenExpires()));
			}
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Users> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = usersRepository.saveAll((Iterable<Users>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("users", locale));
				response.setHasError(true);
				return response;
			}
			List<UsersDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? UsersTransformer.INSTANCE.toLiteDtos(itemsSaved) : UsersTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end update Users-----");
		return response;
	}

	/**
	 * delete Users by using UsersDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<UsersDto> delete(Request<UsersDto> request, Locale locale)  {
		// System.out.println("----begin delete Users-----");

		Response<UsersDto> response = new Response<UsersDto>();
		List<Users>        items    = new ArrayList<Users>();
			
		for (UsersDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la users existe
			Users existingEntity = null;

			existingEntity = usersRepository.findOne(dto.getId());
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("users -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// conversations
			List<Conversations> listOfConversations = conversationsRepository.findBySellerId(existingEntity.getId());
			if (listOfConversations != null && !listOfConversations.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfConversations.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// products
			List<Products> listOfProducts = productsRepository.findBySellerId(existingEntity.getId());
			if (listOfProducts != null && !listOfProducts.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfProducts.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// addresses
			List<Addresses> listOfAddresses = addressesRepository.findByUserId(existingEntity.getId());
			if (listOfAddresses != null && !listOfAddresses.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfAddresses.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// messages
			List<Messages> listOfMessages = messagesRepository.findBySenderId(existingEntity.getId());
			if (listOfMessages != null && !listOfMessages.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfMessages.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// conversations
			List<Conversations> listOfConversations2 = conversationsRepository.findBySellerId(existingEntity.getId());
			if (listOfConversations2 != null && !listOfConversations2.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfConversations2.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// notifications
			List<Notifications> listOfNotifications = notificationsRepository.findByUserId(existingEntity.getId());
			if (listOfNotifications != null && !listOfNotifications.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfNotifications.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// sellerStats
			List<SellerStats> listOfSellerStats = sellerStatsRepository.findBySellerId(existingEntity.getId());
			if (listOfSellerStats != null && !listOfSellerStats.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfSellerStats.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// sessions
			List<Sessions> listOfSessions = sessionsRepository.findByUserId(existingEntity.getId());
			if (listOfSessions != null && !listOfSessions.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfSessions.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// reviews
			List<Reviews> listOfReviews = reviewsRepository.findByUserId(existingEntity.getId());
			if (listOfReviews != null && !listOfReviews.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfReviews.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// promotionUsage
			List<PromotionUsage> listOfPromotionUsage = promotionUsageRepository.findByUserId(existingEntity.getId());
			if (listOfPromotionUsage != null && !listOfPromotionUsage.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfPromotionUsage.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// orderItems
			List<OrderItems> listOfOrderItems = orderItemsRepository.findBySellerId(existingEntity.getId());
			if (listOfOrderItems != null && !listOfOrderItems.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfOrderItems.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// carts
			List<Carts> listOfCarts = cartsRepository.findByUserId(existingEntity.getId());
			if (listOfCarts != null && !listOfCarts.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfCarts.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// orders
			List<Orders> listOfOrders = ordersRepository.findByCustomerId(existingEntity.getId());
			if (listOfOrders != null && !listOfOrders.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfOrders.size() + ")", locale));
				response.setHasError(true);
				return response;
			}


			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			usersRepository.deleteAll((Iterable<Users>) items);

			response.setHasError(false);
		}

		// System.out.println("----end delete Users-----");
		return response;
	}

	/**
	 * get Users by using UsersDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<UsersDto> getByCriteria(Request<UsersDto> request, Locale locale)  throws Exception {
		// System.out.println("----begin get Users-----");

		Response<UsersDto> response = new Response<UsersDto>();
		List<Users> items 			 = usersRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<UsersDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? UsersTransformer.INSTANCE.toLiteDtos(items) : UsersTransformer.INSTANCE.toDtos(items);

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
			response.setCount(usersRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("users", locale));
			response.setHasError(false);
			return response;
		}

		// System.out.println("----end get Users-----");
		return response;
	}

	/**
	 * get full UsersDto by using Users as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private UsersDto getFullInfos(UsersDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
