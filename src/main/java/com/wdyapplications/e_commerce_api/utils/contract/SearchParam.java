
/*
 * Created on 2025-08-23 ( Time 06:16:26 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package com.wdyapplications.e_commerce_api.utils.contract;

import java.util.List;


/**
 * Search Param
 * 
 * @author Dorgeddy
 *
 */

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class SearchParam<T> {
	String  operator;
    T       start;
    T       end;
    List<T> datas;
    Float   distance;

    public SearchParam(String operator, T start, T end, List<T> datas, Float distance) {
        super();
        this.operator = operator;
        this.start    = start;
        this.end      = end;
        this.datas    = datas;
        this.distance = distance;
    }

    public SearchParam(String operator, T start, T end, List<T> datas) {
        this(operator, start, end, datas, null);
    }

    public SearchParam(String operator) {
        this(operator, null, null, null);
    }

    public SearchParam(String operator, T start, T end) {
        this(operator, start, end, null);
    }

    public SearchParam(String operator, List<T> datas) {
        this(operator, null, null, datas);
    }

    public SearchParam(String operator, Float distance) {
        this(operator, null, null, null, distance);
    }
}
