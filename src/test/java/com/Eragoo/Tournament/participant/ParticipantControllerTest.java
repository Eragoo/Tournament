package com.Eragoo.Tournament.participant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ParticipantController.class)
public class ParticipantControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            StandardCharsets.UTF_8);

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

    @Test
    public void saveParticipantWithBlankNameReturns400() throws Exception {
        ParticipantCommand participantCommand = new ParticipantCommand("");

        mockMvc.perform(post("/api/participant", participantCommand))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void saveCorrectParticipantReturns200() throws Exception {
        ParticipantCommand participantCommand = new ParticipantCommand("Joe");
        String requestJson = "{\"name\":\"Joe\"}";
        when(participantService.create(participantCommand)).thenReturn(new ParticipantDto(1L, "Joe"));

        mockMvc.perform(post("/api/participant")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson)
        ).andExpect(status().isOk());
    }
}
