package com.example.app.DAO.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import com.example.app.DAO.ProductDAO;
import com.example.app.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ProductDAOImpl implements ProductDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public boolean addProduct(Product product) {
		entityManager.persist(product);
		return (product.getId() > 0) ? true : false;
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> products = entityManager.createQuery("select product from Product product",Product.class).getResultList();
		return products;
	}

	@Override
	public Product findProductById(int id) {
		return entityManager.find(Product.class,id);
	}

	@Override
	public boolean updateProduct(Product product) {
		if(this.findProductById(product.getId()) != null) {
			return this.addProduct(product);
		}
		else {
			return false;
		}
	}

	@Override
	public boolean deleteProduct(int id) {
		if(findProductById(id) != null) {
			entityManager.remove(this.findProductById(id));
			return true;
		}
		return false;
	}

}
