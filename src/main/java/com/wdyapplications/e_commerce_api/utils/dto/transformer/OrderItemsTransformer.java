

/*
 * Java transformer for entity table order_items 
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
 * TRANSFORMER for table "order_items"
 * 
 * @author Dorgeddy
 *
 */
@Mapper
public interface OrderItemsTransformer {

	OrderItemsTransformer INSTANCE = Mappers.getMapper(OrderItemsTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.products.id", target="productId"),
		@Mapping(source="entity.products.name", target="productsName"),
		@Mapping(source="entity.orders.id", target="orderId"),
		@Mapping(source="entity.users.id", target="sellerId"),
		@Mapping(source="entity.users.firstName", target="usersFirstName"),
		@Mapping(source="entity.users.lastName", target="usersLastName"),
	})
	OrderItemsDto toDto(OrderItems entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<OrderItemsDto> toDtos(List<OrderItems> entities) throws ParseException;

    default OrderItemsDto toLiteDto(OrderItems entity) {
		if (entity == null) {
			return null;
		}
		OrderItemsDto dto = new OrderItemsDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<OrderItemsDto> toLiteDtos(List<OrderItems> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<OrderItemsDto> dtos = new ArrayList<OrderItemsDto>();
		for (OrderItems entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.quantity", target="quantity"),
		@Mapping(source="dto.unitPrice", target="unitPrice"),
		@Mapping(source="dto.totalPrice", target="totalPrice"),
		@Mapping(source="dto.status", target="status"),
		@Mapping(source="products", target="products"),
		@Mapping(source="orders", target="orders"),
		@Mapping(source="users", target="users"),
	})
    OrderItems toEntity(OrderItemsDto dto, Products products, Orders orders, Users users) throws ParseException;

    //List<OrderItems> toEntities(List<OrderItemsDto> dtos) throws ParseException;

}
