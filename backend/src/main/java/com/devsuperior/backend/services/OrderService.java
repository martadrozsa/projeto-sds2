package com.devsuperior.backend.services;

import com.devsuperior.backend.dto.OrderDTO;
import com.devsuperior.backend.dto.ProductDTO;
import com.devsuperior.backend.entities.Order;
import com.devsuperior.backend.entities.Product;
import com.devsuperior.backend.entities.enums.OrderStatus;
import com.devsuperior.backend.repositories.OrderRepository;
import com.devsuperior.backend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {


    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<OrderDTO> findAll() {
        List<Order> list = orderRepository.findOrdersWithProducts();
        return list.stream().map(OrderDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public OrderDTO insert(OrderDTO orderDTO) {

        Order order = new Order(null, orderDTO.getAddress(), orderDTO.getLatitude(), orderDTO.getLongitude(), Instant.now(), OrderStatus.PENDING);

        for (ProductDTO p : orderDTO.getProductDTOList()) {
            Product product = productRepository.getOne(p.getId());
            order.getProducts().add(product);
        }

        order = orderRepository.save(order);
        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO setDelivered(Long id) {
        Order order = orderRepository.getOne(id);
        order.setStatus(OrderStatus.DELIVERED);
        order = orderRepository.save(order);
        return new OrderDTO(order);
    }
}