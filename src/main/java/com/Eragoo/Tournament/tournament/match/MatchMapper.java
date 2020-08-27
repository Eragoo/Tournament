package com.Eragoo.Tournament.tournament.match;

import com.Eragoo.Tournament.participant.ParticipantMapper;
import com.Eragoo.Tournament.tournament.TournamentMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {ParticipantMapper.class, TournamentMapper.class})
public interface MatchMapper {
    @Mapping(target = "tournamentId", source = "tournament.id")
    MatchDto entityToDto(Match match);
}
