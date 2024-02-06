package com.code.repositories;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.code.Entity.DhabaModel;

@Repository
@ComponentScan
public interface Dhabarepositories extends MongoRepository<DhabaModel, Long> {

	GeoResults<DhabaModel> findByLocationNear(Point point, Distance distance);

	// void findByCriteria(Query query);

	List<DhabaModel> findByCategory(String category);

	//List<DhabaModel> find(Query query, DhabaModel class1);

	List<DhabaModel> findByLocation(Query query, Class<DhabaModel> class1);

	//List<DhabaModel> find(Query query);

}
