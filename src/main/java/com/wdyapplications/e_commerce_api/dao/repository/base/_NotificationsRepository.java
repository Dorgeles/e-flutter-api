
package com.wdyapplications.e_commerce_api.dao.repository.base;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Locale;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wdyapplications.e_commerce_api.utils.*;
import com.wdyapplications.e_commerce_api.utils.dto.*;
import com.wdyapplications.e_commerce_api.utils.contract.*;
import com.wdyapplications.e_commerce_api.utils.contract.Request;
import com.wdyapplications.e_commerce_api.utils.contract.Response;
import com.wdyapplications.e_commerce_api.dao.entity.*;

/**
 * Repository customize : Notifications.
 *
 * @author Dorgeddy
 *
 */
@Repository
public interface _NotificationsRepository {
	    /**
     * Finds Notifications by using id as a search criteria.
     *
     * @param id
     * @return An Object Notifications whose id is equals to the given id. If
     *         no Notifications is found, this method returns null.
     */
    @Query("select e from Notifications e where e.id = :id")
    Notifications findOne(@Param("id")Integer id);

    /**
     * Finds Notifications by using title as a search criteria.
     *
     * @param title
     * @return An Object Notifications whose title is equals to the given title. If
     *         no Notifications is found, this method returns null.
     */
    @Query("select e from Notifications e where e.title = :title")
    List<Notifications> findByTitle(@Param("title")String title);
    /**
     * Finds Notifications by using message as a search criteria.
     *
     * @param message
     * @return An Object Notifications whose message is equals to the given message. If
     *         no Notifications is found, this method returns null.
     */
    @Query("select e from Notifications e where e.message = :message")
    List<Notifications> findByMessage(@Param("message")String message);
    /**
     * Finds Notifications by using type as a search criteria.
     *
     * @param type
     * @return An Object Notifications whose type is equals to the given type. If
     *         no Notifications is found, this method returns null.
     */
    @Query("select e from Notifications e where e.type = :type")
    List<Notifications> findByType(@Param("type")String type);
    /**
     * Finds Notifications by using referenceId as a search criteria.
     *
     * @param referenceId
     * @return An Object Notifications whose referenceId is equals to the given referenceId. If
     *         no Notifications is found, this method returns null.
     */
    @Query("select e from Notifications e where e.referenceId = :referenceId")
    List<Notifications> findByReferenceId(@Param("referenceId")String referenceId);
    /**
     * Finds Notifications by using isRead as a search criteria.
     *
     * @param isRead
     * @return An Object Notifications whose isRead is equals to the given isRead. If
     *         no Notifications is found, this method returns null.
     */
    @Query("select e from Notifications e where e.isRead = :isRead")
    List<Notifications> findByIsRead(@Param("isRead")Boolean isRead);
    /**
     * Finds Notifications by using isPushSent as a search criteria.
     *
     * @param isPushSent
     * @return An Object Notifications whose isPushSent is equals to the given isPushSent. If
     *         no Notifications is found, this method returns null.
     */
    @Query("select e from Notifications e where e.isPushSent = :isPushSent")
    List<Notifications> findByIsPushSent(@Param("isPushSent")Boolean isPushSent);
    /**
     * Finds Notifications by using createdAt as a search criteria.
     *
     * @param createdAt
     * @return An Object Notifications whose createdAt is equals to the given createdAt. If
     *         no Notifications is found, this method returns null.
     */
    @Query("select e from Notifications e where e.createdAt = :createdAt")
    List<Notifications> findByCreatedAt(@Param("createdAt")Date createdAt);
    /**
     * Finds Notifications by using readAt as a search criteria.
     *
     * @param readAt
     * @return An Object Notifications whose readAt is equals to the given readAt. If
     *         no Notifications is found, this method returns null.
     */
    @Query("select e from Notifications e where e.readAt = :readAt")
    List<Notifications> findByReadAt(@Param("readAt")Date readAt);

    /**
     * Finds Notifications by using userId as a search criteria.
     *
     * @param userId
     * @return An Object Notifications whose userId is equals to the given userId. If
     *         no Notifications is found, this method returns null.
     */
    @Query("select e from Notifications e where e.users.id = :userId")
    List<Notifications> findByUserId(@Param("userId")Integer userId);

  /**
   * Finds one Notifications by using userId as a search criteria.
   *
   * @param userId
   * @return An Object Notifications whose userId is equals to the given userId. If
   *         no Notifications is found, this method returns null.
   */
  @Query("select e from Notifications e where e.users.id = :userId")
  Notifications findNotificationsByUserId(@Param("userId")Integer userId);




    /**
     * Finds List of Notifications by using notificationsDto as a search criteria.
     *
     * @param request, em
     * @return A List of Notifications
     * @throws DataAccessException,ParseException
     */
    public default List<Notifications> getByCriteria(Request<NotificationsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
        String req = "select e from Notifications e where e IS NOT NULL";
        HashMap<String, Object> param = new HashMap<String, Object>();
        req += getWhereExpression(request, param, locale);
                TypedQuery<Notifications> query = em.createQuery(req, Notifications.class);
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        if (request.getIndex() != null && request.getSize() != null) {
            query.setFirstResult(request.getIndex() * request.getSize());
            query.setMaxResults(request.getSize());
        }
        return query.getResultList();
    }

