

/*
 * Java transformer for entity table orders 
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
 * TRANSFORMER for table "orders"
 * 
 * @author Dorgeddy
 *
 */
@Mapper
public interface OrdersTransformer {

	OrdersTransformer INSTANCE = Mappers.getMapper(OrdersTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="updatedAt"),
		@Mapping(source="entity.shippedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="shippedAt"),
		@Mapping(source="entity.deliveredAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="deliveredAt"),
		@Mapping(source="entity.users.id", target="customerId"),
		@Mapping(source="entity.users.firstName", target="usersFirstName"),
		@Mapping(source="entity.users.lastName", target="usersLastName"),
	})
	OrdersDto toDto(Orders entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<OrdersDto> toDtos(List<Orders> entities) throws ParseException;

    default OrdersDto toLiteDto(Orders entity) {
		if (entity == null) {
			return null;
		}
		OrdersDto dto = new OrdersDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<OrdersDto> toLiteDtos(List<Orders> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<OrdersDto> dtos = new ArrayList<OrdersDto>();
		for (Orders entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.orderNumber", target="orderNumber"),
		@Mapping(source="dto.status", target="status"),
		@Mapping(source="dto.subtotal", target="subtotal"),
		@Mapping(source="dto.taxAmount", target="taxAmount"),
		@Mapping(source="dto.shippingCost", target="shippingCost"),
		@Mapping(source="dto.discountAmount", target="discountAmount"),
		@Mapping(source="dto.totalAmount", target="totalAmount"),
		@Mapping(source="dto.shippingAddress", target="shippingAddress"),
		@Mapping(source="dto.billingAddress", target="billingAddress"),
		@Mapping(source="dto.paymentMethod", target="paymentMethod"),
		@Mapping(source="dto.paymentStatus", target="paymentStatus"),
		@Mapping(source="dto.trackingNumber", target="trackingNumber"),
		@Mapping(source="dto.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="updatedAt"),
		@Mapping(source="dto.shippedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="shippedAt"),
		@Mapping(source="dto.deliveredAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="deliveredAt"),
		@Mapping(source="users", target="users"),
	})
    Orders toEntity(OrdersDto dto, Users users) throws ParseException;

    //List<Orders> toEntities(List<OrdersDto> dtos) throws ParseException;

}
