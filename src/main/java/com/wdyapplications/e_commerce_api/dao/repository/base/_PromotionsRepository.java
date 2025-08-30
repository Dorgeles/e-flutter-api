
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
 * Repository customize : Promotions.
 *
 * @author Dorgeddy
 *
 */
@Repository
public interface _PromotionsRepository {
	    /**
     * Finds Promotions by using id as a search criteria.
     *
     * @param id
     * @return An Object Promotions whose id is equals to the given id. If
     *         no Promotions is found, this method returns null.
     */
    @Query("select e from Promotions e where e.id = :id")
    Promotions findOne(@Param("id")Integer id);

    /**
     * Finds Promotions by using code as a search criteria.
     *
     * @param code
     * @return An Object Promotions whose code is equals to the given code. If
     *         no Promotions is found, this method returns null.
     */
    @Query("select e from Promotions e where e.code = :code")
    Promotions findByCode(@Param("code")String code);
    /**
     * Finds Promotions by using name as a search criteria.
     *
     * @param name
     * @return An Object Promotions whose name is equals to the given name. If
     *         no Promotions is found, this method returns null.
     */
    @Query("select e from Promotions e where e.name = :name")
    Promotions findByName(@Param("name")String name);
    /**
     * Finds Promotions by using description as a search criteria.
     *
     * @param description
     * @return An Object Promotions whose description is equals to the given description. If
     *         no Promotions is found, this method returns null.
     */
    @Query("select e from Promotions e where e.description = :description")
    List<Promotions> findByDescription(@Param("description")String description);
    /**
     * Finds Promotions by using type as a search criteria.
     *
     * @param type
     * @return An Object Promotions whose type is equals to the given type. If
     *         no Promotions is found, this method returns null.
     */
    @Query("select e from Promotions e where e.type = :type")
    List<Promotions> findByType(@Param("type")String type);
    /**
     * Finds Promotions by using value as a search criteria.
     *
     * @param value
     * @return An Object Promotions whose value is equals to the given value. If
     *         no Promotions is found, this method returns null.
     */
    @Query("select e from Promotions e where e.value = :value")
    List<Promotions> findByValue(@Param("value")BigDecimal value);
    /**
     * Finds Promotions by using minOrderAmount as a search criteria.
     *
     * @param minOrderAmount
     * @return An Object Promotions whose minOrderAmount is equals to the given minOrderAmount. If
     *         no Promotions is found, this method returns null.
     */
    @Query("select e from Promotions e where e.minOrderAmount = :minOrderAmount")
    List<Promotions> findByMinOrderAmount(@Param("minOrderAmount")BigDecimal minOrderAmount);
    /**
     * Finds Promotions by using maxUses as a search criteria.
     *
     * @param maxUses
     * @return An Object Promotions whose maxUses is equals to the given maxUses. If
     *         no Promotions is found, this method returns null.
     */
    @Query("select e from Promotions e where e.maxUses = :maxUses")
    List<Promotions> findByMaxUses(@Param("maxUses")Integer maxUses);
    /**
     * Finds Promotions by using usedCount as a search criteria.
     *
     * @param usedCount
     * @return An Object Promotions whose usedCount is equals to the given usedCount. If
     *         no Promotions is found, this method returns null.
     */
    @Query("select e from Promotions e where e.usedCount = :usedCount")
    List<Promotions> findByUsedCount(@Param("usedCount")Integer usedCount);
    /**
     * Finds Promotions by using startDate as a search criteria.
     *
     * @param startDate
     * @return An Object Promotions whose startDate is equals to the given startDate. If
     *         no Promotions is found, this method returns null.
     */
    @Query("select e from Promotions e where e.startDate = :startDate")
    List<Promotions> findByStartDate(@Param("startDate")Date startDate);
    /**
     * Finds Promotions by using endDate as a search criteria.
     *
     * @param endDate
     * @return An Object Promotions whose endDate is equals to the given endDate. If
     *         no Promotions is found, this method returns null.
     */
    @Query("select e from Promotions e where e.endDate = :endDate")
    List<Promotions> findByEndDate(@Param("endDate")Date endDate);
    /**
     * Finds Promotions by using isActive as a search criteria.
     *
     * @param isActive
     * @return An Object Promotions whose isActive is equals to the given isActive. If
     *         no Promotions is found, this method returns null.
     */
    @Query("select e from Promotions e where e.isActive = :isActive")
    List<Promotions> findByIsActive(@Param("isActive")Boolean isActive);
    /**
     * Finds Promotions by using createdAt as a search criteria.
     *
     * @param createdAt
     * @return An Object Promotions whose createdAt is equals to the given createdAt. If
     *         no Promotions is found, this method returns null.
     */
    @Query("select e from Promotions e where e.createdAt = :createdAt")
    List<Promotions> findByCreatedAt(@Param("createdAt")Date createdAt);
    /**
     * Finds Promotions by using updatedAt as a search criteria.
     *
     * @param updatedAt
     * @return An Object Promotions whose updatedAt is equals to the given updatedAt. If
     *         no Promotions is found, this method returns null.
     */
    @Query("select e from Promotions e where e.updatedAt = :updatedAt")
    List<Promotions> findByUpdatedAt(@Param("updatedAt")Date updatedAt);