    /**
     * Finds count of Notifications by using notificationsDto as a search criteria.
     *
     * @param request, em
     * @return Number of Notifications
     *
     */
    public default Long count(Request<NotificationsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
        String req = "select count(e.id) from Notifications e where e IS NOT NULL";
        HashMap<String, Object> param = new HashMap<String, Object>();
        req += getWhereExpression(request, param, locale);
                jakarta.persistence.Query query = em.createQuery(req);
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        Long count = (Long) query.getResultList().get(0);
        return count;
    }

    /**
     * get where expression
     * @param request
     * @param param
     * @param locale
     * @return
     * @throws Exception
     */
    default String getWhereExpression(Request<NotificationsDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
        // main query
        NotificationsDto dto = request.getData() != null ? request.getData() : new NotificationsDto();
        String mainReq = generateCriteria(dto, param, 0, locale);
        // others query
        String othersReq = "";
        if (request.getDatas() != null && !request.getDatas().isEmpty()) {
            Integer index = 1;
            for (NotificationsDto elt : request.getDatas()) {
                String eltReq = generateCriteria(elt, param, index, locale);
                if (request.getIsAnd() != null && request.getIsAnd()) {
                    othersReq += "and (" + eltReq + ") ";
                } else {
                    othersReq += "or (" + eltReq + ") ";
                }
                index++;
            }
        }
        String req = "";
        if (!mainReq.isEmpty()) {
            req += " and (" + mainReq + ") ";
        }
        req += othersReq;

        //order
        if(Direction.fromOptionalString(dto.getOrderDirection()).orElse(null) != null && Utilities.notBlank(dto.getOrderField())) {
            req += " order by e."+dto.getOrderField()+" "+dto.getOrderDirection();
        }
        else {
            req += " order by  e.id desc";
        }
        return req;
    }

    /**
     * generate sql query for dto
     * @param dto
     * @param param
     * @param index
     * @param locale
     * @return
     * @throws Exception
     */
    default String generateCriteria(NotificationsDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
        List<String> listOfQuery = new ArrayList<String>();
        if (dto != null) {
            if (dto.getId() != null || Utilities.searchParamIsNotEmpty(dto.getIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getTitle()) || Utilities.searchParamIsNotEmpty(dto.getTitleParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("title", dto.getTitle(), "e.title", "String", dto.getTitleParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getMessage()) || Utilities.searchParamIsNotEmpty(dto.getMessageParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("message", dto.getMessage(), "e.message", "String", dto.getMessageParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getType()) || Utilities.searchParamIsNotEmpty(dto.getTypeParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("type", dto.getType(), "e.type", "String", dto.getTypeParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getReferenceId()) || Utilities.searchParamIsNotEmpty(dto.getReferenceIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("referenceId", dto.getReferenceId(), "e.referenceId", "String", dto.getReferenceIdParam(), param, index, locale));
            }
            if (dto.getIsRead() != null || Utilities.searchParamIsNotEmpty(dto.getIsReadParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("isRead", dto.getIsRead(), "e.isRead", "Boolean", dto.getIsReadParam(), param, index, locale));
            }
            if (dto.getIsPushSent() != null || Utilities.searchParamIsNotEmpty(dto.getIsPushSentParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("isPushSent", dto.getIsPushSent(), "e.isPushSent", "Boolean", dto.getIsPushSentParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getCreatedAt()) || Utilities.searchParamIsNotEmpty(dto.getCreatedAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("createdAt", dto.getCreatedAt(), "e.createdAt", "Date", dto.getCreatedAtParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getReadAt()) || Utilities.searchParamIsNotEmpty(dto.getReadAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("readAt", dto.getReadAt(), "e.readAt", "Date", dto.getReadAtParam(), param, index, locale));
            }
                        if (dto.getUserId() != null || Utilities.searchParamIsNotEmpty(dto.getUserIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("userId", dto.getUserId(), "e.users.id", "Integer", dto.getUserIdParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getUsersFirstName()) || Utilities.searchParamIsNotEmpty(dto.getUsersFirstNameParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("usersFirstName", dto.getUsersFirstName(), "e.users.firstName", "String", dto.getUsersFirstNameParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getUsersLastName()) || Utilities.searchParamIsNotEmpty(dto.getUsersLastNameParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("usersLastName", dto.getUsersLastName(), "e.users.lastName", "String", dto.getUsersLastNameParam(), param, index, locale));
            }

            /*List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
            if (Utilities.isNotEmpty(listOfCustomQuery)) {
                listOfQuery.addAll(listOfCustomQuery);
            }*/
        }
        return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
    }
}
