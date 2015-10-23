package com.spr.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

import com.spr.model.Shop;

public interface QueryMethod extends Repository<Shop, Integer> {
	@Async
	List<Shop> timBoiTen(String name);
//	@Async
//	Optional<String> findTitleByIdOptional(Long id);
//	@Async
//	Shop findById(Long id);
//	@Async
//	Optional<Shop> findByIdOptional(Long id);
	@Async
	@Query("SELECT sh FROM Shop sh where sh.name = :name")
	List<Shop> findByName(@Param("name")String name);
	
	List<Shop> findByEmplNumber(Integer emplNumber);
//	@Async
//	Stream<Shop> findByTitleStrem(String title);
}
