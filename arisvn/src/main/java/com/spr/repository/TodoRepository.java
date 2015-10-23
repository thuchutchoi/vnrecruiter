package com.spr.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

import com.spr.model.Shop;

public interface TodoRepository extends Repository<Shop, Integer>,
		JpaSpecificationExecutor<Shop> {

	Shop save(Shop createdShop);

	void delete(Shop deleted);

	List<Shop> findAll();

	Optional<Shop> findOne(Integer id);

	//void flush();

}