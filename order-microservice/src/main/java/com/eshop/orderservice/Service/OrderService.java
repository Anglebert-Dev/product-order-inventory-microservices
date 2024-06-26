package com.eshop.orderservice.Service;

import com.eshop.orderservice.DTO.InventoryResponse;
import com.eshop.orderservice.DTO.OrderLineItemsDto;
import com.eshop.orderservice.DTO.OrderRequest;
import com.eshop.orderservice.Model.Order;
import com.eshop.orderservice.Model.OrderLineItems;
import com.eshop.orderservice.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient webClient;


    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsDtoList().stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItemsList);
        List<String> skuCodes =  order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

//        call inventory service , and place order if the product is in stock
       InventoryResponse[] inventoryResponses =  webClient.get()
                .uri("http://localhost:8082/api/inventory" ,
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                        .retrieve()
                                .bodyToMono(InventoryResponse[].class)
                                        .block();

//        assert inventoryResponses != null;
        assert inventoryResponses != null;
        boolean allProductsInStock = Arrays.stream(inventoryResponses)
                .allMatch(InventoryResponse::isInStock);

         if (!allProductsInStock) {
            throw new IllegalArgumentException("Product Not In Stock");
        } else {
            orderRepository.save(order);
        }

    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }


}
