
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
 * Repository customize : PromotionProducts.
 *
 * @author Dorgeddy
 *
 */
@Repository
public interface _PromotionProductsRepository {
	    /**
     * Finds PromotionProducts by using id as a search criteria.
     *
     * @param id
     * @return An Object PromotionProducts whose id is equals to the given id. If
     *         no PromotionProducts is found, this method returns null.
     */
    @Query("select e from PromotionProducts e where e.id = :id")
    PromotionProducts findOne(@Param("id")Integer id);

    /**
     * Finds PromotionProducts by using createdAt as a search criteria.
     *
     * @param createdAt
     * @return An Object PromotionProducts whose createdAt is equals to the given createdAt. If
     *         no PromotionProducts is found, this method returns null.
     */
    @Query("select e from PromotionProducts e where e.createdAt = :createdAt")
    List<PromotionProducts> findByCreatedAt(@Param("createdAt")Date createdAt);

    /**
     * Finds PromotionProducts by using productId as a search criteria.
     *
     * @param productId
     * @return An Object PromotionProducts whose productId is equals to the given productId. If
     *         no PromotionProducts is found, this method returns null.
     */
    @Query("select e from PromotionProducts e where e.products.id = :productId")
    List<PromotionProducts> findByProductId(@Param("productId")Integer productId);

  /**
   * Finds one PromotionProducts by using productId as a search criteria.
   *
   * @param productId
   * @return An Object PromotionProducts whose productId is equals to the given productId. If
   *         no PromotionProducts is found, this method returns null.
   */
  @Query("select e from PromotionProducts e where e.products.id = :productId")
  PromotionProducts findPromotionProductsByProductId(@Param("productId")Integer productId);


    /**
     * Finds PromotionProducts by using promotionId as a search criteria.
     *
     * @param promotionId
     * @return An Object PromotionProducts whose promotionId is equals to the given promotionId. If
     *         no PromotionProducts is found, this method returns null.
     */
    @Query("select e from PromotionProducts e where e.promotions.id = :promotionId")
    List<PromotionProducts> findByPromotionId(@Param("promotionId")Integer promotionId);

  /**
   * Finds one PromotionProducts by using promotionId as a search criteria.
   *
   * @param promotionId
   * @return An Object PromotionProducts whose promotionId is equals to the given promotionId. If
   *         no PromotionProducts is found, this method returns null.
   */
  @Query("select e from PromotionProducts e where e.promotions.id = :promotionId")
  PromotionProducts findPromotionProductsByPromotionId(@Param("promotionId")Integer promotionId);




    /**
     * Finds List of PromotionProducts by using promotionProductsDto as a search criteria.
     *
     * @param request, em
     * @return A List of PromotionProducts
     * @throws DataAccessException,ParseException
     */
    public default List<PromotionProducts> getByCriteria(Request<PromotionProductsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
        String req = "select e from PromotionProducts e where e IS NOT NULL";
        HashMap<String, Object> param = new HashMap<String, Object>();
        req += getWhereExpression(request, param, locale);
                TypedQuery<PromotionProducts> query = em.createQuery(req, PromotionProducts.class);
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
     * Finds count of PromotionProducts by using promotionProductsDto as a search criteria.
     *
     * @param request, em
     * @return Number of PromotionProducts
     *
     */
    public default Long count(Request<PromotionProductsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
        String req = "select count(e.id) from PromotionProducts e where e IS NOT NULL";
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
    default String getWhereExpression(Request<PromotionProductsDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
        // main query
        PromotionProductsDto dto = request.getData() != null ? request.getData() : new PromotionProductsDto();
        String mainReq = generateCriteria(dto, param, 0, locale);
        // others query
        String othersReq = "";
        if (request.getDatas() != null && !request.getDatas().isEmpty()) {
            Integer index = 1;
            for (PromotionProductsDto elt : request.getDatas()) {
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
    default String generateCriteria(PromotionProductsDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
        List<String> listOfQuery = new ArrayList<String>();
        if (dto != null) {
            if (dto.getId() != null || Utilities.searchParamIsNotEmpty(dto.getIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getCreatedAt()) || Utilities.searchParamIsNotEmpty(dto.getCreatedAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("createdAt", dto.getCreatedAt(), "e.createdAt", "Date", dto.getCreatedAtParam(), param, index, locale));
            }
                        if (dto.getProductId() != null || Utilities.searchParamIsNotEmpty(dto.getProductIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("productId", dto.getProductId(), "e.products.id", "Integer", dto.getProductIdParam(), param, index, locale));
            }
                        if (dto.getPromotionId() != null || Utilities.searchParamIsNotEmpty(dto.getPromotionIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("promotionId", dto.getPromotionId(), "e.promotions.id", "Integer", dto.getPromotionIdParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getProductsName()) || Utilities.searchParamIsNotEmpty(dto.getProductsNameParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("productsName", dto.getProductsName(), "e.products.name", "String", dto.getProductsNameParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getPromotionsCode()) || Utilities.searchParamIsNotEmpty(dto.getPromotionsCodeParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("promotionsCode", dto.getPromotionsCode(), "e.promotions.code", "String", dto.getPromotionsCodeParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getPromotionsName()) || Utilities.searchParamIsNotEmpty(dto.getPromotionsNameParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("promotionsName", dto.getPromotionsName(), "e.promotions.name", "String", dto.getPromotionsNameParam(), param, index, locale));
            }

            /*List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
            if (Utilities.isNotEmpty(listOfCustomQuery)) {
                listOfQuery.addAll(listOfCustomQuery);
            }*/
        }
        return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
    }
}
