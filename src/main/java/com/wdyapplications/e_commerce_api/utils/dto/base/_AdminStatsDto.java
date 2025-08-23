
/*
 * Java dto for entity table admin_stats 
 * Created on 2025-08-23 ( Time 06:18:36 )
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
 * DTO customize for table "admin_stats"
 * 
 * @author Smile Back-End generator
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class _AdminStatsDto implements Cloneable {

    protected Integer    id                   ; // Primary Key

    protected Date       statsDate            ;
    protected BigDecimal totalRevenue         ;
    protected Integer    totalOrders          ;
    protected Integer    activeUsers          ;
    protected Integer    newUsers             ;
    protected Integer    totalProducts        ;
    protected Integer    outOfStockProducts   ;
	protected String     calculatedAt         ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------

	// Search param
	protected SearchParam<Integer>  idParam               ;                     
	protected SearchParam<Date>     statsDateParam        ;                     
	protected SearchParam<BigDecimal>totalRevenueParam     ;                     
	protected SearchParam<Integer>  totalOrdersParam      ;                     
	protected SearchParam<Integer>  activeUsersParam      ;                     
	protected SearchParam<Integer>  newUsersParam         ;                     
	protected SearchParam<Integer>  totalProductsParam    ;                     
	protected SearchParam<Integer>  outOfStockProductsParam;                     
	protected SearchParam<String>   calculatedAtParam     ;                     

	// order param
	protected String orderField;
	protected String orderDirection;




}
