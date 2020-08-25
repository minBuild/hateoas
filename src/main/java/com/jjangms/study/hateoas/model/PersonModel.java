package com.jjangms.study.hateoas.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class PersonModel extends RepresentationModel<PersonModel>
{
  String firstName, lastName;
}