

/*
 * Java transformer for entity table payments 
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
 * TRANSFORMER for table "payments"
 * 
 * @author Dorgeddy
 *
 */
@Mapper
public interface PaymentsTransformer {

	PaymentsTransformer INSTANCE = Mappers.getMapper(PaymentsTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="updatedAt"),
		@Mapping(source="entity.orders.id", target="orderId"),
	})
	PaymentsDto toDto(Payments entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<PaymentsDto> toDtos(List<Payments> entities) throws ParseException;

    default PaymentsDto toLiteDto(Payments entity) {
		if (entity == null) {
			return null;
		}
		PaymentsDto dto = new PaymentsDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<PaymentsDto> toLiteDtos(List<Payments> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<PaymentsDto> dtos = new ArrayList<PaymentsDto>();
		for (Payments entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.paymentMethod", target="paymentMethod"),
		@Mapping(source="dto.amount", target="amount"),
		@Mapping(source="dto.currency", target="currency"),
		@Mapping(source="dto.status", target="status"),
		@Mapping(source="dto.transactionId", target="transactionId"),
		@Mapping(source="dto.gatewayResponse", target="gatewayResponse"),
		@Mapping(source="dto.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="updatedAt"),
		@Mapping(source="orders", target="orders"),
	})
    Payments toEntity(PaymentsDto dto, Orders orders) throws ParseException;

    //List<Payments> toEntities(List<PaymentsDto> dtos) throws ParseException;

}
