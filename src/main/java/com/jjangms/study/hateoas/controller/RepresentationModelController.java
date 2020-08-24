package com.jjangms.study.hateoas.controller;

import com.jjangms.study.hateoas.model.Person;
import com.jjangms.study.hateoas.model.PersonModel;
import java.util.Collection;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.PagedModel.PageMetadata;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RepresentationModelController {
  @GetMapping("/representation")
  public PersonModel representation()
  {
    PersonModel model = new PersonModel();
    model.setFirstName("half");
    model.setLastName("jang");
    model.add(Link.of("http://localhost:9613/entity"));
    model.add(Link.of("http://localhost:9613/collection"));

    return model;
  }

  @GetMapping("/entity")
  public EntityModel<Person> entity()
  {
    Person person = new Person("half", "jang");
    EntityModel<Person> model = EntityModel.of(person);
    model.add(Link.of("http://localhost:9613/entㅍㅊㅍㅍ ㅍㄹㅎㅍㅊㅇㄹㄷㄹㄷㅊㄱㄹㅇㄷㄱ5ㅅㅇㄴㅁㅁㅁity"));
    Link link = Link.of("http://localhost:9613/representation", IanaLinkRelations.NEXT);
    model.add(link.withRel("next"));

    return model;
  }

  @GetMapping("/collection")
  public CollectionModel<Person> collection()
  {
    Collection<Person> people = new java.util.ArrayList<>();
    people.add(new Person("half", "jang"));
    people.add(new Person("ms", "jjang"));
    CollectionModel<Person> model = CollectionModel.of(people);

    Link oriLink = Link.of("http://localhost:9613/collection");
    Link firstLink = Link.of("http://localhost:9613/representation", "representation");
    Link secondLink = Link.of("http://localhost:9613/paged", "paged");
    model.add(oriLink.withSelfRel());
    model.add(firstLink.withRel("representation"));
    model.add(secondLink.withRel("paged"));

    return model;
  }

  @GetMapping("/paged")
  public PagedModel<Person> paged()
  {
    Collection<Person> people = new java.util.ArrayList<>();
    people.add(new Person("half", "jang"));
    people.add(new Person("ms", "jjang"));
    PageMetadata metadata = new PageMetadata(1L, 1L, 1L, 1L);
    PagedModel<Person> model = PagedModel.of(people, metadata);

    Link link = Link.of("http://localhost:9613/paged");
    model.add(link.withSelfRel());
    link = Link.of("http://localhost:9613/representation", "representation");
    model.add(link.withRel("representation"));
    link = Link.of("http://localhost:9613/entity", "entity");
    model.add(link.withRel("entity"));
    link = Link.of("http://localhost:9613/collection", "collection");
    model.add(link.withRel("collection"));

    return model;
  }
}
