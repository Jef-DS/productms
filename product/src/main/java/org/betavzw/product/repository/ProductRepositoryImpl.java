package org.betavzw.product.repository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.betavzw.product.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
	private Map<String, Product> products = new HashMap<String, Product>();
	public ProductRepositoryImpl() {
		products.put("00A", new Product("00A", "Pencil", "001"));
		products.put("00B", new Product("00B", "Table", "002"));
		products.put("00C", new Product("00C", "Nothing", "003"));
	}
	public Collection<Product> getAllProducts() {
		return Collections.unmodifiableCollection(products.values());
	}

	public Product getProduct(String id) {
		
		return products.get(id);
	}

	public void addProduct(String id, String name, String categorieId) {
		if (products.containsKey(id)) throw new IllegalArgumentException("Id exists");
		products.put(id, new Product(id, name, categorieId));
	}

	public void updateProduct(String id, String name, String categorieId) {
		if(!products.containsKey(id)) throw new IllegalArgumentException("Id does not exist");
		products.put(id, new Product(id, name, categorieId));

	}

	public void deleteProduct(String id, String naam, String categorieId) {
		if(!products.containsKey(id)) throw new IllegalArgumentException("Id does not exist");
		products.remove(id);
	}

}
