

/*
 * Java transformer for entity table conversations 
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
 * TRANSFORMER for table "conversations"
 * 
 * @author Dorgeddy
 *
 */
@Mapper
public interface ConversationsTransformer {

	ConversationsTransformer INSTANCE = Mappers.getMapper(ConversationsTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="updatedAt"),
		@Mapping(source="entity.lastMessageAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="lastMessageAt"),
		@Mapping(source="entity.users2.id", target="sellerId"),
		@Mapping(source="entity.users2.firstName", target="usersFirstName2"),
		@Mapping(source="entity.users2.lastName", target="usersLastName2"),
		@Mapping(source="entity.users.id", target="clientId"),
		@Mapping(source="entity.users.firstName", target="usersFirstName"),
		@Mapping(source="entity.users.lastName", target="usersLastName"),
	})
	ConversationsDto toDto(Conversations entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<ConversationsDto> toDtos(List<Conversations> entities) throws ParseException;

    default ConversationsDto toLiteDto(Conversations entity) {
		if (entity == null) {
			return null;
		}
		ConversationsDto dto = new ConversationsDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<ConversationsDto> toLiteDtos(List<Conversations> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<ConversationsDto> dtos = new ArrayList<ConversationsDto>();
		for (Conversations entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="updatedAt"),
		@Mapping(source="dto.lastMessageAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="lastMessageAt"),
		@Mapping(source="dto.isActive", target="isActive"),
		@Mapping(source="users2", target="users2"),
		@Mapping(source="users", target="users"),
	})
    Conversations toEntity(ConversationsDto dto, Users users2, Users users) throws ParseException;

    //List<Conversations> toEntities(List<ConversationsDto> dtos) throws ParseException;

}
