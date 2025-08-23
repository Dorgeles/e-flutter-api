

/*
 * Java transformer for entity table addresses 
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
 * TRANSFORMER for table "addresses"
 * 
 * @author Dorgeddy
 *
 */
@Mapper
public interface AddressesTransformer {

	AddressesTransformer INSTANCE = Mappers.getMapper(AddressesTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="updatedAt"),
		@Mapping(source="entity.users.id", target="userId"),
		@Mapping(source="entity.users.firstName", target="usersFirstName"),
		@Mapping(source="entity.users.lastName", target="usersLastName"),
	})
	AddressesDto toDto(Addresses entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<AddressesDto> toDtos(List<Addresses> entities) throws ParseException;

    default AddressesDto toLiteDto(Addresses entity) {
		if (entity == null) {
			return null;
		}
		AddressesDto dto = new AddressesDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<AddressesDto> toLiteDtos(List<Addresses> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<AddressesDto> dtos = new ArrayList<AddressesDto>();
		for (Addresses entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.label", target="label"),
		@Mapping(source="dto.streetAddress", target="streetAddress"),
		@Mapping(source="dto.city", target="city"),
		@Mapping(source="dto.state", target="state"),
		@Mapping(source="dto.postalCode", target="postalCode"),
		@Mapping(source="dto.country", target="country"),
		@Mapping(source="dto.isDefault", target="isDefault"),
		@Mapping(source="dto.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="updatedAt"),
		@Mapping(source="users", target="users"),
	})
    Addresses toEntity(AddressesDto dto, Users users) throws ParseException;

    //List<Addresses> toEntities(List<AddressesDto> dtos) throws ParseException;

}
