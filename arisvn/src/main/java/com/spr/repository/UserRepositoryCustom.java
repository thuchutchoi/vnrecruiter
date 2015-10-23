package com.spr.repository;

import java.util.List;

import com.spr.model.Shop;


public interface UserRepositoryCustom {
	public List<Shop> findShopForPage(int index);
}
