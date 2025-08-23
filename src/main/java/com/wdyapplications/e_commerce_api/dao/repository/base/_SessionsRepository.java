
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
 * Repository customize : Sessions.
 *
 * @author Dorgeddy
 *
 */
@Repository
public interface _SessionsRepository {
	    /**
     * Finds Sessions by using id as a search criteria.
     *
     * @param id
     * @return An Object Sessions whose id is equals to the given id. If
     *         no Sessions is found, this method returns null.
     */
    @Query("select e from Sessions e where e.id = :id")
    Sessions findOne(@Param("id")String id);

    /**
     * Finds Sessions by using token as a search criteria.
     *
     * @param token
     * @return An Object Sessions whose token is equals to the given token. If
     *         no Sessions is found, this method returns null.
     */
    @Query("select e from Sessions e where e.token = :token")
    List<Sessions> findByToken(@Param("token")String token);
    /**
     * Finds Sessions by using expiresAt as a search criteria.
     *
     * @param expiresAt
     * @return An Object Sessions whose expiresAt is equals to the given expiresAt. If
     *         no Sessions is found, this method returns null.
     */
    @Query("select e from Sessions e where e.expiresAt = :expiresAt")
    List<Sessions> findByExpiresAt(@Param("expiresAt")Date expiresAt);
    /**
     * Finds Sessions by using createdAt as a search criteria.
     *
     * @param createdAt
     * @return An Object Sessions whose createdAt is equals to the given createdAt. If
     *         no Sessions is found, this method returns null.
     */
    @Query("select e from Sessions e where e.createdAt = :createdAt")
    List<Sessions> findByCreatedAt(@Param("createdAt")Date createdAt);
    /**
     * Finds Sessions by using deviceInfo as a search criteria.
     *
     * @param deviceInfo
     * @return An Object Sessions whose deviceInfo is equals to the given deviceInfo. If
     *         no Sessions is found, this method returns null.
     */
    @Query("select e from Sessions e where e.deviceInfo = :deviceInfo")
    List<Sessions> findByDeviceInfo(@Param("deviceInfo")String deviceInfo);
    /**
     * Finds Sessions by using ipAddress as a search criteria.
     *
     * @param ipAddress
     * @return An Object Sessions whose ipAddress is equals to the given ipAddress. If
     *         no Sessions is found, this method returns null.
     */
    @Query("select e from Sessions e where e.ipAddress = :ipAddress")
    List<Sessions> findByIpAddress(@Param("ipAddress")String ipAddress);

    /**
     * Finds Sessions by using userId as a search criteria.
     *
     * @param userId
     * @return An Object Sessions whose userId is equals to the given userId. If
     *         no Sessions is found, this method returns null.
     */
    @Query("select e from Sessions e where e.users.id = :userId")
    List<Sessions> findByUserId(@Param("userId")Integer userId);

  /**
   * Finds one Sessions by using userId as a search criteria.
   *
   * @param userId
   * @return An Object Sessions whose userId is equals to the given userId. If
   *         no Sessions is found, this method returns null.
   */
  @Query("select e from Sessions e where e.users.id = :userId")
  Sessions findSessionsByUserId(@Param("userId")Integer userId);




    /**
     * Finds List of Sessions by using sessionsDto as a search criteria.
     *
     * @param request, em
     * @return A List of Sessions
     * @throws DataAccessException,ParseException
     */
    public default List<Sessions> getByCriteria(Request<SessionsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
        String req = "select e from Sessions e where e IS NOT NULL";
        HashMap<String, Object> param = new HashMap<String, Object>();
        req += getWhereExpression(request, param, locale);
                TypedQuery<Sessions> query = em.createQuery(req, Sessions.class);
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
     * Finds count of Sessions by using sessionsDto as a search criteria.
     *
     * @param request, em
     * @return Number of Sessions
     *
     */
    public default Long count(Request<SessionsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
        String req = "select count(e.id) from Sessions e where e IS NOT NULL";
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
    default String getWhereExpression(Request<SessionsDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
        // main query
        SessionsDto dto = request.getData() != null ? request.getData() : new SessionsDto();
        String mainReq = generateCriteria(dto, param, 0, locale);
        // others query
        String othersReq = "";
        if (request.getDatas() != null && !request.getDatas().isEmpty()) {
            Integer index = 1;
            for (SessionsDto elt : request.getDatas()) {
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
    default String generateCriteria(SessionsDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
        List<String> listOfQuery = new ArrayList<String>();
        if (dto != null) {
            if (Utilities.isNotBlank(dto.getId()) || Utilities.searchParamIsNotEmpty(dto.getIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "String", dto.getIdParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getToken()) || Utilities.searchParamIsNotEmpty(dto.getTokenParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("token", dto.getToken(), "e.token", "String", dto.getTokenParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getExpiresAt()) || Utilities.searchParamIsNotEmpty(dto.getExpiresAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("expiresAt", dto.getExpiresAt(), "e.expiresAt", "Date", dto.getExpiresAtParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getCreatedAt()) || Utilities.searchParamIsNotEmpty(dto.getCreatedAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("createdAt", dto.getCreatedAt(), "e.createdAt", "Date", dto.getCreatedAtParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getDeviceInfo()) || Utilities.searchParamIsNotEmpty(dto.getDeviceInfoParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("deviceInfo", dto.getDeviceInfo(), "e.deviceInfo", "String", dto.getDeviceInfoParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getIpAddress()) || Utilities.searchParamIsNotEmpty(dto.getIpAddressParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("ipAddress", dto.getIpAddress(), "e.ipAddress", "String", dto.getIpAddressParam(), param, index, locale));
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
