package com.spr.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.Repository;

import com.spr.model.Shop;

public interface QueryDSL extends QueryDslPredicateExecutor<Shop>,
		Repository<Shop, Integer> {

}
