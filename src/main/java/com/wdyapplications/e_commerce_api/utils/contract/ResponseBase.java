
/*
 * Created on 2025-08-23 ( Time 06:16:26 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package com.wdyapplications.e_commerce_api.utils.contract;


import com.wdyapplications.e_commerce_api.utils.Status;

/**
 * Response Base
 * 
 * @author Dorgeddy
 *
 */

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class ResponseBase {

	protected Status	status = new Status();
	protected boolean	hasError;
	protected String	sessionUser;
	protected Long		count;
}
