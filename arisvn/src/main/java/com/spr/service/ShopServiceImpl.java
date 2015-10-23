package com.spr.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.types.OrderSpecifier;
import com.spr.exception.ShopNotFound;
import com.spr.model.QShop;
import com.spr.model.Shop;
//import com.spr.repository.CustomFunctionality;
import com.spr.repository.QueryDSL;
//import com.spr.repository.QueryDSL;
import com.spr.repository.QueryMethod;
import com.spr.repository.ShopPredicates;
//import com.spr.repository.ShopPredicates;
import com.spr.repository.ShopRepository;
//import com.spr.repository.ShopSpecifications;
import com.spr.repository.TodoRepository;
import com.spr.repository.UserRepository;

@Service
public class ShopServiceImpl implements ShopService {

	@Resource
	private ShopRepository shopRepository;
	@Resource
	private TodoRepository todo;
	@Resource
	private QueryMethod queryM;
	@Resource
	private QueryDSL queryDsl;
	@Resource
	private UserRepository custom;
	protected static final int NUMBER_OF_PERSONS_PER_PAGE = 5;

	@Transactional
	public Shop create(Shop shop) {
		Shop createdShop = shop;
		Shop save = todo.save(createdShop);
		System.out.println("==========" + save.getName() + "=================");
		List<Shop> findByName = queryM.findByName("nghia");
		System.out.println(findByName.size());
		List<Shop> findByEmplNumber = queryM.findByEmplNumber(3);
		System.out.println("findByEmplNumber===" + findByEmplNumber.size());
		
		List<Shop> timBoiTen = queryM.timBoiTen("nghia");
		for (Shop sho : timBoiTen) {
			System.out.println("Tim boi ten"+sho.getId());
		}
//		List<Shop> findAll = todo.findAll(ShopSpecifications
//				.nameIsLike("nghia"));
//		for (Shop sho : findAll) {
//			System.out.println(sho.getName());
//		}
		List<Shop> search = search("nghia");
		for (Shop shop2 : search) {
			System.out.println("DSL" + shop2.getName());
		}

		List<Shop> findAllShopHaveOderByNameAndPaging = custom
				.findShopForPage(0);
		for (Shop shop2 : findAllShopHaveOderByNameAndPaging) {
			System.out.println("CUS" + shop2.getName());
		}
		// return shopRepository.save(createdShop);
		return save;
	}

	@Transactional
	public Shop findById(int id) {
		return shopRepository.findOne(id);
	}

	@Transactional(rollbackFor = ShopNotFound.class)
	public Shop delete(int id) throws ShopNotFound {
		Shop deletedShop = shopRepository.findOne(id);
		// Optional<Shop> todoResult = todo.findOne(id);
		// Shop findShopEntryById = findShopEntryById(id);
		if (deletedShop == null)
			throw new ShopNotFound();

		shopRepository.delete(deletedShop);
		return deletedShop;
	}

	@Transactional
	public List<Shop> findAll() {
		// return shopRepository.findAll();
		Iterable<Shop> findAll = queryDsl.findAll(
				ShopPredicates.lastNameIsLike(null),
				constructPageSpecification(0));
		// Iterable<Shop> findAll = queryDsl.findAll(
		// ShopPredicates.lastNameIsLike(null), orderByNameAsc());
		return constructList(findAll);
	}

	@Transactional(rollbackFor = ShopNotFound.class)
	public Shop update(Shop shop) throws ShopNotFound {
		Shop updatedShop = shopRepository.findOne(shop.getId());

		if (updatedShop == null)
			throw new ShopNotFound();

		updatedShop.setName(shop.getName());
		updatedShop.setEmplNumber(shop.getEmplNumber());
		return updatedShop;
	}

	// private Shop findShopEntryById(int id) {
	// Optional<Shop> todoResult = todo.findOne(id);
	// return todoResult.orElseThrow(() -> new ShopNotFoundException(id));
	// }

	@Transactional(readOnly = true)
	public List<Shop> search(String searchTerm) {
		// Passes the specification created by PersonPredicates class to the
		// repository.
		Iterable<Shop> shops = queryDsl.findAll(ShopPredicates
				.lastNameIsLike(searchTerm));
		return constructList(shops);
	}

	private List<Shop> constructList(Iterable<Shop> shops) {
		List<Shop> list = new ArrayList<Shop>();
		for (Shop shop : shops) {
			list.add(shop);
		}
		return list;
	}

	@Transactional(readOnly = true)
	public List<Shop> searchOrder(String searchTerm) {

		// Passes the specification created by PersonPredicates class and the
		// OrderSpecifier object to the repository.
		Iterable<Shop> persons = queryDsl.findAll(
				ShopPredicates.lastNameIsLike(searchTerm), orderByNameAsc());

		return constructList(persons);
	}

	/**
	 * Returns an OrderSpecifier object which sorts person in ascending order by
	 * using the last name.
	 * 
	 * @return
	 */
	private OrderSpecifier<String> orderByNameAsc() {
		return QShop.shop.name.asc();
	}

	private Pageable constructPageSpecification(int pageIndex) {
		Pageable pageSpecification = new PageRequest(pageIndex,
				NUMBER_OF_PERSONS_PER_PAGE, sortByNameAsc());
		return pageSpecification;
	}

	private Sort sortByNameAsc() {
		return new Sort(Sort.Direction.ASC, "name");
	}
}
