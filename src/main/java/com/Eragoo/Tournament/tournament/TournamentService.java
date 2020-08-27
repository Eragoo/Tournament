package com.Eragoo.Tournament.tournament;

import com.Eragoo.Tournament.error.exception.ConflictException;
import com.Eragoo.Tournament.error.exception.NotFoundException;
import com.Eragoo.Tournament.participant.Participant;
import com.Eragoo.Tournament.participant.ParticipantRepository;
import com.Eragoo.Tournament.tournament.match.MatchDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class TournamentService {
    private TournamentRepository tournamentRepository;
    private TournamentMatchesLayerGenerator matchesLayerGenerator;
    private TournamentMapper tournamentMapper;
    private ParticipantRepository participantRepository;

    public TournamentDto holdTournament(@NotNull Long tournamentId) {
        Tournament tournament = getTournamentIfExist(tournamentId);
        tournament.setOnHold(true);
        return tournamentMapper.entityToDto(tournament);
    }

    public GeneratedTournament start(@NotNull Long tournamentId) {
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
        Set<Participant> participants = getParticipantsByIds(participantsIds);
        addTournamentInParticipants(participants, tournament);
        validateParticipantsNumber(participants);
        tournament.setParticipants(participants);
        int matchesNumber = (participants.size() + 1) / 2;
        tournament.setMatchesNumber(matchesNumber);
        Tournament saved = tournamentRepository.save(tournament);
        return tournamentMapper.entityToDto(saved);
    }

    public TournamentDto getById(@NotNull Long id) {
        Tournament tournament = getTournamentIfExist(id);
        return tournamentMapper.entityToDto(tournament);
    }

    public TournamentDto addParticipantsInTournament(@NotNull Long tournamentId, @NotEmpty Set<Long> participantsIds) {
        Tournament tournament = getTournamentIfExist(tournamentId);
        Set<Participant> participants = tournament.getParticipants();
        Set<Participant> foundParticipants = getParticipantsByIds(participantsIds);
        Collection<Participant> participantWithTournament = addTournamentInParticipants(foundParticipants, tournament);

        participants.addAll(participantWithTournament);
        return tournamentMapper.entityToDto(tournament);
    }

    public TournamentDto removeParticipantsInTournament(@NotNull Long tournamentId, @NotEmpty Set<Long> participantsIds) {
        Tournament tournament = getTournamentIfExist(tournamentId);
        checkTournamentIsOnHold(tournament);
        Set<Participant> participants = tournament.getParticipants();
        Set<Participant> foundParticipants = getParticipantsByIds(participantsIds);
        Collection<Participant> participantsWithoutTournament = removeTournamentInParticipants(foundParticipants);
        participants.removeAll(participantsWithoutTournament);
        return tournamentMapper.entityToDto(tournament);
    }

    private void checkTournamentIsOnHold(Tournament tournament) {
        if (!tournament.isOnHold()) {
            throw new ConflictException("Tournament must be on hold");
        }
    }

    private Collection<Participant> addTournamentInParticipants(@NotEmpty Collection<Participant> participants,
                                                         @NotNull Tournament tournament) {
        for (Participant participant: participants) {
            participant.setTournament(tournament);
        }
        return participants;
    }

    private Collection<Participant> removeTournamentInParticipants(@NotEmpty Collection<Participant> participants) {
        for (Participant participant: participants) {
            participant.setTournament(null);
        }
        return participants;
    }

    private Set<Participant> getParticipantsByIds(@NotEmpty Set<Long> participantsIds) {
        return participantRepository.findAllByIdIn(participantsIds);
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

