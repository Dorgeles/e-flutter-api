
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
 * Repository customize : Addresses.
 *
 * @author Dorgeddy
 *
 */
@Repository
public interface _AddressesRepository {
	    /**
     * Finds Addresses by using id as a search criteria.
     *
     * @param id
     * @return An Object Addresses whose id is equals to the given id. If
     *         no Addresses is found, this method returns null.
     */
    @Query("select e from Addresses e where e.id = :id")
    Addresses findOne(@Param("id")Integer id);

    /**
     * Finds Addresses by using label as a search criteria.
     *
     * @param label
     * @return An Object Addresses whose label is equals to the given label. If
     *         no Addresses is found, this method returns null.
     */
    @Query("select e from Addresses e where e.label = :label")
    List<Addresses> findByLabel(@Param("label")String label);
    /**
     * Finds Addresses by using streetAddress as a search criteria.
     *
     * @param streetAddress
     * @return An Object Addresses whose streetAddress is equals to the given streetAddress. If
     *         no Addresses is found, this method returns null.
     */
    @Query("select e from Addresses e where e.streetAddress = :streetAddress")
    List<Addresses> findByStreetAddress(@Param("streetAddress")String streetAddress);
    /**
     * Finds Addresses by using city as a search criteria.
     *
     * @param city
     * @return An Object Addresses whose city is equals to the given city. If
     *         no Addresses is found, this method returns null.
     */
    @Query("select e from Addresses e where e.city = :city")
    List<Addresses> findByCity(@Param("city")String city);
    /**
     * Finds Addresses by using state as a search criteria.
     *
     * @param state
     * @return An Object Addresses whose state is equals to the given state. If
     *         no Addresses is found, this method returns null.
     */
    @Query("select e from Addresses e where e.state = :state")
    List<Addresses> findByState(@Param("state")String state);
    /**
     * Finds Addresses by using postalCode as a search criteria.
     *
     * @param postalCode
     * @return An Object Addresses whose postalCode is equals to the given postalCode. If
     *         no Addresses is found, this method returns null.
     */
    @Query("select e from Addresses e where e.postalCode = :postalCode")
    List<Addresses> findByPostalCode(@Param("postalCode")String postalCode);
    /**
     * Finds Addresses by using country as a search criteria.
     *
     * @param country
     * @return An Object Addresses whose country is equals to the given country. If
     *         no Addresses is found, this method returns null.
     */
    @Query("select e from Addresses e where e.country = :country")
    List<Addresses> findByCountry(@Param("country")String country);
    /**
     * Finds Addresses by using isDefault as a search criteria.
     *
     * @param isDefault
     * @return An Object Addresses whose isDefault is equals to the given isDefault. If
     *         no Addresses is found, this method returns null.
     */
    @Query("select e from Addresses e where e.isDefault = :isDefault")
    List<Addresses> findByIsDefault(@Param("isDefault")Boolean isDefault);
    /**
     * Finds Addresses by using createdAt as a search criteria.
     *
     * @param createdAt
     * @return An Object Addresses whose createdAt is equals to the given createdAt. If
     *         no Addresses is found, this method returns null.
     */
    @Query("select e from Addresses e where e.createdAt = :createdAt")
    List<Addresses> findByCreatedAt(@Param("createdAt")Date createdAt);
    /**
     * Finds Addresses by using updatedAt as a search criteria.
     *
     * @param updatedAt
     * @return An Object Addresses whose updatedAt is equals to the given updatedAt. If
     *         no Addresses is found, this method returns null.
     */
    @Query("select e from Addresses e where e.updatedAt = :updatedAt")
    List<Addresses> findByUpdatedAt(@Param("updatedAt")Date updatedAt);

    /**
     * Finds Addresses by using userId as a search criteria.
     *
     * @param userId
     * @return An Object Addresses whose userId is equals to the given userId. If
     *         no Addresses is found, this method returns null.
     */
    @Query("select e from Addresses e where e.users.id = :userId")
    List<Addresses> findByUserId(@Param("userId")Integer userId);

  /**
   * Finds one Addresses by using userId as a search criteria.
   *
   * @param userId
   * @return An Object Addresses whose userId is equals to the given userId. If
   *         no Addresses is found, this method returns null.
   */
  @Query("select e from Addresses e where e.users.id = :userId")
  Addresses findAddressesByUserId(@Param("userId")Integer userId);




    /**
     * Finds List of Addresses by using addressesDto as a search criteria.
     *
     * @param request, em
     * @return A List of Addresses
     * @throws DataAccessException,ParseException
     */
    public default List<Addresses> getByCriteria(Request<AddressesDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
        String req = "select e from Addresses e where e IS NOT NULL";
        HashMap<String, Object> param = new HashMap<String, Object>();
        req += getWhereExpression(request, param, locale);
                TypedQuery<Addresses> query = em.createQuery(req, Addresses.class);
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
     * Finds count of Addresses by using addressesDto as a search criteria.
     *
     * @param request, em
     * @return Number of Addresses
     *
     */
    public default Long count(Request<AddressesDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
        String req = "select count(e.id) from Addresses e where e IS NOT NULL";
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
    default String getWhereExpression(Request<AddressesDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
        // main query
        AddressesDto dto = request.getData() != null ? request.getData() : new AddressesDto();
        String mainReq = generateCriteria(dto, param, 0, locale);
        // others query
        String othersReq = "";
        if (request.getDatas() != null && !request.getDatas().isEmpty()) {
            Integer index = 1;
            for (AddressesDto elt : request.getDatas()) {
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
    default String generateCriteria(AddressesDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
        List<String> listOfQuery = new ArrayList<String>();
        if (dto != null) {
            if (dto.getId() != null || Utilities.searchParamIsNotEmpty(dto.getIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getLabel()) || Utilities.searchParamIsNotEmpty(dto.getLabelParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("label", dto.getLabel(), "e.label", "String", dto.getLabelParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getStreetAddress()) || Utilities.searchParamIsNotEmpty(dto.getStreetAddressParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("streetAddress", dto.getStreetAddress(), "e.streetAddress", "String", dto.getStreetAddressParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getCity()) || Utilities.searchParamIsNotEmpty(dto.getCityParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("city", dto.getCity(), "e.city", "String", dto.getCityParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getState()) || Utilities.searchParamIsNotEmpty(dto.getStateParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("state", dto.getState(), "e.state", "String", dto.getStateParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getPostalCode()) || Utilities.searchParamIsNotEmpty(dto.getPostalCodeParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("postalCode", dto.getPostalCode(), "e.postalCode", "String", dto.getPostalCodeParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getCountry()) || Utilities.searchParamIsNotEmpty(dto.getCountryParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("country", dto.getCountry(), "e.country", "String", dto.getCountryParam(), param, index, locale));
            }
            if (dto.getIsDefault() != null || Utilities.searchParamIsNotEmpty(dto.getIsDefaultParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("isDefault", dto.getIsDefault(), "e.isDefault", "Boolean", dto.getIsDefaultParam(), param, index, locale));
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
