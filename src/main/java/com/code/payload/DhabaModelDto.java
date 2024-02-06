package com.code.payload;

import java.util.List;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import lombok.Data;

@Data
public class DhabaModelDto {
	private Long _id;
	private String name;
	private String category;
	private String address;
	private List<String> items;
	private GeoJsonPoint location;
}
