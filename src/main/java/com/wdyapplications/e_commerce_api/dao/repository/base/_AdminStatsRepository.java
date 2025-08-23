
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
 * Repository customize : AdminStats.
 *
 * @author Dorgeddy
 *
 */
@Repository
public interface _AdminStatsRepository {
	    /**
     * Finds AdminStats by using id as a search criteria.
     *
     * @param id
     * @return An Object AdminStats whose id is equals to the given id. If
     *         no AdminStats is found, this method returns null.
     */
    @Query("select e from AdminStats e where e.id = :id")
    AdminStats findOne(@Param("id")Integer id);

    /**
     * Finds AdminStats by using statsDate as a search criteria.
     *
     * @param statsDate
     * @return An Object AdminStats whose statsDate is equals to the given statsDate. If
     *         no AdminStats is found, this method returns null.
     */
    @Query("select e from AdminStats e where e.statsDate = :statsDate")
    List<AdminStats> findByStatsDate(@Param("statsDate")Date statsDate);
    /**
     * Finds AdminStats by using totalRevenue as a search criteria.
     *
     * @param totalRevenue
     * @return An Object AdminStats whose totalRevenue is equals to the given totalRevenue. If
     *         no AdminStats is found, this method returns null.
     */
    @Query("select e from AdminStats e where e.totalRevenue = :totalRevenue")
    List<AdminStats> findByTotalRevenue(@Param("totalRevenue")BigDecimal totalRevenue);
    /**
     * Finds AdminStats by using totalOrders as a search criteria.
     *
     * @param totalOrders
     * @return An Object AdminStats whose totalOrders is equals to the given totalOrders. If
     *         no AdminStats is found, this method returns null.
     */
    @Query("select e from AdminStats e where e.totalOrders = :totalOrders")
    List<AdminStats> findByTotalOrders(@Param("totalOrders")Integer totalOrders);
    /**
     * Finds AdminStats by using activeUsers as a search criteria.
     *
     * @param activeUsers
     * @return An Object AdminStats whose activeUsers is equals to the given activeUsers. If
     *         no AdminStats is found, this method returns null.
     */
    @Query("select e from AdminStats e where e.activeUsers = :activeUsers")
    List<AdminStats> findByActiveUsers(@Param("activeUsers")Integer activeUsers);
    /**
     * Finds AdminStats by using newUsers as a search criteria.
     *
     * @param newUsers
     * @return An Object AdminStats whose newUsers is equals to the given newUsers. If
     *         no AdminStats is found, this method returns null.
     */
    @Query("select e from AdminStats e where e.newUsers = :newUsers")
    List<AdminStats> findByNewUsers(@Param("newUsers")Integer newUsers);
    /**
     * Finds AdminStats by using totalProducts as a search criteria.
     *
     * @param totalProducts
     * @return An Object AdminStats whose totalProducts is equals to the given totalProducts. If
     *         no AdminStats is found, this method returns null.
     */
    @Query("select e from AdminStats e where e.totalProducts = :totalProducts")
    List<AdminStats> findByTotalProducts(@Param("totalProducts")Integer totalProducts);
    /**
     * Finds AdminStats by using outOfStockProducts as a search criteria.
     *
     * @param outOfStockProducts
     * @return An Object AdminStats whose outOfStockProducts is equals to the given outOfStockProducts. If
     *         no AdminStats is found, this method returns null.
     */
    @Query("select e from AdminStats e where e.outOfStockProducts = :outOfStockProducts")
    List<AdminStats> findByOutOfStockProducts(@Param("outOfStockProducts")Integer outOfStockProducts);
    /**
     * Finds AdminStats by using calculatedAt as a search criteria.
     *
     * @param calculatedAt
     * @return An Object AdminStats whose calculatedAt is equals to the given calculatedAt. If
     *         no AdminStats is found, this method returns null.
     */
    @Query("select e from AdminStats e where e.calculatedAt = :calculatedAt")
    List<AdminStats> findByCalculatedAt(@Param("calculatedAt")Date calculatedAt);



