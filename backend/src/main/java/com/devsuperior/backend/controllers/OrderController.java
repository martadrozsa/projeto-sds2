package com.devsuperior.backend.controllers;

import com.devsuperior.backend.dto.ProductDTO;
import com.devsuperior.backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class OrderController {

    private final ProductService productService;

    @Autowired
    public OrderController(ProductService service) {
        this.productService = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        List<ProductDTO> list = productService.findAll();
        return ResponseEntity.ok().body(list);
    }
}
