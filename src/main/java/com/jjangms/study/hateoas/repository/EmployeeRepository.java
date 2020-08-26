package com.jjangms.study.hateoas.repository;

import com.jjangms.study.hateoas.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
