package com.Eragoo.Tournament.tournament;

import com.Eragoo.Tournament.error.exception.ConflictException;
import com.Eragoo.Tournament.error.exception.NotFoundException;
import com.Eragoo.Tournament.participant.Participant;
import com.Eragoo.Tournament.participant.ParticipantRepository;
import com.Eragoo.Tournament.tournament.match.MatchDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Service
@AllArgsConstructor
public class TournamentService {
    private TournamentRepository tournamentRepository;
    private TournamentMatchesLayerGenerator matchesLayerGenerator;
    private TournamentMapper tournamentMapper;
    private ParticipantRepository participantRepository;

    public GeneratedTournament start(@NotNull long tournamentId) {
        Tournament tournament = getTournamentIfExist(tournamentId);

        Set<Participant> participants = tournament.getParticipants();
        int matchesNumber = (participants.size() + 1) / 2;
        Set<MatchDto> matches = matchesLayerGenerator.createLayerWithRandomParticipants(participants);

        return GeneratedTournament.builder()
                .matches(matches)
                .matchesNumber(matchesNumber)
                .build();
    }

    public TournamentDto create(@NotNull TournamentCommand tournamentCommand) {
        Tournament tournament = tournamentMapper.commandToEntity(tournamentCommand);
        Set<Long> participantsIds = tournamentCommand.getParticipantsIds();
        Set<Participant> participants = participantRepository.findAllByIdIn(participantsIds);
        validateParticipantsNumber(participants);
        tournament.setParticipants(participants);
        int matchesNumber = (participants.size() + 1) / 2;
        tournament.setMatchesNumber(matchesNumber);
        Tournament saved = tournamentRepository.save(tournament);
        return tournamentMapper.entityToDto(saved);
    }

    private void validateParticipantsNumber(Set<Participant> participants) {
        int size = participants.size();
        if (size % 8 < 7 && size % 8 != 0) {
            throw new ConflictException("Max number of tournament participants must multiple of 8" +
                    "or be an odd number like 7, 15 ect. ");
        }
    }

    private Tournament getTournamentIfExist(@NotNull long tournamentId) {
        return tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new NotFoundException("Tournament with id " + tournamentId + " not found"));
    }
}

