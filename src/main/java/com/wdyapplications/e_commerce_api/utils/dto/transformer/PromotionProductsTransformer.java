

/*
 * Java transformer for entity table promotion_products 
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
 * TRANSFORMER for table "promotion_products"
 * 
 * @author Dorgeddy
 *
 */
@Mapper
public interface PromotionProductsTransformer {

	PromotionProductsTransformer INSTANCE = Mappers.getMapper(PromotionProductsTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="entity.products.id", target="productId"),
		@Mapping(source="entity.products.name", target="productsName"),
		@Mapping(source="entity.promotions.id", target="promotionId"),
		@Mapping(source="entity.promotions.code", target="promotionsCode"),
		@Mapping(source="entity.promotions.name", target="promotionsName"),
	})
	PromotionProductsDto toDto(PromotionProducts entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<PromotionProductsDto> toDtos(List<PromotionProducts> entities) throws ParseException;

    default PromotionProductsDto toLiteDto(PromotionProducts entity) {
		if (entity == null) {
			return null;
		}
		PromotionProductsDto dto = new PromotionProductsDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<PromotionProductsDto> toLiteDtos(List<PromotionProducts> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<PromotionProductsDto> dtos = new ArrayList<PromotionProductsDto>();
		for (PromotionProducts entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="products", target="products"),
		@Mapping(source="promotions", target="promotions"),
	})
    PromotionProducts toEntity(PromotionProductsDto dto, Products products, Promotions promotions) throws ParseException;

    //List<PromotionProducts> toEntities(List<PromotionProductsDto> dtos) throws ParseException;

}
