
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
 * Repository customize : StockHistory.
 *
 * @author Dorgeddy
 *
 */
@Repository
public interface _StockHistoryRepository {
	    /**
     * Finds StockHistory by using id as a search criteria.
     *
     * @param id
     * @return An Object StockHistory whose id is equals to the given id. If
     *         no StockHistory is found, this method returns null.
     */
    @Query("select e from StockHistory e where e.id = :id")
    StockHistory findOne(@Param("id")Integer id);

    /**
     * Finds StockHistory by using oldQuantity as a search criteria.
     *
     * @param oldQuantity
     * @return An Object StockHistory whose oldQuantity is equals to the given oldQuantity. If
     *         no StockHistory is found, this method returns null.
     */
    @Query("select e from StockHistory e where e.oldQuantity = :oldQuantity")
    List<StockHistory> findByOldQuantity(@Param("oldQuantity")Integer oldQuantity);
    /**
     * Finds StockHistory by using newQuantity as a search criteria.
     *
     * @param newQuantity
     * @return An Object StockHistory whose newQuantity is equals to the given newQuantity. If
     *         no StockHistory is found, this method returns null.
     */
    @Query("select e from StockHistory e where e.newQuantity = :newQuantity")
    List<StockHistory> findByNewQuantity(@Param("newQuantity")Integer newQuantity);
    /**
     * Finds StockHistory by using changeQuantity as a search criteria.
     *
     * @param changeQuantity
     * @return An Object StockHistory whose changeQuantity is equals to the given changeQuantity. If
     *         no StockHistory is found, this method returns null.
     */
    @Query("select e from StockHistory e where e.changeQuantity = :changeQuantity")
    List<StockHistory> findByChangeQuantity(@Param("changeQuantity")Integer changeQuantity);
    /**
     * Finds StockHistory by using reason as a search criteria.
     *
     * @param reason
     * @return An Object StockHistory whose reason is equals to the given reason. If
     *         no StockHistory is found, this method returns null.
     */
    @Query("select e from StockHistory e where e.reason = :reason")
    List<StockHistory> findByReason(@Param("reason")String reason);
    /**
     * Finds StockHistory by using referenceId as a search criteria.
     *
     * @param referenceId
     * @return An Object StockHistory whose referenceId is equals to the given referenceId. If
     *         no StockHistory is found, this method returns null.
     */
    @Query("select e from StockHistory e where e.referenceId = :referenceId")
    List<StockHistory> findByReferenceId(@Param("referenceId")String referenceId);
    /**
     * Finds StockHistory by using createdAt as a search criteria.
     *
     * @param createdAt
     * @return An Object StockHistory whose createdAt is equals to the given createdAt. If
     *         no StockHistory is found, this method returns null.
     */
    @Query("select e from StockHistory e where e.createdAt = :createdAt")
    List<StockHistory> findByCreatedAt(@Param("createdAt")Date createdAt);

    /**
     * Finds StockHistory by using productId as a search criteria.
     *
     * @param productId
     * @return An Object StockHistory whose productId is equals to the given productId. If
     *         no StockHistory is found, this method returns null.
     */
    @Query("select e from StockHistory e where e.products.id = :productId")
    List<StockHistory> findByProductId(@Param("productId")Integer productId);

  /**
   * Finds one StockHistory by using productId as a search criteria.
   *
   * @param productId
   * @return An Object StockHistory whose productId is equals to the given productId. If
   *         no StockHistory is found, this method returns null.
   */
  @Query("select e from StockHistory e where e.products.id = :productId")
  StockHistory findStockHistoryByProductId(@Param("productId")Integer productId);




    /**
     * Finds List of StockHistory by using stockHistoryDto as a search criteria.
     *
     * @param request, em
     * @return A List of StockHistory
     * @throws DataAccessException,ParseException
     */
    public default List<StockHistory> getByCriteria(Request<StockHistoryDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
        String req = "select e from StockHistory e where e IS NOT NULL";
        HashMap<String, Object> param = new HashMap<String, Object>();
        req += getWhereExpression(request, param, locale);
                TypedQuery<StockHistory> query = em.createQuery(req, StockHistory.class);
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
     * Finds count of StockHistory by using stockHistoryDto as a search criteria.
     *
     * @param request, em
     * @return Number of StockHistory
     *
     */
    public default Long count(Request<StockHistoryDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
        String req = "select count(e.id) from StockHistory e where e IS NOT NULL";
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
    default String getWhereExpression(Request<StockHistoryDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
        // main query
        StockHistoryDto dto = request.getData() != null ? request.getData() : new StockHistoryDto();
        String mainReq = generateCriteria(dto, param, 0, locale);
        // others query
        String othersReq = "";
        if (request.getDatas() != null && !request.getDatas().isEmpty()) {
            Integer index = 1;
            for (StockHistoryDto elt : request.getDatas()) {
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
        if (Direction.fromOptionalString(dto.getOrderDirection()).orElse(null) != null && Utilities.notBlank(dto.getOrderField())) {
            req += " group by  e.id";
            req += " order by e." + dto.getOrderField() + " " + dto.getOrderDirection();
        } else {
            req += " group by  e.id";
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
    default String generateCriteria(StockHistoryDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
        List<String> listOfQuery = new ArrayList<String>();
        if (dto != null) {
            if (dto.getId() != null || Utilities.searchParamIsNotEmpty(dto.getIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
            }
            if (dto.getOldQuantity() != null || Utilities.searchParamIsNotEmpty(dto.getOldQuantityParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("oldQuantity", dto.getOldQuantity(), "e.oldQuantity", "Integer", dto.getOldQuantityParam(), param, index, locale));
            }
            if (dto.getNewQuantity() != null || Utilities.searchParamIsNotEmpty(dto.getNewQuantityParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("newQuantity", dto.getNewQuantity(), "e.newQuantity", "Integer", dto.getNewQuantityParam(), param, index, locale));
            }
            if (dto.getChangeQuantity() != null || Utilities.searchParamIsNotEmpty(dto.getChangeQuantityParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("changeQuantity", dto.getChangeQuantity(), "e.changeQuantity", "Integer", dto.getChangeQuantityParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getReason()) || Utilities.searchParamIsNotEmpty(dto.getReasonParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("reason", dto.getReason(), "e.reason", "String", dto.getReasonParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getReferenceId()) || Utilities.searchParamIsNotEmpty(dto.getReferenceIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("referenceId", dto.getReferenceId(), "e.referenceId", "String", dto.getReferenceIdParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getCreatedAt()) || Utilities.searchParamIsNotEmpty(dto.getCreatedAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("createdAt", dto.getCreatedAt(), "e.createdAt", "Date", dto.getCreatedAtParam(), param, index, locale));
            }
                        if (dto.getProductId() != null || Utilities.searchParamIsNotEmpty(dto.getProductIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("productId", dto.getProductId(), "e.products.id", "Integer", dto.getProductIdParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getProductsName()) || Utilities.searchParamIsNotEmpty(dto.getProductsNameParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("productsName", dto.getProductsName(), "e.products.name", "String", dto.getProductsNameParam(), param, index, locale));
            }

            /*List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
            if (Utilities.isNotEmpty(listOfCustomQuery)) {
                listOfQuery.addAll(listOfCustomQuery);
            }*/
        }
        return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
    }
}
