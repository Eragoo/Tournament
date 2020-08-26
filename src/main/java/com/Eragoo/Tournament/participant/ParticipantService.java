package com.Eragoo.Tournament.participant;

import com.Eragoo.Tournament.error.exception.NotFoundException;
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
                .collect(Collectors.toSet()) ;
    }

    public ParticipantDto create(ParticipantCommand command) {
        Participant participant = participantMapper.commandToEntity(command);
        Participant savedParticipant = participantRepository.save(participant);
        return participantMapper.entityToDto(savedParticipant);
    }

    public void delete(long id) {
        Participant participant = getParticipantIfExist(id);
        participantRepository.delete(participant);
    }

    public Participant getParticipantIfExist(long id) {
        return participantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Participant with id " + id + " not found"));
    }
}
