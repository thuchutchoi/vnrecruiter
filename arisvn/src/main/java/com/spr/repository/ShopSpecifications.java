//package com.spr.repository;
//
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//
//
//
//
//
//import org.springframework.data.jpa.domain.Specification;
//
//import com.spr.model.Shop;
//
////import com.spr.model.Shop;
////import com.spr.model.Shop_;
//
//public class ShopSpecifications {
//
//    /**
//     * Creates a specification used to find persons whose last name begins with
//     * the given search term. This search is case insensitive.
//     * @param searchTerm
//     * @return
//     */
//    public static Specification<Shop> nameIsLike(final String searchTerm) {
//        
//        return new Specification<Shop>() {
//            public Predicate toPredicate(Root<Shop> personRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
//                String likePattern = getLikePattern(searchTerm);
//               return cb.like(cb.lower(personRoot.<String>get(Shop_.name)), likePattern);
//            }
//            
//            private String getLikePattern(final String searchTerm) {
//                StringBuilder pattern = new StringBuilder();
//                pattern.append(searchTerm.toLowerCase());
//                pattern.append("%");
//                return pattern.toString();
//            }
//        };
//    }
//}