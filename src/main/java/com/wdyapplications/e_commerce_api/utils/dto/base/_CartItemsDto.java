
/*
 * Java dto for entity table cart_items 
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
 * DTO customize for table "cart_items"
 * 
 * @author Smile Back-End generator
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class _CartItemsDto implements Cloneable {

    protected Integer    id                   ; // Primary Key

    protected Integer    cartId               ;
    protected Integer    productId            ;
    protected Integer    quantity             ;
    protected BigDecimal unitPrice            ;
	protected String     addedAt              ;
	protected String     updatedAt            ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	//protected Integer    products;
	protected String productsName;
	//protected Integer    carts;

	// Search param
	protected SearchParam<Integer>  idParam               ;                     
	protected SearchParam<Integer>  cartIdParam           ;                     
	protected SearchParam<Integer>  productIdParam        ;                     
	protected SearchParam<Integer>  quantityParam         ;                     
	protected SearchParam<BigDecimal>unitPriceParam        ;                     
	protected SearchParam<String>   addedAtParam          ;                     
	protected SearchParam<String>   updatedAtParam        ;                     
	protected SearchParam<Integer>  productsParam         ;                     
	protected SearchParam<String>   productsNameParam     ;                     
	protected SearchParam<Integer>  cartsParam            ;                     

	// order param
	protected String orderField;
	protected String orderDirection;




}
