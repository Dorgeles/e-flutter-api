
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
 * Repository customize : Products.
 *
 * @author Dorgeddy
 *
 */
@Repository
public interface _ProductsRepository {
	    /**
     * Finds Products by using id as a search criteria.
     *
     * @param id
     * @return An Object Products whose id is equals to the given id. If
     *         no Products is found, this method returns null.
     */
    @Query("select e from Products e where e.id = :id")
    Products findOne(@Param("id")Integer id);

    /**
     * Finds Products by using name as a search criteria.
     *
     * @param name
     * @return An Object Products whose name is equals to the given name. If
     *         no Products is found, this method returns null.
     */
    @Query("select e from Products e where e.name = :name")
    Products findByName(@Param("name")String name);
    /**
     * Finds Products by using description as a search criteria.
     *
     * @param description
     * @return An Object Products whose description is equals to the given description. If
     *         no Products is found, this method returns null.
     */
    @Query("select e from Products e where e.description = :description")
    List<Products> findByDescription(@Param("description")String description);
    /**
     * Finds Products by using price as a search criteria.
     *
     * @param price
     * @return An Object Products whose price is equals to the given price. If
     *         no Products is found, this method returns null.
     */
    @Query("select e from Products e where e.price = :price")
    List<Products> findByPrice(@Param("price")BigDecimal price);
    /**
     * Finds Products by using promoPrice as a search criteria.
     *
     * @param promoPrice
     * @return An Object Products whose promoPrice is equals to the given promoPrice. If
     *         no Products is found, this method returns null.
     */
    @Query("select e from Products e where e.promoPrice = :promoPrice")
    List<Products> findByPromoPrice(@Param("promoPrice")BigDecimal promoPrice);
    /**
     * Finds Products by using stockQuantity as a search criteria.
     *
     * @param stockQuantity
     * @return An Object Products whose stockQuantity is equals to the given stockQuantity. If
     *         no Products is found, this method returns null.
     */
    @Query("select e from Products e where e.stockQuantity = :stockQuantity")
    List<Products> findByStockQuantity(@Param("stockQuantity")Integer stockQuantity);
    /**
     * Finds Products by using minStockAlert as a search criteria.
     *
     * @param minStockAlert
     * @return An Object Products whose minStockAlert is equals to the given minStockAlert. If
     *         no Products is found, this method returns null.
     */
    @Query("select e from Products e where e.minStockAlert = :minStockAlert")
    List<Products> findByMinStockAlert(@Param("minStockAlert")Integer minStockAlert);
    /**
     * Finds Products by using status as a search criteria.
     *
     * @param status
     * @return An Object Products whose status is equals to the given status. If
     *         no Products is found, this method returns null.
     */
    @Query("select e from Products e where e.status = :status")
    List<Products> findByStatus(@Param("status")String status);
    /**
     * Finds Products by using averageRating as a search criteria.
     *
     * @param averageRating
     * @return An Object Products whose averageRating is equals to the given averageRating. If
     *         no Products is found, this method returns null.
     */
    @Query("select e from Products e where e.averageRating = :averageRating")
    List<Products> findByAverageRating(@Param("averageRating")BigDecimal averageRating);
    /**
     * Finds Products by using totalReviews as a search criteria.
     *
     * @param totalReviews
     * @return An Object Products whose totalReviews is equals to the given totalReviews. If
     *         no Products is found, this method returns null.
     */
    @Query("select e from Products e where e.totalReviews = :totalReviews")
    List<Products> findByTotalReviews(@Param("totalReviews")Integer totalReviews);
    /**
     * Finds Products by using totalSales as a search criteria.
     *
     * @param totalSales
     * @return An Object Products whose totalSales is equals to the given totalSales. If
     *         no Products is found, this method returns null.
     */
    @Query("select e from Products e where e.totalSales = :totalSales")
    List<Products> findByTotalSales(@Param("totalSales")Integer totalSales);
    /**
     * Finds Products by using isFeatured as a search criteria.
     *
     * @param isFeatured
     * @return An Object Products whose isFeatured is equals to the given isFeatured. If
     *         no Products is found, this method returns null.
     */
    @Query("select e from Products e where e.isFeatured = :isFeatured")
    List<Products> findByIsFeatured(@Param("isFeatured")Boolean isFeatured);
    /**
     * Finds Products by using isRecommended as a search criteria.
     *
     * @param isRecommended
     * @return An Object Products whose isRecommended is equals to the given isRecommended. If
     *         no Products is found, this method returns null.
     */
    @Query("select e from Products e where e.isRecommended = :isRecommended")
    List<Products> findByIsRecommended(@Param("isRecommended")Boolean isRecommended);
    /**
     * Finds Products by using createdAt as a search criteria.
     *
     * @param createdAt
     * @return An Object Products whose createdAt is equals to the given createdAt. If
     *         no Products is found, this method returns null.
     */
    @Query("select e from Products e where e.createdAt = :createdAt")
    List<Products> findByCreatedAt(@Param("createdAt")Date createdAt);
    /**
     * Finds Products by using updatedAt as a search criteria.
     *
     * @param updatedAt
     * @return An Object Products whose updatedAt is equals to the given updatedAt. If
     *         no Products is found, this method returns null.
     */
    @Query("select e from Products e where e.updatedAt = :updatedAt")
    List<Products> findByUpdatedAt(@Param("updatedAt")Date updatedAt);

