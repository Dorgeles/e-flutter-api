
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
 * Repository customize : Messages.
 *
 * @author Dorgeddy
 *
 */
@Repository
public interface _MessagesRepository {
	    /**
     * Finds Messages by using id as a search criteria.
     *
     * @param id
     * @return An Object Messages whose id is equals to the given id. If
     *         no Messages is found, this method returns null.
     */
    @Query("select e from Messages e where e.id = :id")
    Messages findOne(@Param("id")Integer id);

    /**
     * Finds Messages by using content as a search criteria.
     *
     * @param content
     * @return An Object Messages whose content is equals to the given content. If
     *         no Messages is found, this method returns null.
     */
    @Query("select e from Messages e where e.content = :content")
    List<Messages> findByContent(@Param("content")String content);
    /**
     * Finds Messages by using type as a search criteria.
     *
     * @param type
     * @return An Object Messages whose type is equals to the given type. If
     *         no Messages is found, this method returns null.
     */
    @Query("select e from Messages e where e.type = :type")
    List<Messages> findByType(@Param("type")String type);
    /**
     * Finds Messages by using attachmentUrl as a search criteria.
     *
     * @param attachmentUrl
     * @return An Object Messages whose attachmentUrl is equals to the given attachmentUrl. If
     *         no Messages is found, this method returns null.
     */
    @Query("select e from Messages e where e.attachmentUrl = :attachmentUrl")
    List<Messages> findByAttachmentUrl(@Param("attachmentUrl")String attachmentUrl);
    /**
     * Finds Messages by using isRead as a search criteria.
     *
     * @param isRead
     * @return An Object Messages whose isRead is equals to the given isRead. If
     *         no Messages is found, this method returns null.
     */
    @Query("select e from Messages e where e.isRead = :isRead")
    List<Messages> findByIsRead(@Param("isRead")Boolean isRead);
    /**
     * Finds Messages by using createdAt as a search criteria.
     *
     * @param createdAt
     * @return An Object Messages whose createdAt is equals to the given createdAt. If
     *         no Messages is found, this method returns null.
     */
    @Query("select e from Messages e where e.createdAt = :createdAt")
    List<Messages> findByCreatedAt(@Param("createdAt")Date createdAt);
    /**
     * Finds Messages by using readAt as a search criteria.
     *
     * @param readAt
     * @return An Object Messages whose readAt is equals to the given readAt. If
     *         no Messages is found, this method returns null.
     */
    @Query("select e from Messages e where e.readAt = :readAt")
    List<Messages> findByReadAt(@Param("readAt")Date readAt);

    /**
     * Finds Messages by using senderId as a search criteria.
     *
     * @param senderId
     * @return An Object Messages whose senderId is equals to the given senderId. If
     *         no Messages is found, this method returns null.
     */
    @Query("select e from Messages e where e.users.id = :senderId")
    List<Messages> findBySenderId(@Param("senderId")Integer senderId);

  /**
   * Finds one Messages by using senderId as a search criteria.
   *
   * @param senderId
   * @return An Object Messages whose senderId is equals to the given senderId. If
   *         no Messages is found, this method returns null.
   */
  @Query("select e from Messages e where e.users.id = :senderId")
  Messages findMessagesBySenderId(@Param("senderId")Integer senderId);


    /**
     * Finds Messages by using conversationId as a search criteria.
     *
     * @param conversationId
     * @return An Object Messages whose conversationId is equals to the given conversationId. If
     *         no Messages is found, this method returns null.
     */
    @Query("select e from Messages e where e.conversations.id = :conversationId")
    List<Messages> findByConversationId(@Param("conversationId")Integer conversationId);

  /**
   * Finds one Messages by using conversationId as a search criteria.
   *
   * @param conversationId
   * @return An Object Messages whose conversationId is equals to the given conversationId. If
   *         no Messages is found, this method returns null.
   */
  @Query("select e from Messages e where e.conversations.id = :conversationId")
  Messages findMessagesByConversationId(@Param("conversationId")Integer conversationId);




    /**
     * Finds List of Messages by using messagesDto as a search criteria.
     *
     * @param request, em
     * @return A List of Messages
     * @throws DataAccessException,ParseException
     */
    public default List<Messages> getByCriteria(Request<MessagesDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
        String req = "select e from Messages e where e IS NOT NULL";
        HashMap<String, Object> param = new HashMap<String, Object>();
        req += getWhereExpression(request, param, locale);
                TypedQuery<Messages> query = em.createQuery(req, Messages.class);
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
     * Finds count of Messages by using messagesDto as a search criteria.
     *
     * @param request, em
     * @return Number of Messages
     *
     */
    public default Long count(Request<MessagesDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
        String req = "select count(e.id) from Messages e where e IS NOT NULL";
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
    default String getWhereExpression(Request<MessagesDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
        // main query
        MessagesDto dto = request.getData() != null ? request.getData() : new MessagesDto();
        String mainReq = generateCriteria(dto, param, 0, locale);
        // others query
        String othersReq = "";
        if (request.getDatas() != null && !request.getDatas().isEmpty()) {
            Integer index = 1;
            for (MessagesDto elt : request.getDatas()) {
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
    default String generateCriteria(MessagesDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
        List<String> listOfQuery = new ArrayList<String>();
        if (dto != null) {
            if (dto.getId() != null || Utilities.searchParamIsNotEmpty(dto.getIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getContent()) || Utilities.searchParamIsNotEmpty(dto.getContentParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("content", dto.getContent(), "e.content", "String", dto.getContentParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getType()) || Utilities.searchParamIsNotEmpty(dto.getTypeParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("type", dto.getType(), "e.type", "String", dto.getTypeParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getAttachmentUrl()) || Utilities.searchParamIsNotEmpty(dto.getAttachmentUrlParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("attachmentUrl", dto.getAttachmentUrl(), "e.attachmentUrl", "String", dto.getAttachmentUrlParam(), param, index, locale));
            }
            if (dto.getIsRead() != null || Utilities.searchParamIsNotEmpty(dto.getIsReadParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("isRead", dto.getIsRead(), "e.isRead", "Boolean", dto.getIsReadParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getCreatedAt()) || Utilities.searchParamIsNotEmpty(dto.getCreatedAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("createdAt", dto.getCreatedAt(), "e.createdAt", "Date", dto.getCreatedAtParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getReadAt()) || Utilities.searchParamIsNotEmpty(dto.getReadAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("readAt", dto.getReadAt(), "e.readAt", "Date", dto.getReadAtParam(), param, index, locale));
            }
                        if (dto.getSenderId() != null || Utilities.searchParamIsNotEmpty(dto.getSenderIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("senderId", dto.getSenderId(), "e.users.id", "Integer", dto.getSenderIdParam(), param, index, locale));
            }
                        if (dto.getConversationId() != null || Utilities.searchParamIsNotEmpty(dto.getConversationIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("conversationId", dto.getConversationId(), "e.conversations.id", "Integer", dto.getConversationIdParam(), param, index, locale));
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
