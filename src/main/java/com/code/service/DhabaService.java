package com.code.service;

import java.util.List;

import org.springframework.data.geo.Box;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.code.Entity.DhabaModel;
import com.code.conversion.Converion;
import com.code.payload.DhabaModelDto;
import com.code.repositories.Dhabarepositories;
import com.code.request.LocationRequest;
import com.code.request.SpecificLocationRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DhabaService {

	private final Dhabarepositories dhabarepositories;
	
    private final MongoTemplate mongoTemplate;

	private final Converion converion;

	public DhabaModelDto savingData(DhabaModelDto dhabaModel) {
		DhabaModel dtoToEntity = converion.dtoToEntity(dhabaModel);
		DhabaModel save = dhabarepositories.save(dtoToEntity);
		DhabaModelDto entityToDto = converion.entityToDto(save);
		return entityToDto;
	}

	public List<DhabaModel> getAll() {

		List<DhabaModel> findAll = null;
		try {
			findAll = this.dhabarepositories.findAll();
			if (findAll != null) {
				return findAll;
			} else {
				throw new RuntimeException("data empty in database");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return findAll;
	}

	public GeoResults<DhabaModel> getDhabaDetails(LocationRequest locationRequest) {
		System.out.println("Latitude: " + locationRequest.getLatitude() + ", Longitude: "
				+ locationRequest.getLongitude() + ", Distance: " + locationRequest.getDistance());
		Point point = new Point(locationRequest.getLongitude(), locationRequest.getLatitude());
		Distance distance = new Distance(locationRequest.getDistance(), Metrics.KILOMETERS);
		GeoResults<DhabaModel> dhabasNearby = dhabarepositories.findByLocationNear(point, distance);
		System.out.println("Nearby Dhabas: " + dhabasNearby);
		return dhabasNearby;
	}

	public List<DhabaModel> getByCategory(String category) {
		List<DhabaModel> list = null;
		try {
			list = this.dhabarepositories.findByCategory(category);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return list;
	}

	public List<DhabaModel> findDhabaShopsWithinArea(SpecificLocationRequest locationrequest) {
        Query query = new Query(Criteria.where("location").within(
                new Box(new Point(locationrequest.minLongitude, locationrequest.minLatitude),
                        new Point(locationrequest.maxLongitude, locationrequest.maxLatitude))
        ));
        return mongoTemplate.find(query, DhabaModel.class);
    }

}
