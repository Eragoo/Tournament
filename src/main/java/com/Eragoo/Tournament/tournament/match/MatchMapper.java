package com.Eragoo.Tournament.tournament.match;

import com.Eragoo.Tournament.participant.ParticipantMapper;
import com.Eragoo.Tournament.tournament.TournamentMapper;
import org.mapstruct.Mapper;

@Mapper(uses = {ParticipantMapper.class, TournamentMapper.class})
public interface MatchMapper {
    MatchDto entityToDto(Match match);
}
