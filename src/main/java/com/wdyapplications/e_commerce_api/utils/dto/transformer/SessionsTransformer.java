

/*
 * Java transformer for entity table sessions 
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
 * TRANSFORMER for table "sessions"
 * 
 * @author Dorgeddy
 *
 */
@Mapper
public interface SessionsTransformer {

	SessionsTransformer INSTANCE = Mappers.getMapper(SessionsTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.expiresAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="expiresAt"),
		@Mapping(source="entity.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="entity.users.id", target="userId"),
		@Mapping(source="entity.users.firstName", target="usersFirstName"),
		@Mapping(source="entity.users.lastName", target="usersLastName"),
	})
	SessionsDto toDto(Sessions entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<SessionsDto> toDtos(List<Sessions> entities) throws ParseException;

    default SessionsDto toLiteDto(Sessions entity) {
		if (entity == null) {
			return null;
		}
		SessionsDto dto = new SessionsDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<SessionsDto> toLiteDtos(List<Sessions> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<SessionsDto> dtos = new ArrayList<SessionsDto>();
		for (Sessions entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.token", target="token"),
		@Mapping(source="dto.expiresAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="expiresAt"),
		@Mapping(source="dto.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="dto.deviceInfo", target="deviceInfo"),
		@Mapping(source="dto.ipAddress", target="ipAddress"),
		@Mapping(source="users", target="users"),
	})
    Sessions toEntity(SessionsDto dto, Users users) throws ParseException;

    //List<Sessions> toEntities(List<SessionsDto> dtos) throws ParseException;

}
