

/*
 * Java transformer for entity table carts 
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
 * TRANSFORMER for table "carts"
 * 
 * @author Dorgeddy
 *
 */
@Mapper
public interface CartsTransformer {

	CartsTransformer INSTANCE = Mappers.getMapper(CartsTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="updatedAt"),
		@Mapping(source="entity.users.id", target="userId"),
		@Mapping(source="entity.users.firstName", target="usersFirstName"),
		@Mapping(source="entity.users.lastName", target="usersLastName"),
	})
	CartsDto toDto(Carts entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<CartsDto> toDtos(List<Carts> entities) throws ParseException;

    default CartsDto toLiteDto(Carts entity) {
		if (entity == null) {
			return null;
		}
		CartsDto dto = new CartsDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<CartsDto> toLiteDtos(List<Carts> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<CartsDto> dtos = new ArrayList<CartsDto>();
		for (Carts entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="updatedAt"),
		@Mapping(source="users", target="users"),
	})
    Carts toEntity(CartsDto dto, Users users) throws ParseException;

    //List<Carts> toEntities(List<CartsDto> dtos) throws ParseException;

}
