package org.betavzw.category.controller;

import org.betavzw.category.model.Category;
import org.betavzw.category.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController()
@RequestMapping(path = "api")
public class CategoryRestController {
	private CategoryRepository repository;
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryRestController.class);
	@Autowired
	public CategoryRestController(CategoryRepository repository) {
		this.repository = repository;
	}
	@RequestMapping(value = "categories/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Resource<Category>> getCategory(@PathVariable("id") String id){
		LOGGER.debug("Fetching category with id : {}", id);
		Category category = repository.getCategory(id);
		if (category == null) {
			LOGGER.debug("No categorie found with id = {}", id);
			return new ResponseEntity<Resource<Category>>(HttpStatus.NOT_FOUND);
		}
		Resource<Category> categoryRes = new Resource<Category>(category, linkTo(methodOn(CategoryRestController.class).getCategory(category.getId())).withSelfRel());
		return new ResponseEntity<Resource<Category>>(categoryRes, HttpStatus.OK);
	}
	@RequestMapping(value="categories", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Resources<Resource<Category>>> getAllCategories(){
		LOGGER.debug("Fetching all categories");
		Collection<Category> categories = repository.getAllCategories();
		Link links[] = {linkTo(methodOn(CategoryRestController.class).getAllCategories()).withSelfRel(),linkTo(methodOn(CategoryRestController.class).getAllCategories()).withRel("getAllCategories")};
		if (categories.isEmpty()) {
			LOGGER.debug("No categories found");
			return new ResponseEntity<Resources<Resource<Category>>>(HttpStatus.NOT_FOUND);
		}
		List<Resource<Category>> list = new ArrayList<Resource<Category>>();
		for (Category category: categories) {
			list.add(new Resource<Category>(category, linkTo(methodOn(CategoryRestController.class).getCategory(category.getId())).withSelfRel()));
		}
		list.forEach(item -> LOGGER.debug(item.toString()));
		Resources<Resource<Category>> categoryRes = new Resources<Resource<Category>>(list, links);
		return new ResponseEntity<Resources<Resource<Category>>>(categoryRes, HttpStatus.OK);
	}
}