    /**
     * Finds Products by using categoryId as a search criteria.
     *
     * @param categoryId
     * @return An Object Products whose categoryId is equals to the given categoryId. If
     *         no Products is found, this method returns null.
     */
    @Query("select e from Products e where e.categories.id = :categoryId")
    List<Products> findByCategoryId(@Param("categoryId")Integer categoryId);

  /**
   * Finds one Products by using categoryId as a search criteria.
   *
   * @param categoryId
   * @return An Object Products whose categoryId is equals to the given categoryId. If
   *         no Products is found, this method returns null.
   */
  @Query("select e from Products e where e.categories.id = :categoryId")
  Products findProductsByCategoryId(@Param("categoryId")Integer categoryId);


    /**
     * Finds Products by using sellerId as a search criteria.
     *
     * @param sellerId
     * @return An Object Products whose sellerId is equals to the given sellerId. If
     *         no Products is found, this method returns null.
     */
    @Query("select e from Products e where e.users.id = :sellerId")
    List<Products> findBySellerId(@Param("sellerId")Integer sellerId);

  /**
   * Finds one Products by using sellerId as a search criteria.
   *
   * @param sellerId
   * @return An Object Products whose sellerId is equals to the given sellerId. If
   *         no Products is found, this method returns null.
   */
  @Query("select e from Products e where e.users.id = :sellerId")
  Products findProductsBySellerId(@Param("sellerId")Integer sellerId);