    /**
     * Finds List of Promotions by using promotionsDto as a search criteria.
     *
     * @param request, em
     * @return A List of Promotions
     * @throws DataAccessException,ParseException
     */
    public default List<Promotions> getByCriteria(Request<PromotionsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
        String req = "select e from Promotions e where e IS NOT NULL";
        HashMap<String, Object> param = new HashMap<String, Object>();
        req += getWhereExpression(request, param, locale);
                TypedQuery<Promotions> query = em.createQuery(req, Promotions.class);
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
     * Finds count of Promotions by using promotionsDto as a search criteria.
     *
     * @param request, em
     * @return Number of Promotions
     *
     */
    public default Long count(Request<PromotionsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
        String req = "select count(e.id) from Promotions e where e IS NOT NULL";
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
    default String getWhereExpression(Request<PromotionsDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
        // main query
        PromotionsDto dto = request.getData() != null ? request.getData() : new PromotionsDto();
        String mainReq = generateCriteria(dto, param, 0, locale);
        // others query
        String othersReq = "";
        if (request.getDatas() != null && !request.getDatas().isEmpty()) {
            Integer index = 1;
            for (PromotionsDto elt : request.getDatas()) {
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
    default String generateCriteria(PromotionsDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
        List<String> listOfQuery = new ArrayList<String>();
        if (dto != null) {
            if (dto.getId() != null || Utilities.searchParamIsNotEmpty(dto.getIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getCode()) || Utilities.searchParamIsNotEmpty(dto.getCodeParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("code", dto.getCode(), "e.code", "String", dto.getCodeParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getName()) || Utilities.searchParamIsNotEmpty(dto.getNameParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("name", dto.getName(), "e.name", "String", dto.getNameParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getDescription()) || Utilities.searchParamIsNotEmpty(dto.getDescriptionParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("description", dto.getDescription(), "e.description", "String", dto.getDescriptionParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getType()) || Utilities.searchParamIsNotEmpty(dto.getTypeParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("type", dto.getType(), "e.type", "String", dto.getTypeParam(), param, index, locale));
            }
            if (dto.getValue() != null || Utilities.searchParamIsNotEmpty(dto.getValueParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("value", dto.getValue(), "e.value", "BigDecimal", dto.getValueParam(), param, index, locale));
            }
            if (dto.getMinOrderAmount() != null || Utilities.searchParamIsNotEmpty(dto.getMinOrderAmountParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("minOrderAmount", dto.getMinOrderAmount(), "e.minOrderAmount", "BigDecimal", dto.getMinOrderAmountParam(), param, index, locale));
            }
            if (dto.getMaxUses() != null || Utilities.searchParamIsNotEmpty(dto.getMaxUsesParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("maxUses", dto.getMaxUses(), "e.maxUses", "Integer", dto.getMaxUsesParam(), param, index, locale));
            }
            if (dto.getUsedCount() != null || Utilities.searchParamIsNotEmpty(dto.getUsedCountParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("usedCount", dto.getUsedCount(), "e.usedCount", "Integer", dto.getUsedCountParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getStartDate()) || Utilities.searchParamIsNotEmpty(dto.getStartDateParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("startDate", dto.getStartDate(), "e.startDate", "Date", dto.getStartDateParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getEndDate()) || Utilities.searchParamIsNotEmpty(dto.getEndDateParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("endDate", dto.getEndDate(), "e.endDate", "Date", dto.getEndDateParam(), param, index, locale));
            }
            if (dto.getIsActive() != null || Utilities.searchParamIsNotEmpty(dto.getIsActiveParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("isActive", dto.getIsActive(), "e.isActive", "Boolean", dto.getIsActiveParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getCreatedAt()) || Utilities.searchParamIsNotEmpty(dto.getCreatedAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("createdAt", dto.getCreatedAt(), "e.createdAt", "Date", dto.getCreatedAtParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getUpdatedAt()) || Utilities.searchParamIsNotEmpty(dto.getUpdatedAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("updatedAt", dto.getUpdatedAt(), "e.updatedAt", "Date", dto.getUpdatedAtParam(), param, index, locale));
            }

            /*List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
            if (Utilities.isNotEmpty(listOfCustomQuery)) {
                listOfQuery.addAll(listOfCustomQuery);
            }*/
        }
        return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
    }
}
