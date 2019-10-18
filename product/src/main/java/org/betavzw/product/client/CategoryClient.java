package org.betavzw.product.client;

import org.betavzw.product.model.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value="category", url="category:8081")
public interface CategoryClient {
	@RequestMapping(method = RequestMethod.GET, path = "/api/categories/{id}")
	Category findById(@PathVariable("id") String id);
}
