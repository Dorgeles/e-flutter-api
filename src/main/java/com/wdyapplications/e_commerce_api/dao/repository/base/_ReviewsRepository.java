
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
 * Repository customize : Reviews.
 *
 * @author Dorgeddy
 *
 */
@Repository
public interface _ReviewsRepository {
	    /**
     * Finds Reviews by using id as a search criteria.
     *
     * @param id
     * @return An Object Reviews whose id is equals to the given id. If
     *         no Reviews is found, this method returns null.
     */
    @Query("select e from Reviews e where e.id = :id")
    Reviews findOne(@Param("id")Integer id);

    /**
     * Finds Reviews by using rating as a search criteria.
     *
     * @param rating
     * @return An Object Reviews whose rating is equals to the given rating. If
     *         no Reviews is found, this method returns null.
     */
    @Query("select e from Reviews e where e.rating = :rating")
    List<Reviews> findByRating(@Param("rating")Integer rating);
    /**
     * Finds Reviews by using comment as a search criteria.
     *
     * @param comment
     * @return An Object Reviews whose comment is equals to the given comment. If
     *         no Reviews is found, this method returns null.
     */
    @Query("select e from Reviews e where e.comment = :comment")
    List<Reviews> findByComment(@Param("comment")String comment);
    /**
     * Finds Reviews by using createdAt as a search criteria.
     *
     * @param createdAt
     * @return An Object Reviews whose createdAt is equals to the given createdAt. If
     *         no Reviews is found, this method returns null.
     */
    @Query("select e from Reviews e where e.createdAt = :createdAt")
    List<Reviews> findByCreatedAt(@Param("createdAt")Date createdAt);
    /**
     * Finds Reviews by using updatedAt as a search criteria.
     *
     * @param updatedAt
     * @return An Object Reviews whose updatedAt is equals to the given updatedAt. If
     *         no Reviews is found, this method returns null.
     */
    @Query("select e from Reviews e where e.updatedAt = :updatedAt")
    List<Reviews> findByUpdatedAt(@Param("updatedAt")Date updatedAt);
    /**
     * Finds Reviews by using isVerifiedPurchase as a search criteria.
     *
     * @param isVerifiedPurchase
     * @return An Object Reviews whose isVerifiedPurchase is equals to the given isVerifiedPurchase. If
     *         no Reviews is found, this method returns null.
     */
    @Query("select e from Reviews e where e.isVerifiedPurchase = :isVerifiedPurchase")
    List<Reviews> findByIsVerifiedPurchase(@Param("isVerifiedPurchase")Boolean isVerifiedPurchase);

    /**
     * Finds Reviews by using productId as a search criteria.
     *
     * @param productId
     * @return An Object Reviews whose productId is equals to the given productId. If
     *         no Reviews is found, this method returns null.
     */
    @Query("select e from Reviews e where e.products.id = :productId")
    List<Reviews> findByProductId(@Param("productId")Integer productId);

  /**
   * Finds one Reviews by using productId as a search criteria.
   *
   * @param productId
   * @return An Object Reviews whose productId is equals to the given productId. If
   *         no Reviews is found, this method returns null.
   */
  @Query("select e from Reviews e where e.products.id = :productId")
  Reviews findReviewsByProductId(@Param("productId")Integer productId);


    /**
     * Finds Reviews by using userId as a search criteria.
     *
     * @param userId
     * @return An Object Reviews whose userId is equals to the given userId. If
     *         no Reviews is found, this method returns null.
     */
    @Query("select e from Reviews e where e.users.id = :userId")
    List<Reviews> findByUserId(@Param("userId")Integer userId);

  /**
   * Finds one Reviews by using userId as a search criteria.
   *
   * @param userId
   * @return An Object Reviews whose userId is equals to the given userId. If
   *         no Reviews is found, this method returns null.
   */
  @Query("select e from Reviews e where e.users.id = :userId")
  Reviews findReviewsByUserId(@Param("userId")Integer userId);




    /**
     * Finds List of Reviews by using reviewsDto as a search criteria.
     *
     * @param request, em
     * @return A List of Reviews
     * @throws DataAccessException,ParseException
     */
    public default List<Reviews> getByCriteria(Request<ReviewsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
        String req = "select e from Reviews e where e IS NOT NULL";
        HashMap<String, Object> param = new HashMap<String, Object>();
        req += getWhereExpression(request, param, locale);
                TypedQuery<Reviews> query = em.createQuery(req, Reviews.class);
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
     * Finds count of Reviews by using reviewsDto as a search criteria.
     *
     * @param request, em
     * @return Number of Reviews
     *
     */
    public default Long count(Request<ReviewsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
        String req = "select count(e.id) from Reviews e where e IS NOT NULL";
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
    default String getWhereExpression(Request<ReviewsDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
        // main query
        ReviewsDto dto = request.getData() != null ? request.getData() : new ReviewsDto();
        String mainReq = generateCriteria(dto, param, 0, locale);
        // others query
        String othersReq = "";
        if (request.getDatas() != null && !request.getDatas().isEmpty()) {
            Integer index = 1;
            for (ReviewsDto elt : request.getDatas()) {
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
    default String generateCriteria(ReviewsDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
        List<String> listOfQuery = new ArrayList<String>();
        if (dto != null) {
            if (dto.getId() != null || Utilities.searchParamIsNotEmpty(dto.getIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
            }
            if (dto.getRating() != null || Utilities.searchParamIsNotEmpty(dto.getRatingParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("rating", dto.getRating(), "e.rating", "Integer", dto.getRatingParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getComment()) || Utilities.searchParamIsNotEmpty(dto.getCommentParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("comment", dto.getComment(), "e.comment", "String", dto.getCommentParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getCreatedAt()) || Utilities.searchParamIsNotEmpty(dto.getCreatedAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("createdAt", dto.getCreatedAt(), "e.createdAt", "Date", dto.getCreatedAtParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getUpdatedAt()) || Utilities.searchParamIsNotEmpty(dto.getUpdatedAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("updatedAt", dto.getUpdatedAt(), "e.updatedAt", "Date", dto.getUpdatedAtParam(), param, index, locale));
            }
            if (dto.getIsVerifiedPurchase() != null || Utilities.searchParamIsNotEmpty(dto.getIsVerifiedPurchaseParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("isVerifiedPurchase", dto.getIsVerifiedPurchase(), "e.isVerifiedPurchase", "Boolean", dto.getIsVerifiedPurchaseParam(), param, index, locale));
            }
                        if (dto.getProductId() != null || Utilities.searchParamIsNotEmpty(dto.getProductIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("productId", dto.getProductId(), "e.products.id", "Integer", dto.getProductIdParam(), param, index, locale));
            }
                        if (dto.getUserId() != null || Utilities.searchParamIsNotEmpty(dto.getUserIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("userId", dto.getUserId(), "e.users.id", "Integer", dto.getUserIdParam(), param, index, locale));
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
