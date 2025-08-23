
/*
 * Java dto for entity table promotion_usage 
 * Created on 2025-08-23 ( Time 06:18:37 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package com.wdyapplications.e_commerce_api.utils.dto.base;

import java.math.BigDecimal;
import java.util.Date;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;



import com.wdyapplications.e_commerce_api.utils.contract.*;

/**
 * DTO customize for table "promotion_usage"
 * 
 * @author Smile Back-End generator
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class _PromotionUsageDto implements Cloneable {

    protected Integer    id                   ; // Primary Key

    protected Integer    promotionId          ;
    protected Integer    orderId              ;
    protected Integer    userId               ;
    protected BigDecimal discountAmount       ;
	protected String     usedAt               ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	//protected Integer    orders;
	//protected Integer    promotions;
	protected String promotionsCode;
	protected String promotionsName;
	//protected Integer    users;
	protected String usersEmail;
	protected String usersFirstName;
	protected String usersLastName;

	// Search param
	protected SearchParam<Integer>  idParam               ;                     
	protected SearchParam<Integer>  promotionIdParam      ;                     
	protected SearchParam<Integer>  orderIdParam          ;                     
	protected SearchParam<Integer>  userIdParam           ;                     
	protected SearchParam<BigDecimal>discountAmountParam   ;                     
	protected SearchParam<String>   usedAtParam           ;                     
	protected SearchParam<Integer>  ordersParam           ;                     
	protected SearchParam<Integer>  promotionsParam       ;                     
	protected SearchParam<String>   promotionsCodeParam   ;                     
	protected SearchParam<String>   promotionsNameParam   ;                     
	protected SearchParam<Integer>  usersParam            ;                     
	protected SearchParam<String>   usersEmailParam       ;                     
	protected SearchParam<String>   usersFirstNameParam   ;                     
	protected SearchParam<String>   usersLastNameParam    ;                     

	// order param
	protected String orderField;
	protected String orderDirection;




}
