package com.Eragoo.Tournament.tournament;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tournament")
@AllArgsConstructor
public class TournamentController {
    private TournamentService tournamentService;

    @GetMapping("/{id}/start")
    public ResponseEntity<GeneratedTournament> startMatch(@PathVariable Long id) {
        GeneratedTournament generatedTournament = tournamentService.start(id);
        return ResponseEntity.ok(generatedTournament);
    }
}
