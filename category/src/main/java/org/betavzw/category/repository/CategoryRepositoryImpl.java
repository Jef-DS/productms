package org.betavzw.category.repository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


import org.betavzw.category.model.Category;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

	private Map<String, Category> categories = new HashMap<String, Category>();
	public CategoryRepositoryImpl() {
		categories.put("001", new Category("001", "House"));
		categories.put("002", new Category("002", "Work"));
		
	}
	@Override
	public Collection<Category> getAllCategories() {
		return Collections.unmodifiableCollection(categories.values());
	}

	@Override
	public Category getCategory(String id) {
		return categories.get(id);
	}

	@Override
	public void addCategory(String id, String name) {
		if (categories.containsKey(id)) throw new IllegalArgumentException("Id exists");
		categories.put(id, new Category(id, name));

	}

	@Override
	public void updateCategory(String id, String name) {
		if (!categories.containsKey(id)) throw new IllegalArgumentException("Id does not exist");
		categories.put(id, new Category(id, name));

	}

	@Override
	public void deleteCategory(String id, String name) {
		if (!categories.containsKey(id)) throw new IllegalArgumentException("Id does not exist");
		categories.remove(id);
	}

}
