
package com.wdyapplications.e_commerce_api.dao.repository.base;

import java.math.BigDecimal;
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
 * Repository customize : Orders.
 *
 * @author Dorgeddy
 *
 */
@Repository
public interface _OrdersRepository {
	    /**
     * Finds Orders by using id as a search criteria.
     *
     * @param id
     * @return An Object Orders whose id is equals to the given id. If
     *         no Orders is found, this method returns null.
     */
    @Query("select e from Orders e where e.id = :id")
    Orders findOne(@Param("id")Integer id);

    /**
     * Finds Orders by using orderNumber as a search criteria.
     *
     * @param orderNumber
     * @return An Object Orders whose orderNumber is equals to the given orderNumber. If
     *         no Orders is found, this method returns null.
     */
    @Query("select e from Orders e where e.orderNumber = :orderNumber")
    List<Orders> findByOrderNumber(@Param("orderNumber")String orderNumber);
    /**
     * Finds Orders by using status as a search criteria.
     *
     * @param status
     * @return An Object Orders whose status is equals to the given status. If
     *         no Orders is found, this method returns null.
     */
    @Query("select e from Orders e where e.status = :status")
    List<Orders> findByStatus(@Param("status")String status);
    /**
     * Finds Orders by using subtotal as a search criteria.
     *
     * @param subtotal
     * @return An Object Orders whose subtotal is equals to the given subtotal. If
     *         no Orders is found, this method returns null.
     */
    @Query("select e from Orders e where e.subtotal = :subtotal")
    List<Orders> findBySubtotal(@Param("subtotal")BigDecimal subtotal);
    /**
     * Finds Orders by using taxAmount as a search criteria.
     *
     * @param taxAmount
     * @return An Object Orders whose taxAmount is equals to the given taxAmount. If
     *         no Orders is found, this method returns null.
     */
    @Query("select e from Orders e where e.taxAmount = :taxAmount")
    List<Orders> findByTaxAmount(@Param("taxAmount")BigDecimal taxAmount);
    /**
     * Finds Orders by using shippingCost as a search criteria.
     *
     * @param shippingCost
     * @return An Object Orders whose shippingCost is equals to the given shippingCost. If
     *         no Orders is found, this method returns null.
     */
    @Query("select e from Orders e where e.shippingCost = :shippingCost")
    List<Orders> findByShippingCost(@Param("shippingCost")BigDecimal shippingCost);
    /**
     * Finds Orders by using discountAmount as a search criteria.
     *
     * @param discountAmount
     * @return An Object Orders whose discountAmount is equals to the given discountAmount. If
     *         no Orders is found, this method returns null.
     */
    @Query("select e from Orders e where e.discountAmount = :discountAmount")
    List<Orders> findByDiscountAmount(@Param("discountAmount")BigDecimal discountAmount);
    /**
     * Finds Orders by using totalAmount as a search criteria.
     *
     * @param totalAmount
     * @return An Object Orders whose totalAmount is equals to the given totalAmount. If
     *         no Orders is found, this method returns null.
     */
    @Query("select e from Orders e where e.totalAmount = :totalAmount")
    List<Orders> findByTotalAmount(@Param("totalAmount")BigDecimal totalAmount);
    /**
     * Finds Orders by using shippingAddress as a search criteria.
     *
     * @param shippingAddress
     * @return An Object Orders whose shippingAddress is equals to the given shippingAddress. If
     *         no Orders is found, this method returns null.
     */
    @Query("select e from Orders e where e.shippingAddress = :shippingAddress")
    List<Orders> findByShippingAddress(@Param("shippingAddress")String shippingAddress);
    /**
     * Finds Orders by using billingAddress as a search criteria.
     *
     * @param billingAddress
     * @return An Object Orders whose billingAddress is equals to the given billingAddress. If
     *         no Orders is found, this method returns null.
     */
    @Query("select e from Orders e where e.billingAddress = :billingAddress")
    List<Orders> findByBillingAddress(@Param("billingAddress")String billingAddress);
    /**
     * Finds Orders by using paymentMethod as a search criteria.
     *
     * @param paymentMethod
     * @return An Object Orders whose paymentMethod is equals to the given paymentMethod. If
     *         no Orders is found, this method returns null.
     */
    @Query("select e from Orders e where e.paymentMethod = :paymentMethod")
    List<Orders> findByPaymentMethod(@Param("paymentMethod")String paymentMethod);
    /**
     * Finds Orders by using paymentStatus as a search criteria.
     *
     * @param paymentStatus
     * @return An Object Orders whose paymentStatus is equals to the given paymentStatus. If
     *         no Orders is found, this method returns null.
     */
    @Query("select e from Orders e where e.paymentStatus = :paymentStatus")
    List<Orders> findByPaymentStatus(@Param("paymentStatus")String paymentStatus);
    /**
     * Finds Orders by using trackingNumber as a search criteria.
     *
     * @param trackingNumber
     * @return An Object Orders whose trackingNumber is equals to the given trackingNumber. If
     *         no Orders is found, this method returns null.
     */
    @Query("select e from Orders e where e.trackingNumber = :trackingNumber")
    List<Orders> findByTrackingNumber(@Param("trackingNumber")String trackingNumber);
    /**
     * Finds Orders by using createdAt as a search criteria.
     *
     * @param createdAt
     * @return An Object Orders whose createdAt is equals to the given createdAt. If
     *         no Orders is found, this method returns null.
     */
    @Query("select e from Orders e where e.createdAt = :createdAt")
    List<Orders> findByCreatedAt(@Param("createdAt")Date createdAt);
    /**
     * Finds Orders by using updatedAt as a search criteria.
     *
     * @param updatedAt
     * @return An Object Orders whose updatedAt is equals to the given updatedAt. If
     *         no Orders is found, this method returns null.
     */
    @Query("select e from Orders e where e.updatedAt = :updatedAt")
    List<Orders> findByUpdatedAt(@Param("updatedAt")Date updatedAt);
    /**
     * Finds Orders by using shippedAt as a search criteria.
     *
     * @param shippedAt
     * @return An Object Orders whose shippedAt is equals to the given shippedAt. If
     *         no Orders is found, this method returns null.
     */
    @Query("select e from Orders e where e.shippedAt = :shippedAt")
    List<Orders> findByShippedAt(@Param("shippedAt")Date shippedAt);
    /**
     * Finds Orders by using deliveredAt as a search criteria.
     *
     * @param deliveredAt
     * @return An Object Orders whose deliveredAt is equals to the given deliveredAt. If
     *         no Orders is found, this method returns null.
     */
    @Query("select e from Orders e where e.deliveredAt = :deliveredAt")
    List<Orders> findByDeliveredAt(@Param("deliveredAt")Date deliveredAt);

