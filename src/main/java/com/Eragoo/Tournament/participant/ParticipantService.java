package com.Eragoo.Tournament.participant;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ParticipantService {
    private ParticipantRepository participantRepository;
    private ParticipantMapper participantMapper;

    public Set<ParticipantDto> getAll() {
        return participantRepository.findAll()
                .stream()
                .map(participantMapper::entityToDto)
                .collect(Collectors.toSet());
    }
}
