package com.Eragoo.Tournament.participant;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
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
    private ResponseEntity<ParticipantDto> create(@RequestBody ParticipantCommand command) {
        return ResponseEntity.ok(participantService.create(command));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@NotNull Long id) {
        participantService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
