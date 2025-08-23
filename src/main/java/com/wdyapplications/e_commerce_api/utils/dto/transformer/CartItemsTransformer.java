

/*
 * Java transformer for entity table cart_items 
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
 * TRANSFORMER for table "cart_items"
 * 
 * @author Dorgeddy
 *
 */
@Mapper
public interface CartItemsTransformer {

	CartItemsTransformer INSTANCE = Mappers.getMapper(CartItemsTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.addedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="addedAt"),
		@Mapping(source="entity.updatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="updatedAt"),
		@Mapping(source="entity.products.id", target="productId"),
		@Mapping(source="entity.products.name", target="productsName"),
		@Mapping(source="entity.carts.id", target="cartId"),
	})
	CartItemsDto toDto(CartItems entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<CartItemsDto> toDtos(List<CartItems> entities) throws ParseException;

    default CartItemsDto toLiteDto(CartItems entity) {
		if (entity == null) {
			return null;
		}
		CartItemsDto dto = new CartItemsDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<CartItemsDto> toLiteDtos(List<CartItems> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<CartItemsDto> dtos = new ArrayList<CartItemsDto>();
		for (CartItems entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.quantity", target="quantity"),
		@Mapping(source="dto.unitPrice", target="unitPrice"),
		@Mapping(source="dto.addedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="addedAt"),
		@Mapping(source="dto.updatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="updatedAt"),
		@Mapping(source="products", target="products"),
		@Mapping(source="carts", target="carts"),
	})
    CartItems toEntity(CartItemsDto dto, Products products, Carts carts) throws ParseException;

    //List<CartItems> toEntities(List<CartItemsDto> dtos) throws ParseException;

}
