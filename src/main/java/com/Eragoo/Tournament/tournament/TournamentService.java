package com.Eragoo.Tournament.tournament;

import com.Eragoo.Tournament.error.exception.ConflictException;
import com.Eragoo.Tournament.error.exception.NotFoundException;
import com.Eragoo.Tournament.participant.Participant;
import com.Eragoo.Tournament.tournament.match.MatchDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.*;

@Service
@AllArgsConstructor
public class TournamentService {
    private TournamentRepository tournamentRepository;
    private TournamentMatchesLayerGenerator matchesLayerGenerator;

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

