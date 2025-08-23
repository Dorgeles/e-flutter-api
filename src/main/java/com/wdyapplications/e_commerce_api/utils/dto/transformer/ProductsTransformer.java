

/*
 * Java transformer for entity table products 
 * Created on 2025-08-23 ( Time 06:18:37 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package com.wdyapplications.e_commerce_api.utils.dto.transformer;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.wdyapplications.e_commerce_api.utils.contract.*;
import com.wdyapplications.e_commerce_api.utils.dto.*;
import com.wdyapplications.e_commerce_api.dao.entity.*;


/**
 * TRANSFORMER for table "products"
 * 
 * @author Dorgeddy
 *
 */
@Mapper
public interface ProductsTransformer {

	ProductsTransformer INSTANCE = Mappers.getMapper(ProductsTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="updatedAt"),
		@Mapping(source="entity.categories.id", target="categoryId"),
		@Mapping(source="entity.categories.name", target="categoriesName"),
		@Mapping(source="entity.users.id", target="sellerId"),
		@Mapping(source="entity.users.firstName", target="usersFirstName"),
		@Mapping(source="entity.users.lastName", target="usersLastName"),
	})
	ProductsDto toDto(Products entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<ProductsDto> toDtos(List<Products> entities) throws ParseException;

    default ProductsDto toLiteDto(Products entity) {
		if (entity == null) {
			return null;
		}
		ProductsDto dto = new ProductsDto();
		dto.setId( entity.getId() );
		dto.setName( entity.getName() );
		return dto;
    }

	default List<ProductsDto> toLiteDtos(List<Products> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<ProductsDto> dtos = new ArrayList<ProductsDto>();
		for (Products entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.name", target="name"),
		@Mapping(source="dto.description", target="description"),
		@Mapping(source="dto.price", target="price"),
		@Mapping(source="dto.promoPrice", target="promoPrice"),
		@Mapping(source="dto.stockQuantity", target="stockQuantity"),
		@Mapping(source="dto.minStockAlert", target="minStockAlert"),
		@Mapping(source="dto.status", target="status"),
		@Mapping(source="dto.averageRating", target="averageRating"),
		@Mapping(source="dto.totalReviews", target="totalReviews"),
		@Mapping(source="dto.totalSales", target="totalSales"),
		@Mapping(source="dto.isFeatured", target="isFeatured"),
		@Mapping(source="dto.isRecommended", target="isRecommended"),
		@Mapping(source="dto.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="updatedAt"),
		@Mapping(source="categories", target="categories"),
		@Mapping(source="users", target="users"),
	})
    Products toEntity(ProductsDto dto, Categories categories, Users users) throws ParseException;

    //List<Products> toEntities(List<ProductsDto> dtos) throws ParseException;

}
