package com.Eragoo.Tournament.participant;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        return ResponseEntity.ok(participantService.getAll());
    }

    @PostMapping
    private ResponseEntity<ParticipantDto> create(ParticipantCommand command) {
        return ResponseEntity.ok(participantService.create(command));
    }
}
