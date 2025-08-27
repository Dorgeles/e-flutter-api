
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
 * Repository customize : Users.
 *
 * @author Dorgeddy
 *
 */
@Repository
public interface _UsersRepository {
	    /**
     * Finds Users by using id as a search criteria.
     *
     * @param id
     * @return An Object Users whose id is equals to the given id. If
     *         no Users is found, this method returns null.
     */
    @Query("select e from Users e where e.id = :id")
    Users findOne(@Param("id")Integer id);

    /**
     * Finds Users by using email as a search criteria.
     *
     * @param email
     * @return An Object Users whose email is equals to the given email. If
     *         no Users is found, this method returns null.
     */
    @Query("select e from Users e where e.email = :email")
    Users findByEmail(@Param("email")String email);
    /**
     * Finds Users by using passwordHash as a search criteria.
     *
     * @param passwordHash
     * @return An Object Users whose passwordHash is equals to the given passwordHash. If
     *         no Users is found, this method returns null.
     */
    @Query("select e from Users e where e.passwordHash = :passwordHash")
    List<Users> findByPasswordHash(@Param("passwordHash")String passwordHash);
    /**
     * Finds Users by using firstName as a search criteria.
     *
     * @param firstName
     * @return An Object Users whose firstName is equals to the given firstName. If
     *         no Users is found, this method returns null.
     */
    @Query("select e from Users e where e.firstName = :firstName")
    List<Users> findByFirstName(@Param("firstName")String firstName);
    /**
     * Finds Users by using lastName as a search criteria.
     *
     * @param lastName
     * @return An Object Users whose lastName is equals to the given lastName. If
     *         no Users is found, this method returns null.
     */
    @Query("select e from Users e where e.lastName = :lastName")
    List<Users> findByLastName(@Param("lastName")String lastName);
    /**
     * Finds Users by using phone as a search criteria.
     *
     * @param phone
     * @return An Object Users whose phone is equals to the given phone. If
     *         no Users is found, this method returns null.
     */
    @Query("select e from Users e where e.phone = :phone")
    List<Users> findByPhone(@Param("phone")String phone);
    /**
     * Finds Users by using avatarUrl as a search criteria.
     *
     * @param avatarUrl
     * @return An Object Users whose avatarUrl is equals to the given avatarUrl. If
     *         no Users is found, this method returns null.
     */
    @Query("select e from Users e where e.avatarUrl = :avatarUrl")
    List<Users> findByAvatarUrl(@Param("avatarUrl")String avatarUrl);
    /**
     * Finds Users by using role as a search criteria.
     *
     * @param role
     * @return An Object Users whose role is equals to the given role. If
     *         no Users is found, this method returns null.
     */
    @Query("select e from Users e where e.role = :role")
    List<Users> findByRole(@Param("role")String role);
    /**
     * Finds Users by using createdAt as a search criteria.
     *
     * @param createdAt
     * @return An Object Users whose createdAt is equals to the given createdAt. If
     *         no Users is found, this method returns null.
     */
    @Query("select e from Users e where e.createdAt = :createdAt")
    List<Users> findByCreatedAt(@Param("createdAt")Date createdAt);
    /**
     * Finds Users by using updatedAt as a search criteria.
     *
     * @param updatedAt
     * @return An Object Users whose updatedAt is equals to the given updatedAt. If
     *         no Users is found, this method returns null.
     */
    @Query("select e from Users e where e.updatedAt = :updatedAt")
    List<Users> findByUpdatedAt(@Param("updatedAt")Date updatedAt);
    /**
     * Finds Users by using lastLogin as a search criteria.
     *
     * @param lastLogin
     * @return An Object Users whose lastLogin is equals to the given lastLogin. If
     *         no Users is found, this method returns null.
     */
    @Query("select e from Users e where e.lastLogin = :lastLogin")
    List<Users> findByLastLogin(@Param("lastLogin")Date lastLogin);
    /**
     * Finds Users by using isActive as a search criteria.
     *
     * @param isActive
     * @return An Object Users whose isActive is equals to the given isActive. If
     *         no Users is found, this method returns null.
     */
    @Query("select e from Users e where e.isActive = :isActive")
    List<Users> findByIsActive(@Param("isActive")Boolean isActive);
    /**
     * Finds Users by using resetToken as a search criteria.
     *
     * @param resetToken
     * @return An Object Users whose resetToken is equals to the given resetToken. If
     *         no Users is found, this method returns null.
     */
    @Query("select e from Users e where e.resetToken = :resetToken")
    List<Users> findByResetToken(@Param("resetToken")String resetToken);
    /**
     * Finds Users by using resetTokenExpires as a search criteria.
     *
     * @param resetTokenExpires
     * @return An Object Users whose resetTokenExpires is equals to the given resetTokenExpires. If
     *         no Users is found, this method returns null.
     */
    @Query("select e from Users e where e.resetTokenExpires = :resetTokenExpires")
    List<Users> findByResetTokenExpires(@Param("resetTokenExpires")Date resetTokenExpires);



