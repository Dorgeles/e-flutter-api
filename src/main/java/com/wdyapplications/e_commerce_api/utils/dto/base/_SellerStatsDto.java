
/*
 * Java dto for entity table seller_stats 
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
 * DTO customize for table "seller_stats"
 * 
 * @author Smile Back-End generator
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class _SellerStatsDto implements Cloneable {

    protected Integer    id                   ; // Primary Key

    protected Integer    sellerId             ;
    protected Integer    year                 ;
    protected Integer    month                ;
    protected BigDecimal totalRevenue         ;
    protected Integer    totalOrders          ;
    protected Integer    totalProductsSold    ;
    protected BigDecimal averageRating        ;
	protected String     calculatedAt         ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	//protected Integer    users;
	protected String usersEmail;
	protected String usersFirstName;
	protected String usersLastName;

	// Search param
	protected SearchParam<Integer>  idParam               ;                     
	protected SearchParam<Integer>  sellerIdParam         ;                     
	protected SearchParam<Integer>  yearParam             ;                     
	protected SearchParam<Integer>  monthParam            ;                     
	protected SearchParam<BigDecimal>totalRevenueParam     ;                     
	protected SearchParam<Integer>  totalOrdersParam      ;                     
	protected SearchParam<Integer>  totalProductsSoldParam;                     
	protected SearchParam<BigDecimal>averageRatingParam    ;                     
	protected SearchParam<String>   calculatedAtParam     ;                     
	protected SearchParam<Integer>  usersParam            ;                     
	protected SearchParam<String>   usersEmailParam       ;                     
	protected SearchParam<String>   usersFirstNameParam   ;                     
	protected SearchParam<String>   usersLastNameParam    ;                     

	// order param
	protected String orderField;
	protected String orderDirection;




}
