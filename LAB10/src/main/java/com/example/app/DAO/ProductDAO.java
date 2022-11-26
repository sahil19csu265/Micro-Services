package com.example.app.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.app.entities.Product;

public interface ProductDAO extends JpaRepository<Product,Integer>{

}
