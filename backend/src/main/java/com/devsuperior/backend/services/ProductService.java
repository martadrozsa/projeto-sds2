package com.devsuperior.backend.services;

import com.devsuperior.backend.dto.ProductDTO;
import com.devsuperior.backend.entities.Product;
import com.devsuperior.backend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        List<Product> list = productRepository.findAllByOrderByNameAsc();

        return list.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
    }
}
