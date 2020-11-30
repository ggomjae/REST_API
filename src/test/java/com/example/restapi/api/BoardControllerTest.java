package com.example.restapi.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(SpringExtension.class)
@WebMvcTest // @Controller에 사용가능
public class BoardControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void boardTestMethod() throws Exception{
        String str = "retrievePosts";

        mvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().string(str));
    }
}
