package com.Eragoo.Tournament.tournament;

import com.Eragoo.Tournament.tournament.match.MatchDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Builder
public class GeneratedTournament {
    private Set<MatchDto> matches;
    private int matchesNumber;
}
