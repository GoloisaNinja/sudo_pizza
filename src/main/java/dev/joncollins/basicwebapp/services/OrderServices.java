package dev.joncollins.basicwebapp.services;

import dev.joncollins.basicwebapp.entity.OrderProduct;
import dev.joncollins.basicwebapp.entity.Product;
import dev.joncollins.basicwebapp.repositories.OrderProductRepository;
import dev.joncollins.basicwebapp.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServices {
    private OrderProductRepository orderProductRepo;
    private ProductRepository productRepository;
    public void saveOrderProductDetails(String order_id, List<String> pIds) {
        for (String id : pIds) {
            Optional<Product> product = productRepository.findById(id);
            OrderProduct op = new OrderProduct(null, order_id, "A1", product.orElse(null));
            orderProductRepo.saveAndFlush(op);
        }
    }
}
