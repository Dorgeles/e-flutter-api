
/*
 * Java dto for entity table conversations 
 * Created on 2025-08-23 ( Time 06:18:36 )
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
 * DTO customize for table "conversations"
 * 
 * @author Smile Back-End generator
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class _ConversationsDto implements Cloneable {

    protected Integer    id                   ; // Primary Key

    protected Integer    clientId             ;
    protected Integer    sellerId             ;
	protected String     createdAt            ;
	protected String     updatedAt            ;
	protected String     lastMessageAt        ;
    protected Boolean    isActive             ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	//protected Integer    users;
	protected String usersEmail;
	protected String usersFirstName;
	protected String usersLastName;

	protected String usersEmail2;
	protected String usersFirstName2;
	protected String usersLastName2;


	// Search param
	protected SearchParam<Integer>  idParam               ;                     
	protected SearchParam<Integer>  clientIdParam         ;                     
	protected SearchParam<Integer>  sellerIdParam         ;                     
	protected SearchParam<String>   createdAtParam        ;                     
	protected SearchParam<String>   updatedAtParam        ;                     
	protected SearchParam<String>   lastMessageAtParam    ;                     
	protected SearchParam<Boolean>  isActiveParam         ;                     
	protected SearchParam<Integer>  usersParam            ;                     
	protected SearchParam<String>   usersEmailParam       ;                     
	protected SearchParam<String>   usersFirstNameParam   ;                     
	protected SearchParam<String>   usersLastNameParam    ;
	protected SearchParam<String>   usersEmailParam2       ;
	protected SearchParam<String>   usersFirstNameParam2   ;
	protected SearchParam<String>   usersLastNameParam2    ;

	// order param
	protected String orderField;
	protected String orderDirection;




}
