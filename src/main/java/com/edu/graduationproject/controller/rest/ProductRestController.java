package com.edu.graduationproject.controller.rest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.graduationproject.entity.Product;
import com.edu.graduationproject.service.ProductService;

@CrossOrigin("*")
@RestController
public class ProductRestController {
    @Autowired
    ProductService productService;

    @GetMapping("/rest/products")
    public ResponseEntity<List<Product>> getAll(@RequestParam("sort") Optional<String> sort) {
        if (sort.isPresent()) {
            if (sort.get().equalsIgnoreCase("all")) {
                return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
            }
            if (sort.get().equalsIgnoreCase("price_asc")) {
                return new ResponseEntity<>(productService.findAll(Sort.by("price").ascending()),
                        HttpStatus.OK);
            }
            if (sort.get().equalsIgnoreCase("price_desc")) {
                return new ResponseEntity<>(productService.findAll(Sort.by("price").descending()),
                        HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/rest/products/{id}")
    public ResponseEntity<Product> getOne(@PathVariable("id") Integer id) {
        try {
            Product product = productService.findById(id);
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/rest/products/category/{categoryId}")
    public ResponseEntity<List<Product>> findProductByCategory(
            @PathVariable("categoryId") Optional<Integer> categoryId) {
        return ResponseEntity.ok(productService.findByCategoryId(categoryId.get()));
    }

    @GetMapping("/rest/products/sale-off")
    public ResponseEntity<List<Product>> findProductBySaleOff() {
        return ResponseEntity.ok(productService.findAllBySaleOff());
    }

    @GetMapping("/rest/products/price-range/{min}/{max}")
    public ResponseEntity<List<Product>> findProductByPriceRange(@PathVariable("min") Optional<Double> min,
            @PathVariable("max") Optional<Double> max) {
        Double _min = min.orElse(0d);
        Double _max = max.orElse(0d);
        if (_min < _max) {
            return ResponseEntity.ok(productService.findAllByPriceRange(_min, _max));
        }
        return ResponseEntity.badRequest().build();
    }

}
