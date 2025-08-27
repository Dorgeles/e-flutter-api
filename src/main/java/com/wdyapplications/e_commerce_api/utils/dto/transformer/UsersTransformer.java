

/*
 * Java transformer for entity table users 
 * Created on 2025-08-27 ( Time 18:33:49 )
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
 * TRANSFORMER for table "users"
 * 
 * @author Dorgeddy
 *
 */
@Mapper
public interface UsersTransformer {

	UsersTransformer INSTANCE = Mappers.getMapper(UsersTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="updatedAt"),
		@Mapping(source="entity.lastLogin", dateFormat="YYYY-MM-dd HH:mm:ss",target="lastLogin"),
		@Mapping(source="entity.resetTokenExpires", dateFormat="YYYY-MM-dd HH:mm:ss",target="resetTokenExpires"),
	})
	UsersDto toDto(Users entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<UsersDto> toDtos(List<Users> entities) throws ParseException;

    default UsersDto toLiteDto(Users entity) {
		if (entity == null) {
			return null;
		}
		UsersDto dto = new UsersDto();
		dto.setId( entity.getId() );
		dto.setFirstName( entity.getFirstName() );
		dto.setLastName( entity.getLastName() );
		return dto;
    }

	default List<UsersDto> toLiteDtos(List<Users> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<UsersDto> dtos = new ArrayList<UsersDto>();
		for (Users entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.email", target="email"),
		@Mapping(source="dto.passwordHash", target="passwordHash"),
		@Mapping(source="dto.firstName", target="firstName"),
		@Mapping(source="dto.lastName", target="lastName"),
		@Mapping(source="dto.phone", target="phone"),
		@Mapping(source="dto.avatarUrl", target="avatarUrl"),
		@Mapping(source="dto.role", target="role"),
		@Mapping(source="dto.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="updatedAt"),
		@Mapping(source="dto.lastLogin", dateFormat="YYYY-MM-dd HH:mm:ss",target="lastLogin"),
		@Mapping(source="dto.isActive", target="isActive"),
		@Mapping(source="dto.resetToken", target="resetToken"),
		@Mapping(source="dto.resetTokenExpires", dateFormat="YYYY-MM-dd HH:mm:ss",target="resetTokenExpires"),
	})
    Users toEntity(UsersDto dto) throws ParseException;

    //List<Users> toEntities(List<UsersDto> dtos) throws ParseException;

}
