
/*
 * Java dto for entity table promotion_products 
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
 * DTO customize for table "promotion_products"
 * 
 * @author Smile Back-End generator
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class _PromotionProductsDto implements Cloneable {

    protected Integer    id                   ; // Primary Key

    protected Integer    promotionId          ;
    protected Integer    productId            ;
	protected String     createdAt            ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	//protected Integer    products;
	protected String productsName;
	//protected Integer    promotions;
	protected String promotionsCode;
	protected String promotionsName;

	// Search param
	protected SearchParam<Integer>  idParam               ;                     
	protected SearchParam<Integer>  promotionIdParam      ;                     
	protected SearchParam<Integer>  productIdParam        ;                     
	protected SearchParam<String>   createdAtParam        ;                     
	protected SearchParam<Integer>  productsParam         ;                     
	protected SearchParam<String>   productsNameParam     ;                     
	protected SearchParam<Integer>  promotionsParam       ;                     
	protected SearchParam<String>   promotionsCodeParam   ;                     
	protected SearchParam<String>   promotionsNameParam   ;                     

	// order param
	protected String orderField;
	protected String orderDirection;




}
