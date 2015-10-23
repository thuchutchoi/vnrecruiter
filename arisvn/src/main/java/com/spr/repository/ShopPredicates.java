package com.spr.repository;


import com.mysema.query.types.Predicate;
import com.spr.model.QShop;

public class ShopPredicates {
	 public static Predicate lastNameIsLike(final String searchTerm) {
		 QShop shop = QShop.shop;
		 	if(searchTerm==null)
		 	{
		 		return shop.isNotNull();
		 	}
	       
	        return shop.name.startsWithIgnoreCase(searchTerm);
	    }
}
