package com.code.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class LocationRequest {

	private double Latitude;
	private double Longitude;
	private double Distance;

}
