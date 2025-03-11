package com.example.order_service.grpc;


import com.example.order_service.entity.Order;
import com.example.order_service.repository.OrderRepository;
import com.example.order_service.service.OrderService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import com.example.order_service.proto.OrderRequest;
import com.example.order_service.proto.OrderResponse;
import com.example.order_service.proto.OrderServiceGrpc;

@GrpcService
public class OrderGrpcService extends OrderServiceGrpc.OrderServiceImplBase {

    private final OrderService orderService;

    public OrderGrpcService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void createOrder(OrderRequest request, StreamObserver<OrderResponse> responseObserver) {
        Order order = new Order();
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setStatus("CREATED");

        order = orderService.createOrder(order);

        OrderResponse response = OrderResponse.newBuilder()
                .setOrderId(order.getId())
                .setStatus(order.getStatus())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
