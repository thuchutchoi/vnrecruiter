package com.spr.repository;

import org.springframework.data.repository.CrudRepository;

import com.spr.model.Shop;

public interface UserRepository extends CrudRepository<Shop, Integer>,
		UserRepositoryCustom {

}
