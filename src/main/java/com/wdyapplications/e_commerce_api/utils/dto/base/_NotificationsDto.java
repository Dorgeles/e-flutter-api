
/*
 * Java dto for entity table notifications 
 * Created on 2025-08-23 ( Time 06:18:36 )
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
 * DTO customize for table "notifications"
 * 
 * @author Smile Back-End generator
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class _NotificationsDto implements Cloneable {

    protected Integer    id                   ; // Primary Key

    protected Integer    userId               ;
    protected String     title                ;
    protected String     message              ;
    protected String     type                 ;
    protected String     referenceId          ;
    protected Boolean    isRead               ;
    protected Boolean    isPushSent           ;
	protected String     createdAt            ;
	protected String     readAt               ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	//protected Integer    users;
	protected String usersEmail;
	protected String usersFirstName;
	protected String usersLastName;

	// Search param
	protected SearchParam<Integer>  idParam               ;                     
	protected SearchParam<Integer>  userIdParam           ;                     
	protected SearchParam<String>   titleParam            ;                     
	protected SearchParam<String>   messageParam          ;                     
	protected SearchParam<String>   typeParam             ;                     
	protected SearchParam<String>   referenceIdParam      ;                     
	protected SearchParam<Boolean>  isReadParam           ;                     
	protected SearchParam<Boolean>  isPushSentParam       ;                     
	protected SearchParam<String>   createdAtParam        ;                     
	protected SearchParam<String>   readAtParam           ;                     
	protected SearchParam<Integer>  usersParam            ;                     
	protected SearchParam<String>   usersEmailParam       ;                     
	protected SearchParam<String>   usersFirstNameParam   ;                     
	protected SearchParam<String>   usersLastNameParam    ;                     

	// order param
	protected String orderField;
	protected String orderDirection;




}
