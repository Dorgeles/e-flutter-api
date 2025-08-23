
package com.wdyapplications.e_commerce_api.dao.repository.base;

import java.util.Date;
import java.util.List;
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
 * Repository customize : Conversations.
 *
 * @author Dorgeddy
 *
 */
@Repository
public interface _ConversationsRepository {
	    /**
     * Finds Conversations by using id as a search criteria.
     *
     * @param id
     * @return An Object Conversations whose id is equals to the given id. If
     *         no Conversations is found, this method returns null.
     */
    @Query("select e from Conversations e where e.id = :id")
    Conversations findOne(@Param("id")Integer id);

    /**
     * Finds Conversations by using createdAt as a search criteria.
     *
     * @param createdAt
     * @return An Object Conversations whose createdAt is equals to the given createdAt. If
     *         no Conversations is found, this method returns null.
     */
    @Query("select e from Conversations e where e.createdAt = :createdAt")
    List<Conversations> findByCreatedAt(@Param("createdAt")Date createdAt);
    /**
     * Finds Conversations by using updatedAt as a search criteria.
     *
     * @param updatedAt
     * @return An Object Conversations whose updatedAt is equals to the given updatedAt. If
     *         no Conversations is found, this method returns null.
     */
    @Query("select e from Conversations e where e.updatedAt = :updatedAt")
    List<Conversations> findByUpdatedAt(@Param("updatedAt")Date updatedAt);
    /**
     * Finds Conversations by using lastMessageAt as a search criteria.
     *
     * @param lastMessageAt
     * @return An Object Conversations whose lastMessageAt is equals to the given lastMessageAt. If
     *         no Conversations is found, this method returns null.
     */
    @Query("select e from Conversations e where e.lastMessageAt = :lastMessageAt")
    List<Conversations> findByLastMessageAt(@Param("lastMessageAt")Date lastMessageAt);
    /**
     * Finds Conversations by using isActive as a search criteria.
     *
     * @param isActive
     * @return An Object Conversations whose isActive is equals to the given isActive. If
     *         no Conversations is found, this method returns null.
     */
    @Query("select e from Conversations e where e.isActive = :isActive")
    List<Conversations> findByIsActive(@Param("isActive")Boolean isActive);

    /**
     * Finds Conversations by using sellerId as a search criteria.
     *
     * @param sellerId
     * @return An Object Conversations whose sellerId is equals to the given sellerId. If
     *         no Conversations is found, this method returns null.
     */
    @Query("select e from Conversations e where e.users2.id = :sellerId")
    List<Conversations> findBySellerId(@Param("sellerId")Integer sellerId);

  /**
   * Finds one Conversations by using sellerId as a search criteria.
   *
   * @param sellerId
   * @return An Object Conversations whose sellerId is equals to the given sellerId. If
   *         no Conversations is found, this method returns null.
   */
  @Query("select e from Conversations e where e.users2.id = :sellerId")
  Conversations findConversationsBySellerId(@Param("sellerId")Integer sellerId);


    /**
     * Finds Conversations by using clientId as a search criteria.
     *
     * @param clientId
     * @return An Object Conversations whose clientId is equals to the given clientId. If
     *         no Conversations is found, this method returns null.
     */
    @Query("select e from Conversations e where e.users.id = :clientId")
    List<Conversations> findByClientId(@Param("clientId")Integer clientId);

  /**
   * Finds one Conversations by using clientId as a search criteria.
   *
   * @param clientId
   * @return An Object Conversations whose clientId is equals to the given clientId. If
   *         no Conversations is found, this method returns null.
   */
  @Query("select e from Conversations e where e.users.id = :clientId")
  Conversations findConversationsByClientId(@Param("clientId")Integer clientId);




    /**
     * Finds List of Conversations by using conversationsDto as a search criteria.
     *
     * @param request, em
     * @return A List of Conversations
     * @throws DataAccessException,ParseException
     */
    public default List<Conversations> getByCriteria(Request<ConversationsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
        String req = "select e from Conversations e where e IS NOT NULL";
        HashMap<String, Object> param = new HashMap<String, Object>();
        req += getWhereExpression(request, param, locale);
                TypedQuery<Conversations> query = em.createQuery(req, Conversations.class);
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
     * Finds count of Conversations by using conversationsDto as a search criteria.
     *
     * @param request, em
     * @return Number of Conversations
     *
     */
    public default Long count(Request<ConversationsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
        String req = "select count(e.id) from Conversations e where e IS NOT NULL";
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
    default String getWhereExpression(Request<ConversationsDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
        // main query
        ConversationsDto dto = request.getData() != null ? request.getData() : new ConversationsDto();
        String mainReq = generateCriteria(dto, param, 0, locale);
        // others query
        String othersReq = "";
        if (request.getDatas() != null && !request.getDatas().isEmpty()) {
            Integer index = 1;
            for (ConversationsDto elt : request.getDatas()) {
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
    default String generateCriteria(ConversationsDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
        List<String> listOfQuery = new ArrayList<String>();
        if (dto != null) {
            if (dto.getId() != null || Utilities.searchParamIsNotEmpty(dto.getIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getCreatedAt()) || Utilities.searchParamIsNotEmpty(dto.getCreatedAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("createdAt", dto.getCreatedAt(), "e.createdAt", "Date", dto.getCreatedAtParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getUpdatedAt()) || Utilities.searchParamIsNotEmpty(dto.getUpdatedAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("updatedAt", dto.getUpdatedAt(), "e.updatedAt", "Date", dto.getUpdatedAtParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getLastMessageAt()) || Utilities.searchParamIsNotEmpty(dto.getLastMessageAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("lastMessageAt", dto.getLastMessageAt(), "e.lastMessageAt", "Date", dto.getLastMessageAtParam(), param, index, locale));
            }
            if (dto.getIsActive() != null || Utilities.searchParamIsNotEmpty(dto.getIsActiveParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("isActive", dto.getIsActive(), "e.isActive", "Boolean", dto.getIsActiveParam(), param, index, locale));
            }
                        if (dto.getSellerId() != null || Utilities.searchParamIsNotEmpty(dto.getSellerIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("sellerId", dto.getSellerId(), "e.users2.id", "Integer", dto.getSellerIdParam(), param, index, locale));
            }
                        if (dto.getClientId() != null || Utilities.searchParamIsNotEmpty(dto.getClientIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("clientId", dto.getClientId(), "e.users.id", "Integer", dto.getClientIdParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getUsersFirstName()) || Utilities.searchParamIsNotEmpty(dto.getUsersFirstNameParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("usersFirstName", dto.getUsersFirstName(), "e.users2.firstName", "String", dto.getUsersFirstNameParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getUsersLastName()) || Utilities.searchParamIsNotEmpty(dto.getUsersLastNameParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("usersLastName", dto.getUsersLastName(), "e.users2.lastName", "String", dto.getUsersLastNameParam(), param, index, locale));
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
