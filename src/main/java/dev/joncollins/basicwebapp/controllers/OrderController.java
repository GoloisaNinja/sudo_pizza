package dev.joncollins.basicwebapp.controllers;

import dev.joncollins.basicwebapp.entity.CustomerOrder;
import dev.joncollins.basicwebapp.entity.OrderProduct;
import dev.joncollins.basicwebapp.entity.Product;
import dev.joncollins.basicwebapp.entity.SubmitOrderRequest;
import dev.joncollins.basicwebapp.repositories.CustomerOrderRepository;
import dev.joncollins.basicwebapp.repositories.OrderProductRepository;
import dev.joncollins.basicwebapp.repositories.ProductRepository;
import dev.joncollins.basicwebapp.services.OrderServices;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@AllArgsConstructor
public class OrderController {
    private ProductRepository productRepo;
    private CustomerOrderRepository orderRepo;
    private OrderProductRepository orderProdRepo;
    private OrderServices orderServices;

    @GetMapping("/api/products")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productRepo.findAll();
        return ResponseEntity.ok(products);
    }
    @GetMapping("/order")
    public String getOrderView() {
        return "order";
    }
    @PostMapping("/api/submitOrder")
    public ResponseEntity<String> submitOrder(@RequestBody SubmitOrderRequest orderReq) {
        CustomerOrder order = new CustomerOrder(orderReq.getOrder_id(), orderReq.getUser_token(),
                                                null, orderReq.getTotal());
        try {
            System.out.println(orderReq.IsAPizza());
            if (!orderReq.IsAPizza()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            orderRepo.save(order);
            orderServices.saveOrderProductDetails(order.getOrder_id(), orderReq.getProductIds());
            return ResponseEntity.ok("order success");
        } catch(ServerErrorException error) {
            return ResponseEntity.status(error.getStatusCode()).body(error.getMessage());
        }
    }
    @GetMapping("/orderSummary/{id}")
    public ModelAndView orderSummary(@PathVariable String id) {
        ModelAndView mav = new ModelAndView("order-summary");
        List<OrderProduct> itemsOnOrder = orderProdRepo.findAllOrderProductDetailsByOrderId(id);
        CustomerOrder order = orderRepo.findById(id).orElse(null);
        mav.addObject("items", itemsOnOrder);
        mav.addObject("order", order);
        return mav;
    }

    @GetMapping("/orderHistory/{userId}")
    public ModelAndView orderHistory(@PathVariable String userId) {
        ModelAndView mav = new ModelAndView("order-history");
        List<CustomerOrder> orders = orderRepo.findAllOrdersByUserId(userId, Sort.by(Sort.Direction.DESC, "created_on"));
        mav.addObject("customerOrders", orders);
        return mav;
    }
}
