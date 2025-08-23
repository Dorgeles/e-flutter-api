

/*
 * Java transformer for entity table admin_stats 
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
 * TRANSFORMER for table "admin_stats"
 * 
 * @author Dorgeddy
 *
 */
@Mapper
public interface AdminStatsTransformer {

	AdminStatsTransformer INSTANCE = Mappers.getMapper(AdminStatsTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.calculatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="calculatedAt"),
	})
	AdminStatsDto toDto(AdminStats entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<AdminStatsDto> toDtos(List<AdminStats> entities) throws ParseException;

    default AdminStatsDto toLiteDto(AdminStats entity) {
		if (entity == null) {
			return null;
		}
		AdminStatsDto dto = new AdminStatsDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<AdminStatsDto> toLiteDtos(List<AdminStats> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<AdminStatsDto> dtos = new ArrayList<AdminStatsDto>();
		for (AdminStats entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.statsDate", target="statsDate"),
		@Mapping(source="dto.totalRevenue", target="totalRevenue"),
		@Mapping(source="dto.totalOrders", target="totalOrders"),
		@Mapping(source="dto.activeUsers", target="activeUsers"),
		@Mapping(source="dto.newUsers", target="newUsers"),
		@Mapping(source="dto.totalProducts", target="totalProducts"),
		@Mapping(source="dto.outOfStockProducts", target="outOfStockProducts"),
		@Mapping(source="dto.calculatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="calculatedAt"),
	})
    AdminStats toEntity(AdminStatsDto dto) throws ParseException;

    //List<AdminStats> toEntities(List<AdminStatsDto> dtos) throws ParseException;

}
