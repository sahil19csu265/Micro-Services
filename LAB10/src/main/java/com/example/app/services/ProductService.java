package com.example.app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.app.DAO.ProductDAO;
import com.example.app.entities.Product;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductDAO productDAO;
	
	public boolean addProductToDatabase(Product product) {
		return (productDAO.save(product)) != null ? true : false;
	}
	
	public List<Product> getAllProductsFromDatabase(){
		List<Product> originalPriceProducts =  productDAO.findAll();
				
		// business logic of reducing product price with appropriate discount %
		List<Product> discountPriceProducts = originalPriceProducts.stream().map(product -> {
			return new Product(product.getId(), product.getName(), product.getDescription(), product.isAvailable(),
					product.getPrice() - (product.getPrice() * product.getDiscount() / 100), product.getDiscount());
		}).collect(Collectors.toList());			
				
		System.out.println(discountPriceProducts);
		return discountPriceProducts;
	} 
	
}
