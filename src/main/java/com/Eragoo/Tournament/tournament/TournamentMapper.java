package com.Eragoo.Tournament.tournament;

import com.Eragoo.Tournament.participant.ParticipantMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = ParticipantMapper.class)
public interface TournamentMapper {
    @Mapping(target = "participants", ignore = true)
    @Mapping(target = "matchesNumber", ignore = true)
    @Mapping(target = "id", ignore = true)
    Tournament commandToEntity(TournamentCommand command);

    TournamentDto entityToDto(Tournament tournament);
}
