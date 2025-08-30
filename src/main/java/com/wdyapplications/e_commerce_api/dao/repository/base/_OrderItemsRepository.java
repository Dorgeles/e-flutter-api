
package com.wdyapplications.e_commerce_api.dao.repository.base;

import java.math.BigDecimal;
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
 * Repository customize : OrderItems.
 *
 * @author Dorgeddy
 *
 */
@Repository
public interface _OrderItemsRepository {
	    /**
     * Finds OrderItems by using id as a search criteria.
     *
     * @param id
     * @return An Object OrderItems whose id is equals to the given id. If
     *         no OrderItems is found, this method returns null.
     */
    @Query("select e from OrderItems e where e.id = :id")
    OrderItems findOne(@Param("id")Integer id);

    /**
     * Finds OrderItems by using quantity as a search criteria.
     *
     * @param quantity
     * @return An Object OrderItems whose quantity is equals to the given quantity. If
     *         no OrderItems is found, this method returns null.
     */
    @Query("select e from OrderItems e where e.quantity = :quantity")
    List<OrderItems> findByQuantity(@Param("quantity")Integer quantity);
    /**
     * Finds OrderItems by using unitPrice as a search criteria.
     *
     * @param unitPrice
     * @return An Object OrderItems whose unitPrice is equals to the given unitPrice. If
     *         no OrderItems is found, this method returns null.
     */
    @Query("select e from OrderItems e where e.unitPrice = :unitPrice")
    List<OrderItems> findByUnitPrice(@Param("unitPrice")BigDecimal unitPrice);
    /**
     * Finds OrderItems by using totalPrice as a search criteria.
     *
     * @param totalPrice
     * @return An Object OrderItems whose totalPrice is equals to the given totalPrice. If
     *         no OrderItems is found, this method returns null.
     */
    @Query("select e from OrderItems e where e.totalPrice = :totalPrice")
    List<OrderItems> findByTotalPrice(@Param("totalPrice")BigDecimal totalPrice);
    /**
     * Finds OrderItems by using status as a search criteria.
     *
     * @param status
     * @return An Object OrderItems whose status is equals to the given status. If
     *         no OrderItems is found, this method returns null.
     */
    @Query("select e from OrderItems e where e.status = :status")
    List<OrderItems> findByStatus(@Param("status")String status);

    /**
     * Finds OrderItems by using productId as a search criteria.
     *
     * @param productId
     * @return An Object OrderItems whose productId is equals to the given productId. If
     *         no OrderItems is found, this method returns null.
     */
    @Query("select e from OrderItems e where e.products.id = :productId")
    List<OrderItems> findByProductId(@Param("productId")Integer productId);

  /**
   * Finds one OrderItems by using productId as a search criteria.
   *
   * @param productId
   * @return An Object OrderItems whose productId is equals to the given productId. If
   *         no OrderItems is found, this method returns null.
   */
  @Query("select e from OrderItems e where e.products.id = :productId")
  OrderItems findOrderItemsByProductId(@Param("productId")Integer productId);


    /**
     * Finds OrderItems by using orderId as a search criteria.
     *
     * @param orderId
     * @return An Object OrderItems whose orderId is equals to the given orderId. If
     *         no OrderItems is found, this method returns null.
     */
    @Query("select e from OrderItems e where e.orders.id = :orderId")
    List<OrderItems> findByOrderId(@Param("orderId")Integer orderId);

  /**
   * Finds one OrderItems by using orderId as a search criteria.
   *
   * @param orderId
   * @return An Object OrderItems whose orderId is equals to the given orderId. If
   *         no OrderItems is found, this method returns null.
   */
  @Query("select e from OrderItems e where e.orders.id = :orderId")
  OrderItems findOrderItemsByOrderId(@Param("orderId")Integer orderId);


