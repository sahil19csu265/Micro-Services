package com.example.app.DAO;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.example.app.entities.Product;

@Repository
public interface ProductDAO {

	public boolean addProduct(Product product);
	
	public List<Product> getAllProducts();
	
	public Product findProductById(int id);
	
	public boolean updateProduct(Product product);
	
	public boolean deleteProduct(int id);
}
