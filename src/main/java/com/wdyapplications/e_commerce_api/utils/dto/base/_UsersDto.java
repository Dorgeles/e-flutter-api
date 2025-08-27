
/*
 * Java dto for entity table users 
 * Created on 2025-08-27 ( Time 18:33:49 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package com.wdyapplications.e_commerce_api.utils.dto.base;

import java.util.Date;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;



import com.wdyapplications.e_commerce_api.utils.contract.*;

/**
 * DTO customize for table "users"
 * 
 * @author Smile Back-End generator
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class _UsersDto implements Cloneable {

    protected Integer    id                   ; // Primary Key

    protected String     email                ;
    protected String     passwordHash         ;
    protected String     firstName            ;
    protected String     lastName             ;
    protected String     phone                ;
    protected String     avatarUrl            ;
    protected String     role                 ;
	protected String     createdAt            ;
	protected String     updatedAt            ;
	protected String     lastLogin            ;
    protected Boolean    isActive             ;
    protected String     resetToken           ;
	protected String     resetTokenExpires    ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------

	// Search param
	protected SearchParam<Integer>  idParam               ;                     
	protected SearchParam<String>   emailParam            ;                     
	protected SearchParam<String>   passwordHashParam     ;                     
	protected SearchParam<String>   firstNameParam        ;                     
	protected SearchParam<String>   lastNameParam         ;                     
	protected SearchParam<String>   phoneParam            ;                     
	protected SearchParam<String>   avatarUrlParam        ;                     
	protected SearchParam<String>   roleParam             ;                     
	protected SearchParam<String>   createdAtParam        ;                     
	protected SearchParam<String>   updatedAtParam        ;                     
	protected SearchParam<String>   lastLoginParam        ;                     
	protected SearchParam<Boolean>  isActiveParam         ;                     
	protected SearchParam<String>   resetTokenParam       ;                     
	protected SearchParam<String>   resetTokenExpiresParam;                     

	// order param
	protected String orderField;
	protected String orderDirection;




}
