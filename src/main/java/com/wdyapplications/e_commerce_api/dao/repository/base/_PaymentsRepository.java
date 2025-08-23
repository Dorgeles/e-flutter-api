
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
 * Repository customize : Payments.
 *
 * @author Dorgeddy
 *
 */
@Repository
public interface _PaymentsRepository {
	    /**
     * Finds Payments by using id as a search criteria.
     *
     * @param id
     * @return An Object Payments whose id is equals to the given id. If
     *         no Payments is found, this method returns null.
     */
    @Query("select e from Payments e where e.id = :id")
    Payments findOne(@Param("id")Integer id);

    /**
     * Finds Payments by using paymentMethod as a search criteria.
     *
     * @param paymentMethod
     * @return An Object Payments whose paymentMethod is equals to the given paymentMethod. If
     *         no Payments is found, this method returns null.
     */
    @Query("select e from Payments e where e.paymentMethod = :paymentMethod")
    List<Payments> findByPaymentMethod(@Param("paymentMethod")String paymentMethod);
    /**
     * Finds Payments by using amount as a search criteria.
     *
     * @param amount
     * @return An Object Payments whose amount is equals to the given amount. If
     *         no Payments is found, this method returns null.
     */
    @Query("select e from Payments e where e.amount = :amount")
    List<Payments> findByAmount(@Param("amount")BigDecimal amount);
    /**
     * Finds Payments by using currency as a search criteria.
     *
     * @param currency
     * @return An Object Payments whose currency is equals to the given currency. If
     *         no Payments is found, this method returns null.
     */
    @Query("select e from Payments e where e.currency = :currency")
    List<Payments> findByCurrency(@Param("currency")String currency);
    /**
     * Finds Payments by using status as a search criteria.
     *
     * @param status
     * @return An Object Payments whose status is equals to the given status. If
     *         no Payments is found, this method returns null.
     */
    @Query("select e from Payments e where e.status = :status")
    List<Payments> findByStatus(@Param("status")String status);
    /**
     * Finds Payments by using transactionId as a search criteria.
     *
     * @param transactionId
     * @return An Object Payments whose transactionId is equals to the given transactionId. If
     *         no Payments is found, this method returns null.
     */
    @Query("select e from Payments e where e.transactionId = :transactionId")
    List<Payments> findByTransactionId(@Param("transactionId")String transactionId);
    /**
     * Finds Payments by using gatewayResponse as a search criteria.
     *
     * @param gatewayResponse
     * @return An Object Payments whose gatewayResponse is equals to the given gatewayResponse. If
     *         no Payments is found, this method returns null.
     */
    @Query("select e from Payments e where e.gatewayResponse = :gatewayResponse")
    List<Payments> findByGatewayResponse(@Param("gatewayResponse")String gatewayResponse);
    /**
     * Finds Payments by using createdAt as a search criteria.
     *
     * @param createdAt
     * @return An Object Payments whose createdAt is equals to the given createdAt. If
     *         no Payments is found, this method returns null.
     */
    @Query("select e from Payments e where e.createdAt = :createdAt")
    List<Payments> findByCreatedAt(@Param("createdAt")Date createdAt);
    /**
     * Finds Payments by using updatedAt as a search criteria.
     *
     * @param updatedAt
     * @return An Object Payments whose updatedAt is equals to the given updatedAt. If
     *         no Payments is found, this method returns null.
     */
    @Query("select e from Payments e where e.updatedAt = :updatedAt")
    List<Payments> findByUpdatedAt(@Param("updatedAt")Date updatedAt);

    /**
     * Finds Payments by using orderId as a search criteria.
     *
     * @param orderId
     * @return An Object Payments whose orderId is equals to the given orderId. If
     *         no Payments is found, this method returns null.
     */
    @Query("select e from Payments e where e.orders.id = :orderId")
    List<Payments> findByOrderId(@Param("orderId")Integer orderId);

