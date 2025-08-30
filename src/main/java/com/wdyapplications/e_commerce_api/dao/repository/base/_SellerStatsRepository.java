
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
 * Repository customize : SellerStats.
 *
 * @author Dorgeddy
 *
 */
@Repository
public interface _SellerStatsRepository {
	    /**
     * Finds SellerStats by using id as a search criteria.
     *
     * @param id
     * @return An Object SellerStats whose id is equals to the given id. If
     *         no SellerStats is found, this method returns null.
     */
    @Query("select e from SellerStats e where e.id = :id")
    SellerStats findOne(@Param("id")Integer id);

    /**
     * Finds SellerStats by using year as a search criteria.
     *
     * @param year
     * @return An Object SellerStats whose year is equals to the given year. If
     *         no SellerStats is found, this method returns null.
     */
    @Query("select e from SellerStats e where e.year = :year")
    List<SellerStats> findByYear(@Param("year")Integer year);
    /**
     * Finds SellerStats by using month as a search criteria.
     *
     * @param month
     * @return An Object SellerStats whose month is equals to the given month. If
     *         no SellerStats is found, this method returns null.
     */
    @Query("select e from SellerStats e where e.month = :month")
    List<SellerStats> findByMonth(@Param("month")Integer month);
    /**
     * Finds SellerStats by using totalRevenue as a search criteria.
     *
     * @param totalRevenue
     * @return An Object SellerStats whose totalRevenue is equals to the given totalRevenue. If
     *         no SellerStats is found, this method returns null.
     */
    @Query("select e from SellerStats e where e.totalRevenue = :totalRevenue")
    List<SellerStats> findByTotalRevenue(@Param("totalRevenue")BigDecimal totalRevenue);
    /**
     * Finds SellerStats by using totalOrders as a search criteria.
     *
     * @param totalOrders
     * @return An Object SellerStats whose totalOrders is equals to the given totalOrders. If
     *         no SellerStats is found, this method returns null.
     */
    @Query("select e from SellerStats e where e.totalOrders = :totalOrders")
    List<SellerStats> findByTotalOrders(@Param("totalOrders")Integer totalOrders);
    /**
     * Finds SellerStats by using totalProductsSold as a search criteria.
     *
     * @param totalProductsSold
     * @return An Object SellerStats whose totalProductsSold is equals to the given totalProductsSold. If
     *         no SellerStats is found, this method returns null.
     */
    @Query("select e from SellerStats e where e.totalProductsSold = :totalProductsSold")
    List<SellerStats> findByTotalProductsSold(@Param("totalProductsSold")Integer totalProductsSold);
    /**
     * Finds SellerStats by using averageRating as a search criteria.
     *
     * @param averageRating
     * @return An Object SellerStats whose averageRating is equals to the given averageRating. If
     *         no SellerStats is found, this method returns null.
     */
    @Query("select e from SellerStats e where e.averageRating = :averageRating")
    List<SellerStats> findByAverageRating(@Param("averageRating")BigDecimal averageRating);
    /**
     * Finds SellerStats by using calculatedAt as a search criteria.
     *
     * @param calculatedAt
     * @return An Object SellerStats whose calculatedAt is equals to the given calculatedAt. If
     *         no SellerStats is found, this method returns null.
     */
    @Query("select e from SellerStats e where e.calculatedAt = :calculatedAt")
    List<SellerStats> findByCalculatedAt(@Param("calculatedAt")Date calculatedAt);

    /**
     * Finds SellerStats by using sellerId as a search criteria.
     *
     * @param sellerId
     * @return An Object SellerStats whose sellerId is equals to the given sellerId. If
     *         no SellerStats is found, this method returns null.
     */
    @Query("select e from SellerStats e where e.users.id = :sellerId")
    List<SellerStats> findBySellerId(@Param("sellerId")Integer sellerId);

  /**
   * Finds one SellerStats by using sellerId as a search criteria.
   *
   * @param sellerId
   * @return An Object SellerStats whose sellerId is equals to the given sellerId. If
   *         no SellerStats is found, this method returns null.
   */
  @Query("select e from SellerStats e where e.users.id = :sellerId")
  SellerStats findSellerStatsBySellerId(@Param("sellerId")Integer sellerId);




    /**
     * Finds List of SellerStats by using sellerStatsDto as a search criteria.
     *
     * @param request, em
     * @return A List of SellerStats
     * @throws DataAccessException,ParseException
     */
    public default List<SellerStats> getByCriteria(Request<SellerStatsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
        String req = "select e from SellerStats e where e IS NOT NULL";
        HashMap<String, Object> param = new HashMap<String, Object>();
        req += getWhereExpression(request, param, locale);
                TypedQuery<SellerStats> query = em.createQuery(req, SellerStats.class);
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
     * Finds count of SellerStats by using sellerStatsDto as a search criteria.
     *
     * @param request, em
     * @return Number of SellerStats
     *
     */
    public default Long count(Request<SellerStatsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
        String req = "select count(e.id) from SellerStats e where e IS NOT NULL";
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
    default String getWhereExpression(Request<SellerStatsDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
        // main query
        SellerStatsDto dto = request.getData() != null ? request.getData() : new SellerStatsDto();
        String mainReq = generateCriteria(dto, param, 0, locale);
        // others query
        String othersReq = "";
        if (request.getDatas() != null && !request.getDatas().isEmpty()) {
            Integer index = 1;
            for (SellerStatsDto elt : request.getDatas()) {
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
    default String generateCriteria(SellerStatsDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
        List<String> listOfQuery = new ArrayList<String>();
        if (dto != null) {
            if (dto.getId() != null || Utilities.searchParamIsNotEmpty(dto.getIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
            }
            if (dto.getYear() != null || Utilities.searchParamIsNotEmpty(dto.getYearParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("year", dto.getYear(), "e.year", "Integer", dto.getYearParam(), param, index, locale));
            }
            if (dto.getMonth() != null || Utilities.searchParamIsNotEmpty(dto.getMonthParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("month", dto.getMonth(), "e.month", "Integer", dto.getMonthParam(), param, index, locale));
            }
            if (dto.getTotalRevenue() != null || Utilities.searchParamIsNotEmpty(dto.getTotalRevenueParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("totalRevenue", dto.getTotalRevenue(), "e.totalRevenue", "BigDecimal", dto.getTotalRevenueParam(), param, index, locale));
            }
            if (dto.getTotalOrders() != null || Utilities.searchParamIsNotEmpty(dto.getTotalOrdersParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("totalOrders", dto.getTotalOrders(), "e.totalOrders", "Integer", dto.getTotalOrdersParam(), param, index, locale));
            }
            if (dto.getTotalProductsSold() != null || Utilities.searchParamIsNotEmpty(dto.getTotalProductsSoldParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("totalProductsSold", dto.getTotalProductsSold(), "e.totalProductsSold", "Integer", dto.getTotalProductsSoldParam(), param, index, locale));
            }
            if (dto.getAverageRating() != null || Utilities.searchParamIsNotEmpty(dto.getAverageRatingParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("averageRating", dto.getAverageRating(), "e.averageRating", "BigDecimal", dto.getAverageRatingParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getCalculatedAt()) || Utilities.searchParamIsNotEmpty(dto.getCalculatedAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("calculatedAt", dto.getCalculatedAt(), "e.calculatedAt", "Date", dto.getCalculatedAtParam(), param, index, locale));
            }
                        if (dto.getSellerId() != null || Utilities.searchParamIsNotEmpty(dto.getSellerIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("sellerId", dto.getSellerId(), "e.users.id", "Integer", dto.getSellerIdParam(), param, index, locale));
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
