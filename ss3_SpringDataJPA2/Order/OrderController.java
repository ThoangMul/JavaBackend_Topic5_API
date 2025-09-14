package com.example.demo.SpringDataAPIs_bai2.Order;

import com.example.demo.dto.OrderDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Lấy tất cả orders
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    // Lấy orders theo employeeId
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByEmployeeId(@PathVariable("employeeId") Integer employeeId) {
        List<OrderDTO> orders = orderService.getOrdersByEmployeeId(employeeId);
        return ResponseEntity.ok(orders);
    }

    // Lấy tất cả order với employee details
    @GetMapping("/employee-details/{employeeId}")
    public ResponseEntity<List<OrderDetails>> getAllOrdersWithEmployeeDetails(@PathVariable("employeeId") Integer employeeId) {
        return ResponseEntity.ok(orderService.getAllOrdersWithEmployeeDetails(employeeId));
    }

    // Lấy order theo id
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable int id) {
        OrderDTO order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    // Tạo order mới
    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@Validated @RequestBody OrderDTO orderDTO) {
        OrderDTO createdOrder = orderService.createOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    // Cập nhật order
    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable int id,
                                                @Validated @RequestBody OrderDTO updatedOrderDTO) {
        OrderDTO updatedOrder = orderService.updateOrder(id, updatedOrderDTO);
        return ResponseEntity.ok(updatedOrder);
    }

    // Xóa order
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
