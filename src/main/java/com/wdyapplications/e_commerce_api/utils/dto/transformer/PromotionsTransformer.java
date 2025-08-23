

/*
 * Java transformer for entity table promotions 
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
 * TRANSFORMER for table "promotions"
 * 
 * @author Dorgeddy
 *
 */
@Mapper
public interface PromotionsTransformer {

	PromotionsTransformer INSTANCE = Mappers.getMapper(PromotionsTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.startDate", dateFormat="YYYY-MM-dd HH:mm:ss",target="startDate"),
		@Mapping(source="entity.endDate", dateFormat="YYYY-MM-dd HH:mm:ss",target="endDate"),
		@Mapping(source="entity.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="updatedAt"),
	})
	PromotionsDto toDto(Promotions entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<PromotionsDto> toDtos(List<Promotions> entities) throws ParseException;

    default PromotionsDto toLiteDto(Promotions entity) {
		if (entity == null) {
			return null;
		}
		PromotionsDto dto = new PromotionsDto();
		dto.setId( entity.getId() );
		dto.setName( entity.getName() );
		return dto;
    }

	default List<PromotionsDto> toLiteDtos(List<Promotions> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<PromotionsDto> dtos = new ArrayList<PromotionsDto>();
		for (Promotions entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.code", target="code"),
		@Mapping(source="dto.name", target="name"),
		@Mapping(source="dto.description", target="description"),
		@Mapping(source="dto.type", target="type"),
		@Mapping(source="dto.value", target="value"),
		@Mapping(source="dto.minOrderAmount", target="minOrderAmount"),
		@Mapping(source="dto.maxUses", target="maxUses"),
		@Mapping(source="dto.usedCount", target="usedCount"),
		@Mapping(source="dto.startDate", dateFormat="YYYY-MM-dd HH:mm:ss",target="startDate"),
		@Mapping(source="dto.endDate", dateFormat="YYYY-MM-dd HH:mm:ss",target="endDate"),
		@Mapping(source="dto.isActive", target="isActive"),
		@Mapping(source="dto.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="updatedAt"),
	})
    Promotions toEntity(PromotionsDto dto) throws ParseException;

    //List<Promotions> toEntities(List<PromotionsDto> dtos) throws ParseException;

}
