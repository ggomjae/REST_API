package com.example.restapi.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

// 순수 쳐야한다는 불편함이 있음.
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void usersTestMethod() throws Exception{
        String str = "retrieveAllUsers";

        mvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().string(str));
    }
}
