

/*
 * Java transformer for entity table categories 
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
 * TRANSFORMER for table "categories"
 * 
 * @author Dorgeddy
 *
 */
@Mapper
public interface CategoriesTransformer {

	CategoriesTransformer INSTANCE = Mappers.getMapper(CategoriesTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="updatedAt"),
		@Mapping(source="entity.categories.id", target="parentId"),
		@Mapping(source="entity.categories.name", target="categoriesName"),
	})
	CategoriesDto toDto(Categories entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<CategoriesDto> toDtos(List<Categories> entities) throws ParseException;

    default CategoriesDto toLiteDto(Categories entity) {
		if (entity == null) {
			return null;
		}
		CategoriesDto dto = new CategoriesDto();
		dto.setId( entity.getId() );
		dto.setName( entity.getName() );
		return dto;
    }

	default List<CategoriesDto> toLiteDtos(List<Categories> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<CategoriesDto> dtos = new ArrayList<CategoriesDto>();
		for (Categories entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.name", target="name"),
		@Mapping(source="dto.description", target="description"),
		@Mapping(source="dto.iconUrl", target="iconUrl"),
		@Mapping(source="dto.isActive", target="isActive"),
		@Mapping(source="dto.sortOrder", target="sortOrder"),
		@Mapping(source="dto.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="updatedAt"),
		@Mapping(source="categories", target="categories"),
	})
    Categories toEntity(CategoriesDto dto, Categories categories) throws ParseException;

    //List<Categories> toEntities(List<CategoriesDto> dtos) throws ParseException;

}