  /**
   * Finds one Payments by using orderId as a search criteria.
   *
   * @param orderId
   * @return An Object Payments whose orderId is equals to the given orderId. If
   *         no Payments is found, this method returns null.
   */
  @Query("select e from Payments e where e.orders.id = :orderId")
  Payments findPaymentsByOrderId(@Param("orderId")Integer orderId);




    /**
     * Finds List of Payments by using paymentsDto as a search criteria.
     *
     * @param request, em
     * @return A List of Payments
     * @throws DataAccessException,ParseException
     */
    public default List<Payments> getByCriteria(Request<PaymentsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
        String req = "select e from Payments e where e IS NOT NULL";
        HashMap<String, Object> param = new HashMap<String, Object>();
        req += getWhereExpression(request, param, locale);
                TypedQuery<Payments> query = em.createQuery(req, Payments.class);
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
     * Finds count of Payments by using paymentsDto as a search criteria.
     *
     * @param request, em
     * @return Number of Payments
     *
     */
    public default Long count(Request<PaymentsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
        String req = "select count(e.id) from Payments e where e IS NOT NULL";
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
    default String getWhereExpression(Request<PaymentsDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
        // main query
        PaymentsDto dto = request.getData() != null ? request.getData() : new PaymentsDto();
        String mainReq = generateCriteria(dto, param, 0, locale);
        // others query
        String othersReq = "";
        if (request.getDatas() != null && !request.getDatas().isEmpty()) {
            Integer index = 1;
            for (PaymentsDto elt : request.getDatas()) {
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
    default String generateCriteria(PaymentsDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
        List<String> listOfQuery = new ArrayList<String>();
        if (dto != null) {
            if (dto.getId() != null || Utilities.searchParamIsNotEmpty(dto.getIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getPaymentMethod()) || Utilities.searchParamIsNotEmpty(dto.getPaymentMethodParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("paymentMethod", dto.getPaymentMethod(), "e.paymentMethod", "String", dto.getPaymentMethodParam(), param, index, locale));
            }
            if (dto.getAmount() != null || Utilities.searchParamIsNotEmpty(dto.getAmountParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("amount", dto.getAmount(), "e.amount", "BigDecimal", dto.getAmountParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getCurrency()) || Utilities.searchParamIsNotEmpty(dto.getCurrencyParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("currency", dto.getCurrency(), "e.currency", "String", dto.getCurrencyParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getStatus()) || Utilities.searchParamIsNotEmpty(dto.getStatusParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("status", dto.getStatus(), "e.status", "String", dto.getStatusParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getTransactionId()) || Utilities.searchParamIsNotEmpty(dto.getTransactionIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("transactionId", dto.getTransactionId(), "e.transactionId", "String", dto.getTransactionIdParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getGatewayResponse()) || Utilities.searchParamIsNotEmpty(dto.getGatewayResponseParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("gatewayResponse", dto.getGatewayResponse(), "e.gatewayResponse", "String", dto.getGatewayResponseParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getCreatedAt()) || Utilities.searchParamIsNotEmpty(dto.getCreatedAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("createdAt", dto.getCreatedAt(), "e.createdAt", "Date", dto.getCreatedAtParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getUpdatedAt()) || Utilities.searchParamIsNotEmpty(dto.getUpdatedAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("updatedAt", dto.getUpdatedAt(), "e.updatedAt", "Date", dto.getUpdatedAtParam(), param, index, locale));
            }
                        if (dto.getOrderId() != null || Utilities.searchParamIsNotEmpty(dto.getOrderIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("orderId", dto.getOrderId(), "e.orders.id", "Integer", dto.getOrderIdParam(), param, index, locale));
            }

            /*List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
            if (Utilities.isNotEmpty(listOfCustomQuery)) {
                listOfQuery.addAll(listOfCustomQuery);
            }*/
        }
        return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
    }
}
