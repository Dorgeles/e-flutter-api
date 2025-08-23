
/*
 * Java dto for entity table order_items 
 * Created on 2025-08-23 ( Time 06:18:36 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package com.wdyapplications.e_commerce_api.utils.dto.base;

import java.math.BigDecimal;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;



import com.wdyapplications.e_commerce_api.utils.contract.*;

/**
 * DTO customize for table "order_items"
 * 
 * @author Smile Back-End generator
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class _OrderItemsDto implements Cloneable {

    protected Integer    id                   ; // Primary Key

    protected Integer    orderId              ;
    protected Integer    productId            ;
    protected Integer    sellerId             ;
    protected Integer    quantity             ;
    protected BigDecimal unitPrice            ;
    protected BigDecimal totalPrice           ;
    protected String     status               ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	//protected Integer    products;
	protected String productsName;
	//protected Integer    orders;
	//protected Integer    users;
	protected String usersEmail;
	protected String usersFirstName;
	protected String usersLastName;

	// Search param
	protected SearchParam<Integer>  idParam               ;                     
	protected SearchParam<Integer>  orderIdParam          ;                     
	protected SearchParam<Integer>  productIdParam        ;                     
	protected SearchParam<Integer>  sellerIdParam         ;                     
	protected SearchParam<Integer>  quantityParam         ;                     
	protected SearchParam<BigDecimal>unitPriceParam        ;                     
	protected SearchParam<BigDecimal>totalPriceParam       ;                     
	protected SearchParam<String>   statusParam           ;                     
	protected SearchParam<Integer>  productsParam         ;                     
	protected SearchParam<String>   productsNameParam     ;                     
	protected SearchParam<Integer>  ordersParam           ;                     
	protected SearchParam<Integer>  usersParam            ;                     
	protected SearchParam<String>   usersEmailParam       ;                     
	protected SearchParam<String>   usersFirstNameParam   ;                     
	protected SearchParam<String>   usersLastNameParam    ;                     

	// order param
	protected String orderField;
	protected String orderDirection;




}
