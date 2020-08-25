package com.Eragoo.Tournament.tournament.match;

import com.Eragoo.Tournament.participant.ParticipantDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MatchResults {
    private ParticipantDto winner;
    private long scoreDifference;
}
