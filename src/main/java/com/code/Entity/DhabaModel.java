package com.code.Entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "dhaba")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DhabaModel {

	@Id
	private Long _id;
//	@Field("name_")
	private String name;
	//@Field("category_")
	private String category;
	//@Field("address_")
	private String address;

	@Field("items")
	private List<String> items;

	@GeoSpatialIndexed(name = "Location")
	private GeoJsonPoint location;

}
