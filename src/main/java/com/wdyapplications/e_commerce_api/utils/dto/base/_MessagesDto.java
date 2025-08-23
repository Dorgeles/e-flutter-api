
/*
 * Java dto for entity table messages 
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
 * DTO customize for table "messages"
 * 
 * @author Smile Back-End generator
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class _MessagesDto implements Cloneable {

    protected Integer    id                   ; // Primary Key

    protected Integer    conversationId       ;
    protected Integer    senderId             ;
    protected String     content              ;
    protected String     type                 ;
    protected String     attachmentUrl        ;
    protected Boolean    isRead               ;
	protected String     createdAt            ;
	protected String     readAt               ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	//protected Integer    users;
	protected String usersEmail;
	protected String usersFirstName;
	protected String usersLastName;
	//protected Integer    conversations;

	// Search param
	protected SearchParam<Integer>  idParam               ;                     
	protected SearchParam<Integer>  conversationIdParam   ;                     
	protected SearchParam<Integer>  senderIdParam         ;                     
	protected SearchParam<String>   contentParam          ;                     
	protected SearchParam<String>   typeParam             ;                     
	protected SearchParam<String>   attachmentUrlParam    ;                     
	protected SearchParam<Boolean>  isReadParam           ;                     
	protected SearchParam<String>   createdAtParam        ;                     
	protected SearchParam<String>   readAtParam           ;                     
	protected SearchParam<Integer>  usersParam            ;                     
	protected SearchParam<String>   usersEmailParam       ;                     
	protected SearchParam<String>   usersFirstNameParam   ;                     
	protected SearchParam<String>   usersLastNameParam    ;                     
	protected SearchParam<Integer>  conversationsParam    ;                     

	// order param
	protected String orderField;
	protected String orderDirection;




}
