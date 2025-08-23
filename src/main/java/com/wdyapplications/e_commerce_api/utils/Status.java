
/*
 * Created on 2025-08-23 ( Time 06:16:26 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package com.wdyapplications.e_commerce_api.utils;

 import jakarta.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * Status
 * 
 * @author Dorgeddy
 *
 */

 import lombok.Data;
 import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@XmlRootElement
@JsonInclude(Include.NON_NULL)
public class Status {
	private String	code;
	private String	message;
}
