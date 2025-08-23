
/*
 * Java dto for entity table promotions 
 * Created on 2025-08-23 ( Time 06:18:37 )
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
 * DTO customize for table "promotions"
 * 
 * @author Smile Back-End generator
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class _PromotionsDto implements Cloneable {

    protected Integer    id                   ; // Primary Key

    protected String     code                 ;
    protected String     name                 ;
    protected String     description          ;
    protected String     type                 ;
    protected BigDecimal value                ;
    protected BigDecimal minOrderAmount       ;
    protected Integer    maxUses              ;
    protected Integer    usedCount            ;
	protected String     startDate            ;
	protected String     endDate              ;
    protected Boolean    isActive             ;
	protected String     createdAt            ;
	protected String     updatedAt            ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------

	// Search param
	protected SearchParam<Integer>  idParam               ;                     
	protected SearchParam<String>   codeParam             ;                     
	protected SearchParam<String>   nameParam             ;                     
	protected SearchParam<String>   descriptionParam      ;                     
	protected SearchParam<String>   typeParam             ;                     
	protected SearchParam<BigDecimal>valueParam            ;                     
	protected SearchParam<BigDecimal>minOrderAmountParam   ;                     
	protected SearchParam<Integer>  maxUsesParam          ;                     
	protected SearchParam<Integer>  usedCountParam        ;                     
	protected SearchParam<String>   startDateParam        ;                     
	protected SearchParam<String>   endDateParam          ;                     
	protected SearchParam<Boolean>  isActiveParam         ;                     
	protected SearchParam<String>   createdAtParam        ;                     
	protected SearchParam<String>   updatedAtParam        ;                     

	// order param
	protected String orderField;
	protected String orderDirection;




}
