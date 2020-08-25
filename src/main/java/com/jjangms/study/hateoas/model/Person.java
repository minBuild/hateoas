package com.jjangms.study.hateoas.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person
{
  String firstName, lastName;

  public Person(String firstName, String lastName)
  {
    this.firstName = firstName;
    this.lastName = lastName;
  }
}
