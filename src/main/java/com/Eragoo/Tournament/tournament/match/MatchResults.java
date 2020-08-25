package com.Eragoo.Tournament.tournament.match;

import com.Eragoo.Tournament.participant.ParticipantDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Getter
public class MatchResults {
    private ParticipantDto winner;
    private long scoreDifference;
}
