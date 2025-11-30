package edu.jdbcproject.dao;

import edu.jdbcproject.model.Product;

import java.util.List;

public interface ProductDao {
    void  addProduct(Product product);
    Product  getProduct(int id);
    List<Product> getAllProducts();

    void updateProduct(Product product);
    void deleteProduct(int id);
}