    /**
     * Finds List of Products by using productsDto as a search criteria.
     *
     * @param request, em
     * @return A List of Products
     * @throws DataAccessException,ParseException
     */
    public default List<Products> getByCriteria(Request<ProductsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
        String req = "select e from Products e where e IS NOT NULL";
        HashMap<String, Object> param = new HashMap<String, Object>();
        req += getWhereExpression(request, param, locale);
                TypedQuery<Products> query = em.createQuery(req, Products.class);
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
     * Finds count of Products by using productsDto as a search criteria.
     *
     * @param request, em
     * @return Number of Products
     *
     */
    public default Long count(Request<ProductsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
        String req = "select count(e.id) from Products e where e IS NOT NULL";
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
    default String getWhereExpression(Request<ProductsDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
        // main query
        ProductsDto dto = request.getData() != null ? request.getData() : new ProductsDto();
        String mainReq = generateCriteria(dto, param, 0, locale);
        // others query
        String othersReq = "";
        if (request.getDatas() != null && !request.getDatas().isEmpty()) {
            Integer index = 1;
            for (ProductsDto elt : request.getDatas()) {
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
    default String generateCriteria(ProductsDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
        List<String> listOfQuery = new ArrayList<String>();
        if (dto != null) {
            if (dto.getId() != null || Utilities.searchParamIsNotEmpty(dto.getIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getName()) || Utilities.searchParamIsNotEmpty(dto.getNameParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("name", dto.getName(), "e.name", "String", dto.getNameParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getDescription()) || Utilities.searchParamIsNotEmpty(dto.getDescriptionParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("description", dto.getDescription(), "e.description", "String", dto.getDescriptionParam(), param, index, locale));
            }
            if (dto.getPrice() != null || Utilities.searchParamIsNotEmpty(dto.getPriceParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("price", dto.getPrice(), "e.price", "BigDecimal", dto.getPriceParam(), param, index, locale));
            }
            if (dto.getPromoPrice() != null || Utilities.searchParamIsNotEmpty(dto.getPromoPriceParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("promoPrice", dto.getPromoPrice(), "e.promoPrice", "BigDecimal", dto.getPromoPriceParam(), param, index, locale));
            }
            if (dto.getStockQuantity() != null || Utilities.searchParamIsNotEmpty(dto.getStockQuantityParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("stockQuantity", dto.getStockQuantity(), "e.stockQuantity", "Integer", dto.getStockQuantityParam(), param, index, locale));
            }
            if (dto.getMinStockAlert() != null || Utilities.searchParamIsNotEmpty(dto.getMinStockAlertParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("minStockAlert", dto.getMinStockAlert(), "e.minStockAlert", "Integer", dto.getMinStockAlertParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getStatus()) || Utilities.searchParamIsNotEmpty(dto.getStatusParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("status", dto.getStatus(), "e.status", "String", dto.getStatusParam(), param, index, locale));
            }
            if (dto.getAverageRating() != null || Utilities.searchParamIsNotEmpty(dto.getAverageRatingParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("averageRating", dto.getAverageRating(), "e.averageRating", "BigDecimal", dto.getAverageRatingParam(), param, index, locale));
            }
            if (dto.getTotalReviews() != null || Utilities.searchParamIsNotEmpty(dto.getTotalReviewsParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("totalReviews", dto.getTotalReviews(), "e.totalReviews", "Integer", dto.getTotalReviewsParam(), param, index, locale));
            }
            if (dto.getTotalSales() != null || Utilities.searchParamIsNotEmpty(dto.getTotalSalesParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("totalSales", dto.getTotalSales(), "e.totalSales", "Integer", dto.getTotalSalesParam(), param, index, locale));
            }
            if (dto.getIsFeatured() != null || Utilities.searchParamIsNotEmpty(dto.getIsFeaturedParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("isFeatured", dto.getIsFeatured(), "e.isFeatured", "Boolean", dto.getIsFeaturedParam(), param, index, locale));
            }
            if (dto.getIsRecommended() != null || Utilities.searchParamIsNotEmpty(dto.getIsRecommendedParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("isRecommended", dto.getIsRecommended(), "e.isRecommended", "Boolean", dto.getIsRecommendedParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getCreatedAt()) || Utilities.searchParamIsNotEmpty(dto.getCreatedAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("createdAt", dto.getCreatedAt(), "e.createdAt", "Date", dto.getCreatedAtParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getUpdatedAt()) || Utilities.searchParamIsNotEmpty(dto.getUpdatedAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("updatedAt", dto.getUpdatedAt(), "e.updatedAt", "Date", dto.getUpdatedAtParam(), param, index, locale));
            }
                        if (dto.getCategoryId() != null || Utilities.searchParamIsNotEmpty(dto.getCategoryIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("categoryId", dto.getCategoryId(), "e.categories.id", "Integer", dto.getCategoryIdParam(), param, index, locale));
            }
                        if (dto.getSellerId() != null || Utilities.searchParamIsNotEmpty(dto.getSellerIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("sellerId", dto.getSellerId(), "e.users.id", "Integer", dto.getSellerIdParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getCategoriesName()) || Utilities.searchParamIsNotEmpty(dto.getCategoriesNameParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("categoriesName", dto.getCategoriesName(), "e.categories.name", "String", dto.getCategoriesNameParam(), param, index, locale));
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
