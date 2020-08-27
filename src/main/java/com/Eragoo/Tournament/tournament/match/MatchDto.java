package com.Eragoo.Tournament.tournament.match;

import com.Eragoo.Tournament.participant.ParticipantDto;
import lombok.*;

import java.time.Instant;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class MatchDto {
    private ParticipantDto blueParticipant;
    private ParticipantDto redParticipant;
    private Long tournamentId;
    private long blueScore;
    private long redScore;
    private Instant startTime;
    private Instant finishTime;
}
