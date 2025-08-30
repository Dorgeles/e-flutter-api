
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
 * Repository customize : CartItems.
 *
 * @author Dorgeddy
 *
 */
@Repository
public interface _CartItemsRepository {
	    /**
     * Finds CartItems by using id as a search criteria.
     *
     * @param id
     * @return An Object CartItems whose id is equals to the given id. If
     *         no CartItems is found, this method returns null.
     */
    @Query("select e from CartItems e where e.id = :id")
    CartItems findOne(@Param("id")Integer id);

    /**
     * Finds CartItems by using quantity as a search criteria.
     *
     * @param quantity
     * @return An Object CartItems whose quantity is equals to the given quantity. If
     *         no CartItems is found, this method returns null.
     */
    @Query("select e from CartItems e where e.quantity = :quantity")
    List<CartItems> findByQuantity(@Param("quantity")Integer quantity);
    /**
     * Finds CartItems by using unitPrice as a search criteria.
     *
     * @param unitPrice
     * @return An Object CartItems whose unitPrice is equals to the given unitPrice. If
     *         no CartItems is found, this method returns null.
     */
    @Query("select e from CartItems e where e.unitPrice = :unitPrice")
    List<CartItems> findByUnitPrice(@Param("unitPrice")BigDecimal unitPrice);
    /**
     * Finds CartItems by using addedAt as a search criteria.
     *
     * @param addedAt
     * @return An Object CartItems whose addedAt is equals to the given addedAt. If
     *         no CartItems is found, this method returns null.
     */
    @Query("select e from CartItems e where e.addedAt = :addedAt")
    List<CartItems> findByAddedAt(@Param("addedAt")Date addedAt);
    /**
     * Finds CartItems by using updatedAt as a search criteria.
     *
     * @param updatedAt
     * @return An Object CartItems whose updatedAt is equals to the given updatedAt. If
     *         no CartItems is found, this method returns null.
     */
    @Query("select e from CartItems e where e.updatedAt = :updatedAt")
    List<CartItems> findByUpdatedAt(@Param("updatedAt")Date updatedAt);

    /**
     * Finds CartItems by using productId as a search criteria.
     *
     * @param productId
     * @return An Object CartItems whose productId is equals to the given productId. If
     *         no CartItems is found, this method returns null.
     */
    @Query("select e from CartItems e where e.products.id = :productId")
    List<CartItems> findByProductId(@Param("productId")Integer productId);

  /**
   * Finds one CartItems by using productId as a search criteria.
   *
   * @param productId
   * @return An Object CartItems whose productId is equals to the given productId. If
   *         no CartItems is found, this method returns null.
   */
  @Query("select e from CartItems e where e.products.id = :productId")
  CartItems findCartItemsByProductId(@Param("productId")Integer productId);


    /**
     * Finds CartItems by using cartId as a search criteria.
     *
     * @param cartId
     * @return An Object CartItems whose cartId is equals to the given cartId. If
     *         no CartItems is found, this method returns null.
     */
    @Query("select e from CartItems e where e.carts.id = :cartId")
    List<CartItems> findByCartId(@Param("cartId")Integer cartId);

  /**
   * Finds one CartItems by using cartId as a search criteria.
   *
   * @param cartId
   * @return An Object CartItems whose cartId is equals to the given cartId. If
   *         no CartItems is found, this method returns null.
   */
  @Query("select e from CartItems e where e.carts.id = :cartId")
  CartItems findCartItemsByCartId(@Param("cartId")Integer cartId);




    /**
     * Finds List of CartItems by using cartItemsDto as a search criteria.
     *
     * @param request, em
     * @return A List of CartItems
     * @throws DataAccessException,ParseException
     */
    public default List<CartItems> getByCriteria(Request<CartItemsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
        String req = "select e from CartItems e where e IS NOT NULL";
        HashMap<String, Object> param = new HashMap<String, Object>();
        req += getWhereExpression(request, param, locale);
                TypedQuery<CartItems> query = em.createQuery(req, CartItems.class);
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
     * Finds count of CartItems by using cartItemsDto as a search criteria.
     *
     * @param request, em
     * @return Number of CartItems
     *
     */
    public default Long count(Request<CartItemsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
        String req = "select count(e.id) from CartItems e where e IS NOT NULL";
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
    default String getWhereExpression(Request<CartItemsDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
        // main query
        CartItemsDto dto = request.getData() != null ? request.getData() : new CartItemsDto();
        String mainReq = generateCriteria(dto, param, 0, locale);
        // others query
        String othersReq = "";
        if (request.getDatas() != null && !request.getDatas().isEmpty()) {
            Integer index = 1;
            for (CartItemsDto elt : request.getDatas()) {
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
    default String generateCriteria(CartItemsDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
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
            if (Utilities.isNotBlank(dto.getAddedAt()) || Utilities.searchParamIsNotEmpty(dto.getAddedAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("addedAt", dto.getAddedAt(), "e.addedAt", "Date", dto.getAddedAtParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getUpdatedAt()) || Utilities.searchParamIsNotEmpty(dto.getUpdatedAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("updatedAt", dto.getUpdatedAt(), "e.updatedAt", "Date", dto.getUpdatedAtParam(), param, index, locale));
            }
                        if (dto.getProductId() != null || Utilities.searchParamIsNotEmpty(dto.getProductIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("productId", dto.getProductId(), "e.products.id", "Integer", dto.getProductIdParam(), param, index, locale));
            }
                        if (dto.getCartId() != null || Utilities.searchParamIsNotEmpty(dto.getCartIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("cartId", dto.getCartId(), "e.carts.id", "Integer", dto.getCartIdParam(), param, index, locale));
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
