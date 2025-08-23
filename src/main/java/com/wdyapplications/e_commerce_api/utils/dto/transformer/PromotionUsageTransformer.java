

/*
 * Java transformer for entity table promotion_usage 
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
 * TRANSFORMER for table "promotion_usage"
 * 
 * @author Dorgeddy
 *
 */
@Mapper
public interface PromotionUsageTransformer {

	PromotionUsageTransformer INSTANCE = Mappers.getMapper(PromotionUsageTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.usedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="usedAt"),
		@Mapping(source="entity.orders.id", target="orderId"),
		@Mapping(source="entity.promotions.id", target="promotionId"),
		@Mapping(source="entity.promotions.code", target="promotionsCode"),
		@Mapping(source="entity.promotions.name", target="promotionsName"),
		@Mapping(source="entity.users.id", target="userId"),
		@Mapping(source="entity.users.firstName", target="usersFirstName"),
		@Mapping(source="entity.users.lastName", target="usersLastName"),
	})
	PromotionUsageDto toDto(PromotionUsage entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<PromotionUsageDto> toDtos(List<PromotionUsage> entities) throws ParseException;

    default PromotionUsageDto toLiteDto(PromotionUsage entity) {
		if (entity == null) {
			return null;
		}
		PromotionUsageDto dto = new PromotionUsageDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<PromotionUsageDto> toLiteDtos(List<PromotionUsage> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<PromotionUsageDto> dtos = new ArrayList<PromotionUsageDto>();
		for (PromotionUsage entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.discountAmount", target="discountAmount"),
		@Mapping(source="dto.usedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="usedAt"),
		@Mapping(source="orders", target="orders"),
		@Mapping(source="promotions", target="promotions"),
		@Mapping(source="users", target="users"),
	})
    PromotionUsage toEntity(PromotionUsageDto dto, Orders orders, Promotions promotions, Users users) throws ParseException;

    //List<PromotionUsage> toEntities(List<PromotionUsageDto> dtos) throws ParseException;

}
