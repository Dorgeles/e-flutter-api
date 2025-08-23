
/*
 * Java dto for entity table products 
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
 * DTO customize for table "products"
 * 
 * @author Smile Back-End generator
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class _ProductsDto implements Cloneable {

    protected Integer    id                   ; // Primary Key

    protected Integer    sellerId             ;
    protected Integer    categoryId           ;
    protected String     name                 ;
    protected String     description          ;
    protected BigDecimal price                ;
    protected BigDecimal promoPrice           ;
    protected Integer    stockQuantity        ;
    protected Integer    minStockAlert        ;
    protected String     status               ;
    protected BigDecimal averageRating        ;
    protected Integer    totalReviews         ;
    protected Integer    totalSales           ;
    protected Boolean    isFeatured           ;
    protected Boolean    isRecommended        ;
	protected String     createdAt            ;
	protected String     updatedAt            ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	//protected Integer    categories;
	protected String categoriesName;
	//protected Integer    users;
	protected String usersEmail;
	protected String usersFirstName;
	protected String usersLastName;

	// Search param
	protected SearchParam<Integer>  idParam               ;                     
	protected SearchParam<Integer>  sellerIdParam         ;                     
	protected SearchParam<Integer>  categoryIdParam       ;                     
	protected SearchParam<String>   nameParam             ;                     
	protected SearchParam<String>   descriptionParam      ;                     
	protected SearchParam<BigDecimal>priceParam            ;                     
	protected SearchParam<BigDecimal>promoPriceParam       ;                     
	protected SearchParam<Integer>  stockQuantityParam    ;                     
	protected SearchParam<Integer>  minStockAlertParam    ;                     
	protected SearchParam<String>   statusParam           ;                     
	protected SearchParam<BigDecimal>averageRatingParam    ;                     
	protected SearchParam<Integer>  totalReviewsParam     ;                     
	protected SearchParam<Integer>  totalSalesParam       ;                     
	protected SearchParam<Boolean>  isFeaturedParam       ;                     
	protected SearchParam<Boolean>  isRecommendedParam    ;                     
	protected SearchParam<String>   createdAtParam        ;                     
	protected SearchParam<String>   updatedAtParam        ;                     
	protected SearchParam<Integer>  categoriesParam       ;                     
	protected SearchParam<String>   categoriesNameParam   ;                     
	protected SearchParam<Integer>  usersParam            ;                     
	protected SearchParam<String>   usersEmailParam       ;                     
	protected SearchParam<String>   usersFirstNameParam   ;                     
	protected SearchParam<String>   usersLastNameParam    ;                     

	// order param
	protected String orderField;
	protected String orderDirection;




}
