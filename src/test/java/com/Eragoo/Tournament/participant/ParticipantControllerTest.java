package com.Eragoo.Tournament.participant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@WebMvcTest(ParticipantController.class)
public class ParticipantControllerTest {
    @MockBean
    private ParticipantService participantService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllReturns200() throws Exception {
        when(participantService.getAll()).thenReturn(Set.of(new ParticipantDto(1L, "Joe")));
        mockMvc.perform(get("/api/participant"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}
