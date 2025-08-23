

/*
 * Java transformer for entity table product_images 
 * Created on 2025-08-23 ( Time 06:18:36 )
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
 * TRANSFORMER for table "product_images"
 * 
 * @author Dorgeddy
 *
 */
@Mapper
public interface ProductImagesTransformer {

	ProductImagesTransformer INSTANCE = Mappers.getMapper(ProductImagesTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.uploadedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="uploadedAt"),
		@Mapping(source="entity.products.id", target="productId"),
		@Mapping(source="entity.products.name", target="productsName"),
	})
	ProductImagesDto toDto(ProductImages entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<ProductImagesDto> toDtos(List<ProductImages> entities) throws ParseException;

    default ProductImagesDto toLiteDto(ProductImages entity) {
		if (entity == null) {
			return null;
		}
		ProductImagesDto dto = new ProductImagesDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<ProductImagesDto> toLiteDtos(List<ProductImages> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<ProductImagesDto> dtos = new ArrayList<ProductImagesDto>();
		for (ProductImages entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.imageUrl", target="imageUrl"),
		@Mapping(source="dto.altText", target="altText"),
		@Mapping(source="dto.isPrimary", target="isPrimary"),
		@Mapping(source="dto.sortOrder", target="sortOrder"),
		@Mapping(source="dto.uploadedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="uploadedAt"),
		@Mapping(source="products", target="products"),
	})
    ProductImages toEntity(ProductImagesDto dto, Products products) throws ParseException;

    //List<ProductImages> toEntities(List<ProductImagesDto> dtos) throws ParseException;

}
