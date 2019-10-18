package org.betavzw.product.controller;

import org.betavzw.product.client.CategoryClient;
import org.betavzw.product.model.Category;
import org.betavzw.product.model.Product;
import org.betavzw.product.repository.ProductRepository;
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

import feign.FeignException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController()
@RequestMapping(path = "api")
public class ProductRestController {
	private ProductRepository repository;
	private CategoryClient categoryClient;
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductRestController.class);
	
	@Autowired
	public ProductRestController(ProductRepository repository, CategoryClient categoryClient) {
		this.repository = repository;
		this.categoryClient = categoryClient;
	}
	@RequestMapping(value = "products/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Resource<Product>> getProduct(@PathVariable("id") String id){
		LOGGER.debug("Fetching category with id : {}", id);
		Product product = repository.getProduct(id);
		if (product == null) {
			LOGGER.debug("No product found with id = {}", id);
			return new ResponseEntity<Resource<Product>>(HttpStatus.NOT_FOUND);
		}
		if (product.getCategoryName() == null) {
			LOGGER.debug("Getting category with id = {}", product.getCategoryId());
    	    Category category = categoryClient.findById(product.getCategoryId());
			product.setCategoryName(category.getName());
		}
		Resource<Product> productRes = new Resource<Product>(product, linkTo(methodOn(ProductRestController.class).getProduct(product.getId())).withSelfRel());
		return new ResponseEntity<Resource<Product>>(productRes, HttpStatus.OK);	
	}
	@RequestMapping(value="products", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Resources<Resource<Product>>> getAllProducts(){
		LOGGER.debug("Fetching all products");
		Collection<Product> products = repository.getAllProducts();
		Link links[] = {linkTo(methodOn(ProductRestController.class).getAllProducts()).withSelfRel(),linkTo(methodOn(ProductRestController.class).getAllProducts()).withRel("getAllProducts")};
		if (products.isEmpty()) {
			LOGGER.debug("No products found");
			return new ResponseEntity<Resources<Resource<Product>>>(HttpStatus.NOT_FOUND);
		}
		List<Resource<Product>> list = new ArrayList<Resource<Product>>();
		for (Product product: products) {
			list.add(new Resource<Product>(product, linkTo(methodOn(ProductRestController.class).getProduct(product.getId())).withSelfRel()));
		}
		list.forEach(item -> LOGGER.debug(item.toString()));
		Resources<Resource<Product>> productRes = new Resources<Resource<Product>>(list, links);
		return new ResponseEntity<Resources<Resource<Product>>>(productRes, HttpStatus.OK);
	}

}
