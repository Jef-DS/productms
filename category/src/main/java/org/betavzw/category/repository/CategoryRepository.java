package org.betavzw.category.repository;

import java.util.Collection;

import org.betavzw.category.model.Category;

public interface CategoryRepository {
	Collection<Category> getAllCategories();
	Category getCategory(String id);
	void addCategory(String id, String name);
	void updateCategory(String id, String name);
	void deleteCategory(String id, String name);
}
