package com.Eragoo.Tournament.participant;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ParticipantMapper {
    @Mapping(target = "tournamentId", source = "tournament.id")
    ParticipantDto entityToDto(Participant participant);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tournament", ignore = true)
    Participant commandToEntity(ParticipantCommand command);
}

