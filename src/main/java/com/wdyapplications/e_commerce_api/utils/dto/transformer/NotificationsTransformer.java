

/*
 * Java transformer for entity table notifications 
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
 * TRANSFORMER for table "notifications"
 * 
 * @author Dorgeddy
 *
 */
@Mapper
public interface NotificationsTransformer {

	NotificationsTransformer INSTANCE = Mappers.getMapper(NotificationsTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="entity.readAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="readAt"),
		@Mapping(source="entity.users.id", target="userId"),
		@Mapping(source="entity.users.firstName", target="usersFirstName"),
		@Mapping(source="entity.users.lastName", target="usersLastName"),
	})
	NotificationsDto toDto(Notifications entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<NotificationsDto> toDtos(List<Notifications> entities) throws ParseException;

    default NotificationsDto toLiteDto(Notifications entity) {
		if (entity == null) {
			return null;
		}
		NotificationsDto dto = new NotificationsDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<NotificationsDto> toLiteDtos(List<Notifications> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<NotificationsDto> dtos = new ArrayList<NotificationsDto>();
		for (Notifications entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.title", target="title"),
		@Mapping(source="dto.message", target="message"),
		@Mapping(source="dto.type", target="type"),
		@Mapping(source="dto.referenceId", target="referenceId"),
		@Mapping(source="dto.isRead", target="isRead"),
		@Mapping(source="dto.isPushSent", target="isPushSent"),
		@Mapping(source="dto.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="dto.readAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="readAt"),
		@Mapping(source="users", target="users"),
	})
    Notifications toEntity(NotificationsDto dto, Users users) throws ParseException;

    //List<Notifications> toEntities(List<NotificationsDto> dtos) throws ParseException;

}
