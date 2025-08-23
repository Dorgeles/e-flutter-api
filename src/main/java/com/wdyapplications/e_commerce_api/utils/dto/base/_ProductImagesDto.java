
/*
 * Java dto for entity table product_images 
 * Created on 2025-08-23 ( Time 06:18:36 )
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
 * DTO customize for table "product_images"
 * 
 * @author Smile Back-End generator
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class _ProductImagesDto implements Cloneable {

    protected Integer    id                   ; // Primary Key

    protected Integer    productId            ;
    protected String     imageUrl             ;
    protected String     altText              ;
    protected Boolean    isPrimary            ;
    protected Integer    sortOrder            ;
	protected String     uploadedAt           ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	//protected Integer    products;
	protected String productsName;

	// Search param
	protected SearchParam<Integer>  idParam               ;                     
	protected SearchParam<Integer>  productIdParam        ;                     
	protected SearchParam<String>   imageUrlParam         ;                     
	protected SearchParam<String>   altTextParam          ;                     
	protected SearchParam<Boolean>  isPrimaryParam        ;                     
	protected SearchParam<Integer>  sortOrderParam        ;                     
	protected SearchParam<String>   uploadedAtParam       ;                     
	protected SearchParam<Integer>  productsParam         ;                     
	protected SearchParam<String>   productsNameParam     ;                     

	// order param
	protected String orderField;
	protected String orderDirection;




}
