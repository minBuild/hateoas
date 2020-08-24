package com.jjangms.study.hateoas.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RepresentationModelController.class)
@Slf4j
class RepresentationModelControllerTest {
  @Autowired
  MockMvc mockMvc;

  @Test
  public void representation() throws Exception
  {
    mockMvc.perform(get("/representation"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._links.self").exists());
  }

  @Test
  public void entity() throws Exception
  {
    mockMvc.perform(get("/entity"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._links.self").exists());
  }

  @Test
  public void collection() throws Exception
  {
    mockMvc.perform(get("/collection"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._links.self").exists());
  }

  @Test
  public void paged() throws Exception
  {
    mockMvc.perform(get("/paged"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._links.self").exists());
  }
}