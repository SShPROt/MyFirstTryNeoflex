package com.example.SecondTry.tests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.SecondTry.controllers.MainController;
import com.example.SecondTry.service.HolidaysService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class ControllerTest {
    @Mock
    private HolidaysService holidaysService;

    @InjectMocks
    private MainController mainController;
    private MockMvc mockMvc;
    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
    }
    @Test
    void proverka() throws Exception{
        mockMvc.perform(get("/calculate")).andExpect(status().isOk());
    }

}
