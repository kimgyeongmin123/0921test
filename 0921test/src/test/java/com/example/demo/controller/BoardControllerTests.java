package com.example.demo.controller;


import com.example.demo.dto.BoardDto;
import com.example.demo.restcontroller.BoardRestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.StatusAssertions;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = {BoardController.class, BoardRestController.class})
public class BoardControllerTests {

    @Autowired
    MockMvc mvc;

    @DisplayName("GET /board/list 테스트")
    @Test
    public void t1() throws Exception{
        //given
        Long pageNo = 15L;

        //when

        //then
        mvc.perform(get("/board/list").param("pageNo", String.valueOf(pageNo)))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @DisplayName("PUT /board/put 테스트")
    @Test
    public void t2() throws Exception {

        //given
        BoardDto dto = new BoardDto();
        dto.setNo(10L);
        dto.setTitle("망고는고릴라인가?");
        dto.setContents("망고릴라는 고릴라가 아니고 과일이다.");
        dto.setWriter("망고릴라");

        ObjectMapper objectMapper = new ObjectMapper();
        String params = objectMapper.writeValueAsString(dto);

        //when


        //then
        mvc.perform(
                put("/board/put")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(params)
        )
                .andExpect(status().isOk())
                .andDo(print());

    }




}
