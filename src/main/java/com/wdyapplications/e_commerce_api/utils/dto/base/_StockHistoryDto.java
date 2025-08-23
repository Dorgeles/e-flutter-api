
/*
 * Java dto for entity table stock_history 
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
 * DTO customize for table "stock_history"
 * 
 * @author Smile Back-End generator
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class _StockHistoryDto implements Cloneable {

    protected Integer    id                   ; // Primary Key

    protected Integer    productId            ;
    protected Integer    oldQuantity          ;
    protected Integer    newQuantity          ;
    protected Integer    changeQuantity       ;
    protected String     reason               ;
    protected String     referenceId          ;
	protected String     createdAt            ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	//protected Integer    products;
	protected String productsName;

	// Search param
	protected SearchParam<Integer>  idParam               ;                     
	protected SearchParam<Integer>  productIdParam        ;                     
	protected SearchParam<Integer>  oldQuantityParam      ;                     
	protected SearchParam<Integer>  newQuantityParam      ;                     
	protected SearchParam<Integer>  changeQuantityParam   ;                     
	protected SearchParam<String>   reasonParam           ;                     
	protected SearchParam<String>   referenceIdParam      ;                     
	protected SearchParam<String>   createdAtParam        ;                     
	protected SearchParam<Integer>  productsParam         ;                     
	protected SearchParam<String>   productsNameParam     ;                     

	// order param
	protected String orderField;
	protected String orderDirection;




}
