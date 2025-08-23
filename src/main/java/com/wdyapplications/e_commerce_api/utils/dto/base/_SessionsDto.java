
/*
 * Java dto for entity table sessions 
 * Created on 2025-08-23 ( Time 06:18:37 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package com.wdyapplications.e_commerce_api.utils.dto.base;

import java.util.Date;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;



import com.wdyapplications.e_commerce_api.utils.contract.*;

/**
 * DTO customize for table "sessions"
 * 
 * @author Smile Back-End generator
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class _SessionsDto implements Cloneable {

    protected String     id                   ; // Primary Key

    protected Integer    userId               ;
    protected String     token                ;
	protected String     expiresAt            ;
	protected String     createdAt            ;
    protected String     deviceInfo           ;
    protected String     ipAddress            ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	//protected Integer    users;
	protected String usersEmail;
	protected String usersFirstName;
	protected String usersLastName;

	// Search param
	protected SearchParam<String>   idParam               ;                     
	protected SearchParam<Integer>  userIdParam           ;                     
	protected SearchParam<String>   tokenParam            ;                     
	protected SearchParam<String>   expiresAtParam        ;                     
	protected SearchParam<String>   createdAtParam        ;                     
	protected SearchParam<String>   deviceInfoParam       ;                     
	protected SearchParam<String>   ipAddressParam        ;                     
	protected SearchParam<Integer>  usersParam            ;                     
	protected SearchParam<String>   usersEmailParam       ;                     
	protected SearchParam<String>   usersFirstNameParam   ;                     
	protected SearchParam<String>   usersLastNameParam    ;                     

	// order param
	protected String orderField;
	protected String orderDirection;




}
