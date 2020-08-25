package com.Eragoo.Tournament.tournament.match;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/match")
@AllArgsConstructor
public class MatchController {
    private MatchService matchService;

    @GetMapping("{id}/results")
    public ResponseEntity<MatchResults> getWinner(@PathVariable long id) {
        return ResponseEntity.ok(matchService.getResults(id));
    }
}
