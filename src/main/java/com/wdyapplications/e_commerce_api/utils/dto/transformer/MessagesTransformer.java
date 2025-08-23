

/*
 * Java transformer for entity table messages 
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
 * TRANSFORMER for table "messages"
 * 
 * @author Dorgeddy
 *
 */
@Mapper
public interface MessagesTransformer {

	MessagesTransformer INSTANCE = Mappers.getMapper(MessagesTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="entity.readAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="readAt"),
		@Mapping(source="entity.users.id", target="senderId"),
		@Mapping(source="entity.users.firstName", target="usersFirstName"),
		@Mapping(source="entity.users.lastName", target="usersLastName"),
		@Mapping(source="entity.conversations.id", target="conversationId"),
	})
	MessagesDto toDto(Messages entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<MessagesDto> toDtos(List<Messages> entities) throws ParseException;

    default MessagesDto toLiteDto(Messages entity) {
		if (entity == null) {
			return null;
		}
		MessagesDto dto = new MessagesDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<MessagesDto> toLiteDtos(List<Messages> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<MessagesDto> dtos = new ArrayList<MessagesDto>();
		for (Messages entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.content", target="content"),
		@Mapping(source="dto.type", target="type"),
		@Mapping(source="dto.attachmentUrl", target="attachmentUrl"),
		@Mapping(source="dto.isRead", target="isRead"),
		@Mapping(source="dto.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="dto.readAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="readAt"),
		@Mapping(source="users", target="users"),
		@Mapping(source="conversations", target="conversations"),
	})
    Messages toEntity(MessagesDto dto, Users users, Conversations conversations) throws ParseException;

    //List<Messages> toEntities(List<MessagesDto> dtos) throws ParseException;

}
