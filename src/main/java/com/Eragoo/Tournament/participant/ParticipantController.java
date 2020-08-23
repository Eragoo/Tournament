package com.Eragoo.Tournament.participant;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("api/participant")
@AllArgsConstructor
public class ParticipantController {
    private ParticipantService participantService;

    @GetMapping
    private ResponseEntity<Set<ParticipantDto>> getAll() {
        ParticipantDto p1 = new ParticipantDto();
        p1.setId(1L);
        p1.setName("lol");

        ParticipantDto p2 = new ParticipantDto();
        p2.setId(1L);
        p2.setName("lol");

        System.out.println("    ----------- \n " + (p1.hashCode() == p2.hashCode()));
        return ResponseEntity.ok(participantService.getAll());
    }
}
