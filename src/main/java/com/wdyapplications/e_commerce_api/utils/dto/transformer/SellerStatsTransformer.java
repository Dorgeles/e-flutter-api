

/*
 * Java transformer for entity table seller_stats 
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
 * TRANSFORMER for table "seller_stats"
 * 
 * @author Dorgeddy
 *
 */
@Mapper
public interface SellerStatsTransformer {

	SellerStatsTransformer INSTANCE = Mappers.getMapper(SellerStatsTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.calculatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="calculatedAt"),
		@Mapping(source="entity.users.id", target="sellerId"),
		@Mapping(source="entity.users.firstName", target="usersFirstName"),
		@Mapping(source="entity.users.lastName", target="usersLastName"),
	})
	SellerStatsDto toDto(SellerStats entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<SellerStatsDto> toDtos(List<SellerStats> entities) throws ParseException;

    default SellerStatsDto toLiteDto(SellerStats entity) {
		if (entity == null) {
			return null;
		}
		SellerStatsDto dto = new SellerStatsDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<SellerStatsDto> toLiteDtos(List<SellerStats> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<SellerStatsDto> dtos = new ArrayList<SellerStatsDto>();
		for (SellerStats entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.year", target="year"),
		@Mapping(source="dto.month", target="month"),
		@Mapping(source="dto.totalRevenue", target="totalRevenue"),
		@Mapping(source="dto.totalOrders", target="totalOrders"),
		@Mapping(source="dto.totalProductsSold", target="totalProductsSold"),
		@Mapping(source="dto.averageRating", target="averageRating"),
		@Mapping(source="dto.calculatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="calculatedAt"),
		@Mapping(source="users", target="users"),
	})
    SellerStats toEntity(SellerStatsDto dto, Users users) throws ParseException;

    //List<SellerStats> toEntities(List<SellerStatsDto> dtos) throws ParseException;

}
