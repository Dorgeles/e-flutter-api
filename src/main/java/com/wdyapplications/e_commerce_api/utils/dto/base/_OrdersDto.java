
/*
 * Java dto for entity table orders 
 * Created on 2025-08-23 ( Time 06:18:36 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package com.wdyapplications.e_commerce_api.utils.dto.base;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;



import com.wdyapplications.e_commerce_api.utils.contract.*;

/**
 * DTO customize for table "orders"
 * 
 * @author Smile Back-End generator
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class _OrdersDto implements Cloneable {

    protected Integer    id                   ; // Primary Key

    protected String     orderNumber          ;
    protected Integer    customerId           ;
    protected String     status               ;
    protected BigDecimal subtotal             ;
    protected BigDecimal taxAmount            ;
    protected BigDecimal shippingCost         ;
    protected BigDecimal discountAmount       ;
    protected BigDecimal totalAmount          ;
    protected String     shippingAddress      ;
    protected String     billingAddress       ;
    protected String     paymentMethod        ;
    protected String     paymentStatus        ;
    protected String     trackingNumber       ;
	protected String     createdAt            ;
	protected String     updatedAt            ;
	protected String     shippedAt            ;
	protected String     deliveredAt          ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	//protected Integer    users;
	protected String usersEmail;
	protected String usersFirstName;
	protected String usersLastName;

	// Search param
	protected SearchParam<Integer>  idParam               ;                     
	protected SearchParam<String>   orderNumberParam      ;                     
	protected SearchParam<Integer>  customerIdParam       ;                     
	protected SearchParam<String>   statusParam           ;                     
	protected SearchParam<BigDecimal>subtotalParam         ;                     
	protected SearchParam<BigDecimal>taxAmountParam        ;                     
	protected SearchParam<BigDecimal>shippingCostParam     ;                     
	protected SearchParam<BigDecimal>discountAmountParam   ;                     
	protected SearchParam<BigDecimal>totalAmountParam      ;                     
	protected SearchParam<String>   shippingAddressParam  ;                     
	protected SearchParam<String>   billingAddressParam   ;                     
	protected SearchParam<String>   paymentMethodParam    ;                     
	protected SearchParam<String>   paymentStatusParam    ;                     
	protected SearchParam<String>   trackingNumberParam   ;                     
	protected SearchParam<String>   createdAtParam        ;                     
	protected SearchParam<String>   updatedAtParam        ;                     
	protected SearchParam<String>   shippedAtParam        ;                     
	protected SearchParam<String>   deliveredAtParam      ;                     
	protected SearchParam<Integer>  usersParam            ;                     
	protected SearchParam<String>   usersEmailParam       ;                     
	protected SearchParam<String>   usersFirstNameParam   ;                     
	protected SearchParam<String>   usersLastNameParam    ;                     

	// order param
	protected String orderField;
	protected String orderDirection;




}
