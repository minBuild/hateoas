package com.jjangms.study.hateoas.repository;

import com.jjangms.study.hateoas.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
