

/*
 * Java transformer for entity table reviews 
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
 * TRANSFORMER for table "reviews"
 * 
 * @author Dorgeddy
 *
 */
@Mapper
public interface ReviewsTransformer {

	ReviewsTransformer INSTANCE = Mappers.getMapper(ReviewsTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="updatedAt"),
		@Mapping(source="entity.products.id", target="productId"),
		@Mapping(source="entity.products.name", target="productsName"),
		@Mapping(source="entity.users.id", target="userId"),
		@Mapping(source="entity.users.firstName", target="usersFirstName"),
		@Mapping(source="entity.users.lastName", target="usersLastName"),
	})
	ReviewsDto toDto(Reviews entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<ReviewsDto> toDtos(List<Reviews> entities) throws ParseException;

    default ReviewsDto toLiteDto(Reviews entity) {
		if (entity == null) {
			return null;
		}
		ReviewsDto dto = new ReviewsDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<ReviewsDto> toLiteDtos(List<Reviews> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<ReviewsDto> dtos = new ArrayList<ReviewsDto>();
		for (Reviews entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.rating", target="rating"),
		@Mapping(source="dto.comment", target="comment"),
		@Mapping(source="dto.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="updatedAt"),
		@Mapping(source="dto.isVerifiedPurchase", target="isVerifiedPurchase"),
		@Mapping(source="products", target="products"),
		@Mapping(source="users", target="users"),
	})
    Reviews toEntity(ReviewsDto dto, Products products, Users users) throws ParseException;

    //List<Reviews> toEntities(List<ReviewsDto> dtos) throws ParseException;

}
