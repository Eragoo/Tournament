package com.Eragoo.Tournament.tournament.match;

import com.Eragoo.Tournament.participant.ParticipantMapper;
import org.mapstruct.Mapper;

@Mapper(uses = {ParticipantMapper.class})
public interface MatchMapper {
    MatchDto entityToDto(Match match);
}
