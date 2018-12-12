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

	public List<Product> findByNameCatPriceOrd(String name, String category, double priceMin, double priceMax, boolean isAsc,
			int pageNbr, int nbrByPage) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
		Root<Product> productRoot = criteriaQuery.from(Product.class);

		Predicate namePredicate = criteriaBuilder.like(productRoot.get("title"), "%" + name + "%");
		Predicate categoryPredicate = criteriaBuilder.equal(productRoot.get("category"), category);
		Predicate minPredicate = criteriaBuilder.lessThanOrEqualTo(productRoot.get("price"), priceMin);
		Predicate maxPredicate = criteriaBuilder.greaterThanOrEqualTo(productRoot.get("price"), priceMax);

		criteriaQuery.where(namePredicate, categoryPredicate, minPredicate, maxPredicate);

		if (isAsc) {
			criteriaQuery.orderBy(criteriaBuilder.asc(productRoot.get("price")));
		} else {
			criteriaQuery.orderBy(criteriaBuilder.desc(productRoot.get("price")));
		}

		em.createQuery(criteriaQuery).setFirstResult(1 + pageNbr * nbrByPage);
		em.createQuery(criteriaQuery).setMaxResults(nbrByPage);

		return em.createQuery(criteriaQuery).getResultList();
	}
}
