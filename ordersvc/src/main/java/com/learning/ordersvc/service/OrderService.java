package com.learning.ordersvc.service;

import com.learning.ordersvc.domain.Order;
import com.learning.ordersvc.domain.OrderLineItems;
import com.learning.ordersvc.dto.OrderLineItemsDto;
import com.learning.ordersvc.dto.OrderRequest;
import com.learning.ordersvc.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderLineItemsList(orderRequest.getOrderLineItemsDtoList()
                .stream().map(this::mapToDto).toList());

        orderRepository.save(order);
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        return OrderLineItems.builder()
                .price(orderLineItemsDto.getPrice())
                .quantity(orderLineItemsDto.getQuantity())
                .skuCode(orderLineItemsDto.getSkuCode())
                .build();
    }

}
