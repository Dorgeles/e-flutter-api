
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
 * Repository customize : Carts.
 *
 * @author Dorgeddy
 *
 */
@Repository
public interface _CartsRepository {
	    /**
     * Finds Carts by using id as a search criteria.
     *
     * @param id
     * @return An Object Carts whose id is equals to the given id. If
     *         no Carts is found, this method returns null.
     */
    @Query("select e from Carts e where e.id = :id")
    Carts findOne(@Param("id")Integer id);

    /**
     * Finds Carts by using createdAt as a search criteria.
     *
     * @param createdAt
     * @return An Object Carts whose createdAt is equals to the given createdAt. If
     *         no Carts is found, this method returns null.
     */
    @Query("select e from Carts e where e.createdAt = :createdAt")
    List<Carts> findByCreatedAt(@Param("createdAt")Date createdAt);
    /**
     * Finds Carts by using updatedAt as a search criteria.
     *
     * @param updatedAt
     * @return An Object Carts whose updatedAt is equals to the given updatedAt. If
     *         no Carts is found, this method returns null.
     */
    @Query("select e from Carts e where e.updatedAt = :updatedAt")
    List<Carts> findByUpdatedAt(@Param("updatedAt")Date updatedAt);

    /**
     * Finds Carts by using userId as a search criteria.
     *
     * @param userId
     * @return An Object Carts whose userId is equals to the given userId. If
     *         no Carts is found, this method returns null.
     */
    @Query("select e from Carts e where e.users.id = :userId")
    List<Carts> findByUserId(@Param("userId")Integer userId);

  /**
   * Finds one Carts by using userId as a search criteria.
   *
   * @param userId
   * @return An Object Carts whose userId is equals to the given userId. If
   *         no Carts is found, this method returns null.
   */
  @Query("select e from Carts e where e.users.id = :userId")
  Carts findCartsByUserId(@Param("userId")Integer userId);




    /**
     * Finds List of Carts by using cartsDto as a search criteria.
     *
     * @param request, em
     * @return A List of Carts
     * @throws DataAccessException,ParseException
     */
    public default List<Carts> getByCriteria(Request<CartsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
        String req = "select e from Carts e where e IS NOT NULL";
        HashMap<String, Object> param = new HashMap<String, Object>();
        req += getWhereExpression(request, param, locale);
                TypedQuery<Carts> query = em.createQuery(req, Carts.class);
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
     * Finds count of Carts by using cartsDto as a search criteria.
     *
     * @param request, em
     * @return Number of Carts
     *
     */
    public default Long count(Request<CartsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
        String req = "select count(e.id) from Carts e where e IS NOT NULL";
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
    default String getWhereExpression(Request<CartsDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
        // main query
        CartsDto dto = request.getData() != null ? request.getData() : new CartsDto();
        String mainReq = generateCriteria(dto, param, 0, locale);
        // others query
        String othersReq = "";
        if (request.getDatas() != null && !request.getDatas().isEmpty()) {
            Integer index = 1;
            for (CartsDto elt : request.getDatas()) {
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
    default String generateCriteria(CartsDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
        List<String> listOfQuery = new ArrayList<String>();
        if (dto != null) {
            if (dto.getId() != null || Utilities.searchParamIsNotEmpty(dto.getIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getCreatedAt()) || Utilities.searchParamIsNotEmpty(dto.getCreatedAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("createdAt", dto.getCreatedAt(), "e.createdAt", "Date", dto.getCreatedAtParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getUpdatedAt()) || Utilities.searchParamIsNotEmpty(dto.getUpdatedAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("updatedAt", dto.getUpdatedAt(), "e.updatedAt", "Date", dto.getUpdatedAtParam(), param, index, locale));
            }
                        if (dto.getUserId() != null || Utilities.searchParamIsNotEmpty(dto.getUserIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("userId", dto.getUserId(), "e.users.id", "Integer", dto.getUserIdParam(), param, index, locale));
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
