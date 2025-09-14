package com.example.demo.SpringDataAPIs_bai2.Order;

import com.example.demo.springDataAPIs_bai1.lab1_Employees.Employee;
import com.example.demo.springDataAPIs_bai1.lab1_Employees.EmployeeRepository;
import com.example.demo.dto.OrderDetails;
import com.example.demo.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        OrderMapper orderMapper,
                        EmployeeRepository employeeRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.employeeRepository = employeeRepository;
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO getOrderById(int id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        return orderMapper.toDTO(order);
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);

        Employee employee = employeeRepository.findById(orderDTO.getEmployeeId())
                .orElseThrow(() -> new NotFoundException(orderDTO.getEmployeeId()));

        order.setEmployee(employee);

        return orderMapper.toDTO(orderRepository.save(order));
    }

    public OrderDTO updateOrder(int id, OrderDTO orderDTO) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));

        order.setOrderDate(orderDTO.getOrderDate());

        // xử lý employeeId nếu có
        if (orderDTO.getEmployeeId() != null) {
            Employee employee = employeeRepository.findById(orderDTO.getEmployeeId())
                    .orElseThrow(() -> new NotFoundException(orderDTO.getEmployeeId()));
            order.setEmployee(employee);
        }

        Order updatedOrder = orderRepository.save(order);
        return orderMapper.toDTO(updatedOrder);
    }


    public void deleteOrder(int id) {
        if (!orderRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        orderRepository.deleteById(id);
    }

    public List<OrderDTO> getOrdersByEmployeeId(Integer employeeId) {
        return orderRepository.findByEmployee_Id(employeeId)
                .stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Lấy tất cả orders kèm chi tiết Employee
    public List<OrderDetails> getAllOrdersWithEmployeeDetails(Integer employeeId) {
        return orderRepository.findAllOrdersWithEmployeeDetails(employeeId);
    }
}