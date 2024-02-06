package com.code.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SpecificLocationRequest {

	public double minLongitude;
	public double minLatitude;
	public double maxLongitude;
	public double maxLatitude;
}
