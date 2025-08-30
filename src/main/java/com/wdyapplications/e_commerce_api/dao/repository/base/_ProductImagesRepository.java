
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
 * Repository customize : ProductImages.
 *
 * @author Dorgeddy
 *
 */
@Repository
public interface _ProductImagesRepository {
	    /**
     * Finds ProductImages by using id as a search criteria.
     *
     * @param id
     * @return An Object ProductImages whose id is equals to the given id. If
     *         no ProductImages is found, this method returns null.
     */
    @Query("select e from ProductImages e where e.id = :id")
    ProductImages findOne(@Param("id")Integer id);

    /**
     * Finds ProductImages by using imageUrl as a search criteria.
     *
     * @param imageUrl
     * @return An Object ProductImages whose imageUrl is equals to the given imageUrl. If
     *         no ProductImages is found, this method returns null.
     */
    @Query("select e from ProductImages e where e.imageUrl = :imageUrl")
    List<ProductImages> findByImageUrl(@Param("imageUrl")String imageUrl);
    /**
     * Finds ProductImages by using altText as a search criteria.
     *
     * @param altText
     * @return An Object ProductImages whose altText is equals to the given altText. If
     *         no ProductImages is found, this method returns null.
     */
    @Query("select e from ProductImages e where e.altText = :altText")
    List<ProductImages> findByAltText(@Param("altText")String altText);
    /**
     * Finds ProductImages by using isPrimary as a search criteria.
     *
     * @param isPrimary
     * @return An Object ProductImages whose isPrimary is equals to the given isPrimary. If
     *         no ProductImages is found, this method returns null.
     */
    @Query("select e from ProductImages e where e.isPrimary = :isPrimary")
    List<ProductImages> findByIsPrimary(@Param("isPrimary")Boolean isPrimary);
    /**
     * Finds ProductImages by using sortOrder as a search criteria.
     *
     * @param sortOrder
     * @return An Object ProductImages whose sortOrder is equals to the given sortOrder. If
     *         no ProductImages is found, this method returns null.
     */
    @Query("select e from ProductImages e where e.sortOrder = :sortOrder")
    List<ProductImages> findBySortOrder(@Param("sortOrder")Integer sortOrder);
    /**
     * Finds ProductImages by using uploadedAt as a search criteria.
     *
     * @param uploadedAt
     * @return An Object ProductImages whose uploadedAt is equals to the given uploadedAt. If
     *         no ProductImages is found, this method returns null.
     */
    @Query("select e from ProductImages e where e.uploadedAt = :uploadedAt")
    List<ProductImages> findByUploadedAt(@Param("uploadedAt")Date uploadedAt);

    /**
     * Finds ProductImages by using productId as a search criteria.
     *
     * @param productId
     * @return An Object ProductImages whose productId is equals to the given productId. If
     *         no ProductImages is found, this method returns null.
     */
    @Query("select e from ProductImages e where e.products.id = :productId")
    List<ProductImages> findByProductId(@Param("productId")Integer productId);

  /**
   * Finds one ProductImages by using productId as a search criteria.
   *
   * @param productId
   * @return An Object ProductImages whose productId is equals to the given productId. If
   *         no ProductImages is found, this method returns null.
   */
  @Query("select e from ProductImages e where e.products.id = :productId")
  ProductImages findProductImagesByProductId(@Param("productId")Integer productId);




    /**
     * Finds List of ProductImages by using productImagesDto as a search criteria.
     *
     * @param request, em
     * @return A List of ProductImages
     * @throws DataAccessException,ParseException
     */
    public default List<ProductImages> getByCriteria(Request<ProductImagesDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
        String req = "select e from ProductImages e where e IS NOT NULL";
        HashMap<String, Object> param = new HashMap<String, Object>();
        req += getWhereExpression(request, param, locale);
                TypedQuery<ProductImages> query = em.createQuery(req, ProductImages.class);
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
     * Finds count of ProductImages by using productImagesDto as a search criteria.
     *
     * @param request, em
     * @return Number of ProductImages
     *
     */
    public default Long count(Request<ProductImagesDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
        String req = "select count(e.id) from ProductImages e where e IS NOT NULL";
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
    default String getWhereExpression(Request<ProductImagesDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
        // main query
        ProductImagesDto dto = request.getData() != null ? request.getData() : new ProductImagesDto();
        String mainReq = generateCriteria(dto, param, 0, locale);
        // others query
        String othersReq = "";
        if (request.getDatas() != null && !request.getDatas().isEmpty()) {
            Integer index = 1;
            for (ProductImagesDto elt : request.getDatas()) {
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
    default String generateCriteria(ProductImagesDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
        List<String> listOfQuery = new ArrayList<String>();
        if (dto != null) {
            if (dto.getId() != null || Utilities.searchParamIsNotEmpty(dto.getIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getImageUrl()) || Utilities.searchParamIsNotEmpty(dto.getImageUrlParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("imageUrl", dto.getImageUrl(), "e.imageUrl", "String", dto.getImageUrlParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getAltText()) || Utilities.searchParamIsNotEmpty(dto.getAltTextParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("altText", dto.getAltText(), "e.altText", "String", dto.getAltTextParam(), param, index, locale));
            }
            if (dto.getIsPrimary() != null || Utilities.searchParamIsNotEmpty(dto.getIsPrimaryParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("isPrimary", dto.getIsPrimary(), "e.isPrimary", "Boolean", dto.getIsPrimaryParam(), param, index, locale));
            }
            if (dto.getSortOrder() != null || Utilities.searchParamIsNotEmpty(dto.getSortOrderParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("sortOrder", dto.getSortOrder(), "e.sortOrder", "Integer", dto.getSortOrderParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getUploadedAt()) || Utilities.searchParamIsNotEmpty(dto.getUploadedAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("uploadedAt", dto.getUploadedAt(), "e.uploadedAt", "Date", dto.getUploadedAtParam(), param, index, locale));
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
