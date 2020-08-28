package com.jjangms.study.hateoas.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.afford;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.jjangms.study.hateoas.common.exception.EmployeeNotFoundException;
import com.jjangms.study.hateoas.model.Employee;
import com.jjangms.study.hateoas.model.assembler.EmployeeModelAssembler;
import com.jjangms.study.hateoas.repository.EmployeeRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class EmployeeController {

  private final EmployeeRepository repository;
  private final EmployeeModelAssembler assembler;

  EmployeeController(EmployeeRepository repository,
      EmployeeModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }

  @GetMapping("/employees")
  public CollectionModel<EntityModel<Employee>> all() {

    List<EntityModel<Employee>> employees = repository.findAll().stream()
        .map(assembler::toModel)
        .collect(Collectors.toList());

    return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
  }

  @PostMapping("/employees")
  ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee) {

    EntityModel<Employee> entityModel = assembler.toModel(repository.save(newEmployee));

    return ResponseEntity
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
        .body(entityModel);
  }

  @GetMapping("/employees/{id}")
  public EntityModel<Employee> one(@PathVariable Long id) {
    Employee employee = repository.findById(id)
        .orElseThrow(() -> new EmployeeNotFoundException(id));

    return assembler.toModel(employee);
  }

  @PutMapping("/employees/{id}")
  ResponseEntity<?> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

    Employee updatedEmployee = repository.findById(id)
        .map(employee -> repository.save(newEmployee))
        .orElseGet(() -> {
          newEmployee.setId(id);
          return repository.save(newEmployee);
        });

    EntityModel<Employee> entityModel = assembler.toModel(updatedEmployee);

    return ResponseEntity
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
        .body(entityModel);
  }

  @DeleteMapping("/employees/{id}")
  ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
    repository.deleteById(id);

    return ResponseEntity.noContent().build();
  }

}
