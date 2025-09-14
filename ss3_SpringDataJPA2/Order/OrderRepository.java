package com.example.demo.SpringDataAPIs_bai2.Order;

import com.example.demo.dto.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    /**
     * Tìm tất cả Order theo employeeId.
     * @param employeeId ID của nhân viên
     * @return danh sách Order
     */
    List<Order> findByEmployee_Id(Integer employeeId);

    @Query("SELECT new com.example.demo.dto.OrderDetails(" +
            "o.id, o.orderDate, e.id, e.lastName, e.firstName) " +
            "FROM Order o JOIN o.employee e " +
            "WHERE e.id = :employeeId")
    List<OrderDetails> findAllOrdersWithEmployeeDetails(@Param("employeeId") Integer employeeId);
}
