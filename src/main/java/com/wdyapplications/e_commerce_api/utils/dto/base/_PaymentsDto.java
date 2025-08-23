
/*
 * Java dto for entity table payments 
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
 * DTO customize for table "payments"
 * 
 * @author Smile Back-End generator
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class _PaymentsDto implements Cloneable {

    protected Integer    id                   ; // Primary Key

    protected Integer    orderId              ;
    protected String     paymentMethod        ;
    protected BigDecimal amount               ;
    protected String     currency             ;
    protected String     status               ;
    protected String     transactionId        ;
    protected String     gatewayResponse      ;
	protected String     createdAt            ;
	protected String     updatedAt            ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	//protected Integer    orders;

	// Search param
	protected SearchParam<Integer>  idParam               ;                     
	protected SearchParam<Integer>  orderIdParam          ;                     
	protected SearchParam<String>   paymentMethodParam    ;                     
	protected SearchParam<BigDecimal>amountParam           ;                     
	protected SearchParam<String>   currencyParam         ;                     
	protected SearchParam<String>   statusParam           ;                     
	protected SearchParam<String>   transactionIdParam    ;                     
	protected SearchParam<String>   gatewayResponseParam  ;                     
	protected SearchParam<String>   createdAtParam        ;                     
	protected SearchParam<String>   updatedAtParam        ;                     
	protected SearchParam<Integer>  ordersParam           ;                     

	// order param
	protected String orderField;
	protected String orderDirection;




}
