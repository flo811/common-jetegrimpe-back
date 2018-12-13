package dev.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.domain.Product;

@Service
@Transactional
public class ProductService {

	@PersistenceContext
	private EntityManager em;

	public List<Product> findByNameCatPriceOrd(String name, String category, double priceMin, double priceMax, String sort, int pageNbr, int nbrByPage) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
		Root<Product> productRoot = criteriaQuery.from(Product.class);

		Predicate namePredicate = criteriaBuilder.like(productRoot.get("name"), "%" + name + "%");
		Predicate maxPredicate = criteriaBuilder.lessThanOrEqualTo(productRoot.get("price"), priceMax);
		Predicate minPredicate = criteriaBuilder.greaterThanOrEqualTo(productRoot.get("price"), priceMin);

		if (category.equals("tous")) {
			criteriaQuery.where(namePredicate, minPredicate, maxPredicate);
		} else {
			Predicate categoryPredicate = criteriaBuilder.equal(productRoot.get("category"), category);
			criteriaQuery.where(namePredicate, categoryPredicate, minPredicate, maxPredicate);
		}

		if (sort.equals("asc")) {
			criteriaQuery.orderBy(criteriaBuilder.asc(productRoot.get("price")));
		} else {
			criteriaQuery.orderBy(criteriaBuilder.desc(productRoot.get("price")));
		}

		return em.createQuery(criteriaQuery).setFirstResult((pageNbr - 1) * nbrByPage).setMaxResults(nbrByPage)
				.getResultList();
	}
}