    /**
     * Finds OrderItems by using sellerId as a search criteria.
     *
     * @param sellerId
     * @return An Object OrderItems whose sellerId is equals to the given sellerId. If
     *         no OrderItems is found, this method returns null.
     */
    @Query("select e from OrderItems e where e.users.id = :sellerId")
    List<OrderItems> findBySellerId(@Param("sellerId")Integer sellerId);

  /**
   * Finds one OrderItems by using sellerId as a search criteria.
   *
   * @param sellerId
   * @return An Object OrderItems whose sellerId is equals to the given sellerId. If
   *         no OrderItems is found, this method returns null.
   */
  @Query("select e from OrderItems e where e.users.id = :sellerId")
  OrderItems findOrderItemsBySellerId(@Param("sellerId")Integer sellerId);




    /**
     * Finds List of OrderItems by using orderItemsDto as a search criteria.
     *
     * @param request, em
     * @return A List of OrderItems
     * @throws DataAccessException,ParseException
     */
    public default List<OrderItems> getByCriteria(Request<OrderItemsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
        String req = "select e from OrderItems e where e IS NOT NULL";
        HashMap<String, Object> param = new HashMap<String, Object>();
        req += getWhereExpression(request, param, locale);
                TypedQuery<OrderItems> query = em.createQuery(req, OrderItems.class);
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
     * Finds count of OrderItems by using orderItemsDto as a search criteria.
     *
     * @param request, em
     * @return Number of OrderItems
     *
     */
    public default Long count(Request<OrderItemsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
        String req = "select count(e.id) from OrderItems e where e IS NOT NULL";
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
    default String getWhereExpression(Request<OrderItemsDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
        // main query
        OrderItemsDto dto = request.getData() != null ? request.getData() : new OrderItemsDto();
        String mainReq = generateCriteria(dto, param, 0, locale);
        // others query
        String othersReq = "";
        if (request.getDatas() != null && !request.getDatas().isEmpty()) {
            Integer index = 1;
            for (OrderItemsDto elt : request.getDatas()) {
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
    default String generateCriteria(OrderItemsDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
        List<String> listOfQuery = new ArrayList<String>();
        if (dto != null) {
            if (dto.getId() != null || Utilities.searchParamIsNotEmpty(dto.getIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
            }
            if (dto.getQuantity() != null || Utilities.searchParamIsNotEmpty(dto.getQuantityParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("quantity", dto.getQuantity(), "e.quantity", "Integer", dto.getQuantityParam(), param, index, locale));
            }
            if (dto.getUnitPrice() != null || Utilities.searchParamIsNotEmpty(dto.getUnitPriceParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("unitPrice", dto.getUnitPrice(), "e.unitPrice", "BigDecimal", dto.getUnitPriceParam(), param, index, locale));
            }
            if (dto.getTotalPrice() != null || Utilities.searchParamIsNotEmpty(dto.getTotalPriceParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("totalPrice", dto.getTotalPrice(), "e.totalPrice", "BigDecimal", dto.getTotalPriceParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getStatus()) || Utilities.searchParamIsNotEmpty(dto.getStatusParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("status", dto.getStatus(), "e.status", "String", dto.getStatusParam(), param, index, locale));
            }
                        if (dto.getProductId() != null || Utilities.searchParamIsNotEmpty(dto.getProductIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("productId", dto.getProductId(), "e.products.id", "Integer", dto.getProductIdParam(), param, index, locale));
            }
                        if (dto.getOrderId() != null || Utilities.searchParamIsNotEmpty(dto.getOrderIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("orderId", dto.getOrderId(), "e.orders.id", "Integer", dto.getOrderIdParam(), param, index, locale));
            }
                        if (dto.getSellerId() != null || Utilities.searchParamIsNotEmpty(dto.getSellerIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("sellerId", dto.getSellerId(), "e.users.id", "Integer", dto.getSellerIdParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getProductsName()) || Utilities.searchParamIsNotEmpty(dto.getProductsNameParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("productsName", dto.getProductsName(), "e.products.name", "String", dto.getProductsNameParam(), param, index, locale));
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