    /**
     * Finds Orders by using customerId as a search criteria.
     *
     * @param customerId
     * @return An Object Orders whose customerId is equals to the given customerId. If
     *         no Orders is found, this method returns null.
     */
    @Query("select e from Orders e where e.users.id = :customerId")
    List<Orders> findByCustomerId(@Param("customerId")Integer customerId);

  /**
   * Finds one Orders by using customerId as a search criteria.
   *
   * @param customerId
   * @return An Object Orders whose customerId is equals to the given customerId. If
   *         no Orders is found, this method returns null.
   */
  @Query("select e from Orders e where e.users.id = :customerId")
  Orders findOrdersByCustomerId(@Param("customerId")Integer customerId);




    /**
     * Finds List of Orders by using ordersDto as a search criteria.
     *
     * @param request, em
     * @return A List of Orders
     * @throws DataAccessException,ParseException
     */
    public default List<Orders> getByCriteria(Request<OrdersDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
        String req = "select e from Orders e where e IS NOT NULL";
        HashMap<String, Object> param = new HashMap<String, Object>();
        req += getWhereExpression(request, param, locale);
                TypedQuery<Orders> query = em.createQuery(req, Orders.class);
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
     * Finds count of Orders by using ordersDto as a search criteria.
     *
     * @param request, em
     * @return Number of Orders
     *
     */
    public default Long count(Request<OrdersDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
        String req = "select count(e.id) from Orders e where e IS NOT NULL";
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
    default String getWhereExpression(Request<OrdersDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
        // main query
        OrdersDto dto = request.getData() != null ? request.getData() : new OrdersDto();
        String mainReq = generateCriteria(dto, param, 0, locale);
        // others query
        String othersReq = "";
        if (request.getDatas() != null && !request.getDatas().isEmpty()) {
            Integer index = 1;
            for (OrdersDto elt : request.getDatas()) {
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
    default String generateCriteria(OrdersDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
        List<String> listOfQuery = new ArrayList<String>();
        if (dto != null) {
            if (dto.getId() != null || Utilities.searchParamIsNotEmpty(dto.getIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getOrderNumber()) || Utilities.searchParamIsNotEmpty(dto.getOrderNumberParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("orderNumber", dto.getOrderNumber(), "e.orderNumber", "String", dto.getOrderNumberParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getStatus()) || Utilities.searchParamIsNotEmpty(dto.getStatusParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("status", dto.getStatus(), "e.status", "String", dto.getStatusParam(), param, index, locale));
            }
            if (dto.getSubtotal() != null || Utilities.searchParamIsNotEmpty(dto.getSubtotalParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("subtotal", dto.getSubtotal(), "e.subtotal", "BigDecimal", dto.getSubtotalParam(), param, index, locale));
            }
            if (dto.getTaxAmount() != null || Utilities.searchParamIsNotEmpty(dto.getTaxAmountParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("taxAmount", dto.getTaxAmount(), "e.taxAmount", "BigDecimal", dto.getTaxAmountParam(), param, index, locale));
            }
            if (dto.getShippingCost() != null || Utilities.searchParamIsNotEmpty(dto.getShippingCostParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("shippingCost", dto.getShippingCost(), "e.shippingCost", "BigDecimal", dto.getShippingCostParam(), param, index, locale));
            }
            if (dto.getDiscountAmount() != null || Utilities.searchParamIsNotEmpty(dto.getDiscountAmountParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("discountAmount", dto.getDiscountAmount(), "e.discountAmount", "BigDecimal", dto.getDiscountAmountParam(), param, index, locale));
            }
            if (dto.getTotalAmount() != null || Utilities.searchParamIsNotEmpty(dto.getTotalAmountParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("totalAmount", dto.getTotalAmount(), "e.totalAmount", "BigDecimal", dto.getTotalAmountParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getShippingAddress()) || Utilities.searchParamIsNotEmpty(dto.getShippingAddressParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("shippingAddress", dto.getShippingAddress(), "e.shippingAddress", "String", dto.getShippingAddressParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getBillingAddress()) || Utilities.searchParamIsNotEmpty(dto.getBillingAddressParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("billingAddress", dto.getBillingAddress(), "e.billingAddress", "String", dto.getBillingAddressParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getPaymentMethod()) || Utilities.searchParamIsNotEmpty(dto.getPaymentMethodParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("paymentMethod", dto.getPaymentMethod(), "e.paymentMethod", "String", dto.getPaymentMethodParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getPaymentStatus()) || Utilities.searchParamIsNotEmpty(dto.getPaymentStatusParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("paymentStatus", dto.getPaymentStatus(), "e.paymentStatus", "String", dto.getPaymentStatusParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getTrackingNumber()) || Utilities.searchParamIsNotEmpty(dto.getTrackingNumberParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("trackingNumber", dto.getTrackingNumber(), "e.trackingNumber", "String", dto.getTrackingNumberParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getCreatedAt()) || Utilities.searchParamIsNotEmpty(dto.getCreatedAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("createdAt", dto.getCreatedAt(), "e.createdAt", "Date", dto.getCreatedAtParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getUpdatedAt()) || Utilities.searchParamIsNotEmpty(dto.getUpdatedAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("updatedAt", dto.getUpdatedAt(), "e.updatedAt", "Date", dto.getUpdatedAtParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getShippedAt()) || Utilities.searchParamIsNotEmpty(dto.getShippedAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("shippedAt", dto.getShippedAt(), "e.shippedAt", "Date", dto.getShippedAtParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getDeliveredAt()) || Utilities.searchParamIsNotEmpty(dto.getDeliveredAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("deliveredAt", dto.getDeliveredAt(), "e.deliveredAt", "Date", dto.getDeliveredAtParam(), param, index, locale));
            }
                        if (dto.getCustomerId() != null || Utilities.searchParamIsNotEmpty(dto.getCustomerIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("customerId", dto.getCustomerId(), "e.users.id", "Integer", dto.getCustomerIdParam(), param, index, locale));
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
