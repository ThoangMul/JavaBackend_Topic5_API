package com.example.demo.dto;

import java.time.LocalDateTime;

public class OrderDetails {
    private int orderId;
    private LocalDateTime orderDate;
    private int employeeId;
    private String employeeLastName;
    private String employeeFirstName;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String EmployeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public OrderDetails(int orderId, LocalDateTime orderDate, int employeeId,
                        String employeeLastName, String employeeFirstName) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.employeeId = employeeId;
        this.employeeLastName = employeeLastName;
        this.employeeFirstName = employeeFirstName;
    }
}
