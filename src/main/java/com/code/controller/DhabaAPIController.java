package com.code.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.data.geo.GeoResults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.code.Entity.DhabaModel;
import com.code.payload.DhabaModelDto;
import com.code.request.LocationRequest;
import com.code.request.SpecificLocationRequest;
import com.code.service.DhabaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dhabacontroller")
@CrossOrigin(origins = "http://localhost:4200")
public class DhabaAPIController {

	private final DhabaService dhabaService;

	@PostMapping("/save")
	public ResponseEntity<DhabaModelDto> addingData(@RequestBody DhabaModelDto dhabaModel) {
		DhabaModelDto savingData = this.dhabaService.savingData(dhabaModel);
		return new ResponseEntity<DhabaModelDto>(savingData, HttpStatus.CREATED);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<DhabaModel>> findAll() {
		List<DhabaModel> data = this.dhabaService.getAll();
		if (data != null) {
			return new ResponseEntity<>(data, HttpStatus.OK);
		} else {
			throw new RuntimeException("data not found in db..");
		}
	}

	@GetMapping("/get")
	public ResponseEntity<GeoResults<DhabaModel>> gettinggeoData(@RequestBody LocationRequest locationRequest) {
		GeoResults<DhabaModel> dhabaDetails = dhabaService.getDhabaDetails(locationRequest);
		return new ResponseEntity<>(dhabaDetails, HttpStatus.OK);
	}

	@GetMapping("/getBycategory/{Category}")
	public ResponseEntity<List<DhabaModel>> getByCategories(@PathVariable String Category) {
		List<DhabaModel> byCategory = this.dhabaService.getByCategory(Category);
		if (byCategory == null) {
			throw new RuntimeException(" not found for this category");
		} else
			return new ResponseEntity<>(byCategory, HttpStatus.FOUND);

	}

	@GetMapping("/gettingSpecific")
	public ResponseEntity<List<DhabaModel>> findingSpecificLocation(
			@RequestBody SpecificLocationRequest specificLocationRequest) {
		List<DhabaModel> findDhabaShopsWithinArea = this.dhabaService.findDhabaShopsWithinArea(specificLocationRequest);
		if (findDhabaShopsWithinArea.isEmpty()) {
			throw new RuntimeException(" not found at this specific location");
		} else {
			return new ResponseEntity<List<DhabaModel>>(findDhabaShopsWithinArea, HttpStatus.FOUND);
		}
	}

	@GetMapping("/regex/{str}/{latitude}/{longitude}/{distance}")
	public ResponseEntity<List<DhabaModel>> findingRegex(@PathVariable String str, @PathVariable double latitude,
			@PathVariable double longitude, @PathVariable double distance) {
		List<DhabaModel> filterOut = null;
		filterOut = this.dhabaService.filterOut(str, latitude, longitude, distance);
		if (filterOut == null) {
			// return new ResponseEntity<>(filterOut, HttpStatus.FOUND);
			throw new RuntimeException("data not found");
		}
		return new ResponseEntity<>(filterOut, HttpStatus.FOUND);
	}

	@GetMapping("/gettingListOfItems")
	public ResponseEntity<List<DhabaModel>> gettingListOfItems(@RequestParam(value = "items") List<String> items) {
		List<DhabaModel> gettingSpecificItem = this.dhabaService.gettingSpecificItem(items);
		if (!gettingSpecificItem.isEmpty()) {
			return new ResponseEntity<>(gettingSpecificItem, HttpStatus.OK);
		} else {
			throw new RuntimeException("Items in the list were not found");
		}
	}

	@GetMapping("/gettingCat/{str}/{latitude}/{longitude}/{distance}")
	public ResponseEntity<List<DhabaModel>> gettingDataBasedOnCateg(@PathVariable String str,
			@PathVariable double latitude, @PathVariable double longitude, @PathVariable double distance) {
		List<DhabaModel> gettingSpecificCategoryInRange = this.dhabaService.gettingSpecificCategoryInRange(str,
				latitude, longitude, distance);
		if (!gettingSpecificCategoryInRange.isEmpty()) {
			return new ResponseEntity<>(gettingSpecificCategoryInRange, HttpStatus.OK);
		} else {
			throw new RuntimeException("Category in the list with sprcific lat,long were not found");
		}

	}

}
