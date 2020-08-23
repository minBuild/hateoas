package com.jjangms.study.hateoas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;

@Slf4j
public class LinkTest {

  @Test
  @DisplayName("링크 테스트")
  public void link()
  {
    Link link = Link.of("/test");
    assertEquals(link.getHref(), "/test");
    assertEquals(link.getRel(), IanaLinkRelations.SELF);
    log.info("1::{}", link.getHref());
    log.info("1::{}", link.getRel());
    log.info("1::{}", IanaLinkRelations.SELF);

    link = Link.of("/test", "relTest");
    assertEquals(link.getHref(), "/test");
    assertEquals(link.getRel(), LinkRelation.of("relTest"));
    log.info("2::{}", link.getHref());
    log.info("2::{}", link.getRel());
    log.info("2::{}", LinkRelation.of("relTest"));

    link = Link.of("/test", IanaLinkRelations.NEXT);
    assertEquals(link.getRel(), LinkRelation.of("next"));
    assertTrue(IanaLinkRelations.isIanaRel(link.getRel()));
    log.info("3::{}", link.getRel());
    log.info("3::{}", LinkRelation.of("next"));
    log.info("3::{}", IanaLinkRelations.isIanaRel(link.getRel()));
  }
}
