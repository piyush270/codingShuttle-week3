package com.springboot.jpaTutes.controller;

import com.springboot.jpaTutes.entities.ProductEntity;
import com.springboot.jpaTutes.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final Integer PAGE_SIZE = 5;
    private final ProductRepository repository;

    public ProductController(ProductRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public List<ProductEntity> getProducts(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "") String title
    ){
        //return repository.findByOrderByPrice();
       return repository.findByTitleContainingIgnoreCase(
                title,
                PageRequest.of(pageNumber,PAGE_SIZE,Sort.by(sortBy))
        );
    }

    @GetMapping("/sort")
    public List<ProductEntity> getProductsUsingSortClass(@RequestParam(defaultValue = "id") String sortBy){
        //return repository.findBy(Sort.by(sortBy));
        return repository.findBy(
                Sort.by(Sort.Order.desc(sortBy),
                        Sort.Order.asc("price"))
        );
    }

    @GetMapping("/pagination")
    public Page<ProductEntity> getProductsUsingPagination(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "0") Integer pageNumber
    ){
        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by(sortBy));
        return repository.findAll(pageable);
    }

}