    /**
     * Finds List of Users by using usersDto as a search criteria.
     *
     * @param request, em
     * @return A List of Users
     * @throws DataAccessException,ParseException
     */
    public default List<Users> getByCriteria(Request<UsersDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
        String req = "select e from Users e where e IS NOT NULL";
        HashMap<String, Object> param = new HashMap<String, Object>();
        req += getWhereExpression(request, param, locale);
                TypedQuery<Users> query = em.createQuery(req, Users.class);
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
     * Finds count of Users by using usersDto as a search criteria.
     *
     * @param request, em
     * @return Number of Users
     *
     */
    public default Long count(Request<UsersDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
        String req = "select count(e.id) from Users e where e IS NOT NULL";
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
    default String getWhereExpression(Request<UsersDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
        // main query
        UsersDto dto = request.getData() != null ? request.getData() : new UsersDto();
        String mainReq = generateCriteria(dto, param, 0, locale);
        // others query
        String othersReq = "";
        if (request.getDatas() != null && !request.getDatas().isEmpty()) {
            Integer index = 1;
            for (UsersDto elt : request.getDatas()) {
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
    default String generateCriteria(UsersDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
        List<String> listOfQuery = new ArrayList<String>();
        if (dto != null) {
            if (dto.getId() != null || Utilities.searchParamIsNotEmpty(dto.getIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getEmail()) || Utilities.searchParamIsNotEmpty(dto.getEmailParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("email", dto.getEmail(), "e.email", "String", dto.getEmailParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getPasswordHash()) || Utilities.searchParamIsNotEmpty(dto.getPasswordHashParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("passwordHash", dto.getPasswordHash(), "e.passwordHash", "String", dto.getPasswordHashParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getFirstName()) || Utilities.searchParamIsNotEmpty(dto.getFirstNameParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("firstName", dto.getFirstName(), "e.firstName", "String", dto.getFirstNameParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getLastName()) || Utilities.searchParamIsNotEmpty(dto.getLastNameParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("lastName", dto.getLastName(), "e.lastName", "String", dto.getLastNameParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getPhone()) || Utilities.searchParamIsNotEmpty(dto.getPhoneParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("phone", dto.getPhone(), "e.phone", "String", dto.getPhoneParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getAvatarUrl()) || Utilities.searchParamIsNotEmpty(dto.getAvatarUrlParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("avatarUrl", dto.getAvatarUrl(), "e.avatarUrl", "String", dto.getAvatarUrlParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getRole()) || Utilities.searchParamIsNotEmpty(dto.getRoleParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("role", dto.getRole(), "e.role", "String", dto.getRoleParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getCreatedAt()) || Utilities.searchParamIsNotEmpty(dto.getCreatedAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("createdAt", dto.getCreatedAt(), "e.createdAt", "Date", dto.getCreatedAtParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getUpdatedAt()) || Utilities.searchParamIsNotEmpty(dto.getUpdatedAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("updatedAt", dto.getUpdatedAt(), "e.updatedAt", "Date", dto.getUpdatedAtParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getLastLogin()) || Utilities.searchParamIsNotEmpty(dto.getLastLoginParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("lastLogin", dto.getLastLogin(), "e.lastLogin", "Date", dto.getLastLoginParam(), param, index, locale));
            }
            if (dto.getIsActive() != null || Utilities.searchParamIsNotEmpty(dto.getIsActiveParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("isActive", dto.getIsActive(), "e.isActive", "Boolean", dto.getIsActiveParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getResetToken()) || Utilities.searchParamIsNotEmpty(dto.getResetTokenParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("resetToken", dto.getResetToken(), "e.resetToken", "String", dto.getResetTokenParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getResetTokenExpires()) || Utilities.searchParamIsNotEmpty(dto.getResetTokenExpiresParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("resetTokenExpires", dto.getResetTokenExpires(), "e.resetTokenExpires", "Date", dto.getResetTokenExpiresParam(), param, index, locale));
            }

            /*List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
            if (Utilities.isNotEmpty(listOfCustomQuery)) {
                listOfQuery.addAll(listOfCustomQuery);
            }*/
        }
        return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
    }
}
