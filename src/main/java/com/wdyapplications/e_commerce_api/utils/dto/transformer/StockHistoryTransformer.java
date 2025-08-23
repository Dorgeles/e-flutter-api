

/*
 * Java transformer for entity table stock_history 
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
 * TRANSFORMER for table "stock_history"
 * 
 * @author Dorgeddy
 *
 */
@Mapper
public interface StockHistoryTransformer {

	StockHistoryTransformer INSTANCE = Mappers.getMapper(StockHistoryTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="entity.products.id", target="productId"),
		@Mapping(source="entity.products.name", target="productsName"),
	})
	StockHistoryDto toDto(StockHistory entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<StockHistoryDto> toDtos(List<StockHistory> entities) throws ParseException;

    default StockHistoryDto toLiteDto(StockHistory entity) {
		if (entity == null) {
			return null;
		}
		StockHistoryDto dto = new StockHistoryDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<StockHistoryDto> toLiteDtos(List<StockHistory> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<StockHistoryDto> dtos = new ArrayList<StockHistoryDto>();
		for (StockHistory entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.oldQuantity", target="oldQuantity"),
		@Mapping(source="dto.newQuantity", target="newQuantity"),
		@Mapping(source="dto.changeQuantity", target="changeQuantity"),
		@Mapping(source="dto.reason", target="reason"),
		@Mapping(source="dto.referenceId", target="referenceId"),
		@Mapping(source="dto.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="products", target="products"),
	})
    StockHistory toEntity(StockHistoryDto dto, Products products) throws ParseException;

    //List<StockHistory> toEntities(List<StockHistoryDto> dtos) throws ParseException;

}
