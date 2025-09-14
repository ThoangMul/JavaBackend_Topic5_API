package com.example.demo.SpringDataAPIs_bai2.Order;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    // Entity -> DTO
    @Mapping(target = "employeeId", source = "employee.id")
    OrderDTO toDTO(Order order);

    // DTO -> Entity
    @Mapping(target = "employee", ignore = true)
    Order toEntity(OrderDTO orderDTO);
}
