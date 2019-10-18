package org.betavzw.product.repository;

import java.util.Collection;

import org.betavzw.product.model.Product;

public interface ProductRepository {
	Collection<Product> getAllProducts();
	Product getProduct(String id);
	void addProduct(String id, String naam, String categorieId);
	void updateProduct(String id, String naam, String categorieId);
	void deleteProduct(String id, String naam, String categorieId);
}
