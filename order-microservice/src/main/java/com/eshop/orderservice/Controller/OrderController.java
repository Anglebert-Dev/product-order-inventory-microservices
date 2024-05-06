package com.eshop.orderservice.Controller;


import com.eshop.orderservice.DTO.OrderRequest;
import com.eshop.orderservice.Service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest);
//        return String.valueOf(new ResponseEntity<>(HttpStatus.CREATED));
        return "Order Placed Successfully";
    }
}
