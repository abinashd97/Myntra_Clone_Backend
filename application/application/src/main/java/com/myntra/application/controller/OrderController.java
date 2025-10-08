package com.myntra.application.controller;

import com.myntra.application.dto.CreateOrderRequest;
import com.myntra.application.dto.OrderItemRequest;
import com.myntra.application.dto.OrderResponse;
import com.myntra.application.dto.OrderItemResponse;
import com.myntra.application.model.*;
import com.myntra.application.repository.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @PostMapping
    public ResponseEntity<?> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userEmail = authentication.getName();
            
            Optional<User> userOpt = userRepository.findByEmail(userEmail);
            if (userOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            User user = userOpt.get();
            
            // Generate unique order number
            String orderNumber = "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            
            // Create order
            Order order = new Order(
                user,
                orderNumber,
                "CONFIRMED",
                request.getTotalAmount(),
                request.getDeliveryAddress(),
                request.getPaymentMethod(),
                "PAID"
            );
            
            Order savedOrder = orderRepository.save(order);
            
            // Create order items
            for (OrderItemRequest itemRequest : request.getItems()) {
                Optional<Item> itemOpt = itemRepository.findById(itemRequest.getItemId());
                if (itemOpt.isPresent()) {
                    Item item = itemOpt.get();
                    OrderItem orderItem = new OrderItem(
                        savedOrder,
                        item.getId(),
                        item.getItemName(),
                        item.getImage(),
                        item.getCompany(),
                        item.getCurrentPrice(),
                        itemRequest.getQuantity()
                    );
                    orderItemRepository.save(orderItem);
                }
            }
            
            // Fetch the order with items to return clean response
            Order orderWithItems = orderRepository.findByIdWithItems(savedOrder.getId()).orElse(savedOrder);
            OrderResponse orderResponse = convertToOrderResponse(orderWithItems);
            
            return ResponseEntity.ok(orderResponse);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating order: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getUserOrders() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userEmail = authentication.getName();
            
            Optional<User> userOpt = userRepository.findByEmail(userEmail);
            if (userOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            List<Order> orders = orderRepository.findByUserWithItemsOrderByOrderDateDesc(userOpt.get());
            List<OrderResponse> orderResponses = orders.stream()
                    .map(this::convertToOrderResponse)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(orderResponses);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching orders: " + e.getMessage());
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderDetails(@PathVariable Long orderId) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userEmail = authentication.getName();
            
            Optional<User> userOpt = userRepository.findByEmail(userEmail);
            if (userOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            Optional<Order> orderOpt = orderRepository.findByIdWithItems(orderId);
            if (orderOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            Order order = orderOpt.get();
            
            // Check if order belongs to the user
            if (!order.getUser().getEmail().equals(userEmail)) {
                return ResponseEntity.status(403).body("Access denied");
            }
            
            OrderResponse orderResponse = convertToOrderResponse(order);
            return ResponseEntity.ok(orderResponse);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching order details: " + e.getMessage());
        }
    }
    
    private OrderResponse convertToOrderResponse(Order order) {
        List<OrderItemResponse> orderItemResponses = order.getOrderItems() != null ? 
            order.getOrderItems().stream()
                .map(item -> new OrderItemResponse(
                    item.getId(),
                    item.getItemId(),
                    item.getItemName(),
                    item.getItemImage(),
                    item.getCompany(),
                    item.getPrice(),
                    item.getQuantity(),
                    item.getTotalPrice()
                ))
                .collect(Collectors.toList()) : 
            List.of();
            
        return new OrderResponse(
            order.getId(),
            order.getOrderNumber(),
            order.getStatus(),
            order.getTotalAmount(),
            order.getDeliveryAddress(),
            order.getPaymentMethod(),
            order.getPaymentStatus(),
            order.getOrderDate(),
            order.getDeliveryDate(),
            orderItemResponses
        );
    }
}
