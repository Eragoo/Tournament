package com.Eragoo.Tournament.tournament.match;

import com.Eragoo.Tournament.error.exception.ConflictException;
import com.Eragoo.Tournament.error.exception.NotFoundException;
import com.Eragoo.Tournament.participant.Participant;
import com.Eragoo.Tournament.participant.ParticipantDto;
import com.Eragoo.Tournament.participant.ParticipantMapper;
import com.Eragoo.Tournament.tournament.Tournament;
import com.Eragoo.Tournament.tournament.TournamentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
public class MatchService {
    private RandomMatchScoresDecisionManager scoresDecisionManager;
    private ParticipantMapper participantMapper;
    private MatchRepository matchRepository;
    private TournamentRepository tournamentRepository;

    public MatchResults getResults(long id) {
        Match match = getMatchIfExist(id);
        validateMatchParticipants(match);
        playMatch(match);
        Participant winner = getWinner(match);
        Tournament tournament = match.getTournament();
        removeLoosedParticipantFromTournament(tournament, match, winner);
        tournamentRepository.save(tournament);

        long scoreDiff = countMatchScore(match);
        ParticipantDto winnerDto = participantMapper.entityToDto(winner);
        return new MatchResults(winnerDto, scoreDiff);
    }

    private void validateMatchParticipants(Match match) {
        Tournament blueTournament = match.getBlueParticipant().getTournament();
        Tournament redTournament = match.getRedParticipant().getTournament();
        if (redTournament == null || !redTournament.equals(blueTournament)) {
            throw new ConflictException("participants in math with id " + match.getId() + " have not equals tournament");
        }
    }

    private long countMatchScore(Match match) {
        return Math.abs(match.getBlueScore() - match.getRedScore());
    }

    private Match getMatchIfExist(long id) {
        return matchRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Match with id " + id + "not found"));
    }

    private Participant getWinner(@NotNull Match match) {
        return match.getBlueScore() > match.getRedScore() ? match.getBlueParticipant() : match.getRedParticipant();
    }

    private void removeLoosedParticipantFromTournament(@NotNull Tournament tournament,
                                                       @NotNull Match match,
                                                       Participant winner) {
        Set<Participant> participants = tournament.getParticipants();
        removeLoosedParticipantFromCollection(participants, match, winner);
        tournament.setParticipants(participants);
    }

    private void removeLoosedParticipantFromCollection(@NotEmpty Set<Participant> participants,
                                                                   @NotNull Match match,
                                                                   @NotNull Participant winner) {
        Participant blueParticipant = match.getBlueParticipant();
        Participant redParticipant = match.getRedParticipant();
        if (blueParticipant.equals(winner)) {
            participants.remove(redParticipant);
        } else {
            participants.remove(blueParticipant);
        }
    }

    private void playMatch(@NotNull Match match) {
        scoresDecisionManager.setMatchScores(match);
    }
}
