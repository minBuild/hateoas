package com.jjangms.study.hateoas.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jjangms.study.hateoas.common.exception.EmployeeNotFoundException;
import com.jjangms.study.hateoas.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class EmployeeRepositoryTest {

  @Autowired
  private EmployeeRepository employeeRepository;
  private Employee employee;

  private static final String testFirstName = "민";
  private static final String testLastName = "장";
  private static final String testRole = "FIRST";

  @BeforeEach
  void setUp() {
    employee = Employee.builder().firstName(testFirstName).lastName(testLastName).role(testRole).build();
  }

  @AfterEach
  void clear() {
    employeeRepository.delete(employee);
  }

  @Test
  @DisplayName("DB 테스트")
  public void saveAndFindTest() {
    employee = employeeRepository.save(employee);

    Employee findEmployee = employeeRepository.findById(employee.getId())
        .orElseThrow(() -> new EmployeeNotFoundException(employee.getId()));

    assertEquals(findEmployee.getFirstName(), testFirstName);
    assertEquals(findEmployee.getLastName(), testLastName);
    assertEquals(findEmployee.getRole(), testRole);

    log.info("id: {}", findEmployee.getId());
    log.info("first name: {}", findEmployee.getFirstName());
    log.info("last name: {}", findEmployee.getLastName());
    log.info("role: {}", findEmployee.getRole());
  }
}
