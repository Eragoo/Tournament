package com.Eragoo.Tournament.participant;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ParticipantMapper {
    ParticipantDto entityToDto(Participant participant);
    @Mapping(target = "id", ignore = true)
    Participant commandToEntity(ParticipantCommand command);
}