    /**
     * Finds List of AdminStats by using adminStatsDto as a search criteria.
     *
     * @param request, em
     * @return A List of AdminStats
     * @throws DataAccessException,ParseException
     */
    public default List<AdminStats> getByCriteria(Request<AdminStatsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
        String req = "select e from AdminStats e where e IS NOT NULL";
        HashMap<String, Object> param = new HashMap<String, Object>();
        req += getWhereExpression(request, param, locale);
                TypedQuery<AdminStats> query = em.createQuery(req, AdminStats.class);
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
     * Finds count of AdminStats by using adminStatsDto as a search criteria.
     *
     * @param request, em
     * @return Number of AdminStats
     *
     */
    public default Long count(Request<AdminStatsDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
        String req = "select count(e.id) from AdminStats e where e IS NOT NULL";
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
    default String getWhereExpression(Request<AdminStatsDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
        // main query
        AdminStatsDto dto = request.getData() != null ? request.getData() : new AdminStatsDto();
        String mainReq = generateCriteria(dto, param, 0, locale);
        // others query
        String othersReq = "";
        if (request.getDatas() != null && !request.getDatas().isEmpty()) {
            Integer index = 1;
            for (AdminStatsDto elt : request.getDatas()) {
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
    default String generateCriteria(AdminStatsDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
        List<String> listOfQuery = new ArrayList<String>();
        if (dto != null) {
            if (dto.getId() != null || Utilities.searchParamIsNotEmpty(dto.getIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
            }
            if (dto.getStatsDate() != null || Utilities.searchParamIsNotEmpty(dto.getStatsDateParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("statsDate", dto.getStatsDate(), "e.statsDate", "Date", dto.getStatsDateParam(), param, index, locale));
            }
            if (dto.getTotalRevenue() != null || Utilities.searchParamIsNotEmpty(dto.getTotalRevenueParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("totalRevenue", dto.getTotalRevenue(), "e.totalRevenue", "BigDecimal", dto.getTotalRevenueParam(), param, index, locale));
            }
            if (dto.getTotalOrders() != null || Utilities.searchParamIsNotEmpty(dto.getTotalOrdersParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("totalOrders", dto.getTotalOrders(), "e.totalOrders", "Integer", dto.getTotalOrdersParam(), param, index, locale));
            }
            if (dto.getActiveUsers() != null || Utilities.searchParamIsNotEmpty(dto.getActiveUsersParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("activeUsers", dto.getActiveUsers(), "e.activeUsers", "Integer", dto.getActiveUsersParam(), param, index, locale));
            }
            if (dto.getNewUsers() != null || Utilities.searchParamIsNotEmpty(dto.getNewUsersParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("newUsers", dto.getNewUsers(), "e.newUsers", "Integer", dto.getNewUsersParam(), param, index, locale));
            }
            if (dto.getTotalProducts() != null || Utilities.searchParamIsNotEmpty(dto.getTotalProductsParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("totalProducts", dto.getTotalProducts(), "e.totalProducts", "Integer", dto.getTotalProductsParam(), param, index, locale));
            }
            if (dto.getOutOfStockProducts() != null || Utilities.searchParamIsNotEmpty(dto.getOutOfStockProductsParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("outOfStockProducts", dto.getOutOfStockProducts(), "e.outOfStockProducts", "Integer", dto.getOutOfStockProductsParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getCalculatedAt()) || Utilities.searchParamIsNotEmpty(dto.getCalculatedAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("calculatedAt", dto.getCalculatedAt(), "e.calculatedAt", "Date", dto.getCalculatedAtParam(), param, index, locale));
            }

            /*List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
            if (Utilities.isNotEmpty(listOfCustomQuery)) {
                listOfQuery.addAll(listOfCustomQuery);
            }*/
        }
        return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
    }
}
