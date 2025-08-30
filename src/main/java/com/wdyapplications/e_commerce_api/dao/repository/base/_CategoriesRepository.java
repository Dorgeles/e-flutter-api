
package com.wdyapplications.e_commerce_api.dao.repository.base;

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
 * Repository customize : Categories.
 *
 * @author Dorgeddy
 *
 */
@Repository
public interface _CategoriesRepository {
	    /**
     * Finds Categories by using id as a search criteria.
     *
     * @param id
     * @return An Object Categories whose id is equals to the given id. If
     *         no Categories is found, this method returns null.
     */
    @Query("select e from Categories e where e.id = :id")
    Categories findOne(@Param("id")Integer id);

    /**
     * Finds Categories by using name as a search criteria.
     *
     * @param name
     * @return An Object Categories whose name is equals to the given name. If
     *         no Categories is found, this method returns null.
     */
    @Query("select e from Categories e where e.name = :name")
    Categories findByName(@Param("name")String name);
    /**
     * Finds Categories by using description as a search criteria.
     *
     * @param description
     * @return An Object Categories whose description is equals to the given description. If
     *         no Categories is found, this method returns null.
     */
    @Query("select e from Categories e where e.description = :description")
    List<Categories> findByDescription(@Param("description")String description);
    /**
     * Finds Categories by using iconUrl as a search criteria.
     *
     * @param iconUrl
     * @return An Object Categories whose iconUrl is equals to the given iconUrl. If
     *         no Categories is found, this method returns null.
     */
    @Query("select e from Categories e where e.iconUrl = :iconUrl")
    List<Categories> findByIconUrl(@Param("iconUrl")String iconUrl);
    /**
     * Finds Categories by using isActive as a search criteria.
     *
     * @param isActive
     * @return An Object Categories whose isActive is equals to the given isActive. If
     *         no Categories is found, this method returns null.
     */
    @Query("select e from Categories e where e.isActive = :isActive")
    List<Categories> findByIsActive(@Param("isActive")Boolean isActive);
    /**
     * Finds Categories by using sortOrder as a search criteria.
     *
     * @param sortOrder
     * @return An Object Categories whose sortOrder is equals to the given sortOrder. If
     *         no Categories is found, this method returns null.
     */
    @Query("select e from Categories e where e.sortOrder = :sortOrder")
    List<Categories> findBySortOrder(@Param("sortOrder")Integer sortOrder);
    /**
     * Finds Categories by using createdAt as a search criteria.
     *
     * @param createdAt
     * @return An Object Categories whose createdAt is equals to the given createdAt. If
     *         no Categories is found, this method returns null.
     */
    @Query("select e from Categories e where e.createdAt = :createdAt")
    List<Categories> findByCreatedAt(@Param("createdAt")Date createdAt);
    /**
     * Finds Categories by using updatedAt as a search criteria.
     *
     * @param updatedAt
     * @return An Object Categories whose updatedAt is equals to the given updatedAt. If
     *         no Categories is found, this method returns null.
     */
    @Query("select e from Categories e where e.updatedAt = :updatedAt")
    List<Categories> findByUpdatedAt(@Param("updatedAt")Date updatedAt);

    /**
     * Finds Categories by using parentId as a search criteria.
     *
     * @param parentId
     * @return An Object Categories whose parentId is equals to the given parentId. If
     *         no Categories is found, this method returns null.
     */
    @Query("select e from Categories e where e.categories.id = :parentId")
    List<Categories> findByParentId(@Param("parentId")Integer parentId);

  /**
   * Finds one Categories by using parentId as a search criteria.
   *
   * @param parentId
   * @return An Object Categories whose parentId is equals to the given parentId. If
   *         no Categories is found, this method returns null.
   */
  @Query("select e from Categories e where e.categories.id = :parentId")
  Categories findCategoriesByParentId(@Param("parentId")Integer parentId);




    /**
     * Finds List of Categories by using categoriesDto as a search criteria.
     *
     * @param request, em
     * @return A List of Categories
     * @throws DataAccessException,ParseException
     */
    public default List<Categories> getByCriteria(Request<CategoriesDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
        String req = "select e from Categories e where e IS NOT NULL";
        HashMap<String, Object> param = new HashMap<String, Object>();
        req += getWhereExpression(request, param, locale);
                TypedQuery<Categories> query = em.createQuery(req, Categories.class);
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
     * Finds count of Categories by using categoriesDto as a search criteria.
     *
     * @param request, em
     * @return Number of Categories
     *
     */
    public default Long count(Request<CategoriesDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
        String req = "select count(e.id) from Categories e where e IS NOT NULL";
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
    default String getWhereExpression(Request<CategoriesDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
        // main query
        CategoriesDto dto = request.getData() != null ? request.getData() : new CategoriesDto();
        String mainReq = generateCriteria(dto, param, 0, locale);
        // others query
        String othersReq = "";
        if (request.getDatas() != null && !request.getDatas().isEmpty()) {
            Integer index = 1;
            for (CategoriesDto elt : request.getDatas()) {
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
    default String generateCriteria(CategoriesDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
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
            if (Utilities.isNotBlank(dto.getIconUrl()) || Utilities.searchParamIsNotEmpty(dto.getIconUrlParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("iconUrl", dto.getIconUrl(), "e.iconUrl", "String", dto.getIconUrlParam(), param, index, locale));
            }
            if (dto.getIsActive() != null || Utilities.searchParamIsNotEmpty(dto.getIsActiveParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("isActive", dto.getIsActive(), "e.isActive", "Boolean", dto.getIsActiveParam(), param, index, locale));
            }
            if (dto.getSortOrder() != null || Utilities.searchParamIsNotEmpty(dto.getSortOrderParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("sortOrder", dto.getSortOrder(), "e.sortOrder", "Integer", dto.getSortOrderParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getCreatedAt()) || Utilities.searchParamIsNotEmpty(dto.getCreatedAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("createdAt", dto.getCreatedAt(), "e.createdAt", "Date", dto.getCreatedAtParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getUpdatedAt()) || Utilities.searchParamIsNotEmpty(dto.getUpdatedAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("updatedAt", dto.getUpdatedAt(), "e.updatedAt", "Date", dto.getUpdatedAtParam(), param, index, locale));
            }
                        if (dto.getParentId() != null || Utilities.searchParamIsNotEmpty(dto.getParentIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("parentId", dto.getParentId(), "e.categories.id", "Integer", dto.getParentIdParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getCategoriesName()) || Utilities.searchParamIsNotEmpty(dto.getCategoriesNameParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("categoriesName", dto.getCategoriesName(), "e.categories.name", "String", dto.getCategoriesNameParam(), param, index, locale));
            }

            /*List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
            if (Utilities.isNotEmpty(listOfCustomQuery)) {
                listOfQuery.addAll(listOfCustomQuery);
            }*/
        }
        return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
    }
}
