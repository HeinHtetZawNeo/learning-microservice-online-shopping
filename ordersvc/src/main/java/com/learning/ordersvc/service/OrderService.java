package com.learning.ordersvc.service;

import com.learning.ordersvc.domain.Order;
import com.learning.ordersvc.domain.OrderLineItems;
import com.learning.ordersvc.dto.InventoryResponse;
import com.learning.ordersvc.dto.OrderLineItemsDto;
import com.learning.ordersvc.dto.OrderRequest;
import com.learning.ordersvc.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;
    private final String INVENTORY_SVC_URI = "http://INVENTORY-SERVICE/api/inventory";
    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderLineItemsList(orderRequest.getOrderLineItemsDtoList()
                .stream().map(OrderService::mapToBarNyar).toList());

        //call inventory svc, and place order only if product is in stock
        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode).toList();

        InventoryResponse[] inventoryResponses = restTemplate.getForObject(UriComponentsBuilder
                                .fromUriString(INVENTORY_SVC_URI)
                                .queryParam("skuCode",skuCodes)
                                .build()
                                .toUriString(),InventoryResponse[].class);
        boolean allProductInStock = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::getInStock);

        if(allProductInStock)
            orderRepository.save(order);
        else
            throw new IllegalArgumentException("Product is not in stock, please try again later");
    }

    private static OrderLineItems mapToBarNyar(OrderLineItemsDto orderLineItemsDto) {
        return OrderLineItems.builder()
                .price(orderLineItemsDto.getPrice())
                .quantity(orderLineItemsDto.getQuantity())
                .skuCode(orderLineItemsDto.getSkuCode())
                .build();
    }
}
