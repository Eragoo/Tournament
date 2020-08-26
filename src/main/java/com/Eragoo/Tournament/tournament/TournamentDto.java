package com.Eragoo.Tournament.tournament;

import com.Eragoo.Tournament.participant.ParticipantDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TournamentDto {
    private long id;
    private Set<ParticipantDto> participants;
    private int matchesNumber;
}
