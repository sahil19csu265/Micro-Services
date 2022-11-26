package com.example.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.entities.Product;
import com.example.app.services.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@RequestMapping(method = RequestMethod.POST,path="/product",consumes=MediaType.APPLICATION_JSON_VALUE)
	public String addProduct(@RequestBody Product product) {
		System.out.println(product);
		return (productService.addProductToDatabase(product)) 
				? "Product Added Successfully !" 
				: "Failed to Add Product";
	}
	
	@RequestMapping(path="/product",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Product> getProducts(){
		return productService.getAllProductsFromDatabase();
	}
	
	@RequestMapping(path="/product/{productID}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Product getProduct(@PathVariable("productID") int id) {
		return productService.getSingleProduct(id);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,path="/product/{productID}")
	public String deleteProduct(@PathVariable("productID") int id) {
		return (productService.deleteProductFromDatabase(id))
				? "Product Deleted Successfully !"
				: "Failed to delete Product";
	}
}
