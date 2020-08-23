package com.Eragoo.Tournament.participant;

import org.mapstruct.Mapper;

@Mapper
public interface ParticipantMapper {
    ParticipantDto entityToDto(Participant participant);
}
