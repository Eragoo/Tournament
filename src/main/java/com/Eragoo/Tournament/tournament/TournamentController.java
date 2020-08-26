package com.Eragoo.Tournament.tournament;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<TournamentDto> create(@RequestBody TournamentCommand tournamentCommand) {
        return ResponseEntity.ok(tournamentService.create(tournamentCommand));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TournamentDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(tournamentService.getById(id));
    }
}
