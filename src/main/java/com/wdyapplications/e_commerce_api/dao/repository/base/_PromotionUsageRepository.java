
package com.wdyapplications.e_commerce_api.dao.repository.base;

import java.math.BigDecimal;
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
 * Repository customize : PromotionUsage.
 *
 * @author Dorgeddy
 *
 */
@Repository
public interface _PromotionUsageRepository {
	    /**
     * Finds PromotionUsage by using id as a search criteria.
     *
     * @param id
     * @return An Object PromotionUsage whose id is equals to the given id. If
     *         no PromotionUsage is found, this method returns null.
     */
    @Query("select e from PromotionUsage e where e.id = :id")
    PromotionUsage findOne(@Param("id")Integer id);

    /**
     * Finds PromotionUsage by using discountAmount as a search criteria.
     *
     * @param discountAmount
     * @return An Object PromotionUsage whose discountAmount is equals to the given discountAmount. If
     *         no PromotionUsage is found, this method returns null.
     */
    @Query("select e from PromotionUsage e where e.discountAmount = :discountAmount")
    List<PromotionUsage> findByDiscountAmount(@Param("discountAmount")BigDecimal discountAmount);
    /**
     * Finds PromotionUsage by using usedAt as a search criteria.
     *
     * @param usedAt
     * @return An Object PromotionUsage whose usedAt is equals to the given usedAt. If
     *         no PromotionUsage is found, this method returns null.
     */
    @Query("select e from PromotionUsage e where e.usedAt = :usedAt")
    List<PromotionUsage> findByUsedAt(@Param("usedAt")Date usedAt);

    /**
     * Finds PromotionUsage by using orderId as a search criteria.
     *
     * @param orderId
     * @return An Object PromotionUsage whose orderId is equals to the given orderId. If
     *         no PromotionUsage is found, this method returns null.
     */
    @Query("select e from PromotionUsage e where e.orders.id = :orderId")
    List<PromotionUsage> findByOrderId(@Param("orderId")Integer orderId);

  /**
   * Finds one PromotionUsage by using orderId as a search criteria.
   *
   * @param orderId
   * @return An Object PromotionUsage whose orderId is equals to the given orderId. If
   *         no PromotionUsage is found, this method returns null.
   */
  @Query("select e from PromotionUsage e where e.orders.id = :orderId")
  PromotionUsage findPromotionUsageByOrderId(@Param("orderId")Integer orderId);


    /**
     * Finds PromotionUsage by using promotionId as a search criteria.
     *
     * @param promotionId
     * @return An Object PromotionUsage whose promotionId is equals to the given promotionId. If
     *         no PromotionUsage is found, this method returns null.
     */
    @Query("select e from PromotionUsage e where e.promotions.id = :promotionId")
    List<PromotionUsage> findByPromotionId(@Param("promotionId")Integer promotionId);

  /**
   * Finds one PromotionUsage by using promotionId as a search criteria.
   *
   * @param promotionId
   * @return An Object PromotionUsage whose promotionId is equals to the given promotionId. If
   *         no PromotionUsage is found, this method returns null.
   */
  @Query("select e from PromotionUsage e where e.promotions.id = :promotionId")
  PromotionUsage findPromotionUsageByPromotionId(@Param("promotionId")Integer promotionId);


    /**
     * Finds PromotionUsage by using userId as a search criteria.
     *
     * @param userId
     * @return An Object PromotionUsage whose userId is equals to the given userId. If
     *         no PromotionUsage is found, this method returns null.
     */
    @Query("select e from PromotionUsage e where e.users.id = :userId")
    List<PromotionUsage> findByUserId(@Param("userId")Integer userId);

  /**
   * Finds one PromotionUsage by using userId as a search criteria.
   *
   * @param userId
   * @return An Object PromotionUsage whose userId is equals to the given userId. If
   *         no PromotionUsage is found, this method returns null.
   */
  @Query("select e from PromotionUsage e where e.users.id = :userId")
  PromotionUsage findPromotionUsageByUserId(@Param("userId")Integer userId);




    /**
     * Finds List of PromotionUsage by using promotionUsageDto as a search criteria.
     *
     * @param request, em
     * @return A List of PromotionUsage
     * @throws DataAccessException,ParseException
     */
    public default List<PromotionUsage> getByCriteria(Request<PromotionUsageDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
        String req = "select e from PromotionUsage e where e IS NOT NULL";
        HashMap<String, Object> param = new HashMap<String, Object>();
        req += getWhereExpression(request, param, locale);
                TypedQuery<PromotionUsage> query = em.createQuery(req, PromotionUsage.class);
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
     * Finds count of PromotionUsage by using promotionUsageDto as a search criteria.
     *
     * @param request, em
     * @return Number of PromotionUsage
     *
     */
    public default Long count(Request<PromotionUsageDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
        String req = "select count(e.id) from PromotionUsage e where e IS NOT NULL";
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
    default String getWhereExpression(Request<PromotionUsageDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
        // main query
        PromotionUsageDto dto = request.getData() != null ? request.getData() : new PromotionUsageDto();
        String mainReq = generateCriteria(dto, param, 0, locale);
        // others query
        String othersReq = "";
        if (request.getDatas() != null && !request.getDatas().isEmpty()) {
            Integer index = 1;
            for (PromotionUsageDto elt : request.getDatas()) {
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
    default String generateCriteria(PromotionUsageDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
        List<String> listOfQuery = new ArrayList<String>();
        if (dto != null) {
            if (dto.getId() != null || Utilities.searchParamIsNotEmpty(dto.getIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
            }
            if (dto.getDiscountAmount() != null || Utilities.searchParamIsNotEmpty(dto.getDiscountAmountParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("discountAmount", dto.getDiscountAmount(), "e.discountAmount", "BigDecimal", dto.getDiscountAmountParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getUsedAt()) || Utilities.searchParamIsNotEmpty(dto.getUsedAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("usedAt", dto.getUsedAt(), "e.usedAt", "Date", dto.getUsedAtParam(), param, index, locale));
            }
                        if (dto.getOrderId() != null || Utilities.searchParamIsNotEmpty(dto.getOrderIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("orderId", dto.getOrderId(), "e.orders.id", "Integer", dto.getOrderIdParam(), param, index, locale));
            }
                        if (dto.getPromotionId() != null || Utilities.searchParamIsNotEmpty(dto.getPromotionIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("promotionId", dto.getPromotionId(), "e.promotions.id", "Integer", dto.getPromotionIdParam(), param, index, locale));
            }
                        if (dto.getUserId() != null || Utilities.searchParamIsNotEmpty(dto.getUserIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("userId", dto.getUserId(), "e.users.id", "Integer", dto.getUserIdParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getPromotionsCode()) || Utilities.searchParamIsNotEmpty(dto.getPromotionsCodeParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("promotionsCode", dto.getPromotionsCode(), "e.promotions.code", "String", dto.getPromotionsCodeParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getPromotionsName()) || Utilities.searchParamIsNotEmpty(dto.getPromotionsNameParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("promotionsName", dto.getPromotionsName(), "e.promotions.name", "String", dto.getPromotionsNameParam(), param, index, locale));
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
