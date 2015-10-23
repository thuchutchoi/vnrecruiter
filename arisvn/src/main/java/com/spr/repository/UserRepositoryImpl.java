package com.spr.repository;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;
import org.springframework.stereotype.Repository;

import com.spr.model.Shop;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {
	protected static final int NUMBER_OF_PERSONS_PER_PAGE = 5;
	@PersistenceContext
	private EntityManager entityManager;

	private QueryDslJpaRepository<Shop, Integer> shopRepository;

	public UserRepositoryImpl() {

	}

	@Override
	public List<Shop> findShopForPage(int index) {
		Page requestedPage = shopRepository.findAll(
				ShopPredicates.lastNameIsLike(null),
				constructPageSpecification(index));
		return requestedPage.getContent();
	}

	private Pageable constructPageSpecification(int pageIndex) {
		Pageable pageSpecification = new PageRequest(pageIndex,
				NUMBER_OF_PERSONS_PER_PAGE, sortByNameAsc());
		return pageSpecification;
	}

	private Sort sortByNameAsc() {
		return new Sort(Sort.Direction.ASC, "name");
	}

	@PostConstruct
	public void init() {
		JpaEntityInformation<Shop, Integer> shopEntityInfo = new JpaMetamodelEntityInformation<Shop, Integer>(
				Shop.class, entityManager.getMetamodel());
		shopRepository = new QueryDslJpaRepository<Shop, Integer>(
				shopEntityInfo, entityManager);
	}

}
