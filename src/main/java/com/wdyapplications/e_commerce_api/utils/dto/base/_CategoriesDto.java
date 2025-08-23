
/*
 * Java dto for entity table categories 
 * Created on 2025-08-23 ( Time 06:18:36 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package com.wdyapplications.e_commerce_api.utils.dto.base;

import java.util.Date;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;



import com.wdyapplications.e_commerce_api.utils.contract.*;

/**
 * DTO customize for table "categories"
 * 
 * @author Smile Back-End generator
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class _CategoriesDto implements Cloneable {

    protected Integer    id                   ; // Primary Key

    protected String     name                 ;
    protected String     description          ;
    protected String     iconUrl              ;
    protected Integer    parentId             ;
    protected Boolean    isActive             ;
    protected Integer    sortOrder            ;
	protected String     createdAt            ;
	protected String     updatedAt            ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	//protected Integer    categories;
	protected String categoriesName;

	// Search param
	protected SearchParam<Integer>  idParam               ;                     
	protected SearchParam<String>   nameParam             ;                     
	protected SearchParam<String>   descriptionParam      ;                     
	protected SearchParam<String>   iconUrlParam          ;                     
	protected SearchParam<Integer>  parentIdParam         ;                     
	protected SearchParam<Boolean>  isActiveParam         ;                     
	protected SearchParam<Integer>  sortOrderParam        ;                     
	protected SearchParam<String>   createdAtParam        ;                     
	protected SearchParam<String>   updatedAtParam        ;                     
	protected SearchParam<Integer>  categoriesParam       ;                     
	protected SearchParam<String>   categoriesNameParam   ;                     

	// order param
	protected String orderField;
	protected String orderDirection;




}
